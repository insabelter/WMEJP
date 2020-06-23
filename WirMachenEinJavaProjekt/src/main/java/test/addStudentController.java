package test;

import classes.Firma;
import classes.Kurs;
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
    private ComboBox<Kurs> courseDropdown;

    @FXML
    private Slider javaSlider;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField companyField;

    @FXML
    void newStudent(ActionEvent event) {
        //Check if every Textfield is not empty
        if(nameField.getText().equals("")||numberField.getText().equals("")||companyField.getText().equals("")||lastNameField.getText().equals("")){
            System.out.println("is it possible to learn this power?");

        }
        //Create Student based on User Inputs
        else{

            Student newbie= new Student(nameField.getText(),lastNameField.getText(), numberField.getText(),new Kurs(courseDropdown.getSelectionModel().getSelectedItem().toString()),new Firma(companyField.getText()),((int) javaSlider.getValue())/10);
            MainHandler.mainWindowController1.insertInTable(newbie);
        }

    }

    @FXML
    void newCourse(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add options to dropdown

        courseDropdown.getItems().addAll("TINF19AI2","TINF19AI1","KINGSIZE CHONKERS");
    }


}
