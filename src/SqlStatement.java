import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlStatement {
    private final Connection connection;
    private final String SelectAllQuery;
    private final String SelectOneQuery;
    private final String InsertQuery;
    private final String UpdateQuery;
    private final String DeleteQuery;

    public SqlStatement() throws SQLException {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Parzival",
                    "root",
                    "0307");

            this.SelectAllQuery = "SELECT * FROM Testing";
            this.SelectOneQuery = "SELECT * FROM Testing WHERE id = ?";
            this.InsertQuery = "INSERT INTO Testing(id, name, description) VALUES(?,?,?)";
            this.UpdateQuery = "UPDATE Testing SET name = ?, description = ? WHERE id = ?";
            this.DeleteQuery = "DELETE FROM Testing WHERE id = ?";

            System.out.println("Successfully Connected to Database");
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    public void SelectAllCreateStatement() throws SQLException {
        Statement stmt = connection.createStatement();
        String SelectAllQuery = "SELECT * FROM Testing";
        ResultSet rs = stmt.executeQuery(SelectAllQuery);
        printRow(rs);
    }

    public void SelectAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.SelectAllQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        this.printRow(resultSet);
    }

    public void SelectOne(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.SelectOneQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        this.printRow(resultSet);
    }

    public void Insert(int id, String name, String description) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.InsertQuery);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, description);

        preparedStatement.executeUpdate();
    }

    public void Update(int id, String name, String description) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.UpdateQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, description);
        preparedStatement.setInt(3, id);

        preparedStatement.executeUpdate();
    }

    public void Delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.DeleteQuery);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    private void printRow(ResultSet resultset) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultset.getMetaData();
        int count = resultSetMetaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            System.out.print("|" + resultSetMetaData.getColumnName(i) + "|");
        }
        System.out.println("\n");
        while (resultset.next()) {
            for (int i = 1; i <= count; i++) {
                System.out.print("|" + resultset.getString(i) + "|");
            }
            System.out.println();
        }
    }
}
