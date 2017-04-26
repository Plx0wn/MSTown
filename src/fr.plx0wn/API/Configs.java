package fr.plx0wn.API;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import fr.plx0wn.MSTown;

public class Configs {

	public static String colored(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public static FileConfiguration msgConfig = MSTown.msgConfig;
	public static FileConfiguration cityConfig = MSTown.cityConfig;

	public static void saveConfigs() {
		try {
			msgConfig.save(MSTown.msgFile);
			cityConfig.save(MSTown.cityFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void reloadConfigs(CommandSender sender) {
		try {
			msgConfig.load(MSTown.msgFile);
			cityConfig.load(MSTown.cityFile);
			sender.sendMessage(colored(msgConfig.getString("commands.reload-sucess")));
		} catch (Exception e) {
			e.printStackTrace();
			sender.sendMessage(colored(msgConfig.getString("commands.reload-error")));
		}
	}
}
