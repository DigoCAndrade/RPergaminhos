package rodrigo.rpergaminhos.commands;

import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import rodrigo.rpergaminhos.Main;
import rodrigo.rpergaminhos.apis.ItemBuilder;

public class Pergaminhos implements CommandExecutor {
	
	FileConfiguration config = Main.instance.getConfig();

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		if (!(s instanceof Player)) {
			s.sendMessage(config.getString("mensagens.console-erro").replace("&", "§"));
			return true;
		}
		Player p = (Player) s;

		// Verificando se o player digitou o número de argumentos corretos

		Inventory inv = Bukkit.createInventory(null, 3 * 9, config.getString("menu-nome").replace("&", "§"));

		ItemStack invisibilidade = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Invisibilidade I", "", "§7Este pergaminho quando aplicado", "§7em uma calça ao apertar SHIFT fará", "§7você ficar invisivel por §f" + config.getInt("invisibilidade.duracao") + " §7segundos", "§7este pergaminho possui um tempo", "§7de recarga de §f" + config.getInt("invisibilidade.cooldown") + " §7minutos após o uso.", "", "§7Pergaminho para: §fCalças")
				.glow()
				.build();

		ItemStack aspectocongelante = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Aspecto Congelante I", "", "§7Este pergaminho quando aplicado", "§7em uma ferramente de batalha deixa", "§7você com uma chance de §f" + config.getInt("aspecto-congelante.chance") + "% de congelar", "§7seus inimigos por §f" + config.getInt("aspecto-congelante.duracao") + " §7segundos.", "", "§7Pergaminho para: §fEspadas ou Machados")
				.glow()
				.build();

		ItemStack vampiridade = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Vampiridade I", "", "§7Este pergaminho quando aplicado", "§7em uma ferramenta de batalha deixa", "§7você com uma chance de §f" + config.getInt("vampiridade.chance") + "% §7de sugar", "§7a vida de seus oponentes.", "", "§7Pergaminho para: §fEspadas ou Machados")
				.glow()
				.build();

		ItemStack gravitacao = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Gravitação I", "", "§7Este pergaminho quando aplicado", "§7em uma bota faz você não receber", "§7dano de queda.", "", "§7Pergaminho para: §fBotas")
				.glow()
				.build();

		ItemStack viking = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Viking I", "", "§7Este pergaminho quando aplicado", "§7em uma ferramenta de batalha ao", "§7bater em um oponente deixa você", "§7com uma força incrivel.", "", "§7Pergaminho para: §fEspadas ou Machados")
				.glow()
				.build();

		ItemStack aspectotrovao = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Aspecto do Trovão I", "", "§7Este pergaminho quando aplicado em uma", "§7ferramenta de batalha deixa você com", "§7uma chance de §f" + config.getInt("aspecto-do-trovao.chance") + "% §7de spawnar um", "§7raio em seus oponentes.", "", "§7Pergaminho para: §fEspadas ou Machados")
				.glow()
				.build();

		ItemStack escudo = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Escudo I", "", "§7Este pergaminho quando aplicado", "§7em um peitoral deixa você com uma", "§7chance de §f" + config.getInt("escudo.chance") + "% §7de bloquear o dano", "§7de seus oponentes.", "", "§7Pergaminho para: §fPeitorais")
				.glow()
				.build();

		inv.setItem(10, invisibilidade);
		inv.setItem(11, aspectocongelante);
		inv.setItem(12, vampiridade);
		inv.setItem(13, gravitacao);
		inv.setItem(14, viking);
		inv.setItem(15, aspectotrovao);
		inv.setItem(16, escudo);

		p.openInventory(inv);
		p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 10F);
		return false;
	}
}
