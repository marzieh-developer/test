/**
 * 
 */
package CRUD_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//////////////////interface
public interface Dao {
    void Connect(int a,  Scanner scanner2) ;
	void Select(Statement statement, String query) throws SQLException;
    void Delete(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2)  ;
    void Update(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2) ;
	void Insert(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2);
}
