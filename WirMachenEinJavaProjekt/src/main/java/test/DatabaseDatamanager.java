package test;

import classes.Fakultaet;
import classes.Studiengang;
import classes.Studienrichtung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDatamanager {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./wmejpTest";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    Connection conn = null;
    Statement stmt = null;

    List<Fakultaet> lsFakultaet = new ArrayList<>();
    List<Studiengang> lsStudiengang = new ArrayList<>();
    List<Studienrichtung> lsStudienrichtung = new ArrayList<>();

    public List<Studiengang> getAll(){


        ResultSet rs =null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Fakultaeten
            rs = getAllX("fakultaet");
            //add objekts to List
            while(rs.next()){
                lsFakultaet.add(new Fakultaet(rs.getInt("FAKULTAET_ID"),rs.getString("name")));
            }

            //studiengang
            rs = getAllX("studiengang");
            //add objekts to List
            while(rs.next()){
                Studiengang i = new Studiengang(rs.getInt("studiengang_ID"),rs.getString("name"),rs.getString("kuerzel"),rs.getString("Studiengangsleiter"));

                for(Fakultaet x: lsFakultaet){
                    if(x.getId()==rs.getInt("fakultaet_ID")){
                        x.addSlave(i);
                        i.setFakultaet(x);

                        System.out.println(i.getFakultaet());

                    }
                }
                lsStudiengang.add(i);
            }

            //studienrichtung
            rs = getAllX("studienrichtung");
            //add objekts to List
            while(rs.next()){
                lsStudienrichtung.add(new Studienrichtung(rs.getInt("Studienrichtung_id"),rs.getString("name")));
            }




            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();

            //return list
            return lsStudiengang;
        } catch(SQLException se) {

            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        return lsStudiengang;
    }
    private ResultSet getAllX(String klass) throws SQLException { //"SELECT * FROM "+klass+";"  -> gibt Resultset aud der DB zurück.'
        stmt = conn.createStatement();
        String sql =  "SELECT * FROM "+klass+";";
        return stmt.executeQuery(sql);
    }
}
