package test;
import classes.Fakultaet;
import classes.Studiengang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainDBConnectTest {

    public static void main(String[] args) {
        DatabaseDatamanager dbm = new DatabaseDatamanager();
        List<Studiengang> lsFakultaet = dbm.getAll();
        for (Studiengang x:lsFakultaet) {
            //System.out.println(x);
            //System.out.println(x.getId());
        }
    }
}
