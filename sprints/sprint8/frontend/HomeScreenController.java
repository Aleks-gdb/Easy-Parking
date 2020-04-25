//HomeScreenControls.java controller class for HomeScreen.fxml
import java.io.File;
import java.util.*;
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
import javafx.scene.media.*;
import javafx.collections.*;

public class HomeScreenController{
    @FXML BorderPane mainScene;
    @FXML MenuButton userMenu;
    @FXML Button addFilesButton;
    @FXML ListView fileListView;
    private static double xOffset = 0;
    private static double yOffset = 0;
    //private List<Media> songs;
    private Set<String> songs = new HashSet<String>();
    //public static final ObservableList songNames = new FXCollections.observableArrayList();
    ObservableList<String> names = FXCollections.observableArrayList();
    ObservableList<String> toDelete;
    @FXML
    public void initialize(){
        userMenu.setText(LoginScreenController.getUsername());
        fileListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
    @FXML
    private void addFilesToList(MouseEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add a song to your list");
        Stage stage = new Stage();
        List<File> list = fileChooser.showOpenMultipleDialog(stage);
        String fileName;
        if (list != null) {
            for (File file : list) {
                //String filePath = file.getAbsolutePath();
                fileName = file.getName();
                //String fileExtension = FilenameUtils.getExtension(filePath);
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                //System.out.println(filePath);
                //System.out.println(fileName);
                if(fileExtension.equals(".wav") || fileExtension.equals(".mp3"))
                {
                    //String path = filePath.replaceAll("\\\\", "/");
                    //Media song = new Media("file:/" + path);
                    //songs.add(song);
                    //System.out.println(fileName);
                    songs.add(fileName);
                    // songNames.add(fileName);
                    // fileListView.setItems(songNames);
                }
                else
                {
                    //throw an error
                }
            }
            names.setAll(songs);
            fileListView.setItems(names);
        }
        
    }
    @FXML
    private void deleteFilesFromList(MouseEvent event){
        toDelete = fileListView.getSelectionModel().getSelectedItems();
        toDelete.forEach((s) -> {
            songs.remove(s);
        });
        names.setAll(songs);
        fileListView.setItems(names);
    }
    /*Playing songs:
    Mediaplayer mediaPlayer = new MediaPlayer(song); song is a Media object
    MediaPlayer.setAutoPlay(true);
    https://www.javatpoint.com/media-with-javafx
    */
}