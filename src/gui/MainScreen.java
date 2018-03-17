/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.table.DefaultTableModel;

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
        ProgressBar = new javax.swing.JProgressBar();
        ProblemSolver = new javax.swing.JComboBox<>();
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Type", "Interval Values", "Exclusion Values", "Optimize", "Jar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableVars.getTableHeader().setReorderingAllowed(false);
        ScrollPane.setViewportView(TableVars);
        if (TableVars.getColumnModel().getColumnCount() > 0) {
            TableVars.getColumnModel().getColumn(0).setResizable(false);
            TableVars.getColumnModel().getColumn(1).setResizable(false);
            TableVars.getColumnModel().getColumn(1).setCellEditor(new javax.swing.DefaultCellEditor(ComboBoxTypes));
            TableVars.getColumnModel().getColumn(2).setResizable(false);
            TableVars.getColumnModel().getColumn(3).setResizable(false);
            TableVars.getColumnModel().getColumn(4).setResizable(false);
            TableVars.getColumnModel().getColumn(5).setResizable(false);
        }

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

        ProblemSolver.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AbYSS", "CellDE", "dMPOSO", "GDE3", "FastPGA", "IBEA", "MOCHC", "MOCell", "MOEA/D-DE", "pMOEA/D-DE", "MOEA/D-DRA", "NSGA-II", "ssNSGA-II", "NSGAIIr", "NSGAIIa", "pNSGA-II", "OMOPSO", "PAES", "SMPSO", "pSMPSO", "SMPSOhv", "SPEA2" }));

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ProblemSolver, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Start)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
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
                        .addComponent(ButtonAdd))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Start)
                        .addComponent(ProblemSolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuOpSendMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpSendMailActionPerformed
        GUI.getInstance().childscreen(GUI.MailScreen);
    }//GEN-LAST:event_MenuOpSendMailActionPerformed

    private void MenuOpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpAboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpAboutActionPerformed

    private void ButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRemoveActionPerformed
        if(TableVars.getSelectedRow()>-1) {
            ((DefaultTableModel)TableVars.getModel()).removeRow(TableVars.getSelectedRow());
            //TODO change var numbers
        }
    }//GEN-LAST:event_ButtonRemoveActionPerformed

    private void ButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddActionPerformed
        ((DefaultTableModel)TableVars.getModel()).addRow(new Object[]{"","","","",false,""});
        //TODO change var numbers
    }//GEN-LAST:event_ButtonAddActionPerformed

    private void MenuOpEditProblemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpEditProblemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpEditProblemActionPerformed

    private void MenuOpSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpSaveActionPerformed

    private void MenuOpLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpLoadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpLoadActionPerformed

    private void MenuOpLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpLogoutActionPerformed

    private void MenuOpFAQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpFAQActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuOpFAQActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        GUI.getInstance().closescreen(this);
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
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JButton ButtonRemove;
    private javax.swing.JComboBox<String> ComboBoxTypes;
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
    private javax.swing.JComboBox<String> ProblemSolver;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JButton Start;
    private javax.swing.JTable TableVars;
    // End of variables declaration//GEN-END:variables
}
