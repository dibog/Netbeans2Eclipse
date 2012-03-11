package net.bogdoll.nb2e;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class ModuleProject extends BaseProject {
	
	private final String mCodeNameBase;
	private final List<Dependency> mDependencies = Lists.newArrayList();
	private File mSuiteDir;
	private File mNetbeansPlatformDir;

	public ModuleProject(File aNetbeansPlatformDir, File aNetbeansModule) throws JDOMException, IOException 
	{		
		super(aNetbeansPlatformDir, aNetbeansModule);
		
		mNetbeansPlatformDir = checkNotNull(aNetbeansPlatformDir);
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new File(aNetbeansModule, "nbproject/project.xml"));
		Element data = doc.getRootElement().getChild("configuration", ProjectNamespace).getChild("data", ModuleProjectNamespace);
		mCodeNameBase = data.getChild("code-name-base", ModuleProjectNamespace).getTextTrim();
		parseModuleDependencies(data.getChild("module-dependencies", ModuleProjectNamespace));
		
		resolve();
	}

	@SuppressWarnings("unchecked")
	private void parseModuleDependencies(Element aModuleDependencies) {
		for(Element dependency : (List<Element>)aModuleDependencies.getChildren("dependency", ModuleProjectNamespace)) {
			Dependency dep = new Dependency(dependency, ModuleProjectNamespace);
			mDependencies.add(dep);
		}
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("suite-dir", mSuiteDir)
				.add("code-name-base", mCodeNameBase)
				.add("dependencies", mDependencies)
				.toString();
	}

	public void resolve() throws IOException, JDOMException {
		Properties props = new Properties();
		FileInputStream fin = new FileInputStream(new File(mNetbeansModule, "nbproject/suite.properties"));
		try {
			props.load(fin);
			String suiteDir = props.getProperty("suite.dir");
			suiteDir = suiteDir.replaceAll("[$][{]basedir[}]", mNetbeansModule.getPath());
			mSuiteDir = new File(suiteDir).getCanonicalFile();
			
			SuiteProject suite = new SuiteProject(mNetbeansPlatformDir, mSuiteDir);
			suite.resolveModuleDependencies(mDependencies);
		}
		finally {
			fin.close();
		}
	}

	public List<Dependency> getClasspathLibEntries() {
		return mDependencies;
	}

	public List<Resource> getResources() {
		return Arrays.asList(
			new Resource("src", Resource.Type.FOLDER, new File(mNetbeansModule, "src")),
			new Resource("generated-src", Resource.Type.FOLDER, new File(mNetbeansModule, "build/classes-generated")),
			new Resource("bin", Resource.Type.FOLDER, new File(mNetbeansModule, "build/classes")),
			new Resource("nb", Resource.Type.FOLDER, mNetbeansModule)
		);
	}
		
	public void writeDotProject(File aModule) throws FileNotFoundException {
		writeDotProject(aModule, "dotProjectForModule");
	}
}
