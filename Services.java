/**
 * 
 */
package CRUD_Project;
import java.util.Scanner;
public class Services {
	Dao mainDao = new DaoImplement() ;
	@SuppressWarnings("null")
	public Services(Scanner scanner1, Scanner scanner2) {
			while (scanner1 != null) {
//				type type = (type) en.nextElement();
				int a = scanner1.nextInt();
				mainDao.Connect(a, scanner2);
			}
			scanner1.close();
			scanner2.close();
	} 		
}