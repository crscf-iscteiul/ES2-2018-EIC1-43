/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.screens;

import gui.GUI;
import solverandoptimizer.SolverandOptimizer;
import utils.Problem;
import utils.Variable;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;

/**
 *
 * @author Rafael
 */
public class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        initComponents();
        setLocationRelativeTo(null);
        if(SolverandOptimizer.getInstance().getUser()!=null)
            SendInfo.setSelected(true);
        else
            SendInfo.setEnabled(false);
        loadProblemData(SolverandOptimizer.getInstance().getProblem());
        if(TableVars.getModel().getRowCount()>0){
            VariableType.setSelectedIndex(Variable.StringToType((String) TableVars.getModel().getValueAt(0,1)));
        }
        TableVars.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                updateProblemData();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ComboBoxTypes = new javax.swing.JComboBox<>();
        ScrollPane = new javax.swing.JScrollPane();
        TableVars = new javax.swing.JTable();
        ButtonAdd = new javax.swing.JButton();
        ButtonRemove = new javax.swing.JButton();
        Start = new javax.swing.JButton();
        SendInfo = new javax.swing.JCheckBox();
        JarPath = new javax.swing.JTextField();
        VariableType = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        MenuFile = new javax.swing.JMenu();
        MenuOpSave = new javax.swing.JMenuItem();
        MenuOpLoad = new javax.swing.JMenuItem();
        MenuOpLogout = new javax.swing.JMenuItem();
        MenuEdit = new javax.swing.JMenu();
        MenuOpEditProblem = new javax.swing.JMenuItem();
        MenuHelp = new javax.swing.JMenu();
        MenuOpSendMail = new javax.swing.JMenuItem();
        MenuOpFAQ = new javax.swing.JMenuItem();
        MenuOpAbout = new javax.swing.JMenuItem();

        ComboBoxTypes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Integer", "Double", "Binary" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Problem Solver and Optimizer");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        TableVars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Type", "Interval Values", "Exclusion Values", "Optimize"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableVars.getTableHeader().setReorderingAllowed(false);
        ScrollPane.setViewportView(TableVars);

        ButtonAdd.setText("Add Row");
        ButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });

        ButtonRemove.setText("Remove Row");
        ButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRemoveActionPerformed(evt);
            }
        });

        Start.setText("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        SendInfo.setText("Send Info");

        JarPath.setEditable(false);
        JarPath.setText("Optimization Jar");
        JarPath.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        JarPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JarPathMouseClicked(evt);
            }
        });

        VariableType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Integer", "Double", "Binary" }));
        VariableType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VariableTypeActionPerformed(evt);
            }
        });

        jLabel1.setText("External Jar:");

        MenuFile.setText("File");

        MenuOpSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuOpSave.setText("Save");
        MenuOpSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpSaveActionPerformed(evt);
            }
        });
        MenuFile.add(MenuOpSave);

        MenuOpLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        MenuOpLoad.setText("Load");
        MenuOpLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpLoadActionPerformed(evt);
            }
        });
        MenuFile.add(MenuOpLoad);

        MenuOpLogout.setText("Switch User");
        MenuOpLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpLogoutActionPerformed(evt);
            }
        });
        MenuFile.add(MenuOpLogout);

        Menu.add(MenuFile);

        MenuEdit.setText("Edit");

        MenuOpEditProblem.setText("Problem");
        MenuOpEditProblem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpEditProblemActionPerformed(evt);
            }
        });
        MenuEdit.add(MenuOpEditProblem);

        Menu.add(MenuEdit);

        MenuHelp.setText("Help");

        MenuOpSendMail.setText("Send Email");
        MenuOpSendMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpSendMailActionPerformed(evt);
            }
        });
        MenuHelp.add(MenuOpSendMail);

        MenuOpFAQ.setText("FAQ");
        MenuOpFAQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpFAQActionPerformed(evt);
            }
        });
        MenuHelp.add(MenuOpFAQ);

        MenuOpAbout.setText("About");
        MenuOpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpAboutActionPerformed(evt);
            }
        });
        MenuHelp.add(MenuOpAbout);

        Menu.add(MenuHelp);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Start)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SendInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JarPath, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(VariableType, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonRemove)
                        .addComponent(ButtonAdd)
                        .addComponent(VariableType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JarPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Start)
                        .addComponent(SendInfo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuOpSendMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpSendMailActionPerformed
        GUI.getInstance().child_screen(GUI.MailScreen);
    }//GEN-LAST:event_MenuOpSendMailActionPerformed

    private void MenuOpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpAboutActionPerformed
        GUI.getInstance().child_screen(GUI.AboutScreen);
    }//GEN-LAST:event_MenuOpAboutActionPerformed

    private void ButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRemoveActionPerformed
        if(TableVars.getSelectedRow()>-1) {
            ((DefaultTableModel)TableVars.getModel()).removeRow(TableVars.getSelectedRow());
        }
    }//GEN-LAST:event_ButtonRemoveActionPerformed

    private void ButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddActionPerformed
        ((DefaultTableModel)TableVars.getModel()).addRow(new Object[]{"",VariableType.getSelectedItem(),"","",false,""});
    }//GEN-LAST:event_ButtonAddActionPerformed

    private void MenuOpEditProblemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpEditProblemActionPerformed
        GUI.getInstance().child_screen(GUI.ProblemScreen);
    }//GEN-LAST:event_MenuOpEditProblemActionPerformed

    private void MenuOpSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpSaveActionPerformed
        if(SolverandOptimizer.getInstance().getProblem().isProblemValid()){
            JFileChooser path_chooser = new JFileChooser(System.getProperty("user.dir"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
            path_chooser.setFileFilter(filter);
            path_chooser.setDialogTitle("Choose File Problem");
            path_chooser.setSelectedFile(new File(System.getProperty("user.dir")+"\\"+SolverandOptimizer.getInstance().getProblem().getName()+".xml"));
            int returnVal = path_chooser.showSaveDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                if(SolverandOptimizer.getInstance().saveProblem(path_chooser.getSelectedFile().getAbsolutePath()))
                    GUI.getInstance().show_message("File Saved");
            }
        } else {
            GUI.getInstance().show_error("The current problem is not valid");
        }

        }//GEN-LAST:event_MenuOpSaveActionPerformed

    private void MenuOpLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpLoadActionPerformed
        JFileChooser path_chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
        path_chooser.setFileFilter(filter);
        path_chooser.setDialogTitle("Choose File Problem");
        int returnVal = path_chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            if(SolverandOptimizer.getInstance().loadProblem(path_chooser.getSelectedFile().getAbsolutePath()))
                GUI.getInstance().next_screen(GUI.MainScreen);
        }
    }//GEN-LAST:event_MenuOpLoadActionPerformed

    private void MenuOpLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpLogoutActionPerformed
        SolverandOptimizer.getInstance().setUser(null);
        GUI.getInstance().next_screen(GUI.StartScreen);
    }//GEN-LAST:event_MenuOpLogoutActionPerformed

    private void MenuOpFAQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpFAQActionPerformed
        GUI.getInstance().child_screen(GUI.FAQScreen);
    }//GEN-LAST:event_MenuOpFAQActionPerformed

    private void JarPathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JarPathMouseClicked
        JFileChooser path_chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JAR File", "jar");
        path_chooser.setFileFilter(filter);
        path_chooser.setDialogTitle("Choose JAR File");
        int returnVal = path_chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            JarPath.setText(path_chooser.getSelectedFile().getAbsolutePath());
            SolverandOptimizer.getInstance().getProblem().setJarPath(path_chooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_JarPathMouseClicked

    private void VariableTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VariableTypeActionPerformed
        if(TableVars.getModel().getRowCount()>0 && !TableVars.getModel().getValueAt(0,1).equals(VariableType.getSelectedItem())){
            for(int i=0; i<TableVars.getModel().getRowCount(); i++){
                TableVars.getModel().setValueAt(VariableType.getSelectedItem(),i,1);
                if(Variable.StringToType((String)VariableType.getSelectedItem())==Variable.Type_Binary) {
                    TableVars.getModel().setValueAt("-", i, 3);
                }
            }
            updateProblemData();
        }
    }//GEN-LAST:event_VariableTypeActionPerformed

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        if(!SolverandOptimizer.getInstance().getProblem().isProblemValid())
            GUI.getInstance().show_error("The current problem is not valid");
        else
            GUI.getInstance().child_screen(GUI.RunScreen);
    }//GEN-LAST:event_StartActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        GUI.getInstance().close_screen(this);
    }//GEN-LAST:event_formWindowClosing


    public void loadProblemData(Problem problem){
        DefaultTableModel model = (DefaultTableModel) TableVars.getModel();
        if(model.getRowCount()>0)
            for (int i = model.getRowCount() - 1; i > -1; i--)
                model.removeRow(i);

        for(Variable v : problem.getVariables()){
            model.addRow(new Object[]{v.getVariableName(), v.getType_toString(), v.getInterval(), v.getExclusions(), v.isOptimized()});
        }
        TableVars.setModel(model);
    }

    public void updateProblemData(){
        Variable [] variables = new Variable [TableVars.getModel().getRowCount()];
        for(int i=0; i<variables.length; i++){
            String name = (String) TableVars.getModel().getValueAt(i,0);
            String type = (String) TableVars.getModel().getValueAt(i,1);
            String interval = (String) TableVars.getModel().getValueAt(i,2);
            String excluded = (String) TableVars.getModel().getValueAt(i,3);
            boolean optimize = (boolean) TableVars.getModel().getValueAt(i,4);
            variables[i] = new Variable(name, type, interval, excluded, optimize);
        }
        SolverandOptimizer.getInstance().getProblem().updateVaribles(variables);
    }

    public void open(){
        //Show Screen (Thread Safe)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JButton ButtonRemove;
    private javax.swing.JComboBox<String> ComboBoxTypes;
    private javax.swing.JTextField JarPath;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu MenuEdit;
    private javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuHelp;
    private javax.swing.JMenuItem MenuOpAbout;
    private javax.swing.JMenuItem MenuOpEditProblem;
    private javax.swing.JMenuItem MenuOpFAQ;
    private javax.swing.JMenuItem MenuOpLoad;
    private javax.swing.JMenuItem MenuOpLogout;
    private javax.swing.JMenuItem MenuOpSave;
    private javax.swing.JMenuItem MenuOpSendMail;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JCheckBox SendInfo;
    private javax.swing.JButton Start;
    private javax.swing.JTable TableVars;
    private javax.swing.JComboBox<String> VariableType;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
