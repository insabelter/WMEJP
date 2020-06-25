package HandlersControllers;

import classes.Firma;
import classes.Kurs;
import classes.Student;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import test.DataManager;
import test.DatabaseDatamanager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class MainWindowController implements Initializable{
    Stage addStudentStage=null;
    Stage addCourseStage=null;
    Stage editStudentStage=null;
    FXMLLoader addStudentLoader;
    FXMLLoader editStudentLoader;
    FXMLLoader addCourseLoader;



    //main
    @FXML
    private TableView<Student> studList;

    @FXML
    private TableColumn<Student,String> fakultaet;

    @FXML
    private TableColumn<Student,String> studienrichtung;

    @FXML
    private Button addStudent;

    @FXML
    private Button delStudent;

    @FXML
    private Button editStudent;

    @FXML
    private Button addCourse;

    @FXML
    private ComboBox fakultaetDropdown;

    @FXML
    private ComboBox studienrichtungCombobox;

    @FXML
    private ComboBox<Kurs> kursCombobox;

    @FXML
    private ComboBox javaCombobox;

    @FXML
    private TextField numberField;


    @FXML
    void manageCourses(ActionEvent event) {

        if(addCourseStage==null){
            addCourseLoader = loadAddCourseWindow();}
        else if (!addCourseStage.isShowing()){
            addCourseStage.show();
        }
    }

    @FXML
    void addStudClick(ActionEvent event) {

        //handling the opening of addStudent window to be only openable once at a time
        if(addStudentStage==null){
            addStudentLoader = loadAddStudentWindow();}
        else if (!addStudentStage.isShowing()){
            addStudentStage.show();
        }


    }

    @FXML
    void editStudent(ActionEvent event) {

        //handling the opening of addStudent window to be only openable once at a time
        if(editStudentStage==null){
            editStudentLoader = loadEditStudentWindow();}
        else if (!editStudentStage.isShowing()){
            editStudentStage.show();
        }

        editStudentController editStudentController=editStudentLoader.getController();
        Student s = studList.getSelectionModel().getSelectedItem();
        editStudentController.setInformation(s);


    }


    @FXML
    void delStudentOnClick(ActionEvent event) {
        //delete all selected
        studList.getItems().removeAll(studList.getSelectionModel().getSelectedItems());
    }

    @FXML
    void filterList(){
        FilteredList<Student> filteredList= new FilteredList<>(studList.getItems());
        //wip
        filteredList.setPredicate(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                if(!student.getKurs().getStudienrichtung().getStudiengang().getFakultaet().getName().equals(fakultaetDropdown.getSelectionModel().getSelectedItem().toString())&&!fakultaetDropdown.getSelectionModel().getSelectedItem().toString().equals("Alle")){
                    return false;
                }
                if(!student.getKurs().getStudienrichtung().getName().equals(studienrichtungCombobox.getSelectionModel().getSelectedItem().toString())&&!studienrichtungCombobox.getSelectionModel().getSelectedItem().toString().equals("Alle")){
                    return false;
                }


                return true;
            }
        });

    }



    @FXML
    void greyOut(){
        if (studList.getSelectionModel().getSelectedItems().size()<=0){
            delStudent.setDisable(true);
            editStudent.setDisable(true);
        }
        else if(studList.getSelectionModel().getSelectedItems().size()==1){
            delStudent.setDisable(false);
            editStudent.setDisable(false);
        }
        else if(studList.getSelectionModel().getSelectedItems().size()>1){
            delStudent.setDisable(false);
            editStudent.setDisable(true);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //allow multiple selection
        studList.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        //initialize the Table in main window to properly take Student objects as rows
        try{
            //fakultaet.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKurs().getStudienrichtung().getStudiengang().getFakultaet().getName()));
            //studienrichtung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKurs().getStudienrichtung().getName()));

        }catch (NullPointerException n){
            System.out.println("Fakultät und studienrichtung noch gewollt leer!");
        }

        //initialize the Table in main window to properly take Student objects as rows
        studList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("vorname"));
        studList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nachname"));
        studList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("kurs"));
        studList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));
        studList.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("firma"));
        studList.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("javakenntnisse"));

        //put all student of db in table
        studList.getItems().addAll(MainHandler.dm.lsStudent);

        kursCombobox.setConverter(new StringConverter<Kurs>() {
            @Override
            public String toString(Kurs k) {
                if (k==null) return "";
                else{return k.getRaum();}
            }

            @Override
            public Kurs fromString(String s) {
                return null;
            }
        });

        fakultaetDropdown.getItems().addAll("Alle","Technik","Wirtschaft","Gesundheit");
        studienrichtungCombobox.getItems().addAll("Alle","Informatik");
        kursCombobox.getItems().addAll(MainHandler.dm.lsKurs);
        javaCombobox.getItems().addAll("Alle","0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        delStudent.setDisable(true);
        editStudent.setDisable(true);

        //later data from db, but for now test data




    }
//main end

    public void insertInTable(Student stud){
        //add student object to Table
        studList.getItems().add(stud);
    }
    private FXMLLoader loadAddStudentWindow(){
        FXMLLoader loader=null;
        try{

            //load addStudent window
            loader = new FXMLLoader(getClass().getResource("/addStudent.fxml"));
            Parent root= loader.load();

            //show addStudent window
            addStudentStage = new Stage();
            addStudentStage.setScene(new Scene(root));
            addStudentStage.show();


        }
        catch (IOException e){

            e.printStackTrace();

        }
        return loader;
    }

    private FXMLLoader loadEditStudentWindow(){
        FXMLLoader loader=null;
        try{

            //load addStudent window
            loader = new FXMLLoader(getClass().getResource("/editStudent.fxml"));
            Parent root= loader.load();

            //show addStudent window
            editStudentStage = new Stage();
            editStudentStage.setScene(new Scene(root));
            editStudentStage.show();



        }
        catch (IOException e){
            e.printStackTrace();

        }
        return loader;
    }

    private FXMLLoader loadAddCourseWindow(){
        FXMLLoader loader=null;
        try {

            //load addStudent window
            loader = new FXMLLoader(getClass().getResource("/courseManager.fxml"));
            Parent root = loader.load();

            //show addStudent window
            addCourseStage = new Stage();
            addCourseStage.setScene(new Scene(root));
            addCourseStage.show();


        }
        catch (IOException e){
            e.printStackTrace();

        }
        return loader;
    }





}

