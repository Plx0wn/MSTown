package fr.plx0wn.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import fr.plx0wn.MSTown;
import fr.plx0wn.API.Resident;

public class ResidentCommands implements CommandExecutor {
	
	public static String colored(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	static FileConfiguration cityConfig = MSTown.cityConfig;
	static FileConfiguration msgConfig = MSTown.msgConfig;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("resident")) {
			if (args.length == 0) {
				sender.sendMessage(colored("&a---[ Commandes ]---"));
				sender.sendMessage(colored("&a- /resident add [nom de la ville] [nom du joueur]"));
				sender.sendMessage(colored("&a- /resident remove [nom de la ville] [nom du joueur]"));
			}
			
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("add")){
					sender.sendMessage(colored(msgConfig.getString("commands.resident-add-error")));
				}
			}
			
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("add")){
					sender.sendMessage(colored(msgConfig.getString("commands.resident-add-error")));
				}
			}
			if(args.length == 3){
				if(args[0].equalsIgnoreCase("add")){
					Resident.addResident(sender, args[1], args[2]);
				}
				if(args[0].equalsIgnoreCase("remove")){
					Resident.removeResident(sender, args[1], args[2]);
				}
			}
			
		}
		return false;
	}

}
