package classes;

import DataBase.DataManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudienrichtungList extends DataArrayList<Studienrichtung>{
    @Override
    public void fillArray(DataManager dm) throws SQLException {
        Statement stmt = dm.conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDIENRICHTUNG");

        //add objects to List
        while (rsBasicInformation.next()) {
            Studienrichtung newStudienrichtung = new Studienrichtung(
                    rsBasicInformation.getInt("STUDIENRICHTUNG_ID"),
                    rsBasicInformation.getString("NAME"),
                    rsBasicInformation.getString("KUERZEL"),
                    null,
                    new ArrayList<Kurs>());

            //find Studiengang
            int studiengang_id = rsBasicInformation.getInt("STUDIENGANG_ID");
            Studiengang studiengang = dm.lsStudiengang.getById(studiengang_id);
            studiengang.addSlave(newStudienrichtung);
            //Error Handling:
            if(newStudienrichtung.getStudiengang() == null){
                System.out.println("Die Studienrichtung hat immer noch keinen Studiengang!");
            }

            list.add(newStudienrichtung);
        }
    }
}
