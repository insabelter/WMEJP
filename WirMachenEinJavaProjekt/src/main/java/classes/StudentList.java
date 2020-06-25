package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentList extends DataArrayList<Student>{
    @Override
    public void fillArray(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDENT");

        //add objects to List
        while(rsBasicInformation.next()){
            //find Kurs

            //find Firma

            list.add(new Student(
                    rsBasicInformation.getInt("MATRIKEL_NR"),
                    rsBasicInformation.getString("VORNAME"),
                    rsBasicInformation.getString("NACHNAME"),
                    rsBasicInformation.getInt("Javakenntnisse"),
                    null,
                    null));
        }
    }
}
