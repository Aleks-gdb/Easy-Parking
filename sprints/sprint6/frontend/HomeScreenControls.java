//HomeScreenControls.java controller class for HomeScreen.fxml
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.scene.text.*; 
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

public class HomeScreenControls{
    @FXML Button exitButton;
    @FXML Button minButton;
    @FXML Button maxButton;

    @FXML
    private void exit(MouseEvent event){
        event.consume();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void maximize(MouseEvent event){
        Stage stage = (Stage) maxButton.getScene().getWindow();
        if(stage.isFullScreen())
        stage.setFullScreen(false);
        else
        stage.setFullScreen(true);
    }
    @FXML
    private void minimize(MouseEvent event){
        Stage stage = (Stage) minButton.getScene().getWindow();
        stage.setIconified(true);
    }
    // private static double xOffset = 0;
    // private static double yOffset = 0;
    // @FXML Stage mainStage;

    // @FXML
    // private void onMouseDrag(MouseEvent event) {
    //     xOffset = mainStage.getX() - event.getScreenX();
    //     yOffset = mainStage.getY() - event.getScreenY();
    // }
    // @FXML
    // private void onMousePress(MouseEvent event) {
    //     xOffset = mainStage.getX() - event.getScreenX();
    //     yOffset = mainStage.getY() - event.getScreenY();
    // }
    // private void logOut(ActionEvent event){
    //     event.consume();
    //     //System.out.println("Logged out!");
    //     Stage stage = (Stage) splitMenu.getScene().getWindow();
    //     Stage nstage = new Stage();
    //     LoginScreen login = new LoginScreen();
    //     try{
    //         login.start(nstage);
    //         stage.close();
    //     }
    //     catch(Exception ex){
    //             ex.printStackTrace();
    //     }
    // }
}