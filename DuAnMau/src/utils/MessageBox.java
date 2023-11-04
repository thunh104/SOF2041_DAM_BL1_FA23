package utils;

import java.awt.Component;
import javax.swing.JOptionPane;

public class MessageBox {

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "HỆ THỐNG QUẢN LÝ ĐÀO TẠO", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "HỆ THỐNG QUẢN LÝ ĐÀO TẠO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String prompt(Component parent, String message){
        return JOptionPane.showInputDialog(parent, message, "HỆ THỐNG QUẢN LÝ ĐÀO TẠO", JOptionPane.INFORMATION_MESSAGE);
    }
}
