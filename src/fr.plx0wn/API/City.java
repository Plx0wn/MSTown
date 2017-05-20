package fr.plx0wn.API;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.plx0wn.MSTown;

public class City {

	public static FileConfiguration cityConfig = MSTown.cityConfig;
	public static FileConfiguration msgConfig = MSTown.msgConfig;

	public static String colored(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	// API POUR DEV

	public static boolean cityExist(String city) {
		return cityConfig.contains("city." + city);
	}

	public static String getCity(String name) {
		String s = null;
		for (String key : cityConfig.getConfigurationSection("city").getKeys(false)) {
			if (cityConfig.contains("city." + key + ".residents")) {
				if (cityConfig.getString("city." + key + ".residents").contains(name)) {
					s = cityConfig.getString("city." + key + ".name");
				}
			}
		}
		return s;
	}

	public static List<String> getListCity() {
		List<String> list = new ArrayList<>();
		if (cityConfig.contains("city")) {
			for (String key : cityConfig.getConfigurationSection("city").getKeys(false)) {
				list.add(key);
			}
		}
		return list;
	}

	public static boolean isMayor(String player, String city) {
		if (!cityConfig.contains("city." + city + ".mayor") && !cityConfig.contains("city." + city + ".mayor")) {
			return false;
		} else {
			if (cityConfig.getString("city." + city + ".mayor").equalsIgnoreCase(player)
					| cityConfig.getString("city." + city + ".founder").equalsIgnoreCase(player)) {
				return true;
			} else {
				return false;
			}
		}
	}

	// API POUR COMMANDS

	public static void sendListCity(CommandSender sender) {
		if (sender.hasPermission("city.infos")) {
			sender.sendMessage(colored("&aListe des villes:"));
			if (getListCity().size() != 0) {
				for (int i = 0; i < getListCity().size(); i++) {
					sender.sendMessage(colored("&a- " + getListCity().get(i)));
				}
			} else {
				sender.sendMessage(colored("&aIl n'y a aucune ville."));
			}
		} else {
			sender.sendMessage(colored(msgConfig.getString("commands.no-permissions")));
		}
	}

	public static void getCityInfos(CommandSender sender, String city) {
		if (sender.hasPermission("city.infos")) {
			if (cityExist(city)) {
				String mayor = "Aucun";
				String founder = "Aucun";
				int residents = 0;
				if (cityConfig.contains("city." + city + ".founder"))
					founder = cityConfig.getString("city." + city + ".founder");
				if (cityConfig.contains("city." + city + ".mayor"))
					mayor = cityConfig.getString("city." + city + ".mayor");
				if (cityConfig.contains("city." + city + ".residents"))
					residents = cityConfig.getList("city." + city + ".residents").size();

				sender.sendMessage(colored("&a---[Informations: " + city + "]---"));
				sender.sendMessage(colored("&a- Nom: " + city));
				sender.sendMessage(colored("&a- Fondateur: " + founder));
				sender.sendMessage(colored("&a- Maire: " + mayor));
				sender.sendMessage(colored("&a- Nombre d'habitant: " + residents));
			} else {
				sender.sendMessage(colored(msgConfig.getString("city.dont-exist")));
			}
		} else {
			sender.sendMessage(colored(msgConfig.getString("commands.no-permissions")));
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
			if(!Resident.isResident(name, city)){
				sender.sendMessage(colored(msgConfig.getString("residents.not-resident")));
			} else {
				if (sender instanceof Player) {
					
					// PARTIE JOUEUR
					
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
					
					// PARTIE SERVEUR
					
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
