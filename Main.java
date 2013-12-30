import deck.Deck;
import deck.card.Card;

public class Main {
	private Deck deck;
	
	public Main() {
		deck = new Deck();
		deck.shuffle(10000);
		Card remove = deck.draw();
		Card tmp = deck.draw();
		
		while(tmp != null) {
			System.out.println(tmp.getName());
			tmp = deck.draw();
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
