package test;

public class MainDBConnectTest_f {

    public static void main(String[] args) {
        Fakultaet_f f1= new Fakultaet_f();
        f1.fill();
        System.out.println(f1.lsstudiengang.get(1));
    }
}
