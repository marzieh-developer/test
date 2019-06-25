package crudMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainCrud {

	public static void main(String[] args) {

		Scanner scanner1, scanner2;
		String jdbcUrl = "jdbc:postgresql://localhost:5432/jdbc_db";
		String username = "postgres";
		String password = "12349";
		Connection connection = null;
		Statement statement = null;
		PreparedStatement pStatement = null;
		scanner1 = new Scanner(System.in);
		scanner2 = new Scanner(System.in);
		System.out.println(" which kind of CRUD:");
		System.out.println("1-->insert ");
		System.out.println("2-->select ");
		System.out.println("3-->update ");
		System.out.println("4-->delete ");

		for (int i = 1; i <= 4; i++) {
			int a = scanner1.nextInt();
			if (a > 4 || a < 1) 
				System.out.println("please inter number of list!!!");
			else {
			
			try {
				connection = DriverManager.getConnection(jdbcUrl, username, password);
				String sqlUpdate = "UPDATE employee SET name=? ,family=? , email=? WHERE id=1234567 ";
				String sqlSelect = "SELECT * FROM employee ";
				String sqlDelete = "DELETE FROM  employee WHERE id=?";
				String sqlInsert = "INSERT INTO employee(id ,name,family,email) " + "VALUES (? , ? , ? , ? )";
				statement = connection.createStatement();
				switch (a) {
				case 1:
					Insert(pStatement, connection, sqlInsert, scanner2);
					break;
				case 2:
					Select(statement, sqlSelect);
					break;
				case 3:
					Update(pStatement, connection, sqlUpdate, scanner2);
					break;
				case 4:
					Delete(pStatement, connection, sqlDelete, scanner2);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		  } 
		} ///////////////// end of loop
		scanner1.close();
		scanner2.close();
	}
	///////////////////////////////////////////////// **INSERT**
	private static void Insert(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2)
			throws SQLException {
		pStatement = connection.prepareStatement(query);
		System.out.println("inter id:");
		int id = Integer.valueOf(scanner2.nextLine());
		System.out.println("inter name: ");
		String name = scanner2.nextLine();
		System.out.println("inter family ");
		String family = scanner2.nextLine();
		System.out.println("inter email ");
		String email = scanner2.nextLine();
		pStatement.setInt(1, id);
		pStatement.setString(2, name);
		pStatement.setString(3, family);
		pStatement.setString(4, email);
		int rowI = pStatement.executeUpdate();
		if (rowI > 0) {
			System.out.println("insetr is succesfully");
		} else {
			System.out.println("insetr is not succesfully");
		}
		System.out.println("select another number:");
	}
	/////////////////////////////////////////////// **DELETE**
	private static void Delete(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2)
			throws SQLException {
		pStatement = connection.prepareStatement(query);
		System.out.println("inter id: ");
		int id = Integer.valueOf(scanner2.nextLine());
		pStatement.setInt(1, id);
		int rowD = pStatement.executeUpdate();
		if (rowD > 0) {
			System.out.println("user deleted");
		}
		System.out.println("select another number:");
	}
	//////////////////////////////////////////////// **UPDATE**
	private static void Update(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2)
			throws SQLException {
//		System.out.println("inter  which id you want update:");
//		int id = Integer.valueOf(scanner2.nextLine());
		System.out.println("inter name: ");
		String name = scanner2.nextLine();
		System.out.println("inter family ");
		String family = scanner2.nextLine();
		System.out.println("inter email ");
		String email = scanner2.nextLine();		
		pStatement = connection.prepareStatement(query);		
		pStatement.setString(1, name);
		pStatement.setString(2, family);
		pStatement.setString(3, email);
//		pStatement.setInt(4, id);
		int rowU = pStatement.executeUpdate();
		if (rowU > 0) {
		System.out.println("An existing user was updated successfully!");
		}
	}
	////////////////////////////////////////////////// **SELECT**
	private static void Select(Statement pStatement, String query) throws SQLException {

		ResultSet rowS = pStatement.executeQuery(query);
		while (rowS.next()) {
			System.out.println(
					rowS.getInt(1) + "," + rowS.getString(2) + "," + rowS.getString(3) + "," + rowS.getString(4));

		}
		System.out.println("select another number:");
	}
}
