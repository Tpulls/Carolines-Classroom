/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carolinesclassroom;

import javax.swing.*;
import java.awt.*;
// Key Listeners: Changing student names
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// Action Listeners: 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Mouse Listeners: Search text field
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
// Window Listeners: Close window / minimize
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// Load and save function
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
// Custom Date Imports: Set current time
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
// Open dialog import
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author thomas
 */
public class CarolinesClassroom extends JFrame implements ActionListener, KeyListener {

    // Member Variables
    // Total[x][y] to set Multi-dimensional array parameters
    private int totalX = 10;
    private int totalY = 19;
    // Varaibles for fields, button and label coordinate parameters
    int xPos = 0;
    int yPos = 0;
    int fieldIndex[];
    // Array for handling the coordinates of studentName
    int[] axis = new int[2];
    // Declare the Multi-dimensional textfield array
    private JTextField[][] fields;
    // Declare the labels
    private JLabel lblTeacherTitle, lblClassTitle, lblRoomTitle, lblDateTitle, lblTeacher, lblClass, lblRoom, lblDate, lblSearch;
    // Declare the Search field
    private JTextField txtSearch;
    // Declare the buttons
    private JButton btnClear, btnSave, btnSort, btnFind, btnRAF, btnExit, btnClearFind, btnOpen;

    // Read & Write File Source
    private String dataFileName = "TheClassroomLayout_SampleData.csv";
    // RAF File 
    private String rafFileName = "TheClassroomLayoutRAF.csv";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Declare the application
        CarolinesClassroom ClassroomApplication = new CarolinesClassroom();
        // Run the application
        ClassroomApplication.run();
    }

    void run() {
        setTitle("Classroom Plan");
        setBounds(400, 200, 1050, 550);
        //Declare the fields variable as the multidimensional array
        fields = new JTextField[totalX][totalY];
        // Set frame to visible
        setVisible(true);
        // Set frame to none resizable
        setResizable(false);
        // Declare and instantiate the Window Listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Close the application 
                System.exit(0);
            }
        });

        // Declare the DisplayGUI method
        DisplayGUI();
        // Declare the ReadDataFile method
        ReadDataFile(dataFileName);
        // Declare the WriteDataFile method
        WriteDataFile(dataFileName);
        // 

    }

    private void DisplayGUI() {
        // Declare the Springlayout
        SpringLayout springLayout = new SpringLayout();
        // Declare the setLayout method
        setLayout(springLayout);
        // Declare the DisplayTextFields method
        DisplayTextFields(springLayout);
        // Declare the DisplayButtons method
        DisplayButtons(springLayout);
        // Declare the DisplayLabels method
        DisplayLabels(springLayout);
        // Declare the SetupTable method
        SetupTable(springLayout);
    }

    private void DisplayTextFields(SpringLayout layout) {
        // frame, Layout, width, xPos, yPos
        for (int y = 0; y < totalY; y++) {
            for (int x = 0; x < totalX; x++) {
                // Set the x & y Positioning
                int xPos = x * 92 + 60;
                int yPos = y * 20 + 80;
                // Give context to the LocateAJTextField method
                fields[x][y] = LibraryComponents.LocateAJTextField(this, layout, "", 70, 20, xPos, yPos);
            }
        }
    }

    private void DisplayButtons(SpringLayout layout) {
        // frame, listener, layout, caption, xPos, yPos, width, Height
        btnClear = LibraryComponents.LocateAJButton(this, this, layout, "Clear", 250, 475, 80, 25);
        btnSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 150, 475, 80, 25);
        btnSort = LibraryComponents.LocateAJButton(this, this, layout, "Sort", 350, 475, 80, 25);
        btnFind = LibraryComponents.LocateAJButton(this, this, layout, "Find", 450, 475, 80, 25);
        btnClearFind = LibraryComponents.LocateAJButton(this, this, layout, "Reset", 450, 475, 80, 25);
        btnRAF = LibraryComponents.LocateAJButton(this, this, layout, "RAF", 750, 475, 80, 25);
        btnExit = LibraryComponents.LocateAJButton(this, this, layout, "Exit", 850, 475, 80, 25);
        btnOpen = LibraryComponents.LocateAJButton(this, this, layout, "Open", 50, 475, 80, 25);
        txtSearch = LibraryComponents.LocateAJTextField(this, layout, "Enter Student Name:", 130, 20, 550, 480);
        txtSearch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                // Reset the text when the box is clicked
                txtSearch.setText("");
            }

            // **Unused used methods
            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
            // **Unused used methods
        });
    }

    private void DisplayLabels(SpringLayout layout) {
        // Frame, Layout, Caption, size, location, background, setBackground, isBold
        // Loop through the structure for the top row headings
        for (int x = 0; x < totalX; x++) {
            int xPos = x * 92 + 97;
            LibraryComponents.LocateAJLabel(this, layout, Integer.toString(x), new int[]{
                15, 20
            }, new int[]{
                xPos, 50
            },
                    new int[]{
                        152, 205, 250
                    }, false, true, false);
        }

        // Loop through the structure of the left column headings
        for (int y = 0; y < totalY; y++) {
            int yPos = y * 20 + 78;
            LibraryComponents.LocateAJLabel(this, layout, Integer.toString(y), new int[]{
                15, 20
            }, new int[]{
                30, yPos
            },
                    new int[]{
                        152, 205, 250
                    }, false, true, false);
        }
        // Frame, Layout, Caption, size, location, background, setBackground, isBold
        // set the structure for the labels
        // LblTeacherTitle Context
        lblTeacherTitle = LibraryComponents.LocateAJLabel(this, layout, "Teacher:", new int[]{
            80, 30
        }, new int[]{
            100, 10
        },
                new int[]{
                    152, 205, 250
                }, true, true, false);
        // LblClassTitle Context
        lblClassTitle = LibraryComponents.LocateAJLabel(this, layout, "Class:", new int[]{
            60, 30
        }, new int[]{
            325, 10
        },
                new int[]{
                    152, 205, 250
                }, true, true, false);
        // LblRoomTitle Context
        lblRoomTitle = LibraryComponents.LocateAJLabel(this, layout, "Room:", new int[]{
            60, 30
        }, new int[]{
            525, 10
        },
                new int[]{
                    152, 205, 250
                }, true, true, false);
        // LblTeacher Context
        lblTeacher = LibraryComponents.LocateAJLabel(this, layout, " ", new int[]{
            145, 30
        }, new int[]{
            180, 10
        },
                new int[]{
                    152, 205, 250
                }, true, true, true);
        // LblClass Context
        lblClass = LibraryComponents.LocateAJLabel(this, layout, " ", new int[]{
            140, 30
        }, new int[]{
            385, 10
        },
                new int[]{
                    152, 205, 250
                }, true, true, true);
        // LblRoom Context
        lblRoom = LibraryComponents.LocateAJLabel(this, layout, " ", new int[]{
            140, 30
        }, new int[]{
            585, 10
        },
                new int[]{
                    152, 205, 250
                }, true, true, true);
        // LblDateTitle Context
        lblDateTitle = LibraryComponents.LocateAJLabel(this, layout, "Date: ", new int[]{
            60, 30
        }, new int[]{
            725, 10
        },
                new int[]{
                    152, 205, 250
                }, true, true, false);
        lblDate = LibraryComponents.LocateAJLabel(this, layout, " ", new int[]{
            140, 30
        }, new int[]{
            785, 10
        },
                new int[]{
                    152, 205, 250
                }, true, true, true);

    }

    // Set the constrains and conditions
    private void SetupTable(SpringLayout layout) {
        // For fields[x][y], set the fields to editable
        for (int y = 0; y < totalY; y++) {
            for (int x = 0; x < totalX; x++) {
                setFieldProperties(x, y, false);
                txtSearch.setText("Enter Student Name:");
                txtSearch.setForeground(Color.BLUE);
                btnClearFind.setVisible(false);
            }
        }
    }

    // Structure for the editable fields
    private void setFieldProperties(int x, int y, boolean editable) {
        // Set the field properties to editable
        fields[x][y].setEditable(editable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the button is clicked
        if (e.getSource() == btnClear) {
            // Clear the student names
            clearStudentNames();
        }

        // If the button is clicked
        if (e.getSource() == btnSave) {
            // Write to file
            WriteDataFile(dataFileName);
        }

        // If the button is clicked       
        if (e.getSource() == btnSort) {
            // Get the student names
            String[][] names = getStudentNames();
            // Store the total students in a variable
            int total = getTotalStudents();
            // Instantiate the new SortArrayPopup with parameters
            new SortArrayPopup(total, names);
        }

        // If the button is clicked        
        if (e.getSource() == btnFind) {
            // Find a match for students names and highlight according to index
            findStudentName();
        }

        // If the button is clicked        
        if (e.getSource() == btnClearFind) {
            // Reset the text
            txtSearch.setText("");
            // Hide the clearFind button once used
            btnClearFind.setVisible(false);
            // Populate the btnFind
            btnFind.setVisible(true);
            // Set the field indexes of the find to white
            fields[axis[0]][axis[1]].setBackground(new Color(255, 255, 255));
            // Reset the index values
            axis = new int[2];
        }

        // If the button is clicked
        if (e.getSource() == btnRAF) {
            // Write the file
            writeRandomAccessFile(rafFileName);
            // Check the txtSearch is a string value
            int requiredEntry = Integer.parseInt(LibraryComponents.checkInteger(txtSearch.getText()));
            // Read the file
            readRandomAccessFile(rafFileName, requiredEntry);
        }

        // If the button is clicked
        if (e.getSource() == btnOpen) {
            // Get the file path and populate the dialog box
            String filePath = openFileDialog();

        }

        // If the button is clicked
        if (e.getSource() == btnExit) {
            // Exit the application
            System.exit(0);
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public void ReadDataFile(String fileName) {
        try {
            // Open the readFile
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // Create a variable for the headings total
            int headingsEndIndex = 4;
            // Create an array for the headings
            String[] headings = new String[4];
            // set the while variable
            int x = 1;
            // Set the readLine variable
            String readLine;
            // While the data file is not empty
            while ((readLine = br.readLine()) != null) {
                if (x <= headingsEndIndex) {
                    if (x < headingsEndIndex) {
                        // Split the lines and store the index[1] to the headings array
                        headings[x - 1] = readLine.split(",")[1];
                    } else {
                        // Populate the date with the current value
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDateTime now = LocalDateTime.now();
                        headings[x - 1] = dtf.format(now);
                    }

                } else {
                    //Read the student detail lines and store in according arrays split by ","
                    String[] temp = readLine.split(",");
                    // Store the xIndex
                    int xIndex = Integer.parseInt(temp[0]);
                    // Store the yIndex
                    int yIndex = Integer.parseInt(temp[1]);
                    // Store the studentName
                    String title = temp[2];
                    // The if the title contains color context, set the color(no text stored)
                    if (title.equals("BKGRND FILL")) {
                        if (yIndex == 1) {
                            fields[xIndex][yIndex - 1].setEditable(true);
                        } else {
                            fields[xIndex][yIndex + 1].setEditable(true);
                        }
                        fields[xIndex][yIndex].setBackground(new Color(148, 185, 224));

                    } else {
                        // else set the StudentName to the loop x,y values
                        fields[xIndex][yIndex].setText(title);
                    }
                }
                // Increment the if statement
                x++;
            }
            // Close the reading
            br.close();
            // Set the headings text
            setHeadings(headings);

        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    public void WriteDataFile(String fileName) {
        try {
            // Access the writer
            BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
            // Write the teacher heading
            outFile.write("Teacher:," + lblTeacher.getText() + "\n");
            // Write the class heading
            outFile.write("Class:," + lblClass.getText() + "\n");
            // Write the room heading
            outFile.write("Room:," + lblRoom.getText() + "\n");
            // Write the date heading
            outFile.write("Date:," + lblDate.getText() + "\n");

            // Loop through the fields
            for (int x = 0; x < totalX; x++) {
                for (int y = 0; y < totalY; y++) {
                    int getRGB = fields[x][y].getBackground().getRGB();
                    int[] rgb = new int[]{(getRGB >> 16 & 0xFF), (getRGB >> 8 & 0xFF), (getRGB & 0xFF)};
                    // Get the blue color
                    int[] blue = new int[]{148, 185, 224};
                    // If the field contains text
                    if (fields[x][y].getText().length() > 0) {
                        // Write out to x,y,studentName
                        outFile.write(x + "," + y + "," + fields[x][y].getText());
                        // Print new line
                        outFile.newLine();
                        // Otherwise, if the cell contains color conditions
                    } else if (rgb[0] == blue[0] && rgb[1] == blue[1] && rgb[2] == blue[2]) {
                        // Write out x,y,color conditions
                        outFile.write(x + "," + y + "," + "BKGRND FILL,blue");
                        outFile.newLine();
                    }
                }
            }
            // Close the BufferedWriter
            outFile.close();
            // Provide a console message on success
            System.out.println("Classroom Plan data has been saved.");

        } catch (Exception e) {
            System.out.println();
            // Create a visible error report
            e.printStackTrace();
        }
    }

    private void clearStudentNames() {
        //Loop through all fields[x][y]
        for (int x = 1; x < totalX; x++) {
            for (int y = 0; y < totalY; y++) {
                // Check to see separate the fields that contain student information from the front desk & Background fill
                if (fields[x][y].getText().length() > 0) {
                    // Set text to empty
                    fields[x][y].setText("");
                };
            }
        }
    }

    // References: ReadFile
    private void setHeadings(String[] headings) {
        // Take the values stored in the headings array and assign to the labels
        lblTeacher.setText(headings[0]);
        lblClass.setText(headings[1]);
        lblRoom.setText(headings[2]);
        lblDate.setText(headings[3]);
    }

    private String[][] getStudentNames() {
        // Get total number of students
        int totalStudents = getTotalStudents();
        // Create an array that holds the studentNames
        String[][] studentNames = new String[totalStudents][3];
        // Variable for checking the index of the array
        int arrayIndex = 0;
        // Create a loop to run through all the suitable text fields
        for (int x = 0; x < totalX; x++) {
            for (int y = 2; y < totalY; y++) {
                // Get the studetn details
                String studentName = fields[x][y].getText();
                // Ensure that we collect details with text entered
                if (studentName.length() > 0) {
                    // Note: Assign the array index values in correspondence to the popup display format
                    // Assign the field text to index[0]
                    studentNames[arrayIndex][0] = studentName;
                    // Assign the field x value to index[1]
                    studentNames[arrayIndex][1] = Integer.toString(x);
                    // Assign the field x value to index[1]
                    studentNames[arrayIndex][2] = Integer.toString(y);
                    // Increment the array index to store the next rows values
                    arrayIndex++;
                }

            }
        }
        // return the student names by row - [name][x][y]
        return studentNames;
    }

    private int getTotalStudents() {
        // Declare a count variable
        int count = 0;
        // Loop through the field data
        for (int x = 0; x < totalX; x++) {
            for (int y = 2; y < totalY; y++) {
                // Collect the studentNames
                String studentName = fields[x][y].getText();
                // Ensure that the fields contain a text length
                if (studentName.length() > 0) {
                    // Then incrementally count the results
                    count++;
                }
            }
        }
        // Return the total student count
        return count;
    }

    private void writeRandomAccessFile(String rafFileName) {
        try {
            // Access the writer
            RandomAccessFile rafFile = new RandomAccessFile(rafFileName, "rw");
            // Write the teacher heading
            rafFile.writeUTF("Teacher:," + lblTeacher.getText() + "\n");
            // Write the class heading
            rafFile.writeUTF("Class:," + lblClass.getText() + "\n");
            // Write the room heading
            rafFile.writeUTF("Room:," + lblRoom.getText() + "\n");
            // Write the date heading
            rafFile.writeUTF("Date:," + lblDate.getText() + "\n");

            // Loop through the fields
            for (int x = 0; x < totalX; x++) {
                for (int y = 0; y < totalY; y++) {
                    int getRGB = fields[x][y].getBackground().getRGB();
                    int[] rgb = new int[]{(getRGB >> 16 & 0xFF), (getRGB >> 8 & 0xFF), (getRGB & 0xFF)};
                    // Get the blue color
                    int[] blue = new int[]{148, 185, 224};

                    if (fields[x][y].getText().length() > 0) {
                        rafFile.writeUTF(x + "," + y + "," + fields[x][y].getText());
                    } else if (rgb[0] == blue[0] && rgb[1] == blue[1] && rgb[2] == blue[2]) {
                        rafFile.writeUTF(x + "," + y + "," + "BKGRND FILL,blue");
                    }
                }
            }
            // Close the BufferedWriter
            rafFile.close();
            // Provide a console message on success
            System.out.println("Classroom Plan RAF data has been saved.");

        } catch (Exception e) {
            System.out.println();
            // Create a visible error report
            e.printStackTrace();
        }
    }

    private void readRandomAccessFile(String rafFileName, int index) {
        try {
            // Open the readFile
            RandomAccessFile rafFile = new RandomAccessFile(rafFileName, "rw");
            // Create a variable for the headings total
            int headingsEndIndex = 4;
            // Create an array for the headings
            String[] headings = new String[4];
            // set the if statement constraint variable
            int x = 1;
            // Set the readLine variable
            String readLine;
            // While the data file is not empty
            while ((readLine = rafFile.readLine()) != null) {
                if (x <= headingsEndIndex) {
                    if (x < headingsEndIndex) {
                        // Split the lines and store the index[1] to the headings array
                        headings[x - 1] = readLine.split(",")[1];
                    } else {
                        // Populate the date with the current value
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDateTime now = LocalDateTime.now();
                        headings[x - 1] = dtf.format(now);
                    }

                } else {
                    //Read the student detail lines and store in according arrays split by ","
                    String[] temp = readLine.split(",");
                    // Store the xIndex
                    int xIndex = Integer.parseInt(temp[0]);
                    // Store the yIndex
                    int yIndex = Integer.parseInt(temp[1]);
                    // Store the studentName
                    String title = temp[2];
                    // The if the title contains color context, set the color(no text stored)
                    if (title.equals("BKGRND FILL")) {
                        if (yIndex == 1) {
                            fields[xIndex][yIndex - 1].setEditable(true);
                        } else {
                            fields[xIndex][yIndex + 1].setEditable(true);
                        }
                        fields[xIndex][yIndex].setBackground(new Color(148, 185, 224));

                    } else {
                        // else set the StudentName to the loop x,y values
                        fields[xIndex][yIndex].setText(title);
                    }
                }
                // Increment the constraint
                x++;
            }
            // Close the reader
            rafFile.close();
            // Set the the headings text
            setHeadings(headings);

        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void findStudentName() {
        // Declare a boolean
        boolean found = false;
        // Store the search text to a String Variable
        String strSearch = txtSearch.getText();
        // Create a while to handle empty searches
        while (strSearch.isEmpty()) {
            txtSearch.setText("Enter Student name:");
            return;
        }

        // Declare variables to determine the field start point
        for (int x = 0; x < totalX; x++) {
            for (int y = 0; y < totalY; y++) {
                // If the field text equals the txtSearch value
                if (fields[x][y].getText().equalsIgnoreCase(strSearch)) {
                    // Change the index value background
                    fields[x][y].setBackground(new Color(255, 217, 200));
                    // Redefine the boolean
                    found = true;
                    // Store the index values
                    axis[0] = x;
                    axis[1] = y;
                    // Hide the btnFind
                    btnFind.setVisible(false);
                    // Make visiable the btClearFind
                    btnClearFind.setVisible(true);
                }
            }
        }

        if (found == true) {
            // Get the student names
            String[][] names = getStudentNames();
            // Store the total students in a variable
            int total = getTotalStudents();
            // Instantiate the new SortArrayPopup with parameters
            new SortArrayPopup(total, names, axis);
        }
    }

    private String openFileDialog() {
        // Variable for the seelected file
        File selectedFile = null;
        // Instantiate the File chooser
        JFileChooser fileChooser = new JFileChooser();
        // Set the directory
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        // Set the file filter
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        // Show the dialog in the parent panel
        int fileResult = fileChooser.showOpenDialog(this);
        // Check if the user selects a file
        if (fileResult == JFileChooser.APPROVE_OPTION) {
            // User selects a file
            selectedFile = fileChooser.getSelectedFile();
        }
        //Return the selected file
        return selectedFile != null ? selectedFile.getAbsolutePath() : null;
    }

}
