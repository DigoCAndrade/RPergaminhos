package rodrigo.rpergaminhos;

import java.io.File;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rodrigo.rpergaminhos.apis.Cooldown;
import rodrigo.rpergaminhos.commands.DarPergaminho;
import rodrigo.rpergaminhos.commands.Pergaminhos;
import rodrigo.rpergaminhos.events.AspectoCongelante;
import rodrigo.rpergaminhos.events.AspectoDoTrovao;
import rodrigo.rpergaminhos.events.Escudo;
import rodrigo.rpergaminhos.events.Gravitacao;
import rodrigo.rpergaminhos.events.InventoryBlock;
import rodrigo.rpergaminhos.events.Invisibilidade;
import rodrigo.rpergaminhos.events.Vampiridade;
import rodrigo.rpergaminhos.events.Viking;

public class Main extends JavaPlugin {

	public static Cooldown cool;
	public static Main instance;

	@Override
	public void onEnable() {
		instance = this;
		File coolfile = new File(getDataFolder(), "cooldowns.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(coolfile);
		cool = new Cooldown(config, coolfile);
		try {
			config.save(coolfile);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§cNão foi possivel salvar a cooldowns.yml");
		}
        saveDefaultConfig();
		registerCommands();
		registerEvents();
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}

	private void registerCommands() {
		getCommand("darpergaminho").setExecutor(new DarPergaminho());
		getCommand("pergaminhos").setExecutor(new Pergaminhos());
	}

	private void registerEvents() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Invisibilidade(), this);
		pm.registerEvents(new Gravitacao(), this);	
		pm.registerEvents(new Viking(), this);
		pm.registerEvents(new Escudo(), this);
		pm.registerEvents(new Vampiridade(), this);
		pm.registerEvents(new AspectoDoTrovao(), this);
		pm.registerEvents(new AspectoCongelante(), this);
		pm.registerEvents(new InventoryBlock(), this);
	}
}