package HandlersControllers;

import classes.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
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
import org.w3c.dom.Text;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
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
    private TableColumn<Student,String> fakultaetColumn;

    @FXML
    private TableColumn<Student,String> studienrichtungColumn;
    @FXML
    private TableColumn<Student,String> kursColumn;

    @FXML
    private Button addStudent;

    @FXML
    private Button delStudent;

    @FXML
    private Button editStudent;

    @FXML
    private Button addCourse;

    @FXML
    private Button clearFilters;

    @FXML
    private ComboBox<Fakultaet> fakultaetDropdown;

    @FXML
    private ComboBox<Studienrichtung> studienrichtungCombobox;

    @FXML
    private ComboBox<Kurs> kursCombobox;

    @FXML
    private ComboBox<Integer> javaCombobox;

    @FXML
    private TextField numberField;

    @FXML
    void clearFilters(){
        javaCombobox.getSelectionModel().clearSelection();
        kursCombobox.getSelectionModel().clearSelection();
        studienrichtungCombobox.getSelectionModel().clearSelection();
        fakultaetDropdown.getSelectionModel().clearSelection();
        studList.getItems().addAll(MainHandler.dm.lsStudent.list);
    }

    @FXML
    void manageCourses(ActionEvent event) {

        if(addCourseStage==null){
            addCourseLoader = loadAddCourseWindow();}
        if (!addCourseStage.isShowing()){
            addCourseStage.show();
        }
        courseManagerController courseManagerController = addCourseLoader.getController();

    }

    @FXML
    void addStudClick(ActionEvent event) {

        //handling the opening of addStudent window to be only openable once at a time
        if(addStudentStage==null){
            addStudentLoader = loadAddStudentWindow();}
        if (!addStudentStage.isShowing()){
            addStudentStage.show();
        }


    }

    @FXML
    void editStudent(ActionEvent event) {

        //handling the opening of addStudent window to be only openable once at a time
        if(editStudentStage==null){
            editStudentLoader = loadEditStudentWindow();}
        if (!editStudentStage.isShowing()){
            editStudentStage.show();
        }

        editStudentController editStudentController=editStudentLoader.getController();
        Student s = studList.getSelectionModel().getSelectedItem();
        editStudentController.setInformation(s);


    }


    @FXML
    void delStudentOnClick(ActionEvent event) throws SQLException {
        //delete all selected
        for (Student x: studList.getSelectionModel().getSelectedItems()) {
            MainHandler.dm.delete(x, MainHandler.conn);
        }
        studList.getItems().removeAll(studList.getSelectionModel().getSelectedItems());


    }

    @FXML
    void filterList(ActionEvent event){
        studList.getItems().clear();
        ObservableList<Student> actualList= studList.getItems();
        FilteredList<Student> filteredList = new FilteredList<>(actualList);

        studList.setItems(filteredList);

        filteredList.setPredicate(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {

                if(!(fakultaetDropdown.getSelectionModel().getSelectedItem()==null)) {
                    if (!fakultaetDropdown.getSelectionModel().getSelectedItem().getName().equals(student.getKurs().getStudienrichtung().getStudiengang().getFakultaet().getName())) {
                        return false;
                    }

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
        fakultaetColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKurs().getStudienrichtung().getStudiengang().getFakultaet().getName()));
        studienrichtungColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKurs().getStudienrichtung().getName()));
        kursColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKurs().getName()));
        //initialize the Table in main window to properly take Student objects as rows
        studList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("vorname"));
        studList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nachname"));
        studList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));
        studList.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("firma"));
        studList.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("javakenntnisse"));

        //put all student of db in table
        studList.getItems().addAll(MainHandler.dm.lsStudent.list);


        //attach stringconverters for Kurs
        kursCombobox.setConverter(new StringConverter<Kurs>() {
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
        fakultaetDropdown.setConverter(new StringConverter<Fakultaet>() {
            @Override
            public String toString(Fakultaet fakultaet) {
                if(fakultaet==null){return "-";}
                else{return fakultaet.getName();}
            }

            @Override
            public Fakultaet fromString(String s) {
                return null;
            }
        });
        studienrichtungCombobox.setConverter(new StringConverter<Studienrichtung>() {
            @Override
            public String toString(Studienrichtung studienrichtung) {
                if (studienrichtung==null){return "-";}
                else{return studienrichtung.getName();}
            }

            @Override
            public Studienrichtung fromString(String s) {
                return null;
            }
        });

        fakultaetDropdown.getItems().addAll(MainHandler.dm.lsFakultaet.list);
        studienrichtungCombobox.getItems().addAll(MainHandler.dm.lsStudienrichtung.list);
        kursCombobox.getItems().addAll(MainHandler.dm.lsKurs.list);
        javaCombobox.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10);
        delStudent.setDisable(true);
        editStudent.setDisable(true);

        addStudentLoader=loadAddStudentWindow();
        addCourseLoader=loadAddCourseWindow();
        editStudentLoader=loadEditStudentWindow();
        //later data from db, but for now test data




    }
//main end

    public boolean isDuplicate(Student s){
        boolean b = false;
        List<Integer> l= new LinkedList<>();
        studList.getItems().forEach(student -> l.add(student.getMatrikelnummer()));
        if (l.contains(s.getMatrikelnummer())){
            b=true;
        }
        return b;
    }
    public void insertInTable(Student stud){
        //add student object to Table
        studList.getItems().add(stud);
    }
    public void deleteFromTable(Student stud){
        studList.getItems().remove(stud);
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

        }
        catch (IOException e){
            e.printStackTrace();

        }
        return loader;
    }

    public void updateAll(){
        fakultaetDropdown.getItems().clear();
        studienrichtungCombobox.getItems().clear();
        kursCombobox.getItems().clear();


        fakultaetDropdown.getItems().addAll(MainHandler.dm.lsFakultaet.list);
        studienrichtungCombobox.getItems().addAll(MainHandler.dm.lsStudienrichtung.list);
        kursCombobox.getItems().addAll(MainHandler.dm.lsKurs.list);



        editStudentController ec= editStudentLoader.getController();
        ec.updateAll();
        addStudentController ac= addStudentLoader.getController();
        ac.updateAll();
        courseManagerController cc=addCourseLoader.getController();
        cc.updateAll();
    }





}

