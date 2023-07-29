package Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import Classes.Server;
import Main.Main;


public class PlayerQuit implements Listener {

    public Main main;

    public PlayerQuit(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerQuitServer(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        event.setQuitMessage(null);
        
        Server.removePlayer(player);
    }
}
