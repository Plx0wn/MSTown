package fr.plx0wn.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import fr.plx0wn.MSTown;
import fr.plx0wn.API.City;
import fr.plx0wn.API.Configs;

public class MainCommands implements CommandExecutor {

	static FileConfiguration cityconfig = MSTown.cityConfig;
	static FileConfiguration msgconfig = MSTown.msgConfig;
	
	public static String colored(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("mstown")){
			if(sender.hasPermission("mstown.admin")){
				if(args.length == 0){
					sender.sendMessage(colored("&a---[ Commandes ]---"));
					sender.sendMessage(colored("&a- /mstown reload"));
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("reload")){
						Configs.reloadConfigs(sender);
					}
				}
			}
		}
		return false;
	}

}
