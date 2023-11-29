/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.unitec.mini.windows.apps;

import com.unitec.mini.windows.logic.ShellCommandExecutor;
import com.unitec.mini.windows.logic.UserManager.User;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;

public class TerminalApp extends javax.swing.JInternalFrame  implements AppInterface{
    private User authUser;
    String username = "admin";

    private ShellCommandExecutor commandExecutor;
    
    public TerminalApp() {
        initComponents();
        setComponents();
    }

    public void setComponents(){
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/images/icon_terminal_20.png"));
        this.setFrameIcon(appIcon);

        textCommandArea.setBackground(new Color(0, 0, 0, 60));
        textCommandArea.setLineWrap(true);
        textCommandArea.setWrapStyleWord(true);
        textCommandArea.setEditable(false);
        
        try {   
            if (authUser != null) {
                username = authUser.getUsername();
            }

            String userPath = "/src/main/users" + File.separator + username;
            String projectDir = System.getProperty("user.dir") + userPath;
            File userRootdir = new File(projectDir);

            commandExecutor = new ShellCommandExecutor(textCommandArea, userRootdir);
        } catch (Exception e) {
            System.out.println("folder not found. close app?");
        }
    }
    
    public void setAuthenticatedUser(User loggedUser){
        authUser = loggedUser;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Main = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textCommandArea = new javax.swing.JTextArea();
        textInputArea = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Terminal");
        setOpaque(false);

        jPanel_Main.setBackground(new Color(0, 0, 0, 60));

        textCommandArea.setColumns(20);
        textCommandArea.setRows(5);
        jScrollPane1.setViewportView(textCommandArea);

        textInputArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textInputAreaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_MainLayout = new javax.swing.GroupLayout(jPanel_Main);
        jPanel_Main.setLayout(jPanel_MainLayout);
        jPanel_MainLayout.setHorizontalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textInputArea)
                .addContainerGap())
        );
        jPanel_MainLayout.setVerticalGroup(
            jPanel_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textInputArea, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel_Main, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textInputAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textInputAreaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String command = textInputArea.getText().trim();
            commandExecutor.executeCommand(command);
            textInputArea.setText("");
        }
    }//GEN-LAST:event_textInputAreaKeyPressed
    
    @Override
    public void closeFrame() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textCommandArea;
    private javax.swing.JTextField textInputArea;
    // End of variables declaration//GEN-END:variables
}
