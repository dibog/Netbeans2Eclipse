package net.bogdoll.nb2e;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.JDOMException;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public abstract class BaseProject implements Project 
{
	protected final File mNetbeansModule;
	protected final File mNetbeansPlatform;

	protected BaseProject(File aNetbeansPlatform, File aNetbeansModule) {
		mNetbeansPlatform = checkNotNull(aNetbeansPlatform);
		mNetbeansModule = checkNotNull(aNetbeansModule);
	}
	
	public String getName() {
		return mNetbeansModule.getName();
	}
	
	public File getLocation() {
		return mNetbeansModule;
	}
	
	protected void writeDotClasspath(File aModule) throws FileNotFoundException {
		File dotClassPath = new File(aModule, ".classpath");
		PrintStream out = new PrintStream(new FileOutputStream(dotClassPath));
		try {
			STGroupFile group = getGroupFile("dotClasspath.stg");		
			ST db = group.getInstanceOf("dotClasspath");
			db.add("module", this);
			out.println(db.render());
		}
		finally {
			out.close();
		}
	}		
	

	public void create(File aEclipseWorkspace) throws FileNotFoundException, JDOMException, IOException {
		File module = new File(aEclipseWorkspace, mNetbeansModule.getName());
		module.mkdir();
		
		writeDotProject(module);
		writeDotClasspath(module);
		writeNetbeansBuilder(module);
	}
	
	protected abstract void writeDotProject(File aModule) throws FileNotFoundException;

	protected void writeNetbeansBuilder(File aModule) throws FileNotFoundException {
		File externalToolDir = new File(aModule, ".externalToolBuilders");
		externalToolDir.mkdir();
		
		File launch = new File(externalToolDir, "Netbeans_Builder.launch");
		PrintStream out = new PrintStream(new FileOutputStream(launch));
		try {
			STGroupFile group = getGroupFile("Builder.launch.stg");		
			ST db = group.getInstanceOf("netbeansBuilder");
			db.add("module", this);
			out.println(db.render());
		}
		finally {
			out.close();
		}
	}
	
	protected void writeDotProject(File aModule, String aTemplate) throws FileNotFoundException {
		File dotProject = new File(aModule, ".project");
		PrintStream out = new PrintStream(new FileOutputStream(dotProject));
		try {
			STGroupFile group = getGroupFile("dotProject.stg");		
			ST db = group.getInstanceOf(aTemplate);
			db.add("module", this);
			out.println(db.render());
		}
		finally {
			out.close();
		}
	}
	
	protected List<String> extractSymbols(String aModules) {
		List<String> symbols = Lists.newArrayList();
		int pos = 0;
		Pattern pattern = Pattern.compile("[$][{](.*?)[}]");
		Matcher m = pattern.matcher(aModules);
		while(m.find(pos)) {
			String module = m.group(1);
			symbols.add(module);
			pos = m.end();
		}
		return symbols;
	}
	
	private STGroupFile getGroupFile(String aGroupFile) {
		URL url = getClass().getResource(aGroupFile);
		STGroupFile group =  new STGroupFile(url, "UTF8",'$','$');		
		return group;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("netbeans-module", mNetbeansModule)
				.toString();
	}
}