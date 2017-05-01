package fr.plx0wn.API;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.plx0wn.MSTown;

public class City {

	public static String colored(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static FileConfiguration cityConfig = MSTown.cityConfig;
	public static FileConfiguration msgConfig = MSTown.msgConfig;

	public static boolean cityExist(String city) {
		return cityConfig.contains("city." + city);
	}

	public static boolean isMayor(String player, String city) {
		if (cityConfig.getString("city." + city + ".mayor").equalsIgnoreCase(player)
				| cityConfig.getString("city." + city + ".founder").equalsIgnoreCase(player)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean cityHaveMayor(String city) {
		if (cityConfig.contains("city." + city + ".mayor") || cityConfig.contains("city." + city + ".founder")) {
			return true;
		} else {
			return false;
		}
	}

	public static void addCity(CommandSender sender, String city) {
		if (!cityExist(city)) {
			if (sender.hasPermission("city.manage")) {
				sender.sendMessage(colored(msgConfig.getString("city.created").replace("[city]", city)));
				cityConfig.set("city." + city + ".name", city);
				Configs.saveConfigs();
			} else {
				sender.sendMessage(colored(msgConfig.getString("commands.no-permissions")));
			}
		} else {
			sender.sendMessage(colored(msgConfig.getString("city.already-exist")));
		}

	}

	public static void removeCity(CommandSender sender, String city) {
		if (!cityExist(city)) {
			sender.sendMessage(colored(msgConfig.getString("city.dont-exist")));
		} else {
			sender.sendMessage(colored(msgConfig.getString("city.removed").replace("[city]", city)));
			cityConfig.set("city." + city, null);
			Configs.saveConfigs();
		}
	}

	public static void setMayor(CommandSender sender, String city, String name) {
		if (!cityExist(city)) {
			sender.sendMessage(colored(msgConfig.getString("city.dont-exist")));
		} else {
			if (sender instanceof Player) {
				Player player = ((Player) sender).getPlayer();
				if (City.isMayor(player.getName(), city) | player.hasPermission("city.mayor")) {
					sender.sendMessage(colored(
							msgConfig.getString("city.set-mayor").replace("[player]", name).replace("[city]", city)));
					cityConfig.set("city." + city + ".mayor", name);

					if (!cityConfig.contains("city." + city + ".residents")) {
						Resident.addResident(sender, city, name);
					}

					Configs.saveConfigs();
				} else {
					sender.sendMessage(colored(msgConfig.getString("commands.no-permissions")));
				}
			} else {
				sender.sendMessage(colored(
						msgConfig.getString("city.set-mayor").replace("[player]", name).replace("[city]", city)));
				cityConfig.set("city." + city + ".mayor", name);

				if (!cityConfig.contains("city." + city + ".residents")) {
					Resident.addResident(sender, city, name);
				}

				Configs.saveConfigs();

			}
		}
	}

	public static void setFounder(CommandSender sender, String city, String name) {
		if (!cityExist(city)) {
			sender.sendMessage(colored(msgConfig.getString("city.dont-exist")));
		} else {
			if (sender instanceof Player) {
				Player player = ((Player) sender).getPlayer();
				if (City.isMayor(player.getName(), city) | player.hasPermission("city.mayor")) {
					sender.sendMessage(colored(
							msgConfig.getString("city.set-founder").replace("[player]", name).replace("[city]", city)));

					if (!cityConfig.contains("city." + city + ".residents")) {
						Resident.addResident(sender, city, name);
					}

					cityConfig.set("city." + city + ".founder", name);
					Configs.saveConfigs();
				} else {
					sender.sendMessage(colored(msgConfig.getString("commands.no-permissions")));
				}
			} else {
				sender.sendMessage(colored(
						msgConfig.getString("city.set-founder").replace("[player]", name).replace("[city]", city)));
				cityConfig.set("city." + city + ".founder", name);

				if (!cityConfig.contains("city." + city + ".residents")) {
					Resident.addResident(sender, city, name);
				}

				Configs.saveConfigs();
			}
		}
	}

}
