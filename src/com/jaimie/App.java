package com.jaimie;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JButton btnConvert;
    public JPanel pnlMain;
    private JLabel lblConfirmation;
    private JButton btnCopy;


    public App() {
        btnConvert.addActionListener(e -> {
            String s = JOptionPane.showInputDialog("Enter a number").toLowerCase();
            if (s.contains("m") || s.contains("b") || s.contains("t")){
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == 'm' || s.charAt(i) == 'b' || s.charAt(i) == 't') {
                        String s2 = s.substring(0, i);
                        char letter = s.charAt(i);
                        if (s.contains(".") || s.contains(",")) {
                            int pointIndex = -1;
                            for (int j = 0; j < s.length(); j++) {
                                if (s.charAt(j) == '.') {
                                    pointIndex = j + 1;
                                }
                            }

                            String s3 = s2.replace(".", "");
                            String s4 = s3.replace(",", "");
                            int after = s2.substring(pointIndex).length();
                            if (after > 3) {
                                after = 3;
                                s4 = s4.substring(0, pointIndex + 2);
                            }
                            String number = convert(s4, letter, true,  after);
                            if (number.contains("m") || number.contains("b") || number.contains("t")) {
                                lblConfirmation.setText("Invalid number!");
                                lblConfirmation.setForeground(Color.RED);
                            } else {
                                StringSelection stringSelection = new StringSelection(number);
                                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                clipboard.setContents(stringSelection, null);
                                lblConfirmation.setForeground(Color.GREEN);
                                lblConfirmation.setText("Your number has been copied to the clipboard!");
                            }

                        } else {
                            String number = convert(s2, letter, false, 0);
                            if (number.contains("m") || number.contains("b") || number.contains("t")) {
                                lblConfirmation.setText("Invalid number!");
                                lblConfirmation.setForeground(Color.RED);
                            } else {
                                StringSelection stringSelection = new StringSelection(number);
                                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                clipboard.setContents(stringSelection, null);
                                lblConfirmation.setForeground(Color.GREEN);
                                lblConfirmation.setText("Your number has been copied to the clipboard!");
                            }
                        }
                    }
                }
            } else {
                lblConfirmation.setText("Enter a number followed by 'm', 'b', or 't'!");
                lblConfirmation.setForeground(Color.RED);
            }
        });
    }

    private String convert(String number, char letter, boolean containsPoint, int numbersAfter) {
        StringBuilder newString = new StringBuilder();
        if (containsPoint) {
            if (letter == 'm') {
                newString.append(number);
                for (int i = 0; i <= (5 - numbersAfter); i++) {
                    newString.append("0");
                }
            } else if (letter == 'b') {
                if (numbersAfter > 3) {
                    numbersAfter = 3;
                }
                newString.append(number);
                for (int i = 0; i <= (8 - numbersAfter); i++) {
                    newString.append("0");
                }
            } else if (letter == 't') {
                newString.append(number);
                for (int i = 0; i <= (12 - numbersAfter); i++) {
                    newString.append("0");
                }
            } else {
                return "Unknown letter";
            }
        } else {
            if (letter == 'm') {
                newString.append(number);
                for (int i = 0; i <= 5; i++) {
                    newString.append("0");
                }
            } else if (letter == 'b') {
                newString.append(number);
                for (int i = 0; i <= 8; i++) {
                    newString.append("0");
                }
            } else if (letter == 't') {
                newString.append(number);
                for (int i = 0; i <= 12; i++) {
                    newString.append("0");
                }
            } else {
                return "Unknown letter";
            }
        }

        return newString.toString();
    }
}
