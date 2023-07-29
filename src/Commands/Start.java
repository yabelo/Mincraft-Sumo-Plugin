package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Classes.Server;
import Main.Main;

public class Start implements CommandExecutor{
	
	public Main main;

	public Start(Main main) {
		super();
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) return false;
		
		Server.startSumo();
		
		return true;
	}

}
