package DataBase;

public class MainDBConnectTest {

    public static void main(String[] args) {
        DataManager dm = new DataManager();
        DatabaseDatamanager dbm = new DatabaseDatamanager();
        dbm.initializeAll(dm);

        
    }
}
