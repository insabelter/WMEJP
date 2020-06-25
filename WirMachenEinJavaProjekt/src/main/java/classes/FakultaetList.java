package classes;


import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FakultaetList extends DataArrayList<Fakultaet>{
    //list: List of type Fakultaet

    @Override
    public void fillArray(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM FAKULTAET");

        //add objects to List
        while(rsBasicInformation.next()){
            list.add(new Fakultaet(
                    rsBasicInformation.getInt("FAKULTAET_ID"),
                    rsBasicInformation.getString("NAME"),
                    null, //rsBasicInformation.getString("VERANTWORTLICHER"),//ToDo!! Add to DB
                    null));
        }
    }
}
