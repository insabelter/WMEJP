package HandlersControllers;


import classes.Kurs;
import classes.Student;
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
import java.util.ResourceBundle;

public class editStudentController implements Initializable {
    Student currentStudent=null;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ComboBox<Kurs> courseDropdown;

    @FXML
    private TextField numberField;

    @FXML
    private TextField companyField;

    @FXML
    private Slider javaSlider;

    @FXML
    private Button addButton;
    @FXML
    private Label javakenntnisseLabel;

    void setInformation(Student s){
        currentStudent = s;
        nameField.setText(s.getVorname());
        lastNameField.setText(s.getNachname());
        courseDropdown.getSelectionModel().select(s.getKurs());
        numberField.setText(String.valueOf(s.getMatrikelnummer()));
        companyField.setText(s.getFirma());
        javaSlider.setValue(s.getJavakenntnisse()*10);

    }

    @FXML
    void saveStudent(){
        Kurs oldCourse = currentStudent.getKurs();
        Kurs newCourse = courseDropdown.getSelectionModel().getSelectedItem();
        oldCourse.getStudents().remove(currentStudent);
        newCourse.getStudents().add(currentStudent);
        currentStudent.setKurs(newCourse);
        currentStudent.setFirma(companyField.getText());
        currentStudent.setJavakenntnisse(((int)javaSlider.getValue())/10);
        currentStudent.setVorname(nameField.getText());
        currentStudent.setNachname(lastNameField.getText());
        try{
            MainHandler.dm.update(currentStudent,MainHandler.conn);
            MainHandler.mainWindowController1.updateAll();
        }catch(SQLException s){
            s.printStackTrace();
            JOptionPane.showMessageDialog(new Frame(),"Fehler beim Speichern");
        }




    }
    @FXML
    void displayNumber(){
        int i = ((int)javaSlider.getValue())/10;
        javakenntnisseLabel.setText(String.valueOf(i));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

