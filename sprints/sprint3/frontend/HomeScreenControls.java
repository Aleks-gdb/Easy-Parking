import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class HomeScreenControls{
    
    @FXML
    private void logOut(ActionEvent event){
        event.consume();
        System.out.println("Logged out!");
    }
}