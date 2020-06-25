package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudiengangList extends DataArrayList<Studiengang>{
    @Override
    public void fillArray(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDIENGANG");

        //add objects to List
        while (rsBasicInformation.next()) {
            list.add(new Studiengang(
                    rsBasicInformation.getInt("STUDIENGANG_ID"),
                    rsBasicInformation.getString("NAME"),
                    rsBasicInformation.getString("KUERZEL"),
                    rsBasicInformation.getString("STUDIENGANGSLEITER"),
                    null,
                    null));
        }
    }
}
