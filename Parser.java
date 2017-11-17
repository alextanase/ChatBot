
public class Parser {
	
	private Sentence sentence;
	public static Sentence[] informations = DataBase.getInformations();
	public static String[] keyWords = { "ce", "cand" , "cum" , "care" };
	static double threshold = 0.3;
	
	public Parser(Sentence sentence) {
		this.sentence = sentence;
		
	}
	public void setThreshold(int value) {
		threshold = value;
	}
	
	public static boolean isQuestion(Sentence sentence) {	
		
		if(sentence.hasQuestionMark()) return true;
		
		if(sentence.hasKeyWords(keyWords)) return true;
		return false;
	}
	
	public void pickProbableInformation() {
		int answers = 0;
		computeScore(informations);
		sortInformationBySore();
		if(informations[0].score < threshold)	
			System.out.println("Nu am gasit un raspuns");
		else
			System.out.println("Raspuns: " + informations[answers]);
		while(answers < 3) {
			answers++;
			if(informations[answers].score < threshold)	break;
			System.out.println("Informatie relevanta: " + informations[answers]);
		};
		
	}
	
	public void sortInformationBySore() {
		for(int i = 0; i < informations.length; i++) 
			for(int j = i + 1; j < informations.length; j++) 
				if(informations[i].compareTo(informations[j]) < 0) {
					Sentence aux = informations[i];
					informations[i] = informations[j];
					informations[j] = aux;
				}
	}
	
	public void computeScore(Sentence[] information) {
		for(int i = 0; i < informations.length; i++)
			informations[i].score /= sentence.words.length;
	}
	public void analyzeInformations() {
		for(int i = 0; i < informations.length; i++)
				for(String sWord : sentence.words) {
					if(informations[i].toString().toLowerCase().contains(sWord.toLowerCase()) == true)
						informations[i].score++;
		}
		pickProbableInformation();
	}
	
}
