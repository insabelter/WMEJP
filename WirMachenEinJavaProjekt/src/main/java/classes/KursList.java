package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KursList extends DataArrayList<Kurs>{
    @Override
    public void fillArray(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM KURS");
        //ResultSet rsStudienrichtungen = stmt.executeQuery("SELECT KURS.KURS_ID, STUDIENRICHTUNG.NAME FROM KURS JOIN STUDIENRICHTUNG ON KURS.STUDIENRICHTUNG_ID = STUDIENRICHTUNG.STUDIENRICHTUNG_ID");

        //add objects to List
        while(rsBasicInformation.next()){

            list.add(new Kurs(
                    rsBasicInformation.getInt("KURS_ID"),
                    rsBasicInformation.getInt("JAHRGANG"),
                    rsBasicInformation.getInt("NUMMER"),
                    rsBasicInformation.getString("RAUM"),
                    rsBasicInformation.getString("EMAILVERTEILER"),
                    //toDo: Funktion, die zu Studienrichtung_id die Studienrichtung zurückgibt
                    null, //findStudienrichtung(rsStudienrichtungen.getInt("STUDIENRICHTUNG_ID"))
                    new ArrayList<Student>()));
        }
    }
}
