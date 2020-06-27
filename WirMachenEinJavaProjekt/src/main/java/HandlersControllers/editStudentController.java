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
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
        javakenntnisseLabel.setText(String.valueOf(s.getJavakenntnisse()));

    }

    @FXML
    void saveStudent(){
        if(nameField.getText().equals("")||companyField.getText().equals("")||lastNameField.getText().equals("")|| !Pattern.matches("[äöüÄÖÜa-zA-Z- ]+",nameField.getText())||!Pattern.matches("[äöüÄÖÜa-zA-Z- ]+",companyField.getText())||!Pattern.matches("[äöüÄÖÜa-zA-Z- ]+",lastNameField.getText())){
            JOptionPane.showMessageDialog(new Frame(),"Textfelder die nicht optional sind dürfen nicht leer sein.","Fehler",JOptionPane.ERROR_MESSAGE);
        }
        else if(nameField.getText().length()>101||lastNameField.getText().length()>101){
            JOptionPane.showMessageDialog(new Frame(),"Vorname und Nachname dürfen maximal 100 Zeichen lang sein.","Fehler",JOptionPane.ERROR_MESSAGE);
        }else{
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
            Stage stage =(Stage) addButton.getScene().getWindow();
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

