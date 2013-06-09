
package createapptables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * HIT8119
 * @author chandan 1785265
 */
public class CreateAppTables {

    public static void main(String[] args) {
        //dropTables();
        //createAccountTable();
        createTransactionTable();
    }
    
    public static Connection getConnection()
            throws SQLException, IOException {
        // first, need to set the driver for connection
        // for Derby
        final String url = "jdbc:derby://localhost/sun-appserv-samples";
        final String username = "APP";
        final String password = "APP";
        System.setProperty("jdbc.drivers",
                "org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection(url, username, password);
    }
    
    public static void dropTables(){
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute("drop table Transactions");
            statement.execute("drop table Account");
            System.out.println("Tables dropped successfully");
        }catch(SQLException ex){
             ex.printStackTrace();
            System.out.println("SQL exception occured while deleting tables");
        }catch(IOException ex){
             ex.printStackTrace();
            System.out.println("IO exception occured while deleting tables");
        }
    }
    
    public static void createAccountTable() {
        try {
            final String ACCOUNT = "account";
            // get connection
            Connection connection = getConnection();
            // get statement
            Statement statement = connection.createStatement();

            /**
             * execute query to create a data table with the required fields:
             */
            statement.execute("CREATE TABLE " + ACCOUNT
                    + " (accountNo CHAR(6) PRIMARY KEY,"
                    + " Name CHAR(25), email CHAR(20), balance decimal(10,2),"
                    + " Password CHAR(8), PhoneNo CHAR(10))");
            System.out.println("Account table created successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL exception occured while creating account table");
        }catch (IOException ex){
            ex.printStackTrace();
            System.out.println("IO exception occured while creating account table");
        }
    }
    
    public static void createTransactionTable() {
        try {
            final String TRANSACTIONS = "transactions";
            // get connection
            Connection connection = getConnection();
            // get statement
            Statement statement = connection.createStatement();

            /**
             * execute query to create a data table with the required fields:
             */
            statement.execute("CREATE TABLE " + TRANSACTIONS
                    + " (transactionid int not null primary key "
                    + "GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1)"
                    + ", accountTo CHAR(6),"
                    + " accountFrom CHAR(6), amount decimal(10,2), date CHAR(30),"
                    + "foreign key (accountTo) references account(accountNo),"
                    + "foreign key (accountFrom) references account(accountNo))");
            System.out.println("Transactions table created successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL exception occured while creating transaction table");
        }catch (IOException ex){
            ex.printStackTrace();
            System.out.println("IO exception occured while creating transaction table");
        }
    }
}
