package rodrigo.rpergaminhos.events;

import java.util.ArrayList;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rodrigo.rpergaminhos.Main;

public class Escudo implements Listener {
	
	FileConfiguration config = Main.instance.getConfig();

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
		if (item.getType() != Material.DIAMOND_CHESTPLATE)
			if (item.getType() != Material.GOLD_CHESTPLATE)
				if (item.getType() != Material.IRON_CHESTPLATE)
					if (item.getType() != Material.CHAINMAIL_CHESTPLATE)
						if (item.getType() != Material.LEATHER_CHESTPLATE)
							return;

		ItemMeta bookmeta = book.getItemMeta();
		ItemMeta itemmeta = item.getItemMeta();
		if (!bookmeta.getDisplayName().equals(config.getString("pergaminhos.nome").replace("&", "§")))
			return;
		if (!bookmeta.getLore().contains("§7Escudo I"))
			return;

		if (itemmeta.hasLore() && itemmeta.getLore().contains("§7Escudo I")) {
			e.setCancelled(true);
			p.sendMessage(config.getString("mensagens.ja-encantado").replace("&", "§"));
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 0.7F, 1f);
			return;
		}

		ArrayList<String> lore = new ArrayList<String>();
		if (itemmeta.hasLore()) {
			lore.addAll(itemmeta.getLore());
		}
		lore.add("§7Escudo I");
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
	public void onDamage(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		Player p = (Player) e.getEntity();
		ItemStack chestplate = p.getInventory().getChestplate();
		if (chestplate == null)
			return;
		if (chestplate.getType() == Material.AIR)
			return;
		if (!chestplate.hasItemMeta())
			return;
		if (!chestplate.getItemMeta().hasLore())
			return;
		if (!chestplate.getItemMeta().getLore().contains("§7Escudo I"))
			return;
		Random random = new Random();
		int percent = random.nextInt(100) + 1;
		if (percent < config.getInt("escudo.chance")) {
			p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 1F, 1F);
			e.setDamage(0.0);
			p.sendMessage(config.getString("mensagens.escudo-bloqueado").replace("&", "§"));
		}
	}
}
