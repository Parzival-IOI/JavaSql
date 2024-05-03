import TestingConnection.ConnectDatabase;

public class Main {
    public static void main(String[] args) {
        try {
            ConnectDatabase connectDatabase = new ConnectDatabase();
//            connectDatabase.createDatabase();
//            connectDatabase.createTable();
//            connectDatabase.insertData();
            connectDatabase.selectData();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}