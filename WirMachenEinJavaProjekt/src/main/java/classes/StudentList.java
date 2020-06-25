package classes;

import DataBase.DataManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentList extends DataArrayList<Student>{
    @Override
    public void fillArray(DataManager dm, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDENT");

        //add objects to List
        while(rsBasicInformation.next()){
            Student newStudent = new Student(
                    rsBasicInformation.getInt("MATRIKEL_NR"),
                    rsBasicInformation.getString("VORNAME"),
                    rsBasicInformation.getString("NACHNAME"),
                    rsBasicInformation.getInt("Javakenntnisse"),
                    null, //wird bei addSlave befüllt
                    null); //wird bei addSlave befüllt

            //find Kurs
            int kurs_id = rsBasicInformation.getInt("KURS_ID");
            Kurs kurs = dm.lsKurs.getById(kurs_id);
            kurs.addSlave(newStudent);
            //Error Handling:
            if(newStudent.getKurs() == null){
                System.out.println("The student still has no Class!");
            }

            //find Firma
            int firma_id = rsBasicInformation.getInt("FIRMA_ID");
            Firma firma = dm.lsFirma.getById(firma_id);
            firma.addSlave(newStudent);
            //Error Handling:
            if(newStudent.getFirma() == null){
                System.out.println("The student still has no Company!");
            }

            list.add(newStudent);
        }
    }
}
