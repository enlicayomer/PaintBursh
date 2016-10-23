package com.omerenlicay;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Github:https://github.com/enlicayomer/PaintBursh
 */
public class DrawFrame extends JFrame {

    private JLabel stausLabel; //label display mouse coordinatess
    private DrawPanel panel; //draw panel for the shapes

    private JButton undo; // button to undo last drawn shape
    private JButton redo; // button to redo an undo
    private JButton clear; // button to clear panel
    private JButton rect;
    private JButton oval;
    private JButton line;
    private JComboBox colors; //combobox with color options

    //array of strings containing color options for JComboBox colors
    private String colorOptions[]
            = {"Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Light Gray",
                "Magenta", "Orange", "Pink", "Red", "White", "Yellow"};

    //aray of Color objects contating Color constants
    private Color colorArray[]
            = {Color.BLACK, Color.BLUE, Color.CYAN, Color.darkGray, Color.GRAY,
                Color.GREEN, Color.lightGray, Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

    private JComboBox shapes; //combobox with shape options

    //array of strings containing shape options for JComboBox shapes
    private String shapeOptions[]
            = {"Line", "Rectangle", "Oval"};

    private JCheckBox filled; //checkbox to select whether shape is filled or not

    private JPanel widgetJPanel; //holds the widgets: buttons, comboboxes and checkbox
    private JPanel widgetPadder; //encapsulates widgetJPanel and adds padding around the edges 

    public DrawFrame() {
        super("SuperPaint Application v2.0!"); //sets the name of DrawFrame

        JLabel statusLabel = new JLabel(""); //create JLabel object to pass into DrawPanel

        panel = new DrawPanel(statusLabel); //create draw panel and pass in JLabel

        //create buttons
        Icon left = new ImageIcon(getClass().getResource("left_arrow.png"));
        Icon right = new ImageIcon(getClass().getResource("redo_arrow.png"));
        Icon clearico = new ImageIcon(getClass().getResource("clear.png"));

        undo = new JButton("Geri");
        undo.setIcon(left);
        redo = new JButton("İleri");
        redo.setIcon(right);
        clear = new JButton("Temizle");
        clear.setIcon(clearico);
        rect = new JButton("rect");
        oval = new JButton("oval");
        line = new JButton("line");

        //create comboboxes
        colors = new JComboBox(colorOptions);
        shapes = new JComboBox(shapeOptions);

        //create checkbox
        filled = new JCheckBox("Filled");

        //JPanel object, widgetJPanel, with grid layout for widgets
        widgetJPanel = new JPanel();
        widgetJPanel.setLayout(new GridLayout(2, 6, 10, 10)); //sets padding between widgets in gridlayout

        //JPanel object, widgetPadder, with flowlayout to encapsulate and pad the widgetJPanel
        widgetPadder = new JPanel();
        widgetPadder.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5)); //sets padding around the edges

        // add widgets to widgetJPanel
        widgetJPanel.add(undo);
        widgetJPanel.add(redo);
        widgetJPanel.add(clear);
        widgetJPanel.add(colors);
       // widgetJPanel.add(shapes);
        widgetJPanel.add(filled);
        widgetJPanel.add(rect);
        widgetJPanel.add(oval);
        widgetJPanel.add(line);

        // add widgetJPanel to widgetPadder
        widgetPadder.add(widgetJPanel);

        //add widgetPadder and panel to JFrame
        add(widgetPadder, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // create new ButtonHandler for button event handling
        ButtonHandler buttonHandler = new ButtonHandler();
        undo.addActionListener(buttonHandler);
        redo.addActionListener(buttonHandler);
        clear.addActionListener(buttonHandler);
        rect.addActionListener(buttonHandler);
        oval.addActionListener(buttonHandler);
        line.addActionListener(buttonHandler);

        //create handlers for combobox and checkbox
        ItemListenerHandler handler = new ItemListenerHandler();
        colors.addItemListener(handler);
        shapes.addItemListener(handler);
        filled.addItemListener(handler);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setVisible(true);

    } // end DrawFrame constructor

    /**
     * private inner class for button event handling
     */
    private class ButtonHandler implements ActionListener {

        // handles button events
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("Geri")) {
                panel.clearLastShape();
            } else if (event.getActionCommand().equals("İleri")) {
                panel.redoLastShape();
            } else if (event.getActionCommand().equals("Temizle")) {
                panel.clearDrawing();
            } else if (event.getActionCommand().equals("rect")) {
                panel.setCurrentShapeType(1);

            } else if (event.getActionCommand().equals("oval")) {
                panel.setCurrentShapeType(2);

            } else if (event.getActionCommand().equals("line")) {
                panel.setCurrentShapeType(0);

            }

        } // end method actionPerformed
    } // end private inner class ButtonHandler

    /**
     * private inner class for checkbox and combobox event handling
     */
    private class ItemListenerHandler implements ItemListener {

        public void itemStateChanged(ItemEvent event) {
            // process filled checkbox events
            if (event.getSource() == filled) {
                boolean checkFill = filled.isSelected() ? true : false; //
                panel.setCurrentShapeFilled(checkFill);
            }

            // determine whether combo box selected
            if (event.getStateChange() == ItemEvent.SELECTED) {
                //if event source is combo box colors pass in colorArray at index selected.
                if (event.getSource() == colors) {
                    panel.setCurrentShapeColor(colorArray[colors.getSelectedIndex()]);
                } //else if event source is combo box shapes pass in index selected
                else if (event.getSource() == shapes) {
                    panel.setCurrentShapeType(shapes.getSelectedIndex());
                }
            }

        } // end method itemStateChanged
    }

} // end class DrawFrame
