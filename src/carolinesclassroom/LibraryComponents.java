/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carolinesclassroom;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;

// The LibraryComponents Class is a procedure for the positioning of program features according to SpringLayout
/**
 *
 * @author thomas
 */
public class LibraryComponents {

    public static JLabel LocateAJLabel(JFrame myJFrame, SpringLayout myJLabelLayout, String JLabelCaption, int[] size, int[] location,
            int[] background, boolean setBackground, boolean isBold, boolean editable) {
        // Instantiate the JLabel
        JLabel myJLabel = new JLabel(JLabelCaption);

        // Add to screen
        myJFrame.add(myJLabel);

        // Set the preferred Size
        myJLabel.setPreferredSize(new Dimension(size[0], size[1]));

        if (setBackground) {
            // Set the opaque to true
            myJLabel.setOpaque(true);
            // Set the background color
            myJLabel.setBackground(new Color(background[0], background[1], background[2]));

            myJLabel.setFont(new Font("TimesRoman", Font.BOLD, 16));

            //myJLabel.setEditable(editable);
        }
        // Set constraints on location
        myJLabelLayout.putConstraint(SpringLayout.WEST, myJLabel, location[0], SpringLayout.WEST, myJFrame);
        myJLabelLayout.putConstraint(SpringLayout.NORTH, myJLabel, location[1], SpringLayout.NORTH, myJFrame);

        // Return to calling method
        return myJLabel;
    }

    public static JTextField LocateAJTextField(JFrame myJFrame, /*KeyListener myKeyLstnr,*/ SpringLayout myJTextFieldLayout,
            String JLabelCaption, int width, int height, int x, int y) {
        JTextField field = new JTextField(JLabelCaption);

        field.setPreferredSize(new Dimension(width, height));

        myJFrame.add(field);

        myJTextFieldLayout.putConstraint(SpringLayout.WEST, field, x, SpringLayout.WEST, myJFrame);
        myJTextFieldLayout.putConstraint(SpringLayout.NORTH, field, y, SpringLayout.NORTH, myJFrame);
        
        return field;
    }

    public static JButton LocateAJButton(JFrame myJFrame, ActionListener myActLstnr, SpringLayout myJButtonLayout,
            String JButtonCaption, int x, int y, int w, int h) {
        // Declare the button
        JButton myJButton = new JButton(JButtonCaption);

        // Add the button to the frame
        myJFrame.add(myJButton);
        // Add the action Listener
        myJButton.addActionListener(myActLstnr);

        // Put the contraints to the Layout
        myJButtonLayout.putConstraint(SpringLayout.WEST, myJButton, x, SpringLayout.WEST, myJFrame);
        myJButtonLayout.putConstraint(SpringLayout.NORTH, myJButton, y, SpringLayout.NORTH, myJFrame);
        myJButton.setPreferredSize(new Dimension(w, h));
        // Return the JButton to the calling method
        return myJButton;
    }

    public static JTextArea LocateAJTextArea(JFrame myJFrame, SpringLayout myLayout, JTextArea myJTextArea, int x, int y, int w, int h) {
        myJTextArea = new JTextArea(w, h);
        myJFrame.add(myJTextArea);
        myLayout.putConstraint(SpringLayout.WEST, myJTextArea, x, SpringLayout.WEST, myJFrame);
        myLayout.putConstraint(SpringLayout.NORTH, myJTextArea, y, SpringLayout.NORTH, myJFrame);
        return myJTextArea;
    }

    public static void clearJTextFieldArray(JTextField[][] JTxtFld, int minX, int minY, int maxX, int maxY) {
        for (int y = minY; y < maxY; y++) {
            for (int x = minX; x < maxX; x++) {
                JTxtFld[x][y].setText("");
            }
        }
    }

    public static int getLargestIndex(int arr[]) {
        int largestIndex = -1;
        int largestValue = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largestValue) {
                largestValue = arr[i];
                largestIndex = i;
            }
        }
        return largestIndex;
    }

    public static int getLargestValue(int arr[]) {
        int largestValue = -1;
        for (int i = 0; i < arr.length; i++) {
            if (largestValue > arr[i]) {
                largestValue = arr[i];
            }
        }
        return largestValue;
    }

    public static String checkInteger(String strValue) {
        try {
            Integer.parseInt(strValue);
            return strValue;
        } catch (Exception e) {
            return "0";
        }
    }
}
