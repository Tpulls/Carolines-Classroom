/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carolinesclassroom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// Action Listeners: 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Sort Array for the popup window
 *
 * @author thomas
 */
public class SortArrayPopup extends JFrame {

    int totalX = 3;
    int totalNames = 0;
    int xPos = 0;
    //Declare the sortArray
    String[] sortArray;
    // instantiate the tempStudentArray
    String[] tempStudentArray = new String[totalNames];
    // instantiate the studentNames array
    String[][] studentNames = new String[totalNames][3];
    // instantiate the indexFields Array
    JTextField[][] indexFields = new JTextField[totalNames][3];
    // declare the labels
    private JLabel lblStudent, lblxIndex, lblyIndex;

    // Variable for holding the axis to highlight
    // Left null if non
    int[] axis = null;
    // Initiated when the sort button is pressed
    public SortArrayPopup(int namesTotal, String[][] allNames) {
        totalNames = namesTotal;
        studentNames = allNames;
        tempStudentArray = new String[totalNames];
        indexFields = new JTextField[totalNames][3];

        InitializeFrame();

    }
    // Initiated when the find button returns a true result
    public SortArrayPopup(int namesTotal, String[][] allNames, int[] axisToHighlight) {
        totalNames = namesTotal;
        studentNames = allNames;
        tempStudentArray = new String[totalNames];
        indexFields = new JTextField[totalNames][3];

        // Assign the axis to highlight
        axis = axisToHighlight;
        
        // Attempt to close window on btnClearFind action performed in CaronlinesClassroom
        /*if  (CarolinesClassroom.event.getAction() == (e.getSource() == btnClearFind)){
            window.dismiss();
        }*/
        
        InitializeFrame();
            
    }

    public void InitializeFrame() {
        // Setting up the frame
        setBounds(1445, 200, 370, 550);
        setResizable(false);
        setVisible(true);
        setTitle("Student List");

        // Adding JTextArea information
        displayGUI();

    }

    private void displayGUI() {
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);
        DisplayTextFields(springLayout);
        DisplayLabels(springLayout);
        sortStudent();
    }

    public void DisplayTextFields(SpringLayout layout) {
        // frame, Layout, width, xPos, yPos
        for (int x = 0; x < totalNames; x++) {

            String studentName = studentNames[x][0];
            String xIndex = studentNames[x][1];
            String yIndex = studentNames[x][2];
            int xPos = x * 26 + 45;

            // Initialize the textfields
            indexFields[x][0] = LibraryComponents.LocateAJTextField(this, layout, studentName, 70, 20, 60, xPos);
            indexFields[x][1] = LibraryComponents.LocateAJTextField(this, layout, xIndex, 70, 20, 145, xPos);
            indexFields[x][2] = LibraryComponents.LocateAJTextField(this, layout, yIndex, 70, 20, 225, xPos);
            //Set the textfields
            setTextFields(indexFields[x][0]);
            setTextFields(indexFields[x][1]);
            setTextFields(indexFields[x][2]);
        }
    }

    private void DisplayLabels(SpringLayout layout) {
        // Frame, Layout, Caption, size, location, background, setBackground, isBold, editable
        lblStudent = LibraryComponents.LocateAJLabel(this, layout, "  Student", new int[]{95, 30}, new int[]{60, 10}, new int[]{
            152, 205, 250}, true, true, false);
        lblxIndex = LibraryComponents.LocateAJLabel(this, layout, "Across", new int[]{85, 30}, new int[]{155, 10}, new int[]{
            152, 205, 250}, true, true, false);
        lblyIndex = LibraryComponents.LocateAJLabel(this, layout, "Down", new int[]{65, 30}, new int[]{240, 10}, new int[]{
            152, 205, 250}, true, true, false);
        // Set the label fields
        setLabelFields(lblStudent);
        setLabelFields(lblxIndex);
        setLabelFields(lblyIndex);
    }

    private void setLabelFields(JLabel label) {
        // Set label font
        label.setFont(new Font("TimesRoman", Font.BOLD, 16));
    }

    private void setTextFields(JTextField field) {
        //Set field constraints
        field.setEditable(false);
        field.setFont(new Font("TimesRoman", Font.BOLD, 14));
    }

    private void sortStudent() {
        copyToSortTable();
        sortTheSortTable();
        displaySortedTable();
    }

    private void copyToSortTable() {
        // Loop through all of the students
        for (int x = 0; x < totalNames; x++) {
            // Convert the 2D array into a singular array
            tempStudentArray[x] = studentNames[x][0] + "," + studentNames[x][1] + "," + studentNames[x][2];
        }
    }

    private void sortTheSortTable() {
        // Sort the array
        Arrays.sort(tempStudentArray, 0, totalNames);
        // Loop through all of the students again
    }

    private void displaySortedTable() {
        // Loop through all of the students again
        for (int x = 0; x < totalNames; x++) {
            // Convert the singular array into a 2D array
            String[] temp = tempStudentArray[x].split(",");
            // Input the data into the 2D array
            studentNames[x][0] = temp[0];
            studentNames[x][1] = temp[1];
            studentNames[x][2] = temp[2];
            // Set the index Fields
            indexFields[x][0].setText(temp[0]);
            indexFields[x][1].setText(temp[1]);
            indexFields[x][2].setText(temp[2]);

            // If axis exists and there is a match of the index values
            if (axis != null && (Integer.parseInt(studentNames[x][1]) == axis[0] && Integer.parseInt(studentNames[x][2]) == axis[1])) {
                // Highlight the index fields
                indexFields[x][0].setBackground(new Color(255, 217, 200));
                indexFields[x][1].setBackground(new Color(255, 217, 200));
                indexFields[x][2].setBackground(new Color(255, 217, 200));
            }
        }
    }
}
