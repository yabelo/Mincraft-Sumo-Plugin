package Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;
import Utils.Utils;

public class SetPlayer implements CommandExecutor{
	
	public Main main;

	public SetPlayer(Main main) {
		super();
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		if (args.length == 1) {
			Location pl = player.getLocation();
			double x = pl.getX();
			double y = pl.getY();
			double z = pl.getZ();
			double yaw = pl.getYaw();
			double pitch = pl.getPitch();
			main.setLocationInConfig("Player." + args[0], x, y, z, yaw, pitch);
			player.sendMessage(Utils.chat("&dPlayer" + args[0] + " &fspawn location setted."));
		}
		else {
			player.sendMessage(Utils.chat("&cUsage: /setplayer <1/2>."));
			return false;
		}
		return true;
	}

}
