
public class Sentence {
	public String[] words;
	public double score;
	
	public Sentence(String phrase) {
		this.words = phrase.split(" ");
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (String word : words) {
			s.append(word);
			s.append(" ");
		}
		return s.toString();
	}
	
	public boolean hasQuestionMark() {
		for(String word : words) {
			if(word.endsWith("?") == true)
				return true;
		}
		return false;
	}
	
	public boolean hasKeyWords(String[] keyWords) {
		for(String word : words) {
			for(String keyWord : keyWords)
				if(word.contains(keyWord)) return true;
		}
		return false;
	}
	
	public double compareTo(Sentence s) {
		return this.score - s.score;
	}
}
