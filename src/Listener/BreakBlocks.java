package Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import Classes.Server;
import Enums.Status;

public class BreakBlocks implements Listener {


	@EventHandler
	public void onPlayerBreakBlocks(BlockBreakEvent event) {

		if (Server.getStatus() == Status.STOP)
			return;
		
		event.setCancelled(true);
	}

}
