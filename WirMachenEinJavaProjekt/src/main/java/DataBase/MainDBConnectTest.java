package DataBase;

import classes.Student;

public class MainDBConnectTest {

    public static void main(String[] args) {
        DataManager dm = new DataManager();
        DatabaseDatamanager dbm = new DatabaseDatamanager();
        dbm.initializeAll(dm);
        //dbm.update("Fakultaet","name='prepared'","fakultaet_id>20");
        //dbm.delete(dm.lsStudent.list.get(1),dm);

    }
}
