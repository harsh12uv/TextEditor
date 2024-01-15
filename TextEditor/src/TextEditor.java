//importing the javaSwing package
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class TextEditor implements ActionListener{

    JFrame frame;

    JMenuBar menuBar;

    JTextArea textArea;

    JMenu file,edit;

    JMenuItem newW,save,open;
    JMenuItem cut,copy,paste,selectAll,close;

    TextEditor(){

//        initializing a frame
        frame=new JFrame();
        menuBar=new JMenuBar();

        textArea=new JTextArea();

        file=new JMenu("File");
        edit=new JMenu("Edit");

//        initialize menu items

        newW=new JMenuItem("New Window");
        save=new JMenuItem("Save File");
        open=new JMenuItem("Open File");

//        adding action listeners

        newW.addActionListener(this);
        save.addActionListener(this);
        open.addActionListener(this);

        file.add(newW);
        file.add(save);
        file.add(open);

        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("close");
//        adding action listeners

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

//        add to menubar
        menuBar.add(file);
        menuBar.add((edit));
//        setting menuBar to the frame
        frame.setJMenuBar(menuBar);
        //addind content panel
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
//        frame.add(textArea);
//        setting the dimensions of the frame

        panel.add(textArea,BorderLayout.CENTER);

        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);
        frame.setBounds(0,0,400,400);
        frame.setTitle("Harsh's Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==open){
//            open a file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption =fileChooser.showOpenDialog(null);
//            if we have selected the open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //get the selected file
                File file=fileChooser.getSelectedFile();
                //getting the file path
                String filePath=file.getPath();
                try{
                    FileReader fileReader=new FileReader(filePath);

                    BufferedReader br=new BufferedReader(fileReader);

                    String intermediate="",output="";
                    while((intermediate=br.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //now we have to set thr output string to the textarea
                    textArea.setText(output);
                }
                catch (FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==save){
            JFileChooser fileChooser=new JFileChooser("C:");

            int chooseOption=fileChooser.showSaveDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create a filewith chosen directory path and file name
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                  try{
                      FileWriter fileWriter=new FileWriter(file);
                      BufferedWriter br=new BufferedWriter( fileWriter);

                      textArea.write(br);
                      br.close();
                  }
                  catch(IOException ioException){
                      ioException.printStackTrace();
                  }
            }
        }
        if(actionEvent.getSource()==newW){
            TextEditor textEditor=new TextEditor();
        }

    }
    public static void main(String[] args) {
            TextEditor x=new TextEditor();
    }
}