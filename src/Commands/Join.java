package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Classes.Server;
import Enums.Status;
import Main.Main;
import Utils.Utils;

public class Join implements CommandExecutor{
	
	public Main main;

	public Join(Main main) {
		super();
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		
		if(Server.getStatus() == Status.STOP) {
			Server.addPlayer(player);
			player.sendMessage(Utils.chat("&dYou joined sumo!"));
		}
		else {
			player.sendMessage(Utils.chat("&cyou can't join sumo."));
			return false;
		}
		
		return true;
	}

}
