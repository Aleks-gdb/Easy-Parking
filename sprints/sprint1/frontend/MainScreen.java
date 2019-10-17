import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import java.util.*;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.scene.text.Text; 
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class MainScreen extends Application //implements Observer
{
    public void start(Stage primaryStage) throws Exception
    {
        // Create a scene and place it in the stage
        Scene scene = new Scene(window(primaryStage), 1180, 700);
        scene.getStylesheets().add("Style.css");
        primaryStage.setTitle("Instrument Recognition Software"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }

    private static double xOffset = 0;
    private static double yOffset = 0;

    public BorderPane window(Stage primaryStage)
    {
        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        border.setTop(hbox);
        border.setCenter(addGridPane(primaryStage));
        addStackPane(hbox);
        //Make the window draggable
        border.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        border.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

        return border;
    }

    public void addStackPane(HBox h){
        StackPane stack = new StackPane();
        Button buttonClose = new Button("X");
        buttonClose.setId("windowNav");

        Button buttonMinimize = new Button("_");
        buttonMinimize.setId("windowNav");

        buttonClose.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent e) 
            {
                Stage stage = (Stage) buttonClose.getScene().getWindow();
                stage.close();
            }
        });

        buttonMinimize.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                Stage stage = (Stage) buttonMinimize.getScene().getWindow();
                stage.setIconified(true);
            }
        });
        StackPane.setMargin(buttonMinimize, new Insets(0, 36, 0, 0));
        stack.getChildren().addAll(buttonMinimize, buttonClose);
        stack.setAlignment(Pos.TOP_RIGHT);

        h.getChildren().add(stack);            // Add to HBox from Example 1-2
        HBox.setHgrow(stack, Priority.ALWAYS);
    }
    
    public HBox addHBox(){
        HBox hbox = new HBox();
        hbox.setId("hbox");
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);
        hbox.setAlignment(Pos.TOP_LEFT);
        
        Text logo = new Text("Instrument Recognition Software");
        logo.setId("logo");
        
        hbox.getChildren().add(logo);

        return hbox;
    }

    public GridPane addGridPane(Stage p){
        //Creating a GridPane container
        GridPane grid = new GridPane();
	    grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        
        Text welcomeText = new Text("Welcome!");
        grid.add(welcomeText, 0, 0);
        welcomeText.setId("text");

        return grid;
    }
    public static void main(String[] args) 
    {

        Application.launch(args);
       
    }
    
}
