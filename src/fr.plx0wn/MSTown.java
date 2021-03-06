package fr.plx0wn;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.plx0wn.API.Configs;
import fr.plx0wn.Commands.CityCommands;
import fr.plx0wn.Commands.MainCommands;
import fr.plx0wn.Commands.ResidentCommands;

public class MSTown extends JavaPlugin {
	
	public static Plugin instance;
	public static FileConfiguration msgConfig, cityConfig;
	public static File msgFile, cityFile;
	
	public void onEnable(){
		createFiles();
		CommandExecutor cExecutor = new CityCommands();
		getCommand("ville").setExecutor(cExecutor);
		getCommand("city").setExecutor(cExecutor);
		CommandExecutor rExecutor = new ResidentCommands();
		getCommand("resident").setExecutor(rExecutor);
		CommandExecutor mExecutor = new MainCommands();
		getCommand("mstown").setExecutor(mExecutor);
	}
	
	private void createFiles() {

		cityFile = new File(getDataFolder(), "city.yml");
		msgFile = new File(getDataFolder(), "messages.yml");
		if (!cityFile.exists()) {
			cityFile.getParentFile().mkdirs();
			saveResource("city.yml", false);
		}
		if (!msgFile.exists()) {
			msgFile.getParentFile().mkdirs();
			saveResource("messages.yml", false);
		}

		try {
			cityConfig = new YamlConfiguration();
			cityConfig.load(cityFile);

			msgConfig = new YamlConfiguration();
			msgConfig.load(msgFile);
		} catch (InvalidConfigurationException | IOException e) {
			e.printStackTrace();
		}
	}

}
