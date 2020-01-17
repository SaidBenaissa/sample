package sample;
import java.sql.*;
import java.sql.DriverManager;


import java.math.*;

public class Sample {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/employee";

    //Database credentials
    static final String USER = "root";
    static final String PASS = "nice";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // 2- Registry JDBC Driver
            Class.forName(JDBC_DRIVER);

            // 3- Open a connection
            System.out.println("Connecting to the database ... ");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 4- Executing the query
            System.out.println(" Creating a statement ... ");
            stmt = conn.createStatement();
            String sql;

            sql = "SELECT id, first, last, age FROM employees";
            ResultSet rs = stmt.executeQuery(sql);

            // 5 - Extract the data from set
            while(rs.next()) {
                // Retrieve by column_name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last =  rs.getString("last");

                // Display values
                System.out.println("ID: "+id);
                System.out.println("AGE: "+age);
                System.out.println("FIRST: "+first);
                System.out.println("LAST: "+last);
            }
            // Clean-up Env
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // handle JDBC errors
            se.printStackTrace();
        }catch (Exception e) {
            //  handle Class.forName errors
            e.printStackTrace();
        }finally {
            // Block used to close resources
            try {
                if(stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                // TODO: handle exception
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                // TODO: handle exception
                se.printStackTrace();
            }
        }
        System.out.println("GoodBye");

    }



}
