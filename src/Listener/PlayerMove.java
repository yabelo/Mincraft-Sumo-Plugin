package Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import Classes.Server;
import Enums.Status;
import Main.Main;

public class PlayerMove implements Listener{
	
	public Main main;

	public PlayerMove(Main main) {
		super();
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if(Server.freezedPlayers.contains(player)) {
			if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ()) {
				player.teleport(event.getFrom());
			}
		}
		else if(Server.getStatus() == Status.RUNNING) {
			if(player.getLocation().getY() <= main.getConfig().getDouble("Water Level") + 1) {
				
				try {
					for(Player p : Server.getPlayers()) {
						if(p != player) {
							Server.setWinner(p);
						}
					}
				} catch (Exception e) {
					return;
				}
			}
		}
		
	}

}
