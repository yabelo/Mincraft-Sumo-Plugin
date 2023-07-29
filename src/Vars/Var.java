package Vars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import Main.Main;

public class Var {
	private static FileConfiguration config = Main.getInstance().getConfig();
	
	public static World world = Bukkit.getWorld("Map");
	private static Location pl1 = new Location(world, getDouble("Player.1.x"), getDouble("Player.1.y"), getDouble("Player.1.z"), getFloat("Player.1.yaw"), getFloat("Player.1.pitch"));
	private static Location pl2 = new Location(world, getDouble("Player.2.x"), getDouble("Player.2.y"), getDouble("Player.2.z"), getFloat("Player.2.yaw"), getFloat("Player.2.pitch"));
	public static Location[] playerLocations = {pl1, pl2};
	
	public static double waterLevel = getDouble("Water Level");
	
	

	public static FileConfiguration getConfig() {
		return config;
	}

	public static void setConfig(FileConfiguration config) {
		Var.config = config;
	}
	
	private static double getDouble(String s) {
		if(!config.contains(s)) return 0;
		return config.getDouble(s);
	}
	
	private static float getFloat(String s) {
		if(!config.contains(s)) return 0;
		return (float)config.getDouble(s);
	}
}
