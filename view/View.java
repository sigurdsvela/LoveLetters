package view;

public abstract class View {

	/**
	 * see {@link #getInt(String, String)}
	 */
	public abstract boolean getYesOrNo(String question);

	/**
	 * Get yes or no answer from user
	 * @param question
	 * @param invalidAnswerMessage
	 * @return
	 */
	public abstract boolean getYesOrNo(String question,
			String invalidAnswerMessage);

	/**
	 * Get int from user between <i>lowerBound<i> and <i>upperCound<i>
	 * 
	 * @param question
	 * @param lowerBound
	 * @param upperBound
	 * @param invalidAnswerMessage
	 * @return
	 */
	public abstract int getIntBetweenBoundaries(String question,
			int lowerBound, int upperBound, String invalidAnswerMessage);

	/**
	 * see {@link #getIntBetweenBoundaries(String, int, int, String)  }
	 */
	public abstract int getIntBetweenBoundaries(String question,
			int lowerBound, int upperBound);

	/**
	 * see {@link #getInt(String, String) }
	 */
	public abstract int getInt(String question);

	/**
	 * Get a int from user
	 * @param question
	 * @param invalidAnswerMessage
	 * @return
	 */
	public abstract int getInt(String question, String invalidAnswerMessage);
	
	/**
	 * Print message to view
	 * @param message
	 */
	public abstract void print(String message);
	
	/**
	 * Will be printing information to view.
	 * @param information	is the text to be printed to view
	 */
	public abstract void println(String information);
	
	
	/**
	 * Will print the string on the view, and return whatever the user types in.
	 * @param string
	 * @return
	 */
	public abstract String getInformation(String string);
	
	/**
	 * Will return information from view
	 * @return
	 */
	public abstract String getInformation();
	
	/**
	 * see {@link #getMultipleChoiceAnswer(String, String[], String, boolean) getMultipleChoiceAnswer}
	 * 
	 * @param question
	 * @param choices
	 * @return
	 */
	public abstract String getMultipleChoiceAnswer(String question,
			String[] choices);

	/**
	 * see {@link #getMultipleChoiceAnswer(String, String[]) getMultipleChoiceAnswer}
	 * 
	 * @param question
	 * @param choices
	 * @return
	 */
	public abstract String getMultipleChoiceAnswerIgnoreCase(String question,
			String[] choices);

	/**
	 * see {@link #getMultipleChoiceAnswer(String, String[]) getMultipleChoiceAnswer}
	 * 
	 * @param question
	 * @param choices
	 * @return
	 */
	public abstract String getMultipleChoiceAnswer(String question,
			String[] choices, String invalidAnswerMessage);

	/**
	 * see {@link #getMultipleChoiceAnswer(String, String[]) getMultipleChoiceAnswer}
	 * 
	 * @param question
	 * @param choices
	 * @return
	 */
	public abstract String getMultipleChoiceAnswerIgnoreCase(String question,
			String[] choices, String invalidAnswerMessage);
}
