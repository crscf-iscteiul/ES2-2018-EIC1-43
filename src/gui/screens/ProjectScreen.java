/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.screens;

import gui.GUI;
import solver.SolverandOptimizer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rafael
 */
public class ProjectScreen extends javax.swing.JFrame {

    /**
     * Creates new form ProjectScreen
     */
    public ProjectScreen() {
        //TODO Load old known problems and display them
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollPane = new javax.swing.JScrollPane();
        History = new javax.swing.JList<>();
        ButtonNew = new javax.swing.JButton();
        ButtonLoad = new javax.swing.JButton();
        ButtonFAQ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Problem Solver and Optimizer - Problem Selector");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        ScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Problem History"));

        ScrollPane.setViewportView(History);

        ButtonNew.setText("New Problem");
        ButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNewActionPerformed(evt);
            }
        });

        ButtonLoad.setText("Load Problem");
        ButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLoadActionPerformed(evt);
            }
        });

        ButtonFAQ.setText("FAQ");
        ButtonFAQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonFAQActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonLoad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonFAQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(ButtonNew)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonFAQ)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNewActionPerformed
        GUI.getInstance().next_screen(GUI.ProblemScreen);
    }//GEN-LAST:event_ButtonNewActionPerformed

    private void ButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLoadActionPerformed
        if(History.getSelectedIndex()!=-1){
            //TODO Read and load file to a class format
            GUI.getInstance().next_screen(GUI.MainScreen);
        }
        else {
            JFileChooser path_chooser = new JFileChooser(System.getProperty("user.dir"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
            path_chooser.setFileFilter(filter);
            path_chooser.setDialogTitle("Choose File Problem");
            int returnVal = path_chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                if(SolverandOptimizer.getInstance().loadProblem(path_chooser.getSelectedFile().getAbsolutePath()))
                    GUI.getInstance().next_screen(GUI.MainScreen);
            }
        }
    }//GEN-LAST:event_ButtonLoadActionPerformed

    private void ButtonFAQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonFAQActionPerformed
        GUI.getInstance().child_screen(GUI.FAQScreen);
    }//GEN-LAST:event_ButtonFAQActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        GUI.getInstance().close_screen(this);
    }//GEN-LAST:event_formWindowClosing

    public void open(){
        //Show Screen (Thread Safe)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonFAQ;
    private javax.swing.JButton ButtonLoad;
    private javax.swing.JButton ButtonNew;
    private javax.swing.JList<String> History;
    private javax.swing.JScrollPane ScrollPane;
    // End of variables declaration//GEN-END:variables
}
