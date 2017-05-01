package fr.plx0wn.API;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.plx0wn.MSTown;

public class Resident {

	public static String colored(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static boolean isResident(String name, String city) {
		if (cityConfig.contains("city." + city + ".residents")) {
			if (cityConfig.getList("city." + city + ".residents").contains(name)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static FileConfiguration cityConfig = MSTown.cityConfig;
	public static FileConfiguration msgConfig = MSTown.msgConfig;

	public static void addResident(CommandSender sender, String city, String name) {
		if (City.cityExist(city)) {
			if (City.cityHaveMayor(city)) {
				if (!isResident(name, city)) {
					if (sender instanceof Player) {
						Player player = ((Player) sender).getPlayer();
						if (City.isMayor(player.getName(), city) | player.hasPermission("city.mayor")) {
							sender.sendMessage(colored(msgConfig.getString("residents.add-resident")
									.replace("[city]", city).replace("[player]", name)));
							if (cityConfig.contains("city." + city + ".residents")) {
								List<String> residents = cityConfig.getStringList("city." + city + ".residents");
								residents.add(name);
								cityConfig.set("city." + city + ".residents", residents);
								Configs.saveConfigs();
							} else {
								List<String> residents = new ArrayList<>();
								residents.add(name);
								cityConfig.set("city." + city + ".residents", residents);
								Configs.saveConfigs();
							}
						} else {
							sender.sendMessage(colored(msgConfig.getString("commands.no-permissions")));
						}
					} else {
						if (cityConfig.contains("city." + city + ".residents")) {
							List<String> residents = cityConfig.getStringList("city." + city + ".residents");
							residents.add(name);
							cityConfig.set("city." + city + ".residents", residents);
							Configs.saveConfigs();
						} else {
							List<String> residents = new ArrayList<>();
							residents.add(name);
							cityConfig.set("city." + city + ".residents", residents);
							Configs.saveConfigs();
						}
					}
				} else {
					sender.sendMessage(colored(msgConfig.getString("residents.already-resident")));
				}
			} else {
				sender.sendMessage(colored("&cVous devez d'abord choisir un maire."));
			}
		} else {
			sender.sendMessage(colored(msgConfig.getString("city.dont-exist")));
		}
	}

	public static void removeResident(CommandSender sender, String city, String name) {
		if (City.cityExist(city)) {
			if (isResident(name, city)) {
				if (sender instanceof Player) {
					Player player = ((Player) sender).getPlayer();
					if (City.isMayor(player.getName(), city) | player.hasPermission("city.mayor")) {
						sender.sendMessage(colored(msgConfig.getString("residents.remove-resident").replace("[city]", city)
								.replace("[player]", name)));
						List<String> residents = cityConfig.getStringList("city." + city + ".residents");
						residents.remove(name);
						cityConfig.set("city." + city + ".residents", residents);
						Configs.saveConfigs();
					} else {
						sender.sendMessage(colored(msgConfig.getString("commands.no-permissions")));
					}
				} else {
					sender.sendMessage(colored(msgConfig.getString("residents.remove-resident").replace("[city]", city)
							.replace("[player]", name)));
					List<String> residents = cityConfig.getStringList("city." + city + ".residents");
					residents.remove(name);
					cityConfig.set("city." + city + ".residents", residents);
					Configs.saveConfigs();
				}
			} else {
				sender.sendMessage(colored(msgConfig.getString("residents.not-resident")));
			}
		} else {
			sender.sendMessage(colored(msgConfig.getString("city.dont-exist")));
		}
	}

}
