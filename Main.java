package CRUD_Project;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	public static void main(String args[]) throws SQLException {
		
		System.out.println(" which kind of CRUD:");
		System.out.println("1-->insert ");
		System.out.println("2-->select ");
		System.out.println("3-->update ");
		System.out.println("4-->delete ");
		
		Scanner scanner1, scanner2;
		scanner1 = new Scanner(System.in);
		scanner2 = new Scanner(System.in);
		Services services=new Services(scanner1, scanner2);
		System.out.println(services);
	}
	

}
