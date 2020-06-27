package HandlersControllers;

import classes.Kurs;
import classes.Student;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
        if(nameField.getText().equals("")||companyField.getText().equals("")||lastNameField.getText().equals("")){
            JOptionPane.showMessageDialog(new Frame(),"Textfelder die nicht optional sind dürfen nicht leer sein.","Fehler",JOptionPane.ERROR_MESSAGE);
        }
        //Create Student based on User Inputs
        else{
            Student newbie=null;
            try{
                newbie= new Student(1,nameField.getText(),lastNameField.getText(),((int)javaSlider.getValue())/10,courseDropdown.getSelectionModel().getSelectedItem(),companyField.getText());
            }catch(NumberFormatException n){
                return;
            }
            if (!MainHandler.mainWindowController1.isDuplicate(newbie)){

                MainHandler.dm.insert(newbie, MainHandler.conn);
                MainHandler.dm.lsStudent.fillArray(MainHandler.dm,MainHandler.conn);
                MainHandler.mainWindowController1.updateAll();

            }
            else{JOptionPane.showMessageDialog(new Frame(),"Die Matrikelnummer "+newbie.getMatrikelnummer()+" ist schon vergeben.");}


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
