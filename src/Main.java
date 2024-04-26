
public class Main {
    public static void main(String[] args) {
        try {
            SqlStatement sqlStatement = new SqlStatement();
//            sqlStatement.Insert(2, "Art3mis", "testing 2");
            sqlStatement.SelectOne(1);
            sqlStatement.SelectAll();
            sqlStatement.closeConnection();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}