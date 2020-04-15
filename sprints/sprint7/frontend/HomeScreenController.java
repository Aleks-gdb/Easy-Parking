//HomeScreenControls.java controller class for HomeScreen.fxml
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.EventHandler;
import javafx.scene.text.*; 
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;
import javafx.util.*;

public class HomeScreenController{
    @FXML BorderPane mainScene;
    @FXML MenuButton userMenu;
    private static double xOffset = 0;
    private static double yOffset = 0;

    @FXML
    public void initialize(){
        userMenu.setText(LoginScreenController.getUsername());
    }
    @FXML
    private void exit(MouseEvent event){
        event.consume();
        Stage stage = (Stage) mainScene.getScene().getWindow();
        try{
            Connect main = new Connect("close");
        }catch(Exception ex){
            System.out.println("Oops!");
        }
        stage.close();
    }
    @FXML
    private void maximize(MouseEvent event){
        Stage stage = (Stage) mainScene.getScene().getWindow();
        if(stage.isFullScreen())
        stage.setFullScreen(false);
        else
        stage.setFullScreen(true);
    }
    @FXML
    private void minimize(MouseEvent event){
        Stage stage = (Stage) mainScene.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    private void press(MouseEvent event) {
            xOffset = ((Stage)mainScene.getScene().getWindow()).getX() - event.getScreenX();
            yOffset = ((Stage)mainScene.getScene().getWindow()).getY() - event.getScreenY();
    }
    @FXML
    private void drag(MouseEvent event) {
        ((Stage)mainScene.getScene().getWindow()).setX(event.getScreenX() + xOffset);
        ((Stage)mainScene.getScene().getWindow()).setY(event.getScreenY() + yOffset);
    }
    @FXML
    private void logOut(ActionEvent event){
        event.consume();
        try{
            Scene root = FXMLLoader.load(getClass().getResource("screens/LoginScreen.fxml"));
            ((Stage)mainScene.getScene().getWindow()).setScene(root);
        }
        catch(Exception ex){
            ex.printStackTrace();            
        }
    }
}