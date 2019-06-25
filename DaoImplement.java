package CRUD_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/////////////////implementation
public class DaoImplement implements Dao {
	static String jdbcUrl = "jdbc:postgresql://localhost:5432/jdbc_db";
	static String username = "postgres";
	static String password = "12349";
	static Connection connection = null;
	static PreparedStatement pStatement = null;
	static Statement statement = null;

	static String sqlUpdate = "UPDATE employee SET name=? ,family=? , email=? WHERE id=456 ";
	static String sqlSelect = "SELECT * FROM employee ";
	static String sqlDelete = "DELETE FROM  employee WHERE id=?";
	static String sqlInsert = "INSERT INTO employee(id ,name,family,email) " + "VALUES (? , ? , ? , ? )";

	@Override
	public void Connect(int a, Scanner scanner2) {
		if (a < 1 || a >= 5) {
			System.out.println("please enter number of list!!!");

		} else {
			try {
				connection = DriverManager.getConnection(jdbcUrl, username, password);
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	}

	@Override
	public void Select(Statement statement, String query) {

		try {
			ResultSet rowS = statement.executeQuery(query);
			while (rowS.next()) {
				System.out.println(
						rowS.getInt(1) + "," + rowS.getString(2) + "," + rowS.getString(3) + "," + rowS.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("select another number:");

	}

	@Override
	public void Delete(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2) {
		try {
			pStatement = connection.prepareStatement(query);
			System.out.println("inter id: ");
			int id = Integer.valueOf(scanner2.nextLine());
			pStatement.setInt(1, id);
			int rowD = pStatement.executeUpdate();
			if (rowD > 0) {
				System.out.println("user deleted");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("select another number:");
	}

	@Override
	public void Update(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2) {
		System.out.println("inter name: ");
		String name = scanner2.nextLine();
		System.out.println("inter family ");
		String family = scanner2.nextLine();
		System.out.println("inter email ");
		String email = scanner2.nextLine();
		try {
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, name);
			pStatement.setString(2, family);
			pStatement.setString(3, email);
			int rowU = pStatement.executeUpdate();
			if (rowU > 0) {
				System.out.println("An existing user was updated successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void Insert(PreparedStatement pStatement, Connection connection, String query, Scanner scanner2) {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("select another number:");
	}

}
