package HandlersControllers;

import classes.Kurs;
import classes.Student;
import classes.Studienrichtung;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class courseManagerController implements Initializable {
    @FXML
    private TableView<Student> studList;

    @FXML
    private ComboBox<Kurs> courseCombobox;

    @FXML
    private Label courseroomLabel;

    @FXML
    private TextField newCourseroomTextfield;

    @FXML
    private Button addButton;

    @FXML
    private TextField jahrgangTextfield;

    @FXML
    private TextField numberTextfield;

    @FXML
    private ComboBox<Studienrichtung> studienrichtungComboBox;

    @FXML
    void displayCourse(){
        studList.getItems().clear();
        if(courseCombobox.getSelectionModel().getSelectedItem()!=null){
            studList.getItems().addAll(courseCombobox.getSelectionModel().getSelectedItem().getStudents());
            courseroomLabel.setText(courseCombobox.getSelectionModel().getSelectedItem().getRaum());
        }


    }

    @FXML
    void newCourse(){
        List<Student> l =new LinkedList<>();
        if(jahrgangTextfield.getText().equals("")||numberTextfield.getText().equals("")|| newCourseroomTextfield.getText().equals("")||Pattern.matches("[0-9]+", numberTextfield.getText()) == false||Pattern.matches("[0-9]+", jahrgangTextfield.getText()) == false){
            JOptionPane.showMessageDialog(new Frame(),"Eingabe überprüfen! Jahrgang und Nummer müssen numerisch sein und kein Feld darf leer sein!");
        }
        else if(jahrgangTextfield.getText().length()>3||numberTextfield.getText().length()>5) {
            JOptionPane.showMessageDialog(new Frame(),"Jahrgang darf maximal 2 Zeichen betragen und Nummer maximal 4.");
        }
        else{

            Kurs newCourse = new Kurs(1, Integer.parseInt(jahrgangTextfield.getText()), Integer.parseInt(numberTextfield.getText()), newCourseroomTextfield.getText(), "", studienrichtungComboBox.getSelectionModel().getSelectedItem(), l);
            newCourse.createName();
            try{
            MainHandler.dm.insert(newCourse,MainHandler.conn);
            }catch(SQLException s){
                s.printStackTrace();
            }
            MainHandler.mainWindowController1.updateAll();
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));
        studList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("vorname"));
        studList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nachname"));

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
        studienrichtungComboBox.setConverter(new StringConverter<Studienrichtung>() {
            @Override
            public String toString(Studienrichtung studienrichtung) {
                if (studienrichtung==null) return "";
                else{return studienrichtung.getName();}
            }

            @Override
            public Studienrichtung fromString(String s) {
                return null;
            }
        });
        System.out.println(MainHandler.dm.lsKurs.list);
        courseCombobox.getItems().addAll(MainHandler.dm.lsKurs.list);
        studienrichtungComboBox.getItems().addAll(MainHandler.dm.lsStudienrichtung.list);
        courseCombobox.getSelectionModel().selectFirst();
        studienrichtungComboBox.getSelectionModel().selectFirst();
        courseroomLabel.setText(courseCombobox.getSelectionModel().getSelectedItem().getRaum());
        studList.getItems().clear();
        studList.getItems().addAll(courseCombobox.getSelectionModel().getSelectedItem().getStudents());


    }

    public void updateAll(){
        courseCombobox.getItems().clear();
        studienrichtungComboBox.getItems().clear();
        courseCombobox.getItems().addAll(MainHandler.dm.lsKurs.list);
        studienrichtungComboBox.getItems().addAll(MainHandler.dm.lsStudienrichtung.list);
        studienrichtungComboBox.getSelectionModel().selectFirst();


    }

    public void insertInTable(Student... student){
        studList.getItems().addAll(student);
    }
}
