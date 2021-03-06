package view;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TerminalView extends View {
	private Scanner sc; // Will be used for processing data to/from players
	
	public TerminalView() {
		sc = new Scanner(System.in);
	}

	/**
	 * Print line to view
	 * @param message	is the text to be printed to view
	 */
	public void println(String message) {
		System.out.println(message);
	}
	
	
	/**
	 * Print message to view
	 * @param message
	 */
	public void print(String message) {
		System.out.println(message);
	}
	
	/**
	 * Will return information from view
	 * @return
	 */
	public String getInformation() {
		return sc.nextLine();
	}
	
	public String getInformation(String string) {
		System.out.println(string);
		return sc.nextLine();
	}
	
	
	/* (non-Javadoc)
	 * @see view.v#getYesOrNo(java.lang.String)
	 */
	@Override
	public boolean getYesOrNo(String question) {
		return getYesOrNo(question, "%a is not a valid answer");
	}
	
	/* (non-Javadoc)
	 * @see view.v#getYesOrNo(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean getYesOrNo(String question, String invalidAnswerMessage) {
		boolean answer = false;
		String sAnswer = null;
		while (sAnswer == null) {
			sAnswer = sc.nextLine();
			if (sAnswer.compareToIgnoreCase("y") == 0 ||
				sAnswer.compareToIgnoreCase("yes") == 0 ||
				sAnswer.compareToIgnoreCase("yeah") == 0 ||
				sAnswer.compareToIgnoreCase("yupp") == 0) {
				answer = true;
			} else if (sAnswer.compareToIgnoreCase("n") == 0 ||
					sAnswer.compareToIgnoreCase("no") == 0 ||
					sAnswer.compareToIgnoreCase("nah") == 0 ||
					sAnswer.compareToIgnoreCase("nop") == 0) {
				answer = false;
			} else {
				println(invalidAnswerMessage.replace("%a", sAnswer));
				sAnswer = null;
			}
		}
		return answer;
	}
	
	/* (non-Javadoc)
	 * @see view.v#getIntBetweenBoundaries(java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public int getIntBetweenBoundaries(String question, int lowerBound, int upperBound, String invalidAnswerMessage) {
		int answer = -1;
		print(question);
		while (answer == -1) {
			answer = sc.nextInt();
			if (!(answer >= lowerBound && answer <= upperBound)) {
				System.out.println(invalidAnswerMessage.replace("%a", "" + answer + ""));
				answer = -1;
			} else {
				break;
			}
		}
		return answer;
	}
	
	/* (non-Javadoc)
	 * @see view.v#getIntBetweenBoundaries(java.lang.String, int, int)
	 */
	@Override
	public int getIntBetweenBoundaries(String question, int lowerBound, int upperBound) {
		return getIntBetweenBoundaries(question, lowerBound, upperBound, "\"%a\" is not a number between " + lowerBound + " and " + upperBound + "!");
	}
	
	
	/* (non-Javadoc)
	 * @see view.v#getInt(java.lang.String)
	 */
	@Override
	public int getInt(String question) {
		return getInt(question, "\"%a\" is not a number.");
	}
	
	
	/* (non-Javadoc)
	 * @see view.v#getInt(java.lang.String, java.lang.String)
	 */
	@Override
	public int getInt(String question, String invalidAnswerMessage) {
		println(question);
		int answer = -1;
		while (answer == -1) {
			try {
				answer = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				println(invalidAnswerMessage.replace("%a", "" + answer + ""));
			}
		}
		return sc.nextInt();
	}
	
	/* (non-Javadoc)
	 * @see view.V#getMultipleChoiceAnswer(java.lang.String, java.lang.String[])
	 */
	@Override
	public String getMultipleChoiceAnswer(String question, String[] choices) {
		return getMultipleChoiceAnswer(question, choices, "Not a valid answer.", false);
	}
	
	/* (non-Javadoc)
	 * @see view.V#getMultipleChoiceAnswerIgnoreCase(java.lang.String, java.lang.String[])
	 */
	@Override
	public String getMultipleChoiceAnswerIgnoreCase(String question, String[] choices) {
		return getMultipleChoiceAnswer(question, choices, "Not a valid answer.", true);
	}
	
	/* (non-Javadoc)
	 * @see view.V#getMultipleChoiceAnswer(java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public String getMultipleChoiceAnswer(String question, String[] choices, String invalidAnswerMessage) {
		return getMultipleChoiceAnswer(question, choices, invalidAnswerMessage, false);
	}
	
	/* (non-Javadoc)
	 * @see view.V#getMultipleChoiceAnswerIgnoreCase(java.lang.String, java.lang.String[], java.lang.String)
	 */
	@Override
	public String getMultipleChoiceAnswerIgnoreCase(String question, String[] choices, String invalidAnswerMessage) {
		return getMultipleChoiceAnswer(question, choices, invalidAnswerMessage, true);
	}
	
	/**
	 * Get the user to answer a multiple choice. For example
	 * 
	 * What opponent should you like to affect?
	 *    Master
	 *    Sigurd
	 *    Hogaboga
	 *    
	 * if the user answers "Hogaboga" then String("Hogaboga") will be returned
	 * 
	 * @param question The question
	 * @param choices Choices
	 * @param invalidAnswerMessage What message to print on invalid answer. Use %a to reference the answer of the player.
	 * @param boolean ignoreCase
	 * @return
	 */
	private String getMultipleChoiceAnswer(String question, String[] choices, String invalidAnswerMessage, boolean ignoreCase) {
		String answer = null;
		Set<String> choiceSet = new HashSet<String>();
		for (String choice : choices) {
			if (ignoreCase) {
				choiceSet.add(choice.toLowerCase());
			} else {
				choiceSet.add(choice);
			}
		}
		while (answer == null) {
			println(question);
			for (String choice : choices)
				println("\t" + choice);
			answer = getInformation();
			
			if (ignoreCase) {
				answer.toLowerCase();
			}
			if (!choiceSet.contains(answer)) {
				println(invalidAnswerMessage.replace("%a", answer) + "\n");
				answer = null;
			}
		}
		return answer;
	}
	
	
}
