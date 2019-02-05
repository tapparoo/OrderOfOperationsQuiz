package orderofoperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOfOperations {
	private final Map<String, String[]> ANSWER_KEY = new HashMap<>();
	private Map<String, String[]> shuffledAnswers = new HashMap<>();
	
	{
		ANSWER_KEY.put("A", new String[]{"Paren/Array/dot", "(), [], ."});
		ANSWER_KEY.put("B", new String[]{"Post-unary", "i++, i--"});
		ANSWER_KEY.put("C", new String[]{"Pre-unary/Other unary", "++i, --i, +, -"});
		ANSWER_KEY.put("D", new String[]{"Mult/Div/Mod", "*, /, %"});
		ANSWER_KEY.put("E", new String[]{"Add/Subtract", "+, -"});
		ANSWER_KEY.put("F", new String[]{"Relational", "<, >, <=, >="});
		ANSWER_KEY.put("G", new String[]{"Equal/not equal", "==, !="});
		ANSWER_KEY.put("H", new String[]{"Bitwise AND", "&"});
		ANSWER_KEY.put("I", new String[]{"Bitwise inclusive OR", "|"});
		ANSWER_KEY.put("J", new String[]{"Short-circuit AND", "&&"});
		ANSWER_KEY.put("K", new String[]{"Short-circuit OR", "||"});
		ANSWER_KEY.put("L", new String[]{"Ternary", "boolean ? expr1 : expr2"});
		ANSWER_KEY.put("M", new String[]{"Assignment", "=, +=, -=, *=, /=, %="});
		ANSWER_KEY.put("N", new String[]{"Lambda", "->"});
	}
	
	public OrderOfOperations() {
		shuffleList();
	}
	
	public String judgeAnswers(String[] guesses) {
		StringBuilder sb = new StringBuilder();
		int numCorrect = 0;
		boolean allCorrect = true;
		char c = 65;
		
		
		for(String key : guesses) {
			if(!shuffledAnswers.get(key)[0].equals(ANSWER_KEY.get("" + c++)[0])) {
				allCorrect = false;
			}else {
				numCorrect++;
			}
		}
		if(allCorrect) {
			sb.append("\nYou got all of them correct.  Nice job!\n");
		}
		else {
			sb.append("\nYou got ");
			sb.append(numCorrect);
			sb.append(" out of " );
			sb.append(ANSWER_KEY.keySet().size());
			sb.append(" correct.  Keep Trying!\n");
		}
		return sb.toString();
	}

	public void shuffleList() {
		List<String> keys = new ArrayList<>(ANSWER_KEY.keySet());
		Collections.shuffle(keys);
		char c = 65;
		
		for (String key : keys) {
			shuffledAnswers.put("" + c++, ANSWER_KEY.get(key));
		}
	}
	
	public Map<String, String[]>  getShuffledAnswers() {
		return shuffledAnswers;
	}
	
	public Map<String, String[]> getOriginalMap() {
		return ANSWER_KEY;
	}
}
