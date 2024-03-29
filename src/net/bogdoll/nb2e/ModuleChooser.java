package net.bogdoll.nb2e;

import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author dieter
 */
public class ModuleChooser extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	
	private boolean mResult;
    
    /**
     * Creates new form ModuleChooser
     */
    public ModuleChooser() {
        initComponents();
    }

    public File getNetbeansPlatform() {
        return new File(platformTf.getText());
    }

    public File getNetbeansModule() {
        return new File(nbModuleTf.getText());
    }

    public File getEclipseWorkspace() {
        return new File(eclipseWorkspaceTf.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel platformLbl = new javax.swing.JLabel();
        platformTf = new javax.swing.JTextField();
        browseForPlatform = new javax.swing.JButton();
        javax.swing.JLabel nbModuleLbl = new javax.swing.JLabel();
        nbModuleTf = new javax.swing.JTextField();
        browseForNbModule = new javax.swing.JButton();
        javax.swing.JLabel eclipseWorkspaceLbl = new javax.swing.JLabel();
        eclipseWorkspaceTf = new javax.swing.JTextField();
        browseForEclipseWorkspace = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        platformLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        platformLbl.setText("Netbeans Platform:");

        browseForPlatform.setText("Browse...");
        browseForPlatform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseForPlatformActionPerformed(evt);
            }
        });

        nbModuleLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nbModuleLbl.setText("Netbeans Module:");

        browseForNbModule.setText("Browse...");
        browseForNbModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseForNbModuleActionPerformed(evt);
            }
        });

        eclipseWorkspaceLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        eclipseWorkspaceLbl.setText("Eclipse Workspace:");

        browseForEclipseWorkspace.setText("Browse...");
        browseForEclipseWorkspace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseForEclipseWorkspaceActionPerformed(evt);
            }
        });

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(platformLbl)
                            .addComponent(eclipseWorkspaceLbl)
                            .addComponent(nbModuleLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(platformTf, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                            .addComponent(eclipseWorkspaceTf, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nbModuleTf, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(browseForPlatform)
                            .addComponent(browseForNbModule)
                            .addComponent(browseForEclipseWorkspace)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {applyButton, cancelButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(platformLbl)
                    .addComponent(platformTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseForPlatform))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseForNbModule)
                    .addComponent(nbModuleTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nbModuleLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseForEclipseWorkspace)
                    .addComponent(eclipseWorkspaceTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eclipseWorkspaceLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void browseForPlatformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseForPlatformActionPerformed
        browseForFolder(platformTf);
    }//GEN-LAST:event_browseForPlatformActionPerformed

    private void browseForNbModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseForNbModuleActionPerformed
        browseForFolder(nbModuleTf);
    }//GEN-LAST:event_browseForNbModuleActionPerformed

    private void browseForEclipseWorkspaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseForEclipseWorkspaceActionPerformed
        browseForFolder(eclipseWorkspaceTf);
    }//GEN-LAST:event_browseForEclipseWorkspaceActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        mResult = true;
        SwingUtilities.getWindowAncestor(this).setVisible(false);
    }//GEN-LAST:event_applyButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        mResult = false;
        SwingUtilities.getWindowAncestor(this).setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void browseForFolder(JTextField aField) {
        String text = aField.getText();
        JFileChooser chooser = new JFileChooser(text);
        chooser.setFileHidingEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
            aField.setText( chooser.getSelectedFile().getAbsolutePath() );
        }        
    }
    
    public static File[] choose() {
        JDialog dlg = new JDialog();
        dlg.setModal(true);
        dlg.setResizable(false);
        ModuleChooser md = new ModuleChooser();
        dlg.setContentPane(md);
        dlg.pack();
        dlg.setVisible(true);
        
        if(md.mResult) {
            return new File[]{
                md.getNetbeansPlatform(),
                md.getNetbeansModule(),
                md.getEclipseWorkspace()
            };
        } else {
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton browseForEclipseWorkspace;
    private javax.swing.JButton browseForNbModule;
    private javax.swing.JButton browseForPlatform;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField eclipseWorkspaceTf;
    private javax.swing.JTextField nbModuleTf;
    private javax.swing.JTextField platformTf;
    // End of variables declaration//GEN-END:variables
}
