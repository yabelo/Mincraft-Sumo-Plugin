package Main;

import java.text.DecimalFormat;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import Classes.Server;
import Commands.Join;
import Commands.SetPlayer;
import Commands.SetWaterLevel;
import Commands.Start;
import Listener.BreakBlocks;
import Listener.HitPlayer;
import Listener.PlayerHunger;
import Listener.PlayerJoin;
import Listener.PlayerMove;
import Listener.PlayerQuit;

public class Main extends JavaPlugin {
	
	private static Plugin instance;

	@Override
	public void onEnable() {
		
		instance = this;
		
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
		getServer().getPluginManager().registerEvents(new HitPlayer(), this);
		getServer().getPluginManager().registerEvents(new PlayerHunger(), this);
		getServer().getPluginManager().registerEvents(new BreakBlocks(), this);
		
		getCommand("js").setExecutor(new Join(this));
		getCommand("joinsumo").setExecutor(new Join(this));
		getCommand("ss").setExecutor(new Join(this));
		getCommand("startsumo").setExecutor(new Start(this));
		getCommand("setplayer").setExecutor(new SetPlayer(this));
		getCommand("setwaterlevel").setExecutor(new SetWaterLevel(this));
		
		reloadConfig();
		saveConfig();
	}

	@Override
	public void onDisable() {
		Server.stopSumo();
	}
	
	public static Plugin getInstance() {
		return instance;
	}

	public void setLocationInConfig(String label, double x, double y, double z, double yaw, double pitch) {
	    double[] nums = {x, y, z, yaw, pitch};

	    for (int i = 0; i < nums.length; i++) {
	        if (nums[i] < 0) {
	            nums[i] = -nums[i];  // Make negative numbers positive
	            nums[i] = Double.valueOf(new DecimalFormat("#.###").format(nums[i])); // Format positive numbers
	            nums[i] = -nums[i];  // Make positive numbers negative again
	        } else {
	            nums[i] = Double.valueOf(new DecimalFormat("#.###").format(nums[i])); // Format non-negative numbers
	        }
	    }

	    getConfig().set(label + ".x", nums[0]);
	    getConfig().set(label + ".y", nums[1]);
	    getConfig().set(label + ".z", nums[2]);
	    getConfig().set(label + ".yaw", nums[3]);
	    getConfig().set(label + ".pitch", nums[4]);

	    saveConfig();
	}
	
	public void setLocationInConfig(String label, double x, double y, double z) {
	    double[] nums = {x, y, z};

	    for (int i = 0; i < nums.length; i++) {
	        if (nums[i] < 0) {
	            nums[i] = -nums[i];  // Make negative numbers positive
	            nums[i] = Double.valueOf(new DecimalFormat("#.###").format(nums[i])); // Format positive numbers
	            nums[i] = -nums[i];  // Make positive numbers negative again
	        } else {
	            nums[i] = Double.valueOf(new DecimalFormat("#.###").format(nums[i])); // Format non-negative numbers
	        }
	    }

	    getConfig().set(label + ".x", nums[0]);
	    getConfig().set(label + ".y", nums[1]);
	    getConfig().set(label + ".z", nums[2]);

	    saveConfig();
	}
}
