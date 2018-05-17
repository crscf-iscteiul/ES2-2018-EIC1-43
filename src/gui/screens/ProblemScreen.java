/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.screens;

import gui.GUI;
import solver.SolverandOptimizer;
import utils.Problem;
import utils.Variable;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Rafael
 */
public class ProblemScreen extends javax.swing.JFrame {

    /**
     * Creates new form NewProblemScreen
     */
    public ProblemScreen(){
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

        ButtonDone = new javax.swing.JButton();
        ScrollPane = new javax.swing.JScrollPane();
        Description = new javax.swing.JTextArea();
        LabelTime = new javax.swing.JLabel();
        LabelVars = new javax.swing.JLabel();
        Panel = new javax.swing.JPanel();
        ProblemName = new javax.swing.JTextField();
        MaxVars = new javax.swing.JFormattedTextField();
        MaxTime = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Problem Solver and Optimizer");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        ButtonDone.setText("Done");
        ButtonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDoneActionPerformed(evt);
            }
        });

        ScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Problem Description"));

        Description.setColumns(20);
        Description.setRows(5);
        ScrollPane.setViewportView(Description);

        LabelTime.setText("Max Wait Time");

        LabelVars.setText("Max Vars");

        Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Problem Name"));

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ProblemName)
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ProblemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MaxVars.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        MaxVars.setText("10");

        MaxTime.setText("HH:mm:ss");
        MaxTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MaxTimeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                MaxTimeFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                    .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MaxTime, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LabelVars)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MaxVars, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonDone)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTime)
                    .addComponent(LabelVars)
                    .addComponent(MaxVars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonDone)
                    .addComponent(MaxTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MaxTimeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MaxTimeFocusGained
        if(MaxTime.getText().equals("HH:mm:ss"))
            MaxTime.setText("");
    }//GEN-LAST:event_MaxTimeFocusGained

    private void MaxTimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MaxTimeFocusLost
        try{
            int time = Integer.parseInt(MaxTime.getText());
            int h = time/10000;
            int m = (time-(h*10000))/100;
            int s = (time-(h*10000)-(m*100));

            MaxTime.setText(String.format("%02d:%02d:%02d",h,m,s));
        } catch (NumberFormatException e1){

        }
        try{
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            MaxTime.setText(format.format(format.parse(MaxTime.getText())));
        } catch (ParseException e) {
            MaxTime.setText("");
        }

        if(MaxTime.getText().equals(""))
            MaxTime.setText("HH:mm:ss");
    }//GEN-LAST:event_MaxTimeFocusLost

    private void ButtonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDoneActionPerformed
        if(!ProblemName.getText().equals("")) {
            String problem_name = ProblemName.getText();
            String problem_description = Description.getText();
            int maxVars = Integer.valueOf(MaxVars.getText());
            String maxTime = MaxTime.getText();

            Variable[] variables = new Variable[maxVars];
            if(SolverandOptimizer.getInstance().getProblem()!=null){
                for(int i=0; i<SolverandOptimizer.getInstance().getProblem().getVariables().length; i++)
                    variables[i] = SolverandOptimizer.getInstance().getProblem().getVariables()[i];
            }
            for(int i=0; i<maxVars; i++)
                if(variables[i]==null)
                    variables[i] = new Variable("","");

            Problem currentProblem = new Problem(problem_name, problem_description, maxVars, maxTime, variables);
            SolverandOptimizer.getInstance().changeProblem(currentProblem);

            GUI.getInstance().next_screen(GUI.MainScreen);
        }
        else{
            JOptionPane.showMessageDialog(this, "Please insert a valid Problem name", "Missing Data", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_ButtonDoneActionPerformed

    public void setProblem(Problem problem){
        ProblemName.setText(problem.getProblemName());
        Description.setText(problem.getProblemDescription());
        MaxVars.setText(problem.getNumVars()+"");
        MaxTime.setText(problem.getMaxTime()+"");
    }

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
    private javax.swing.JButton ButtonDone;
    private javax.swing.JTextArea Description;
    private javax.swing.JLabel LabelTime;
    private javax.swing.JLabel LabelVars;
    private javax.swing.JTextField MaxTime;
    private javax.swing.JFormattedTextField MaxVars;
    private javax.swing.JPanel Panel;
    private javax.swing.JTextField ProblemName;
    private javax.swing.JScrollPane ScrollPane;
    // End of variables declaration//GEN-END:variables
}
