package test;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    Parent root = null;
    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) {
        buildScene();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public void buildScene(){
        try {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
