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
    @FXML SplitMenuButton splitMenu;
    @FXML
    private void logOut(ActionEvent event){
        event.consume();
        //System.out.println("Logged out!");
        Stage stage = (Stage) splitMenu.getScene().getWindow();
        Stage nstage = new Stage();
        LoginScreen login = new LoginScreen();
        try{
            login.start(nstage);
            stage.close();
        }
        catch(Exception ex){
                ex.printStackTrace();
        }
    }
}