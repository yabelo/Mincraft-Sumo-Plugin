package Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class HitPlayer implements Listener{
	
	@EventHandler
	public void onHitPlayer(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player) return;
		
		Player player = (Player) event.getEntity();
		
		if(player.getHealth() < 20) player.setHealth(20);
	}

}
