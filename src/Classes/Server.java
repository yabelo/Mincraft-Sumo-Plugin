package Classes;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import Enums.Status;
import Main.Main;
import Utils.Utils;
import Vars.Var;

public class Server {

	private static ArrayList<Player> players = new ArrayList<Player>(2);
	public static ArrayList<Player> freezedPlayers = new ArrayList<Player>(2);
	private static Status status = Status.STOP;

	public static void addPlayer(Player player) {

		players.add(player);

		checkIfCanStart();
	}

	public static void removePlayer(Player player) {
		players.remove(player);
		freezedPlayers.remove(player);

		if (status == Status.STOP) {
			return;
		}

		switch (status) {
		case START:
			players.get(0).sendMessage(Utils.chat("&cSumo disabled."));
			resetSumo();
			break;
		case RUNNING:
			setWinner(players.get(0));
			stopSumo();
			break;
		default:
		}
	}

	private static void resetSumo() {
		status = Status.STOP;
		freezedPlayers.clear();

		Player p = players.get(0);

		removePlayer(p);
		addPlayer(p);

	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static void setStatus(Status status) {
		Server.status = status;
	}

	public static Status getStatus() {
		return status;
	}

	private static void checkIfCanStart() {

		if (players.size() == 2) {
			startSumo();
		} else {
			new BukkitRunnable() {

				@Override
				public void run() {

					if (players.size() == 2) {
						startSumo();
					}
					players.get(0).sendMessage(Utils.chat("&dWaiting for players..."));

				}
			}.runTaskLater(Main.getInstance(), 20L);
		}
	}

	public static void startSumo() {
		setStatus(Status.START);
		Var.world.setPVP(false);

		makeChanges();
		sendStartMessage();

		new BukkitRunnable() {

			@Override
			public void run() {
				if (status == Status.STOP)
					return;
				setStatus(Status.RUNNING);
			}
		}.runTaskLater(Main.getInstance(), 20L * 6);

	}

	public static void stopSumo() {
		if (!players.isEmpty())
			players.clear();
		if (!freezedPlayers.isEmpty())
			freezedPlayers.clear();

		status = Status.STOP;
		Var.world.setPVP(true);

	}

	public static void setWinner(Player player) {

		for (Player p : getPlayers()) {
			p.sendMessage(Utils.chat("&6&l" + player.getName() + " won the game!"));
		}

		stopSumo();
	}

	private static void sendStartMessage() {

		for (Player player : getPlayers()) {

			new BukkitRunnable() {
				int counter = 6;

				@Override
				public void run() {
					counter--;

					if (status == Status.STOP)
						return;

					if (counter == 3) {
						teleportPlayersToLocation();
					}

					switch (counter) {
					case 5:
						player.sendMessage(Utils.chat("Starting in &e5"));
						break;
					case 4:
						player.sendMessage(Utils.chat("Starting in &34"));
						break;
					case 3:
						player.sendMessage(Utils.chat("Starting in &c3"));

						freezePlayers(player);
						break;
					case 2:
						player.sendMessage(Utils.chat("Starting in &b2"));
						break;
					case 1:
						player.sendMessage(Utils.chat("Starting in &a1"));

						freezePlayers(player);
						Var.world.setPVP(true);
						return;
					}
				}
			}.runTaskTimer(Main.getInstance(), 20L, 20L);
		}

	}

	private static void teleportPlayersToLocation() {
		int i = 0;
		for (Player player : getPlayers()) {
			player.teleport(Var.playerLocations[i]);
			i++;
		}
	}

	private static void freezePlayers(Player player) {

		if (freezedPlayers.contains(player))
			freezedPlayers.remove(player);
		else
			freezedPlayers.add(player);

	}

	private static void makeChanges() {

		for (Player player : getPlayers()) {
			player.setGameMode(GameMode.SURVIVAL);
			player.setHealth(20);
			player.setFoodLevel(20);
			player.getInventory().clear();
			player.getInventory().setArmorContents(null);
		}
	}
}
