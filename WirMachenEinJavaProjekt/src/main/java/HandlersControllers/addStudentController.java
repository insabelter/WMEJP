package HandlersControllers;

import classes.Firma;
import classes.Kurs;
import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
            JOptionPane.showMessageDialog(new Frame(),"Textfelder die nicht optional sind dürfen nicht leer sein.","Fehler",JOptionPane.ERROR_MESSAGE);
        }
        //Create Student based on User Inputs
        else{
          Student newbie= new Student(numberField.getText(),nameField.getText(),lastNameField.getText(),((int)javaSlider.getValue())/10,courseDropdown.getSelectionModel().getSelectedItem(),new Firma(3, "Atos", "Peter", "123", new ArrayList<Student>()));

          MainHandler.mainWindowController1.insertInTable(newbie);
        }

    }

    @FXML
    void newCourse(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add options to dropdown

        courseDropdown.getItems().addAll(MainHandler.dm.lsKurs);
        courseDropdown.getSelectionModel().selectFirst();
    }


}
