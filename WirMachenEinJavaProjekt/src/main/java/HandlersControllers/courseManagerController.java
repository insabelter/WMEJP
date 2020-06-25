package HandlersControllers;

import classes.Kurs;
import classes.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class courseManagerController implements Initializable {
    @FXML
    private TableView<Student> studList;

    @FXML
    private ComboBox<Kurs> courseCombobox;

    @FXML
    private Label courseroomLabel;

    @FXML
    private TextField newCourseTextfield;

    @FXML
    private TextField newCourseroomTextfield;

    @FXML
    private Button addButton;

    @FXML
    void displayCourse(){
        studList.getItems().clear();
        studList.getItems().addAll(courseCombobox.getSelectionModel().getSelectedItem().getStudents());
    }

    @FXML
    void newCourse(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));
        studList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("lastname"));
        studList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("firstname"));
        courseCombobox.setConverter(new StringConverter<Kurs>() {
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
        courseCombobox.getItems().addAll(MainHandler.dm.lsKurs.list);

    }
}
