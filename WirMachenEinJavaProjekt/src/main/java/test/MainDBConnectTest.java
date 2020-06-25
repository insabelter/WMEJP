package test;
import classes.Fakultaet;
import classes.Studiengang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainDBConnectTest {

    public static void main(String[] args) {
        DataManager dm = new DataManager();
        DatabaseDatamanager dbm = new DatabaseDatamanager();
        dbm.initializeAll(dm);

        
    }
}
