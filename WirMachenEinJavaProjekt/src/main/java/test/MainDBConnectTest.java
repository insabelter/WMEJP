package test;
import classes.Fakultaet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainDBConnectTest {

    public static void main(String[] args) {
        DatabaseDatamanager dbm = new DatabaseDatamanager();
        List<Fakultaet> lsFakultaet = dbm.getAll();
        for (Fakultaet x:lsFakultaet) {
            System.out.println(x);
        }
    }
}
