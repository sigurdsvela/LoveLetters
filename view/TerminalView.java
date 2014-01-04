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
	 * Will be printing information to view.
	 * @param information	is the text to be printed to view
	 */
	public void setInformation(String information) {
		System.out.println(information);
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
	
	/**
	 * Get int from user between <i>lowerBound<i> and <i>upperCound<i>
	 * 
	 * @param question
	 * @param lowerBound
	 * @param upperBound
	 * @param invalidAnswerMessage
	 * @return
	 */
	public int getIntBetweenBoundaries(String question, int lowerBound, int upperBound, String invalidAnswerMessage) {
		int answer = -1;
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
	
	/**
	 * set {@link #getIntBetweenBoundaries(String, int, int, String)  }
	 */
	public int getIntBetweenBoundaries(String question, int lowerBound, int upperBound) {
		return getIntBetweenBoundaries(question, lowerBound, upperBound, "\"%a\" is not a number between " + lowerBound + " and " + upperBound + "!");
	}
	
	
	/**
	 * set {@link #getInt(String, String) }
	 */
	public int getInt(String question) {
		return getInt(question, "\"%a\" is not a number.");
	}
	
	
	/**
	 * Get a int from user
	 * @param question
	 * @param invalidAnswerMessage
	 * @return
	 */
	public int getInt(String question, String invalidAnswerMessage) {
		setInformation(question);
		int answer = -1;
		while (answer == -1) {
			try {
				answer = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				setInformation(invalidAnswerMessage.replace("%a", "" + answer + ""));
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
			setInformation(question);
			for (String choice : choices)
				setInformation("\t" + choice);
			answer = getInformation();
			
			if (ignoreCase) {
				answer.toLowerCase();
			}
			if (!choiceSet.contains(answer)) {
				setInformation(invalidAnswerMessage.replace("%a", answer) + "\n");
				answer = null;
			}
		}
		return answer;
	}
	
	
}
