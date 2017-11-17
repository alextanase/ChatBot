import java.util.Scanner;

public class Bot {
	
	boolean greeted = false;
	Scanner sc = new Scanner(System.in);
	
	public void start() {
		
		try {
			System.out.println("Username: ");
			String username = sc.nextLine();
			
			System.out.println("Mail: ");
			String mail = sc.nextLine();
			
			System.out.println("Utilizator logat: " + username + ", Email: " + mail);
			System.out.println("Bot -> Buna ziua si bine ati venit. Cu ce va pot ajuta?");
			interact();
		}catch(Exception e) {
			System.out.println("Logare nereusita!");
			e.printStackTrace();
		}
		
		
	}
	
	public void checkForGreeting(String sentence) {
		String[] greetings = {"salut", "buna", "neata"};
		String[] words = sentence.split(" ");
		for(String word : words) {
			for(int i = 0; i < greetings.length; i++)
				if(word.equalsIgnoreCase(greetings[i])) {
					greeted = true;
					//System.out.println("<Bot>Am fost salutat!");
				}
		}
	}
	
	public void interact() {
		String userMessage = sc.nextLine();
		String[] phrase =  userMessage.split("[.!]");
		checkForGreeting(phrase[0]);
		while(userMessage.equalsIgnoreCase("exit") != true) {
			phrase =  userMessage.split("[.!]");
			for(int i = 0; i < phrase.length; i++) {
				Sentence s = new Sentence(phrase[i]);
				if(Parser.isQuestion(s)){
					Parser p = new Parser(s);
					p.analyzeInformations();
				}
			}
			userMessage= sc.nextLine();
		}
	}
}
