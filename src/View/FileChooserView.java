//package View;
//
//import javafx.stage.FileChooser;
//import java.awt.Desktop;
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
///**
// * Created by Negin Nafissi on 28-10-2016.
// */
//public class FileChooserView extends Application {
//
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        FileChooser chooser=new FileChooser();
//
//        chooser.setFileSelectionMode(chooser.FILES_AND_DIRECTORIES);
//        chooser.setDialogTitle("Choose a file");
//        chooser.setCurrentDirectory(new File(System.getProperties().getProperty("user.home")));
//        {
//            public boolean accept(final File f)
//            {
//                return f.isDirectory()|| file.getAbsolutePath().endsWith(".xls");
//            }
//
//            public String getDescription()
//            {
//                return "Excel files (*.xls)";
//            }
//        });
//
//        int returnVal1=chooser.showSaveDialog(this);
//        if (returnVal1 == JFileChooser.APPROVE_OPTION)
//        {
//
//
//            file1 = chooser.getSelectedFile();
//
//            if(!file1.exists())
//            {
//
//                FileOutputStream fileOut =  new FileOutputStream(file1);
//                hwb.write(fileOut);
//                fileOut.close();
//                System.out.println("\n Your Excel file has been generated!");
//                JOptionPane.showMessageDialog(this,"File Created.");
//            }
//            else if(file1.exists())
//            {
//                int res=JOptionPane.showConfirmDialog(this,"File already exists.Do you wish to overwrite?");
//                if(res == JOptionPane.YES_OPTION)
//                {
//                    FileOutputStream fileOut =  new FileOutputStream(file1);
//                    hwb.write(fileOut);
//                    fileOut.close();
//                    System.out.println("\n Your Excel file has been generated!");
//                    JOptionPane.showMessageDialog(this,"File Created.");
//                }
//                else if(res == JOptionPane.NO_OPTION)
//                {
//                    int returnVal2=chooser.showSaveDialog(this);
//                    if (returnVal2 == JFileChooser.APPROVE_OPTION)
//                    {
//
//                        File file2 = chooser.getSelectedFile();
//                        if(!file2.exists())
//                        {
//
//                            FileOutputStream fileOut =  new FileOutputStream(file2);
//                            hwb.write(fileOut);
//                            fileOut.close();
//                            System.out.println("\n Your Excel file has been generated!");
//                            JOptionPane.showMessageDialog(this,"File Created.");
//                        }
//
//                    }
//                }
//                else if (res == JOptionPane.CANCEL_OPTION)
//                {
//                    JOptionPane.showMessageDialog(this, "User cancelled operation.");
//                }
//            }
//        }
//
//
//    }
//}
//
