package rodrigo.rpergaminhos.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import rodrigo.rpergaminhos.Main;

public class Invisibilidade implements Listener {

	FileConfiguration config = Main.instance.getConfig();

	HashMap<Player, Long> delay = new HashMap<Player, Long>();
	ArrayList<Player> animacoes = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();
		ItemStack book = e.getCursor();
		if (e.getClick() != ClickType.LEFT)
			return;
		if (book == null || item == null)
			return;
		if (!book.hasItemMeta())
			return;
		if (!book.getItemMeta().hasDisplayName())
			return;
		if (!book.getItemMeta().hasLore())
			return;
		if (book.getType() != Material.EMPTY_MAP)
			return;
		if (book.getAmount() != 1)
			return;
		if (item.getType() == Material.AIR)
			return;
		if (item.getType() != Material.DIAMOND_LEGGINGS)
			if (item.getType() != Material.GOLD_LEGGINGS)
				if (item.getType() != Material.IRON_LEGGINGS)
					if (item.getType() != Material.CHAINMAIL_LEGGINGS)
						if (item.getType() != Material.LEATHER_LEGGINGS)
							return;

		ItemMeta bookmeta = book.getItemMeta();
		ItemMeta itemmeta = item.getItemMeta();
		if (!bookmeta.getDisplayName().equals(config.getString("pergaminhos.nome").replace("&", "§")))
			return;
		if (!bookmeta.getLore().contains("§7Invisibilidade I"))
			return;

		if (itemmeta.hasLore() && itemmeta.getLore().contains("§7Invisibilidade I")) {
			e.setCancelled(true);
			p.sendMessage(config.getString("mensagens.ja-encantado").replace("&", "§"));
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 0.7F, 1f);
			return;
		}

		ArrayList<String> lore = new ArrayList<String>();
		if (itemmeta.hasLore()) {
			lore.addAll(itemmeta.getLore());
		}
		lore.add("§7Invisibilidade I");
		if (!itemmeta.hasEnchants()) {
			itemmeta.addEnchant(Enchantment.ARROW_FIRE, 1, false);
			itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		itemmeta.setLore(lore);
		item.setItemMeta(itemmeta);
		e.setCurrentItem(null);
		e.setCursor(item);
		p.playSound(p.getLocation(), Sound.ANVIL_USE, 0.7F, 1f);
	}

	@EventHandler
	public void onShift(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		PlayerInventory inv = p.getInventory();
		if (inv.getLeggings() == null)
			return;
		if (inv.getLeggings().getType() == Material.AIR)
			return;
		if (!inv.getLeggings().hasItemMeta())
			return;
		if (!inv.getLeggings().getItemMeta().hasLore())
			return;
		if (!inv.getLeggings().getItemMeta().getLore().contains("§7Invisibilidade I"))
			return;
		if (e.isSneaking()) {
			if (!Main.cool.temDelay(e.getPlayer().getName())) {
				Main.cool.addDelay(e.getPlayer().getName());
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
					@Override
					public void run() {
						animacoes.remove(p);
						for (Player pl : Bukkit.getOnlinePlayers()) {
							pl.showPlayer(p);
						}
						p.sendMessage(config.getString("mensagens.invisibilidade-acabou")
								.replace("@cooldown", "" + config.getInt("invisibilidade.cooldown")).replace("&", "§"));
						for (int n = 0; n < 100; n++) {
							p.getWorld().playEffect(p.getLocation().add(0, 0.5, 0), Effect.ENDER_SIGNAL, 100);
						}
					}
				}, 20 * config.getInt("invisibilidade.duracao"));
				animacoes.add(p);
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.hidePlayer(p);
				}
				p.sendMessage(config.getString("mensagens.invisibilidade-ativada")
						.replace("@duracao", "" + config.getInt("invisibilidade.duracao")).replace("&", "§"));
				for (int n = 0; n < 100; n++) {
					p.getWorld().playEffect(p.getLocation().add(0, 0.5, 0), Effect.ENDER_SIGNAL, 100);
				}
			} else {
				if (Main.cool.acabouDelay(e.getPlayer().getName(),
						TimeUnit.MINUTES.toMillis(config.getInt("invisibilidade.cooldown")))) {
					Main.cool.removeDelay(e.getPlayer().getName());
					Main.cool.addDelay(e.getPlayer().getName());
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
						@Override
						public void run() {
							animacoes.remove(p);
							for (Player pl : Bukkit.getOnlinePlayers()) {
								pl.showPlayer(p);
							}
							p.sendMessage(config.getString("mensagens.invisibilidade-acabou")
									.replace("@cooldown", "" + config.getInt("invisibilidade.cooldown"))
									.replace("&", "§"));
							for (int n = 0; n < 100; n++) {
								p.getWorld().playEffect(p.getLocation().add(0, 0.5, 0), Effect.ENDER_SIGNAL, 100);
							}
						}
					}, 20 * config.getInt("invisibilidade.duracao"));
					animacoes.add(p);
					for (Player pl : Bukkit.getOnlinePlayers()) {
						pl.hidePlayer(p);
					}
					p.sendMessage(config.getString("mensagens.invisibilidade-ativada")
							.replace("@duracao", "" + config.getInt("invisibilidade.duracao")).replace("&", "§"));
					for (int n = 0; n < 100; n++) {
						p.getWorld().playEffect(p.getLocation().add(0, 0.5, 0), Effect.ENDER_SIGNAL, 100);
					}
				} else {
					p.sendMessage(config.getString("mensagens.invisibilidade-cooldown")
							.replace("@cooldown",
									"" + Main.cool.getDelayString(p.getName(),
											TimeUnit.MINUTES.toMillis(config.getInt("invisibilidade.cooldown"))))
							.replace("&", "§"));
				}
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (animacoes.contains(p)) {
			for (Player pl : Bukkit.getOnlinePlayers()) {
				pl.showPlayer(p);
			}
			for (int n = 0; n < 100; n++) {
				p.getWorld().playEffect(p.getLocation().add(0, 0.5, 0), Effect.ENDER_SIGNAL, 100);
			}
		}
	}
}
