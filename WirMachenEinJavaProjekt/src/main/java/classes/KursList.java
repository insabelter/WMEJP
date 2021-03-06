package classes;

import DataBase.DataManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KursList extends DataArrayList<Kurs>{
    @Override
    public void fillArray(DataManager dm, Connection conn) throws SQLException {
        list.clear();
        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM KURS");
        //ResultSet rsStudienrichtungen = stmt.executeQuery("SELECT KURS.KURS_ID, STUDIENRICHTUNG.NAME FROM KURS JOIN STUDIENRICHTUNG ON KURS.STUDIENRICHTUNG_ID = STUDIENRICHTUNG.STUDIENRICHTUNG_ID");

        //add objects to List
        while(rsBasicInformation.next()){
            Kurs newKurs = new Kurs(
                    rsBasicInformation.getInt("KURS_ID"),
                    rsBasicInformation.getInt("JAHRGANG"),
                    rsBasicInformation.getInt("NUMMER"),
                    rsBasicInformation.getString("RAUM"),
                    rsBasicInformation.getString("EMAILVERTEILER"),
                    null,
                    new ArrayList<Student>()); //wird bei Studentenerstellung befüllt

            //find Studienrichtung
            int studienrichtung_id = rsBasicInformation.getInt("STUDIENRICHTUNG_ID");
            Studienrichtung studienrichtung = dm.lsStudienrichtung.getById(studienrichtung_id);
            studienrichtung.addSlave(newKurs);
            //Error Handling:
            if(newKurs.getStudienrichtung() == null){
                System.out.println("Der Kurs hat immer noch keine Studienrichtung!");
            }

            list.add(newKurs);
        }
    }
}
