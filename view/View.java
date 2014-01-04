package view;

public abstract class View {
	/**
	 * Will be printing information to view.
	 * @param information	is the text to be printed to view
	 */
	public abstract void setInformation(String information);
	
	
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
