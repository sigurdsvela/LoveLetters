package view;

public abstract class View {
	/**
	 * Will be printing information to view.
	 * @param information	is the text to be printed to view
	 */
	public abstract void setInformation(String information);
	
	/**
	 * Will return information from view
	 * @return
	 */
	public abstract String getInformation();
}
