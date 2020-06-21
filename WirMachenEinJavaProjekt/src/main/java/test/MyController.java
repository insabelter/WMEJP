package test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MyController {
    Parent window2=null;
    Stage stage2 = new Stage();
    //main
    @FXML
    private TableView<?> studList;

    @FXML
    private GridPane buttons;

    @FXML
    private Button addStudent;

    @FXML
    private Button editData;

    @FXML
    private Button delStudent;

    @FXML
    private Button addCourse;

    @FXML
    void addCourseOnClick(ActionEvent event) {

    }

    @FXML
    void addStudClick(ActionEvent event) {
        try {
            window2= FXMLLoader.load(getClass().getClassLoader().getResource("addStudent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage2.setScene(new Scene(window2));
        stage2.show();

    }

    @FXML
    void delStudentOnClick(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }
//main end

//add student
    @FXML
    private Button addButton;

    @FXML
    private Label javaSlider;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField companyField;

    @FXML
    void newStudent(ActionEvent event) {
        if(nameField.getText().equals("")||numberField.getText().equals("")||companyField.getText().equals("")){
            System.out.println("is it possible to learn this power?");
        }
    }
//add student end

}

