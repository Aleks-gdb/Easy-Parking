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

public class MLDemo extends Application //implements Observer
{
    public void start(Stage primaryStage) throws Exception
    {
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        
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

        Button buttonMax = new Button("[]");
        buttonMax.setId("windowNav");

        Button buttonMinimize = new Button("_");
        buttonMinimize.setId("windowNav");

        buttonClose.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent e) 
            {
                Stage stage = (Stage) buttonClose.getScene().getWindow();
                String data = "close";
                try{
                Connect main = new Connect(data);
                }catch(Exception ex){
                    System.out.println("Oops!");
                }
                stage.close();
            }
        });

        buttonMax.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                Stage stage = (Stage) buttonMax.getScene().getWindow();
                stage.setFullScreen(true);
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
        StackPane.setMargin(buttonMinimize, new Insets(0, 72, 0, 0));
        StackPane.setMargin(buttonMax, new Insets(0, 36, 0, 0));
        stack.getChildren().addAll(buttonMinimize,buttonMax, buttonClose);
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
        final TextField iterNum = new TextField();
        Text textName = new Text ("Number of iterations:");
        textName.setId("text");
        //Defining the Submit button
        Button run = new Button("Run!");
        Button logOut = new Button("Log out");
        final Label message = new Label("");
        final Label warning = new Label("Warning: Average run time for one iteration is 75s.");
        warning.setTextFill(Color.rgb(210, 39, 30));
        run.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent e) 
            {
                e.consume();
                if(iterNum.getText().equals("")){
                    message.setText("You must provide an iteration number!");
                    message.setTextFill(Color.rgb(210, 39, 30));
                }
                else if(Integer.parseInt(iterNum.getText()) < 1){
                    message.setText("The number must be >= 1!");
                    message.setTextFill(Color.rgb(210, 39, 30));
                }
                else{
                    try{
                    message.setText("");
                    Connect send = new Connect(iterNum.getText());
                    }catch(Exception c){
                        message.setText("Something went wrong");
                        message.setTextFill(Color.rgb(210, 39, 30));
                    }
                }
                iterNum.clear();
            }
        });

        logOut.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                event.consume();
                Stage nstage = new Stage();
                LoginScreen login = new LoginScreen();
                try{
                    login.start(nstage);
                    p.close();
                }
                catch(Exception ex){
                        ex.printStackTrace();
                }
            }
        });
 
        //Add fields to the grid
        grid.add(textName, 0, 0);
        grid.add(iterNum, 1, 0);
        grid.add(warning, 0, 1, 2, 1);
        grid.add(run, 2, 0);
        grid.add(logOut, 1, 5);
        grid.add(message, 3, 0);
        GridPane.setHalignment(run, HPos.CENTER);
        grid.setHgap(5);
        grid.setVgap(10);
        return grid;
    }
    public static void main(String[] args) 
    {

        Application.launch(args);
    }
    
}
