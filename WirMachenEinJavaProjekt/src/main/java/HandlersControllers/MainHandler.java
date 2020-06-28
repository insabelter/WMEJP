package HandlersControllers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DataBase.DataManager;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainHandler extends Application {


    Parent root = null;
    static MainWindowController mainWindowController1;
    static DataManager dm = new DataManager();
    static Connection conn = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        try{
            //Create DB Connection
            Class.forName("org.h2.Driver");
            System.out.println(System.getProperty("user.dir"));
            conn = DriverManager.getConnection("jdbc:h2:"+System.getProperty("user.dir")+"/src/main/resources/wmejpTest","sa","");
            dm.initializeAll(conn);
            buildScene();

            //Set root as scene and show
            stage.setScene(new Scene(root));
            stage.setOnHiding(windowEvent -> mainWindowController1.closeAll());
            stage.setMaxHeight(1000);
            stage.setMaxWidth(1500);
            stage.setMinWidth(1450);
            stage.setMinHeight(1000);

            stage.show();
        }catch(SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void buildScene(){
        //initialize loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));

        //load resource into root
        try {
            root= loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get Controller of loader for later access/passing back values
        mainWindowController1 = loader.getController();

    }





}
