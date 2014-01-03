package view;

import java.util.Scanner;

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
}
