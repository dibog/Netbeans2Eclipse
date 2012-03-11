package net.bogdoll.nb2e;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ProjectCreator 
{
	public static Project createProject(File aNetbeansPlatformDir, File aNetbeansModule) throws JDOMException, IOException 
	{
		checkNotNull(aNetbeansPlatformDir);
		checkNotNull(aNetbeansModule);
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new File(aNetbeansModule, "nbproject/project.xml"));
		String type = doc.getRootElement().getChild("type", Project.ProjectNamespace).getTextTrim();
		
		Project project = null;
		if("org.netbeans.modules.apisupport.project".equals(type)) {
			project = new ModuleProject(aNetbeansPlatformDir, aNetbeansModule);
		}
		else if ("org.netbeans.modules.apisupport.project.suite".equals(type)) {
			project = new SuiteProject(aNetbeansPlatformDir, aNetbeansModule);
		}
		else if ("org.netbeans.modules.java.j2seproject".equals(type)) {
			project = new J2SEProject(aNetbeansPlatformDir, aNetbeansModule);
		}
		
		return project;
	}
	
	public static void main(String[] args) throws Exception {
//		ProjectCreator.createProject(
//				new File("/Applications/NetBeans/NetBeans 7.1.app/Contents/Resources/NetBeans"), 
//				new File("/Users/Dieter/NetBeansProjects/ExampleApp/AboutModule"));

//		Project project = 
//				ProjectCreator.createProject(
//					new File("/Applications/NetBeans/NetBeans 7.1.app/Contents/Resources/NetBeans"), 
//					new File("/Users/Dieter/NetBeansProjects/ExampleApp"));
//		project.create(new File(".."));
		
		Project project = 
			ProjectCreator.createProject(
				new File("/Applications/NetBeans/NetBeans 7.1.app/Contents/Resources/NetBeans"), 
				new File("/Users/Dieter/NetBeansProjects/Netbeans2Eclipse"));
		project.create(new File(".."));
	}
}