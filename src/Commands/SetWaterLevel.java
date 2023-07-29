package Commands;

import java.text.DecimalFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;
import Utils.Utils;

public class SetWaterLevel implements CommandExecutor {

	public Main main;

	public SetWaterLevel(Main main) {
		super();
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player))
			return false;
		
		Player player = (Player) sender;

		if (args.length == 1) {

			Double y = Double.parseDouble(args[0]);

			if (y < 0) {
				y = -y; // Make negative numbers positive
				y = Double.valueOf(new DecimalFormat("#.###").format(y)); // Format positive numbers
				y = -y; // Make positive numbers negative again
			} else {
				y = Double.valueOf(new DecimalFormat("#.###").format(y)); // Format non-negative numbers
			}
			main.getConfig().set("Water Level", y);

			main.saveConfig();
		}
		else {
			player.sendMessage(Utils.chat("&cUsage: /setwaterlevel <int>."));
			return false;
		}
		
		return true;
	}

}
