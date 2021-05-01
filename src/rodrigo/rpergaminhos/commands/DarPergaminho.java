package rodrigo.rpergaminhos.commands;

import org.bukkit.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import rodrigo.rpergaminhos.Main;
import rodrigo.rpergaminhos.apis.PergaminhosAPI;

public class DarPergaminho implements CommandExecutor {

	FileConfiguration config = Main.instance.getConfig();

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player tem permissão
		if (!(s.hasPermission(config.getString("darpergaminho-permissao")))) {
			s.sendMessage(config.getString("mensagens.permissao-erro").replace("&", "§"));
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length > 3 || args.length < 1) {
			s.sendMessage(config.getString("mensagens.comando-incorreto").replace("&", "§"));
			return true;
		}

		// Pegando a quantia
		int quantia;
		if (args.length < 2) {
			quantia = 1;
		} else {
			try {
				quantia = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				s.sendMessage(config.getString("mensagens.numero-invalido").replace("@numero", args[1]).replace("&", "§"));
				return true;
			}
		}

		// Pegando o player
		Player p;
		if (args.length < 3) {
			if (!(s instanceof Player)) {
				s.sendMessage(config.getString("mensagens.comando-incorreto").replace("&", "§"));
				return true;
			}
			p = (Player) s;
		} else {
			p = Bukkit.getPlayer(args[2]);
			if (p == null) {
				s.sendMessage(config.getString("mensagens.jogador-invalido").replace("@jogador", args[1]).replace("&", "§"));
				return true;
			}
		}

		switch (args[0].toLowerCase()) {
		case "invisibilidade":
			PergaminhosAPI.darInvisibilidade(quantia, p);
			p.updateInventory();
			s.sendMessage(config.getString("mensagens.pergaminho-enviado").replace("@quantia", "" + quantia)
					.replace("@pergaminho", "Invisibilidade I").replace("@jogador", p.getName()).replace("&", "§"));
			break;
		case "aspectocongelante":
			PergaminhosAPI.darAspectoCongelante(quantia, p);
			p.updateInventory();
			s.sendMessage(config.getString("mensagens.pergaminho-enviado").replace("@quantia", "" + quantia)
					.replace("@pergaminho", "Aspecto Congelante I").replace("@jogador", p.getName()).replace("&", "§"));
			break;
		case "aspectodotrovao":
			PergaminhosAPI.darAspectoDoTrovao(quantia, p);
			p.updateInventory();
			s.sendMessage(config.getString("mensagens.pergaminho-enviado").replace("@quantia", "" + quantia)
					.replace("@pergaminho", "Aspecto do Trovão I").replace("@jogador", p.getName()).replace("&", "§"));
			break;
		case "escudo":
			PergaminhosAPI.darEscudo(quantia, p);
			p.updateInventory();
			s.sendMessage(config.getString("mensagens.pergaminho-enviado").replace("@quantia", "" + quantia)
					.replace("@pergaminho", "Escudo I").replace("@jogador", p.getName()).replace("&", "§"));
			break;
		case "gravitacao":
			PergaminhosAPI.darGravitacao(quantia, p);
			p.updateInventory();
			s.sendMessage(config.getString("mensagens.pergaminho-enviado").replace("@quantia", "" + quantia)
					.replace("@pergaminho", "Gravitação I").replace("@jogador", p.getName()).replace("&", "§"));
			break;
		case "vampiridade":
			PergaminhosAPI.darVampiridade(quantia, p);
			p.updateInventory();
			s.sendMessage(config.getString("mensagens.pergaminho-enviado").replace("@quantia", "" + quantia)
					.replace("@pergaminho", "Vampiridade I").replace("@jogador", p.getName()).replace("&", "§"));
			break;
		case "viking":
			PergaminhosAPI.darViking(quantia, p);
			p.updateInventory();
			s.sendMessage(config.getString("mensagens.pergaminho-enviado").replace("@quantia", "" + quantia)
					.replace("@pergaminho", "Viking I").replace("@jogador", p.getName()).replace("&", "§"));
			break;
		case "@a":
			PergaminhosAPI.darAll(quantia, p);
			p.updateInventory();
			s.sendMessage(config.getString("mensagens.pergaminho-enviado-todos").replace("@quantia", "" + quantia).replace("@jogador", p.getName()).replace("&", "§"));
			break;
		default:
			String pergaminhos = "Invisibilidade, AspectoCongelante, Vampiridade, Gravitacao, Viking, AspectoDoTrovao, Escudo";
			p.sendMessage(config.getString("mensagens.pergaminho-invalido").replace("@pergaminhos", pergaminhos).replace("&", "§"));
			break;
		}
		return false;
	}
}