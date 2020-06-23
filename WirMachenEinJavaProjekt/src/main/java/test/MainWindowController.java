package test;

import classes.Student;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainWindowController implements Initializable{
    Stage addStudentStage=null;
    private boolean initialized;
    //main
    @FXML
    private TableView<Student> studList;

    @FXML
    private GridPane buttons;

    @FXML
    private Button addStudent;

    @FXML
    private Button editData;

    @FXML
    private Button delStudent;

    @FXML
    private Button addCourse;

    @FXML
    void addCourseOnClick(ActionEvent event) {

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
    void delStudentOnClick(ActionEvent event) {
        //delete all selected
        studList.getItems().removeAll(studList.getSelectionModel().getSelectedItems());
    }

    @FXML
    void edit(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //allow multiple selection
        studList.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        //initialize the Table in main window to properly take Student objects as rows
        studList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        studList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("kurs"));
        studList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("matnr"));
        studList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("company"));
        studList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("javaxp"));


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


}

