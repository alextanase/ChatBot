import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
	private  String host, user, password;
	Connection connection;
	static Sentence[] informations;
	
	public DataBase(String host,String user, String password) {
		this.host = host;
		this.user = user;
		this.password = password;
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			connection = DriverManager.getConnection(host, user, password);
			System.out.println("Connected");
		}catch(Exception e) {	
			e.printStackTrace();
		}		
	}
	
	public int getNumberOfInformations() {
		try {
			Statement querry = null;
			querry = connection.createStatement();
			ResultSet rs = querry.executeQuery("select max(id) from text");
			rs.next();
			return rs.getInt(1);
		}catch(Exception e) {
			return 0;
		}
	}
	
	public void printInformations(Sentence[] informations) {
		System.out.println("Length: " + informations.length);
		for(int i = 0; i < informations.length; i++) {
			System.out.println(informations[i]);
		}
			
	}
	
	static public Sentence[] getInformations() {
		return informations;
	}
	
	public void loadDB() {
		informations = new Sentence[getNumberOfInformations()];
		try {
			int i = 0;
			Statement querry = null;
			querry = connection.createStatement();
			ResultSet rs = querry.executeQuery("select * from text");
			while(rs.next()){
				String phrase = rs.getString("sentence");
				informations[i] = new Sentence(phrase);
				i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//printInformations(informations);
	}
	
}
