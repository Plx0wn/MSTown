package fr.plx0wn.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import fr.plx0wn.MSTown;
import fr.plx0wn.API.City;

public class CityCommands implements CommandExecutor {

	public static FileConfiguration msgConfig = MSTown.msgConfig;
	public static FileConfiguration cityConfig = MSTown.cityConfig;

	public static String colored(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("ville") || label.equalsIgnoreCase("city")) {
			if (args.length == 0) {
				sender.sendMessage(colored("&a---[ Commandes ]---"));
				sender.sendMessage(colored("&a- /ville add [nom de la ville] [nom du joueur]"));
				sender.sendMessage(colored("&a- /ville remove [nom de la ville] [nom du joueur]"));
				sender.sendMessage(colored("&a- /ville mayor [nom de la ville] [nouveau maire]"));
				sender.sendMessage(colored("&a- /ville founder [nom de la ville] [nom du joueur]"));
				sender.sendMessage(colored("&a- /ville info [nom de la ville]"));
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("add")) {
					sender.sendMessage(colored(msgConfig.getString("commands.city-add-error")));
				}
				if (args[0].equalsIgnoreCase("remove")) {
					sender.sendMessage(colored(msgConfig.getString("commands.city-remove-error")));
				}
				if (args[0].equalsIgnoreCase("mayor")) {
					sender.sendMessage(colored(msgConfig.getString("commands.city-mayor-error")));
				}
				if (args[0].equalsIgnoreCase("founder")) {
					sender.sendMessage(colored(msgConfig.getString("commands.city-founder-error")));
				}
				if (args[0].equalsIgnoreCase("list")) {
					City.sendListCity(sender);
				}
				if (args[0].equalsIgnoreCase("info")) {
					sender.sendMessage(colored(msgConfig.getString("commands.city-info-error")));
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("add")) {
					City.addCity(sender, args[1]);
				}
				if (args[0].equalsIgnoreCase("remove")) {
					City.removeCity(sender, args[1]);
				}
				if (args[0].equalsIgnoreCase("mayor")) {
					sender.sendMessage(colored(msgConfig.getString("commands.city-mayor-error")));
				}
				if (args[0].equalsIgnoreCase("founder")) {
					sender.sendMessage(colored(msgConfig.getString("commands.city-founder-error")));
				}
				if (args[0].equalsIgnoreCase("info")) {
					City.getCityInfos(sender, args[1]);
				}
			}
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("mayor")) {
					City.setMayor(sender, args[1], args[2]);
				}
				if (args[0].equalsIgnoreCase("founder")) {
					City.setFounder(sender, args[1], args[2]);
				}
			}

		}
		return false;
	}

}
