package rodrigo.rpergaminhos.apis;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rodrigo.rpergaminhos.Main;

public class PergaminhosAPI {
	
	static FileConfiguration config = Main.instance.getConfig();

	public static void darInvisibilidade(Integer quantia, Player p) {
		ItemStack invisibilidade = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Invisibilidade I", "", "§7Este pergaminho quando aplicado", "§7em uma calça ao apertar SHIFT fará", "§7você ficar invisivel por §f" + config.getInt("invisibilidade.duracao") + " §7segundos", "§7este pergaminho possui um tempo", "§7de recarga de §f" + config.getInt("invisibilidade.cooldown") + " §7minutos após o uso.", "", "§7Pergaminho para: §fCalças")
				.glow()
				.build();
		for (int i = 0; i < quantia; i++) {
			p.getInventory().addItem(invisibilidade);
		}
	}

	public static void darAspectoCongelante(Integer quantia, Player p) {
		ItemStack aspectocongelante = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Aspecto Congelante I", "", "§7Este pergaminho quando aplicado", "§7em uma ferramente de batalha deixa", "§7você com uma chance de §f" + config.getInt("aspecto-congelante.chance") + "% de congelar", "§7seus inimigos por §f" + config.getInt("aspecto-congelante.duracao") + " §7segundos.", "", "§7Pergaminho para: §fEspadas ou Machados")
				.glow()
				.build();
		for (int i = 0; i < quantia; i++) {
			p.getInventory().addItem(aspectocongelante);
		}
	}

	public static void darVampiridade(Integer quantia, Player p) {
		ItemStack vampiridade = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Vampiridade I", "", "§7Este pergaminho quando aplicado", "§7em uma ferramenta de batalha deixa", "§7você com uma chance de §f" + config.getInt("vampiridade.chance") + "% §7de sugar", "§7a vida de seus oponentes.", "", "§7Pergaminho para: §fEspadas ou Machados")
				.glow()
				.build();
		for (int i = 0; i < quantia; i++) {
			p.getInventory().addItem(vampiridade);
		}
	}
	
	public static void darGravitacao(Integer quantia, Player p) {
		ItemStack gravitacao = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Gravitação I", "", "§7Este pergaminho quando aplicado", "§7em uma bota faz você não receber", "§7dano de queda.", "", "§7Pergaminho para: §fBotas")
				.glow()
				.build();
		for (int i = 0; i < quantia; i++) {
			p.getInventory().addItem(gravitacao);
		}
	}

	public static void darViking(Integer quantia, Player p) {
				ItemStack viking = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Viking I", "", "§7Este pergaminho quando aplicado", "§7em uma ferramenta de batalha ao", "§7bater em um oponente deixa você", "§7com uma força incrivel.", "", "§7Pergaminho para: §fEspadas ou Machados")
				.glow()
				.build();
		for (int i = 0; i < quantia; i++) {
			p.getInventory().addItem(viking);
		}
	}

	public static void darAspectoDoTrovao(Integer quantia, Player p) {
		ItemStack aspectotrovao = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Aspecto do Trovão I", "", "§7Este pergaminho quando aplicado em uma", "§7ferramenta de batalha deixa você com", "§7uma chance de §f" + config.getInt("aspecto-do-trovao.chance") + "% §7de spawnar um", "§7raio em seus oponentes.", "", "§7Pergaminho para: §fEspadas ou Machados")
				.glow()
				.build();
		for (int i = 0; i < quantia; i++) {
			p.getInventory().addItem(aspectotrovao);
		}
	}

	public static void darEscudo(Integer quantia, Player p) {
		ItemStack escudo = new ItemBuilder(Material.EMPTY_MAP)
				.name("§ePergaminho")
				.lore("§7Escudo I", "", "§7Este pergaminho quando aplicado", "§7em um peitoral deixa você com uma", "§7chance de §f" + config.getInt("escudo.chance") + "% §7de bloquear o dano", "§7de seus oponentes.", "", "§7Pergaminho para: §fPeitorais")
				.glow()
				.build();
		for (int i = 0; i < quantia; i++) {
			p.getInventory().addItem(escudo);
		}
	}
	
	public static void darAll(Integer quantia, Player p) {
		darAspectoCongelante(quantia, p);
		darAspectoDoTrovao(quantia, p);
		darEscudo(quantia, p);
		darGravitacao(quantia, p);
		darInvisibilidade(quantia, p);
		darVampiridade(quantia, p);
		darViking(quantia, p);
	}
}
