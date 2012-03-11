package net.bogdoll.nb2e;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;
import org.jdom.Namespace;

public interface Project 
{
	Namespace ProjectNamespace = Namespace.getNamespace("http://www.netbeans.org/ns/project/1");
	Namespace ModuleProjectNamespace = Namespace.getNamespace("http://www.netbeans.org/ns/nb-module-project/3");
	Namespace SuiteProjectNamespace = Namespace.getNamespace("http://www.netbeans.org/ns/nb-module-suite-project/1");
	Namespace J2SEProjectNamespace = Namespace.getNamespace("http://www.netbeans.org/ns/j2se-project/3");
	
	String getName();
	List<Resource> getResources();
	File getLocation();	
	
	void create(File aEclipseWorkspace) throws FileNotFoundException, JDOMException, IOException;
}