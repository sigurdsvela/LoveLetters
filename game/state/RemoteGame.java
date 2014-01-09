package game.state;

import player.Player;

public class RemoteGame extends Game{
	
	/**
	 * Tells whether this game has started or not
	 */
	private boolean hasStarted;

	/* OTHER METHODS */
	
	@Override
	public void init() {
	}

	@Override
	public void end() {
	}

	@Override
	public boolean playerJoin(Player player) {
        if (hasStarted() || getPlayer( player.getName() ) != null) return false;
        players.add(player);
        return true;
	}

	/* STANDARD GETTERS AND SETTERS */
	
	public boolean hasStarted() {
		return hasStarted;
	}

	public void setHasStarted(boolean hasStarted) {
		this.hasStarted = hasStarted;
	}
}
