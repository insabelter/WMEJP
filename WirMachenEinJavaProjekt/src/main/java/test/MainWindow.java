package test;

import classes.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {
    Parent root = null;
    static mainController mainController1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
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
        mainController1 = loader.getController();




    }





}
