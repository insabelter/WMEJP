package classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DataArrayList<C extends HasID> {
    public List<C> list;

    public DataArrayList() {
        this.list = new ArrayList<>();
    }

    abstract public void fillArray(Connection conn) throws SQLException;

    public C getById(int id){
        C toReturn = null;
        for (C object: list
        ) {
            if(id == object.getId()){
                toReturn = object;
            }
        }
        return toReturn;
    }

    public void printArray(){
        for (C object: list
        ) {
            System.out.println(object.toString());
        }
        System.out.println("");
    }
}
