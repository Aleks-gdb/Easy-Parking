import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import java.util.*;
import java.io.*;
import java.net.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.scene.text.*; 
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class LoginScreen extends Application //implements Observer
{
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        // Create a scene and place it in the stage
        Scene scene = new Scene(window(primaryStage), 1180, 700);
        scene.getStylesheets().add("Style.css");
        primaryStage.setTitle("Instrument Recognition Software"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage�

    }

    private static double xOffset = 0;
    private static double yOffset = 0;

    public BorderPane window(Stage primaryStage)
    {
        BorderPane border = new BorderPane();
        HBox hbox= addHBox();
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
                String[] data = new String[]{"close"};
                try{
                Connect main = new Connect(data, true);
                }catch(Exception ex){
                    System.out.println("Oops!");
                }
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
	    grid.setVgap(1);
        grid.setHgap(1);
        grid.setId("loginGrid");
        grid.setAlignment(Pos.CENTER);


        //Defining the Username text field for login
        final TextField username = new TextField();
        Text textName = new Text ("Name:");
        textName.setId("text");
        //Defining the Password text field for login
        final PasswordField password = new PasswordField();
        Text textPassword = new Text ("Password:");
        textPassword.setId("text");
        //Defining the Submit button
        Button login = new Button("Login");
        Hyperlink register = new Hyperlink("Register!");
        TextFlow prompt = new TextFlow( new Text("Don't have an account? "), register);
        prompt.setId("prompt");
        final Label message = new Label("");
        login.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent e) 
            {
                if (password.getText().equals("hello")) {
                    message.setText("");
                    MainScreen main = new MainScreen();
                    try{
                        main.start(p);}
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                } else {
                    message.setText("Your password is incorrect!");
                    message.setTextFill(Color.rgb(210, 39, 30));
                }
                password.clear();
            }
        });

        register.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent e) 
            {
                if(username.getText().equals("")){
                    message.setText("You must provide a username!");
                    message.setTextFill(Color.rgb(210, 39, 30));
                }
                else if(password.getText().equals("")){
                    message.setText("You must provide a password!");
                    message.setTextFill(Color.rgb(210, 39, 30));
                }
                else{
                    String[] data = new String[]{username.getText()};
                    try{
                    Connect check = new Connect(data, false);
                    if (check.taken) {
                        message.setText("That username is taken!");
                        message.setTextFill(Color.rgb(210, 39, 30));
                    } else {
                        message.setText("");
                        String[] register = new String[]{username.getText(), password.getText()};
                        Connect main = new Connect(register, true);
                    }
                    }catch(Exception c){
                        message.setText("Something went wrong");
                        message.setTextFill(Color.rgb(210, 39, 30));
                    }
                }
            }
        });

        //Add fields to the grid
        grid.add(textName, 0, 0);
        grid.add(username, 1, 0);
        grid.add(textPassword, 0, 1);
        grid.add(password, 1, 1);
        grid.add(login, 1, 3);
        grid.add(prompt, 1, 4);
        grid.add(message, 1, 5);
        GridPane.setHalignment(login, HPos.CENTER);
        return grid;
    }
    public static void main(String[] args) 
    {

        Application.launch(args);
    }
    
}
