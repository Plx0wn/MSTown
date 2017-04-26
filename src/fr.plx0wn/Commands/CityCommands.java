package fr.plx0wn.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import fr.plx0wn.MSTown;
import fr.plx0wn.API.City;
import fr.plx0wn.API.Configs;

public class CityCommands implements CommandExecutor {
	
	public static FileConfiguration msgConfig = MSTown.msgConfig;
	public static FileConfiguration cityConfig = MSTown.cityConfig;

	public static String colored(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("ville")) {
			if (args.length == 0) {
				// LIST COMMANDS
			}
			if(sender.hasPermission("city.add")){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("add")){
						sender.sendMessage(colored(msgConfig.getString("commands.city-add-error")));
					}
				}
				if(args.length == 2){
					City.addCity(sender, args[1]);
				}
			} else {
				sender.sendMessage(colored(msgConfig.getString("commands.no-permissions")));
			}
			
		}
		return false;
	}

}
