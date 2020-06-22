package test;

import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class addStudentController implements Initializable {

    private TextField studList;



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
        //Check if every Textfield is not empty
        if(nameField.getText().equals("")||numberField.getText().equals("")||companyField.getText().equals("")){
            System.out.println("is it possible to learn this power?");
        }
        //Create Student based on User Inputs (java experience and Kurs, not yet implemented)
        else{
        Student newbie= new Student(nameField.getText(), numberField.getText(), "TINF19AI2", companyField.getText(), 10);
        MainHandler.mainWindowController1.insertInTable(newbie);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
