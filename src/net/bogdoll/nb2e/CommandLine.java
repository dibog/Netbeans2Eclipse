package net.bogdoll.nb2e;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import java.io.File;
import java.io.IOException;
import org.jdom.JDOMException;

public class CommandLine {
    @Parameter(names={"-netbeans"}, description="Path to the Netbeans installation folder; mandatory except when using the -ui option")
    private File mNetbeans;

    @Parameter(names={"-module"}, description="Path to the Netbeans Project; mandatory except when using the -ui option")
    private File mNbModule;
        
    @Parameter(names={"-workspace"}, description="Path to the Eclipse Workspace; mandatory except when using the -ui option")
    private File mEclipseWorkspace;
    
    @Parameter(names={"-ui"}, description="Start a ui to select the paths")
    private boolean mUseUI;
    
    public static void main(String[] args) throws JDOMException, IOException {
        CommandLine cmd = new CommandLine();
        JCommander jco = new JCommander(cmd);
        try {
            jco.parse(args); 
            cmd.parseCmds();
            cmd.copyToEclipse();
        }
        catch(ParameterException pe) 
        {
            System.err.println(pe.getLocalizedMessage());
            jco.usage();
        }
    }

    private void copyToEclipse() throws JDOMException, IOException {
        if(mUseUI) {
            File[] paths = ModuleChooser.choose();
            ProjectCreator.createProject(paths[0], paths[1]).create(paths[2]);
        }
        else {
            ProjectCreator.createProject(mNbModule, mNbModule).create(mEclipseWorkspace);
        }
    }

    private void parseCmds() {
        if(!mUseUI) {
            if(mNetbeans==null | mNbModule==null | mEclipseWorkspace==null) {
                throw new ParameterException("You must specify either -ui or all the other options");
            }
        }
    }
}
