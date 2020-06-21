package test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class MyController {

//main objects
    @FXML
    private AnchorPane foundation;

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
        Stage stage2 = new Stage();
        Parent scene2 = null;
        try {

            scene2 = FXMLLoader.load(getClass().getClassLoader().getResource("addStudent.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage2.setScene(new Scene(scene2));
        stage2.show();


    }

    @FXML
    void delStudentOnClick(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }
//main objects end

//addStudent objects
    @FXML
    private TextField nameField;

    @FXML
    private TextField courseField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField companyField;

    @FXML
    private Slider javaSlider;

    @FXML
    private Button addButton;

    @FXML
    void newStudent(ActionEvent event) {
        if(nameField.getText().equals("")||courseField.getText().equals("")||numberField.getText().equals("")||companyField.getText().equals("")){
            JOptionPane.showInputDialog("Kein Feld kann leer sein!");
        }





    }





}
