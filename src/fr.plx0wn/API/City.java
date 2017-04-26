package fr.plx0wn.API;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import fr.plx0wn.MSTown;

public class City {
	
	public static String colored(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static FileConfiguration cityconfig = MSTown.cityConfig;
	public static FileConfiguration msgconfig = MSTown.msgConfig;
	
	public static boolean cityExist(String city){
		return cityconfig.contains("city." + city);
	}
	
	public static void addCity(CommandSender sender, String city){
		if(!cityExist(city)){
			cityconfig.createSection("city." + city);
			Configs.saveConfigs();
		} else {
			sender.sendMessage(colored(msgconfig.getString("city.already-exist")));
		}
		
	}

}
