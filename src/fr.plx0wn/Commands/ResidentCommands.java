package fr.plx0wn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import fr.plx0wn.MSTown;

public class ResidentCommands implements CommandExecutor {

	static FileConfiguration cityconfig = MSTown.cityConfig;
	static FileConfiguration msgconfig = MSTown.msgConfig;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("resident")) {
			if (args.length == 0) {
				// LIST COMMANDS
			}
			
//			// RESIDENT ADD
//			
//			if (sender.hasPermission("resident.add")) {
//				if (args.length == 1) {
//					if (args[0].equalsIgnoreCase("add")) {
//						sender.sendMessage(API.cs(msgconfig.getString("commands.resident-add-error")));
//					}
//				}
//				if (args.length == 2) {
//					if (args[0].equalsIgnoreCase("add")) {
//						sender.sendMessage(API.cs(msgconfig.getString("commands.resident-add-error")));
//					}
//				}
//				if (args.length == 3) {
//					if (args[0].equalsIgnoreCase("add")) {
//						API.addPlayerToCity(sender, args[1], args[2]);
//					}
//				}
//			} else {
//				sender.sendMessage(API.cs(msgconfig.getString("commands.no-permissions")));
//			}
//			
//			// RESIDENT REMOVE
//			
//			if (sender.hasPermission("resident.remove")) {
//				if (args.length == 1) {
//					if (args[0].equalsIgnoreCase("remove")) {
//						sender.sendMessage(API.cs(msgconfig.getString("commands.resident-remove-error")));
//					}
//				}
//				if (args.length == 2) {
//					if (args[0].equalsIgnoreCase("remove")) {
//						sender.sendMessage(API.cs(msgconfig.getString("commands.resident-remove-error")));
//					}
//				}
//				if (args.length == 3) {
//					if (args[0].equalsIgnoreCase("remove")) {
//						API.removePlayerToCity(sender, args[1], args[2]);
//					}
//				}
//			} else {
//				sender.sendMessage(API.cs(msgconfig.getString("commands.no-permissions")));
//			}
			
		}
		return false;
	}

}
