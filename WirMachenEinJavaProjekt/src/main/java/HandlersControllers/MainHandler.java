package HandlersControllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import test.DataManager;
import test.DatabaseDatamanager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainHandler extends Application {


    Parent root = null;
    static MainWindowController mainWindowController1;
    static DataManager dm = new DataManager();
    DatabaseDatamanager dbm = new DatabaseDatamanager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        dbm.initializeAll(dm);
        buildScene();

        //Set root as scene and show
        stage.setScene(new Scene(root));
        stage.show();
    }

    void buildScene(){
        //initialize loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));

        //load resource into root
        try {
            root= loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get Controller of loader for later access/passing back values
        mainWindowController1 = loader.getController();




    }





}
