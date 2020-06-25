package HandlersControllers;


import classes.Kurs;
import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class editStudentController implements Initializable {

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

    void setInformation(Student s){
        nameField.setText(s.getVorname());
        lastNameField.setText(s.getNachname());
        courseDropdown.getSelectionModel().select(s.getKurs());
        numberField.setText(String.valueOf(s.getMatrikelnummer()));
        //companyField.setText(s.getFirma().getName());
        javaSlider.setValue(s.getJavakenntnisse());

    }

    @FXML
    void saveStudent(){

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
}

