package net.bogdoll.nb2e;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class SuiteProject extends BaseProject {
	
	private final String mName;
	private final Platform mPlatform;
	private final List<File> mModules;

	public SuiteProject(File aNetbeansPlatformDir, File aNetbeansModule) throws IOException, JDOMException 
	{		
		super(aNetbeansPlatformDir, aNetbeansModule);

		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new File(aNetbeansModule, "nbproject/project.xml"));

		Element data = doc.getRootElement().getChild("configuration", ProjectNamespace).getChild("data", SuiteProjectNamespace);
		mName = data.getChild("name", SuiteProjectNamespace).getTextTrim();
		mPlatform = parsePlatform();
		mModules = parseSuiteModules();
	}

	private Platform parsePlatform() throws IOException {
		FileInputStream fin = new FileInputStream(new File(mNetbeansModule, "nbproject/platform.properties"));
		try {
			Properties props = new Properties();
			props.load(fin);
			
			Splitter splitByComma = Splitter.on(',').trimResults();

			String clusterPath = props.getProperty("cluster.path");
			clusterPath = clusterPath.replaceAll("[$][{]nbplatform.active.dir[}]", mNetbeansPlatform.getPath());
			Iterable<String> clusterPaths = splitByComma.split(clusterPath);
			
			String disabledModule = props.getProperty("cluster.path");
			Iterable<String> disabledModules = splitByComma.split(disabledModule);
			
			return new Platform(mNetbeansPlatform, clusterPaths, disabledModules);
		}
		finally {
			fin.close();
		}
	}
	
	private List<File> parseSuiteModules() throws IOException {
		FileInputStream fin = new FileInputStream(new File(mNetbeansModule, "nbproject/project.properties"));
		try {
			Properties props = new Properties();
			props.load(fin);
			
			List<File> moduleFiles = Lists.newArrayList();
			String modules = props.getProperty("modules");
			List<String> symbols = extractSymbols(modules);
			for(String symbol : symbols) {
				String path = props.getProperty(symbol);
				File module = new File(mNetbeansModule, path).getAbsoluteFile().getCanonicalFile();
				moduleFiles.add(module);
			}
			
			return moduleFiles;
		}
		finally {
			fin.close();
		}
	}


	
	public void resolveModuleDependencies(Collection<Dependency> aDependencies) {
		mPlatform.resolveModuleDependencies(aDependencies);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("name", mName)
				.add("platform", mPlatform)
				.add("modules", mModules)
				.toString();
	}

	public void writeDotClasspath(File aModule) {		
	}
	
	public List<Resource> getResources() {
		return Arrays.asList(
			new Resource("build.xml", Resource.Type.FILE, new File(mNetbeansModule, "build.xml")),
			new Resource("nb", Resource.Type.FOLDER, mNetbeansModule),
			new Resource("public-package-jars", Resource.Type.FOLDER, new File(mNetbeansModule, "build/public-package-jars"))
		);
	}
	
	public void writeDotProject(File aModule) throws FileNotFoundException {
		writeDotProject(aModule, "dotProjectForSuite");
	}
	
	@Override
	public void create(File aEclipseWorkspace) throws JDOMException, IOException {
		super.create(aEclipseWorkspace);
		for(File module : mModules) {
			Project p = ProjectCreator.createProject(mNetbeansPlatform, module);
			p.create(aEclipseWorkspace);
		}
	}
}