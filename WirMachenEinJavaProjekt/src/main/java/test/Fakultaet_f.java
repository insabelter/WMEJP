package test;

import classes.Studiengang;

import java.sql.*;
import java.util.List;

public class Fakultaet_f extends DBAcces_f {
    private int id;
    private String name;
    public List<Studiengang> lsstudiengang;
    private String verantwortlicher;
    private String execstmt = "SELECT *FROM STUDIENGANG";

    @Override
    public void fill() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            stmt = conn.createStatement();
            String sql = execstmt;
            rs = stmt.executeQuery(sql);
            System.out.println(rs);
            while(rs.next()){
                lsstudiengang.add(new Studiengang(rs.getInt("studiengang_id"),rs.getString("name"),rs.getString("kuerzel"),rs.getString("studiengangsleiter")));
            }


            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();


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
    }
}