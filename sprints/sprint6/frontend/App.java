//LoginScreen.java to handle login
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*; 
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class App extends Application //implements Observer
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
		root.getStylesheets().add("Style.css");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        primaryStage.setScene(root); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    public static void main(String[] args) 
    {

        Application.launch(args);
    }
    
}
