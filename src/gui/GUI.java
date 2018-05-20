package gui;

import gui.screens.*;
import solverandoptimizer.SolverandOptimizer;

import javax.swing.*;

public class GUI {

    public static final int StartScreen = 0;
    public static final int ProjectScreen = 1;
    public static final int ProblemScreen = 2;
    public static final int MainScreen = 3;
    public static final int MailScreen = 4;
    public static final int FAQScreen = 5;
    public static final int AboutScreen = 6;
    public static final int RunScreen = 7;

    private static GUI instance;
    public static GUI getInstance() {
        if (instance == null)
            instance = new GUI();
        return instance;
    }

    private JFrame parent;
    private JFrame child;

    private GUI() {
        setLookAndFeel("Windows");
        parent = new StartScreen();
        ((StartScreen) parent).open();
    }

    private void setLookAndFeel(String LookName) {
        //TODO Make Better
        //<editor-fold defaultstate="collapsed" desc="Swing look and feel setting code">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (LookName.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    public void next_screen(int screen) {
        switch (screen) {
            case (0):
                parent.dispose();
                parent = new StartScreen();
                ((StartScreen) parent).open();
                break;
            case (1):
                parent.dispose();
                parent = new ProjectScreen();
                ((ProjectScreen) parent).open();
                break;
            case (2):
                parent.dispose();
                parent = new ProblemScreen();
                parent.setTitle("Problem Solver and Optimizer - New Problem");
                ((ProblemScreen) parent).open();
                break;
            case (3):
                parent.dispose();
                parent = new MainScreen();
                parent.setTitle("Problem Solver and Optimizer - " + SolverandOptimizer.getInstance().getProblem().getProblemName());
                ((MainScreen)parent).open();
                break;
            case (4):
                parent.dispose();
                parent = new MailScreen();
                ((MailScreen)parent).open();
                break;
            case (5):
                parent.dispose();
                parent = new FAQScreen();
                ((FAQScreen)parent).open();
                break;
        }
    }

    public void child_screen(int screen) {
        switch (screen) {
            case (2):
                parent.setEnabled(false);
                child = new ProblemScreen();
                child.setTitle("Problem Solver and Optimizer - Edit Problem");
                ((ProblemScreen)child).setProblem(SolverandOptimizer.getInstance().getProblem());
                child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ((ProblemScreen)child).open();
                break;
            case (4):
                parent.setEnabled(false);
                child = new MailScreen();
                child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ((MailScreen)child).open();
                break;
            case (5):
                parent.setEnabled(false);
                child = new FAQScreen();
                child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ((FAQScreen)child).open();
                break;
            case (6):
                parent.setEnabled(false);
                child = new AboutScreen();
                child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ((AboutScreen)child).open();
                break;
            case (7):
                parent.setEnabled(false);
                child = new RunScreen();
                child.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ((RunScreen)child).open();
                break;
        }
    }

    public void close_screen(JFrame frame){
        if(frame==parent){
            parent.dispose();
            //If need do something more
            System.exit(0);
        }
        if(frame==child){
            child.dispose();
            parent.setEnabled(true);
            parent.toFront();
        }
    }

    public void show_error(String error){
        if(child!=null)
            JOptionPane.showMessageDialog(child, error, "Error", JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(parent, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void show_message(String error){
        if(child!=null)
            JOptionPane.showMessageDialog(child, error, "Error", JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(parent, error, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
}
