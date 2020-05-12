//HomeScreen.java post-login user homepage
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreen extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
		root.getStylesheets().add("Style.css");
		
		primaryStage.setTitle("Instrument Recognition Software");
		primaryStage.setScene(new Scene(root, 1180, 700));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}