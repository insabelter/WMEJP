package test;

import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class addStudentController implements Initializable {

    private TextField studList;



    @FXML
    private Button addButton;

    @FXML
    private ComboBox courseDropdown;

    @FXML
    private Slider javaSlider;

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
        //Create Student based on User Inputs
        else{

            Student newbie= new Student(nameField.getText(), numberField.getText(), courseDropdown.getSelectionModel().getSelectedItem().toString(), companyField.getText(),  ((int) javaSlider.getValue())/10);
            MainHandler.mainWindowController1.insertInTable(newbie);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add options to dropdown
        courseDropdown.getItems().addAll("TINF19AI2","TINF19AI1","KINGSIZE CHONKERS");
    }
}
