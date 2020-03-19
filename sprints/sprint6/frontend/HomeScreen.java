//HomeScreen.java post-login user homepage
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

public class HomeScreen extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		root.getStylesheets().add("Style.css");
		
		primaryStage.setTitle("Instrument Recognition Software");
		primaryStage.setScene(new Scene(root, 1180, 700));
		primaryStage.show();
	}
	
    private static double xOffset = 0;
    private static double yOffset = 0;

    public BorderPane window(Stage primaryStage)
    {
        BorderPane border = new BorderPane();
        HBox hbox= addHBox();
        border.setTop(hbox);
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
	public static void main(String[] args) {
		launch(args);
	}
	
}