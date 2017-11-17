import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	static int index = 0;
	static String host = "jdbc:mysql://localhost:3306/chatbotdb";
	static Scanner read = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		DataBase db  = new DataBase(host, "root", "root");
		db.connect();
		db.loadDB();
		
		
		Bot bot = new Bot();
		bot.start();
		
		
		try {
			db.connection.close();
			read.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
