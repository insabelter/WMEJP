package classes;

import DataBase.DataManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentList extends DataArrayList<Student>{
    @Override
    public void fillArray(DataManager dm) throws SQLException {
        Statement stmt = dm.conn.createStatement();
        ResultSet rsBasicInformation = stmt.executeQuery("SELECT * FROM STUDENT");

        //add objects to List
        while(rsBasicInformation.next()){
            Student newStudent = new Student(
                    rsBasicInformation.getInt("MATRIKEL_NR"),
                    rsBasicInformation.getString("VORNAME"),
                    rsBasicInformation.getString("NACHNAME"),
                    rsBasicInformation.getInt("JAVAKENNTNISSE"),
                    null, //wird bei addSlave befüllt
                    rsBasicInformation.getString("FIRMA")); //rsBasicInformation.getString("FIRMA"),

            //find Kurs
            int kurs_id = rsBasicInformation.getInt("KURS_ID");
            Kurs kurs = dm.lsKurs.getById(kurs_id);
            kurs.addSlave(newStudent);
            //Error Handling:
            if(newStudent.getKurs() == null){
                System.out.println("The student still has no Class!");
            }

            list.add(newStudent);
        }
    }
}
