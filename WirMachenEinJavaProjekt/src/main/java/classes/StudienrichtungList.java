package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudienrichtungList extends DataArrayList<Studienrichtung>{
    @Override
    public void fillArray(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDIENRICHTUNG");

        //add objects to List
        while (rsBasicInformation.next()) {
            list.add(new Studienrichtung(
                    rsBasicInformation.getInt("STUDIENRICHTUNG_ID"),
                    rsBasicInformation.getString("NAME"),
                    rsBasicInformation.getString("KUERZEL"),
                    null,
                    null));
        }
    }
}
