
//HomeScreenControls.java controller class for HomeScreen.fxml
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.*;

public class HomeScreenControls{
    // @FXML SplitMenuButton splitMenu;
    // @FXML

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));

        Scene scene = new Scene(root, 300, 275);
    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

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