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

public class LoginScreenController{
    @FXML Button exitButton;
    @FXML Button minButton;
    @FXML Button maxButton;
    @FXML TextField username;
    @FXML PasswordField password; 

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

    public String getUsername() {
        return username.getText();
    }
    
    public String getPassword(){
        return password.getText();
    }
    @FXML
    private void login(MouseEvent event){
        //System.out.println(username.getText());
        if(username.getText().isEmpty()) {
           System.out.println("ugh");
        }
        if(password.getText().isEmpty()) {
            System.out.println("ughh");
        }

        // if(username.getText().equals("")){
        //     // message.setText("You must provide a username!");
        //     // message.setTextFill(Color.rgb(210, 39, 30));
        //     System.out.println("Oops username");
        // }
        // else if(password.getText().equals("")){
        //     // message.setText("You must provide a password!");
        //     // message.setTextFill(Color.rgb(210, 39, 30));
        //     System.out.println("Oops password");
        // }
        // else{
        //     String user = username.getText() + " " + password.getText() + " login" ;
        //     try{
        //     Connect validation = new Connect(user, true);
        //     if (validation.exists) {
        //         //message.setText("");
        //         HomeScreen main = new HomeScreen();
        //         //MLDemo main = new MLDemo();
        //         // try{
        //         //     main.start(p);}
        //         // catch(Exception ex){
        //         //     ex.printStackTrace();
        //         // }
        //     }else {
        //         // message.setText("Username or password incorrect!");
        //         // message.setTextFill(Color.rgb(210, 39, 30));
        //         System.out.println("Oops username or pass incorrect");
        //     }
        //     }catch(Exception c){
        //         // message.setText("Sorry! Something went wrong.");
        //         // message.setTextFill(Color.rgb(210, 39, 30));
        //         System.out.println("Oops something went wrong");
        //     }
        // }
        // password.clear();
    }
    @FXML
    private void register(MouseEvent event){
        System.out.println(password.getText());
        // if(username.getText().equals("")){
        //     // message.setText("You must provide a username!");
        //     // message.setTextFill(Color.rgb(210, 39, 30));
        //     System.out.println("Oops username");
        // }
        // else if(password.getText().equals("")){
        //     // message.setText("You must provide a password!");
        //     // message.setTextFill(Color.rgb(210, 39, 30));
        //     System.out.println("Oops password");
        // }
        // else{
        //     String user = username.getText() + " " + password.getText() + " register";
        //     try{
        //     Connect validation = new Connect(user, false);
        //     if (validation.taken) {
        //         // message.setText("That username is taken!");
        //         // message.setTextFill(Color.rgb(210, 39, 30));
        //         System.out.println("Oops username taken");
        //     }else {
        //         // message.setText(username.getText() + " has been registered!");
        //         // message.setTextFill(Color.rgb(0, 255, 0));
        //         System.out.println("user registered");
        //     }
        //     }catch(Exception c){
        //         // message.setText("Sorry! Something went wrong.");
        //         // message.setTextFill(Color.rgb(210, 39, 30));
        //         System.out.println("Oops usomething went wrong");
        //     }
        // }
    }
}