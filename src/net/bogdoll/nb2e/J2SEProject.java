package net.bogdoll.nb2e;

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

public class J2SEProject extends BaseProject {
	
	private final String mName;
	private final List<String> mSourceRoots = Lists.newArrayList();
	private final List<String> mTestRoots = Lists.newArrayList();
	private List<File> mJars;
	
	public J2SEProject(File aNetbeansPlatformDir, File aNetbeansModule) throws JDOMException, IOException 
	{		
		super(aNetbeansPlatformDir, aNetbeansModule);
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new File(aNetbeansModule, "nbproject/project.xml"));

		Element data = doc.getRootElement().getChild("configuration", ProjectNamespace).getChild("data", J2SEProjectNamespace);
		mName = data.getChild("name", J2SEProjectNamespace).getTextTrim();

		parseSourceRoots(data.getChild("source-roots", J2SEProjectNamespace));
		parseTestRoots(data.getChild("test-roots", J2SEProjectNamespace));
		mJars = parseJars();
	}

	@SuppressWarnings("unchecked")
	private void parseSourceRoots(Element aSourceRoots) {
		for(Element root : (List<Element>)aSourceRoots.getChildren("root", J2SEProjectNamespace)) {
			String id = root.getAttributeValue("id");
			mSourceRoots.add(id);
		}
	}

	@SuppressWarnings("unchecked")
	private void parseTestRoots(Element aSourceRoots) {
		for(Element root : (List<Element>)aSourceRoots.getChildren("root", J2SEProjectNamespace)) {
			String id = root.getAttributeValue("id");
			mTestRoots.add(id);
		}
	}
	
	private List<File> parseJars() throws IOException {
		FileInputStream fin = new FileInputStream(new File(mNetbeansModule, "nbproject/project.properties"));
		try {
			Properties props = new Properties();
			props.load(fin);
			
			List<File> moduleFiles = Lists.newArrayList();
			String modules = props.getProperty("javac.classpath");
			List<String> symbols = extractSymbols(modules);
			for(String symbol : symbols) {
				String path = props.getProperty(symbol);
				File module = new File(path).getCanonicalFile();
				moduleFiles.add(module);
			}
			
			return moduleFiles;
		}
		finally {
			fin.close();
		}
	}
	
	public List<Dependency> getClasspathLibEntries() {
		List<Dependency> deps = Lists.newArrayList();
		for(File jar : mJars) {
			deps.add( new Dependency("dummy", jar) );
		}
		
		return deps;
	}
	
	public List<Resource> getResources() {
		
		return Arrays.asList(
			new Resource("src", Resource.Type.FOLDER, new File(mNetbeansModule, "src")),
			new Resource("generated-src", Resource.Type.FOLDER, new File(mNetbeansModule, "build/generated-sources")),
			new Resource("bin", Resource.Type.FOLDER, new File(mNetbeansModule, "build/classes")),
			new Resource("nb", Resource.Type.FOLDER, mNetbeansModule)
		);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("name", mName)
				.add("source-roots", mSourceRoots)
				.add("test-roots", mTestRoots)
				.add("jars", mJars)
				.toString();
	}
	
	public void writeDotProject(File aModule) throws FileNotFoundException {
		writeDotProject(aModule, "dotProjectForJ2SE");
	}
}