package HandlersControllers;

import classes.Kurs;
import classes.Student;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;


import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class addStudentController implements Initializable {


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
    private TextField companyField;

    @FXML
    private Label javakenntnisseLabel;

    @FXML
    void newStudent(ActionEvent event) throws SQLException {
        //Check if every Textfield is not empty
        if(nameField.getText().equals("")||companyField.getText().equals("")||lastNameField.getText().equals("")|| !Pattern.matches("[äöüÄÖÜa-zA-Z- ]+",nameField.getText())||!Pattern.matches("[äöüÄÖÜa-zA-Z- ]+",companyField.getText())||!Pattern.matches("[äöüÄÖÜa-zA-Z- ]+",lastNameField.getText())){
            JOptionPane.showMessageDialog(new Frame(),"Textfelder die nicht optional sind dürfen nicht leer sein. Außerdem nur alphabetisch.","Fehler",JOptionPane.ERROR_MESSAGE);
        }
        else if(nameField.getText().length()>101||lastNameField.getText().length()>101){
            JOptionPane.showMessageDialog(new Frame(),"Vorname und Nachname dürfen maximal 100 Zeichen lang sein.","Fehler",JOptionPane.ERROR_MESSAGE);
        }
        //Create Student based on User Inputs
        else{
            Student newbie;
            try{
                newbie= new Student(1,nameField.getText(),lastNameField.getText(),((int)javaSlider.getValue())/10,courseDropdown.getSelectionModel().getSelectedItem(),companyField.getText());
            }catch(NumberFormatException n){
                return;
            }
            if (!MainHandler.mainWindowController1.isDuplicate(newbie)){

                MainHandler.dm.insert(newbie, MainHandler.conn);
                MainHandler.dm.lsKurs.fillArray(MainHandler.dm,MainHandler.conn);
                for (Kurs k:MainHandler.dm.lsKurs.list) {
                    k.createName();
                }
                MainHandler.dm.lsStudent.fillArray(MainHandler.dm,MainHandler.conn);
                MainHandler.mainWindowController1.updateAll();

            }
            else{JOptionPane.showMessageDialog(new Frame(),"Die Matrikelnummer "+newbie.getMatrikelnummer()+" ist schon vergeben.");}
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.hide();
        }

    }

    @FXML
    void displayNumber(){
        int i = ((int)javaSlider.getValue())/10;
        javakenntnisseLabel.setText(String.valueOf(i));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add options to dropdown
        int i = ((int)javaSlider.getValue())/10;
        javakenntnisseLabel.setText(String.valueOf(i));
        courseDropdown.setConverter(new StringConverter<Kurs>() {
            @Override
            public String toString(Kurs k) {
                if (k==null) return "";
                else{return k.getName();}
            }

            @Override
            public Kurs fromString(String s) {
                return null;
            }
        });


        courseDropdown.getItems().addAll(MainHandler.dm.lsKurs.list);
        courseDropdown.getSelectionModel().selectFirst();
    }

    public void updateAll(){
        courseDropdown.getItems().clear();
        courseDropdown.getItems().addAll(MainHandler.dm.lsKurs.list);
        courseDropdown.getSelectionModel().selectFirst();
    }


}
