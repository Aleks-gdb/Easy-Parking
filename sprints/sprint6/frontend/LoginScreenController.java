import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
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
import javafx.scene.image.*;
import javafx.util.*;
import javafx.fxml.FXMLLoader;
import javafx.animation.FadeTransition;

public class LoginScreenController{
    @FXML Button exitButton;
    @FXML Button minButton;
    @FXML Button maxButton;
    @FXML Button loginButton;
    @FXML Button registerButton;
    @FXML TextField username;
    @FXML PasswordField password; 
    @FXML Label message;
    @FXML BorderPane mainScene;
    private static double xOffset = 0;
    private static double yOffset = 0;
    static String usernameText;

    @FXML
    private void exit(MouseEvent event){
        event.consume();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        try{
            Connect main = new Connect("close");
        }catch(Exception ex){
            System.out.println("Oops!");
        }
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

    public static String getUsername(){
        return usernameText;
    }
    @FXML
    private void login(MouseEvent event){
        event.consume();
        usernameText = username.getText();
        if(usernameText.equals("")){
            message.setText("You must provide a username!");
            message.setTextFill(Color.rgb(210, 39, 30));
        }
        else if(password.getText().equals("")){
            message.setText("You must provide a password!");
            message.setTextFill(Color.rgb(210, 39, 30));
        }
        else{
            String user = usernameText + " " + password.getText() + " login" ;
            try{
            Connect validation = new Connect(user, true);
            if (validation.exists) {
                message.setText("");
                //MLDemo main = new MLDemo();
                try{
                    Scene root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
                    ((Stage)mainScene.getScene().getWindow()).setScene(root);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }else {
                message.setText("Username or password incorrect!");
                message.setTextFill(Color.rgb(210, 39, 30));
            }
            }catch(Exception c){
                message.setText("Sorry! Something went wrong.");
                message.setTextFill(Color.rgb(210, 39, 30));
            }
        }
        password.clear();
    }
    @FXML
    private void register(MouseEvent event){
        if(username.getText().equals("")){
            message.setText("You must provide a username!");
            message.setTextFill(Color.rgb(210, 39, 30));
        }
        else if(password.getText().equals("")){
            message.setText("You must provide a password!");
            message.setTextFill(Color.rgb(210, 39, 30));
        }
        else{
            String user = username.getText() + " " + password.getText() + " register";
            try{
            Connect validation = new Connect(user, false);
            if (validation.taken) {
                message.setText("That username is taken!");
                message.setTextFill(Color.rgb(210, 39, 30));
            }else {
                message.setText(username.getText() + " has been registered!");
                message.setTextFill(Color.rgb(0, 255, 0));
            }
            }catch(Exception c){
                message.setText("Sorry! Something went wrong.");
                message.setTextFill(Color.rgb(210, 39, 30));
            }
        }
    }
}