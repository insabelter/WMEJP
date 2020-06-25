package HandlersControllers;

import classes.Kurs;
import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

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
            Student newbie=null;
            try{
                newbie= new Student(Integer.parseInt(numberField.getText()),nameField.getText(),lastNameField.getText(),((int)javaSlider.getValue())/10,courseDropdown.getSelectionModel().getSelectedItem(),companyField.getText());
            }catch(NumberFormatException n){
                return;
            }

            MainHandler.dbm.insert("STUDENT","vorname,nachname,javakenntnisse,kurs_id,firma_id","'"+newbie.getVorname()+"','"+newbie.getNachname()+"',"+newbie.getJavakenntnisse()+","+newbie.getKurs().getId()+","+"4");
            MainHandler.dm.lsStudent.list.add(newbie);
            MainHandler.mainWindowController1.insertInTable(newbie);
        }

    }

    @FXML
    void newCourse(){

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


}
