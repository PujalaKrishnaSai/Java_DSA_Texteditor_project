import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, saveFile, openFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor(){
        //Initialize frame
        frame = new JFrame();
        //Initialize text area
        textArea = new JTextArea();
        //Initialize menuBar
        menuBar = new JMenuBar();
        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        //Initialize Menu items
        //File Menu items
        newFile = new JMenuItem("New window");
        saveFile = new JMenuItem("Save File");
        openFile = new JMenuItem("Open File");
        //Edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close window");
        //Add action listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //Add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);
        //Set menu bar to our frame
        frame.setJMenuBar(menuBar);
        //Create Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0, 0));
        //Add text Area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        //Set dimensions of frame
        frame.setBounds(100, 100, 800,500);

        //Set frame to be visible
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //If cut menu item is clicked
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        //If copy menu item is clicked
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        //If paste menu item is clicked
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        //If selectAll menu item is clicked
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        //If close menu item is clicked
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        //If newFile menu item is clicked
        if(actionEvent.getSource()==newFile){
            TextEditor newWindow = new TextEditor();
        }
        //If openFile menu item is clicked
        if(actionEvent.getSource()==openFile){
            //Initialized a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //Getting chosen option in file chooser
            int chooseOption = fileChooser.showOpenDialog(null);
            //If chosen option is the approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting selected file from file chooser
                File file = fileChooser.getSelectedFile();
                //Getting path of the selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    //Intermediate for current line; output for complete content of file
                    String intermediate = "", output = "";
                    //Read line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    bufferedReader.close();
                    //set text area with the content of the file
                    textArea.setText(output);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        //If saveFile menu item is clicked
        if(actionEvent.getSource()==saveFile){
            //Initialize File chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //Get chosen option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //If chosen option is the approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create a new file with directory's path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}