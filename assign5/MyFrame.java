/*
    Author: Jiashan Li
    Version: 2015.11.26
    Student ID: 10171607
    Instructor: James Tam
    Course: CPSC 233(T04)
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;


public class MyFrame extends JFrame implements ActionListener
{
    private ImageIcon aIcon;
    private ImageIcon buttonIcon;
    private ImageIcon buttonIcon2;
    private JLabel aLabel1;
    private JLabel aLabel2;
    private JLabel aLabel;
    private JButton left;
    private JButton right;
    private JTextField text1;
    private JTextArea text2;
    private JScrollPane textScroll;
    private GridBagLayout aLayout;
    private GridBagConstraints aConstraint;
    private PrintWriter pw = null;
    private FileWriter fw = null;

    public MyFrame ()
    {
        setTitle("Order information");
        setSize (400,300);           
        
        aIcon = new ImageIcon("data/logo.png");
        aLabel = new JLabel(aIcon); 

        aLabel1 = new JLabel("Name");
        aLabel2 = new JLabel("Address");
        right = new JButton("Claer");
        left = new JButton("Save"); 
        text1 = new JTextField(10);      
        text2 = new JTextArea(4, 10);
        text2.setLineWrap(true);     // change line when one line is full //
        textScroll = new JScrollPane(text2);
        aConstraint = new GridBagConstraints();    
        aLayout = new GridBagLayout();    // use the GridBagLayout to make it easy for the user to see the userinterface //
        
        left.addActionListener(this);    // click save then some actions happen //
        right.addActionListener(this);  // click clear then some actions happen //     
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aConstraint.insets = new Insets(10,10,10,10);
        aConstraint.anchor = GridBagConstraints.NORTHWEST;
        setLayout(aLayout); 

        addWidget(aLabel, 0, 0, 2, 1);
        addWidget(aLabel1, 0, 1, 1, 1);
        addWidget(aLabel2, 1, 1, 1, 1);
        addWidget(text1, 0, 2, 1, 1);
        addWidget(text2, 1, 2, 1, 1);
        addWidget(left, 0, 3, 1, 1);    
        addWidget(right, 1, 3, 1, 1); 

        enhanceButton(); 
        setVisible(true);     
    }


    // add the component into frame and set the position and size of the component //
    public void addWidget (Component widget, int x, int y, int w, int h)
    {
        aConstraint.gridx = x;
        aConstraint.gridy = y;
        aConstraint.gridwidth = w;
        aConstraint.gridheight = h;
        aLayout.setConstraints (widget, aConstraint);
        add(widget);        // Calling method of super class.
    }
  

   // when dealing with the component, actions happen //
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.left)   // when dealing with the save button //
        {
        String s1 = text1.getText();
        System.out.println(s1);        // print out the content in textfield //

        String s2 = text2.getText();
        System.out.println(s2);       // print out the content in textarea //



        try             // print out the content in the two field into the order.txt file //
        {
            fw = new FileWriter("order.txt");
            pw = new PrintWriter(fw);
            pw.println(s1);
            pw.println(s2);
            fw.close();
        }       
        catch (IOException l)
        {
        System.out.println("Error writing to file");
        }



        setTitle("Saving information");       // when press save, show the information on the window title, and this information stay for 1 second //
        try 
        {
             // Time delay or pause program for 1,000 milliseconds (1 seconds)
          Thread.sleep(1000); 
        }
        catch (InterruptedException ex) 
        {
          System.out.println("Pausing of program was interrupted"); 
        }
        setTitle("Order information");
           
        }

        else if(e.getSource() == this.right)  // when press clear button, the text in two fields will clear //
        {
           text1.setText("");      
           text2.setText("");


        setTitle("Clearing information");      // when press clear, show the information on the window title, and this information stay for 1 second //
        try 
        {
             // Time delay or pause program for 1,000 milliseconds (1 seconds)
          Thread.sleep(1000); 
        }
        catch (InterruptedException ex) 
        {
          System.out.println("Pausing of program was interrupted"); 
        }
        setTitle("Order information");
        }
        
    } 

    public void enhanceButton()         
    {   // add image for the two button //
        // Path is relative to the location of the source code
       buttonIcon = new ImageIcon("data/clear.png");
       right.setIcon(buttonIcon);
       buttonIcon2 = new ImageIcon("data/save.png");
       left.setIcon(buttonIcon2);
    }


}
