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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class MainWindowController implements Initializable{
    Stage addStudentStage=null;
    public static List<Kurs> alleKurse = new ArrayList<Kurs>();
    public static List<Firma> alleFirmen = new ArrayList<Firma>();
    Stage addCourseStage=null;
    Stage editStudentStage=null;



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
    private ComboBox kursCombobox;

    @FXML
    private ComboBox javaCombobox;

    @FXML
    private TextField numberField;


    @FXML
    void manageCourses(ActionEvent event) {
        if(addCourseStage==null){
            loadAddCourseWindow();}
        else if (!addCourseStage.isShowing()){
            addCourseStage.show();
        }
    }

    @FXML
    void addStudClick(ActionEvent event) {

        //handling the opening of addStudent window to be only openable once at a time
        if(addStudentStage==null){
        loadAddStudentWindow();}
        else if (!addStudentStage.isShowing()){
            addStudentStage.show();
        }


    }

    @FXML
    void editStudClick(ActionEvent event) {

        //handling the opening of addStudent window to be only openable once at a time
        if(editStudentStage==null){
            loadEditStudentWindow();}
        else if (!editStudentStage.isShowing()){
            editStudentStage.show();
        }


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
    void editStudent(){

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

        //fakultaet.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKurs().getStudienrichtung().getStudiengang().getFakultaet().getName()));
        //studienrichtung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKurs().getStudienrichtung().getName()));

        studList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("firstname"));
        studList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lastname"));
        studList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("kurs"));
        studList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));
        studList.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("firma"));
        studList.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("javakenntnisse"));
        fakultaetDropdown.getItems().addAll("Alle","Technik","Wirtschaft","Gesundheit");
        studienrichtungCombobox.getItems().addAll("Alle","Informatik");
        kursCombobox.getItems().addAll("Alle","TINF19AI2","TINF19AI1");
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
    private void loadAddStudentWindow(){
        try{

            //load addStudent window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addStudent.fxml"));
            Parent root= loader.load();

            //show addStudent window
            addStudentStage = new Stage();
            addStudentStage.setScene(new Scene(root));
            addStudentStage.show();



        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

    private void loadEditStudentWindow(){
        try{

            //load addStudent window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/editStudent.fxml"));
            Parent root= loader.load();

            //show addStudent window
            addCourseStage = new Stage();
            addCourseStage.setScene(new Scene(root));
            addCourseStage.show();



        }
        catch (IOException e){
            e.printStackTrace();

        }
    }

    private void loadAddCourseWindow(){
        try{

            //load addStudent window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/courseManager.fxml"));
            Parent root= loader.load();

            //show addStudent window
            addCourseStage = new Stage();
            addCourseStage.setScene(new Scene(root));
            addCourseStage.show();



        }
        catch (IOException e){
            e.printStackTrace();

        }
    }





}

