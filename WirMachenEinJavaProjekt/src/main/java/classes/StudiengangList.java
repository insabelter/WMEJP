package classes;

import DataBase.DataManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudiengangList extends DataArrayList<Studiengang>{
    @Override
    public void fillArray(DataManager dm) throws SQLException {
        Statement stmt = dm.conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDIENGANG");

        //add objects to List
        while (rsBasicInformation.next()) {
             Studiengang newStudiengang = new Studiengang(
                    rsBasicInformation.getInt("STUDIENGANG_ID"),
                    rsBasicInformation.getString("NAME"),
                    rsBasicInformation.getString("KUERZEL"),
                    rsBasicInformation.getString("STUDIENGANGSLEITER"),
                    null,
                    new ArrayList<Studienrichtung>());

            //find Fakultaet
            int fakultaet_id = rsBasicInformation.getInt("FAKULTAET_ID");
            Fakultaet fakultaet = dm.lsFakultaet.getById(fakultaet_id);
            fakultaet.addSlave(newStudiengang);
            //Error Handling:
            if(newStudiengang.getFakultaet() == null){
                System.out.println("Der Studiengang hat immer noch keine Fakultaet!");
            }

            list.add(newStudiengang);
        }
    }
}
