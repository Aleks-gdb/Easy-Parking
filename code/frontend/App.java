//App.java to launch the application
import java.awt.Desktop;
import java.io.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.text.*; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class App extends Application //implements Observer
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene root = FXMLLoader.load(getClass().getResource("/screens/LoginScreen.fxml"));
		root.getStylesheets().add("/screens/Style.css");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        primaryStage.setScene(root); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    public static void main(String[] args) 
    {
        // try{
        // File server = new File("Server.exe");
        // Desktop.getDesktop().open(server);
        // }catch(Exception e)
        // {
        //     e.printStackTrace();
        // }
        Application.launch(args);
    }
    
}
