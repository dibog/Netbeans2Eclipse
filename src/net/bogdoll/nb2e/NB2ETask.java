package net.bogdoll.nb2e;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class NB2ETask extends Task {
    private File mNetbeans;
    private File mNbModule;
    private File mEclipseWorkspace;

    public void setNetbeans(File aNetbeans) {
    	mNetbeans = aNetbeans;
    }
    
    public void setNbModule(File aNbModule) {
    	mNbModule = aNbModule;
    }
    
    public void setEclipseWorkspace(File aEclipseWorkspace) {
    	mEclipseWorkspace = aEclipseWorkspace;
    }
    
	private void checkFolder(File aFile, String aMessage) {
		if(aFile==null || !aFile.exists() || !aFile.isDirectory())
			throw new BuildException(aMessage);
	}
    
    @Override
    public void execute() throws BuildException {
    	checkFolder(mNetbeans, "netbeans must point to the netbeans installation folder");
    	checkFolder(mNbModule, "nbModule must point to the netbeans module");
    	checkFolder(mEclipseWorkspace, "eclipseWorkspace must point to the eclipse workspace");
    	
    	try {
            ProjectCreator.createProject(mNetbeans, mNbModule).create(mEclipseWorkspace);
            log("Creating links for netbeans module '"+mNbModule.getAbsolutePath()+"' inside of eclipse workspace '"+mEclipseWorkspace.getAbsolutePath()+"'");
        } catch (Exception e) {
            throw new BuildException(e);
        } 
    }

}
