package fr.plx0wn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import fr.plx0wn.MSTown;
import fr.plx0wn.API.Configs;

public class MainCommands implements CommandExecutor {

	static FileConfiguration cityconfig = MSTown.cityConfig;
	static FileConfiguration msgconfig = MSTown.msgConfig;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("mstown")){
			if(sender.hasPermission("mstown.admin")){
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
