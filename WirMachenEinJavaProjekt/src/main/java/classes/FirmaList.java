package classes;

import DataBase.DataManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FirmaList extends DataArrayList<Firma>{
    @Override
    public void fillArray(DataManager dm, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM FIRMA");

        //add objects to List
        while(rsBasicInformation.next()){
            list.add(new Firma(
                    rsBasicInformation.getInt("FIRMA_ID"),
                    rsBasicInformation.getString("NAME"),
                    rsBasicInformation.getString("ANSPRECHPARTNER"),
                    rsBasicInformation.getString("ADRESSE"),
                    new ArrayList<Student>())); //wird bei Studentenerstellung befüllt
        }
    }
}
