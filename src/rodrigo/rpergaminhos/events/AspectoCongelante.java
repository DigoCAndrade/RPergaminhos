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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rodrigo.rpergaminhos.Main;

public class AspectoCongelante implements Listener {
	
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
		if (item.getType() != Material.DIAMOND_SWORD)
			if (item.getType() != Material.GOLD_SWORD)
				if (item.getType() != Material.IRON_SWORD)
					if (item.getType() != Material.STONE_SWORD)
						if (item.getType() != Material.WOOD_SWORD)
							if (item.getType() != Material.DIAMOND_AXE)
								if (item.getType() != Material.GOLD_AXE)
									if (item.getType() != Material.IRON_AXE)
										if (item.getType() != Material.STONE_AXE)
											if (item.getType() != Material.WOOD_AXE)
												return;

		ItemMeta bookmeta = book.getItemMeta();
		ItemMeta itemmeta = item.getItemMeta();
		if (!bookmeta.getDisplayName().equals(config.getString("pergaminhos.nome").replace("&", "§")))
			return;
		if (!bookmeta.getLore().contains("§7Aspecto Congelante I"))
			return;

		if (itemmeta.hasLore() && itemmeta.getLore().contains("§7Aspecto Congelante I")) {
			e.setCancelled(true);
			p.sendMessage(config.getString("mensagens.ja-encantado").replace("&", "§"));
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 0.7F, 1f);
			return;
		}

		ArrayList<String> lore = new ArrayList<String>();
		if (itemmeta.hasLore()) {
			lore.addAll(itemmeta.getLore());
		}
		lore.add("§7Aspecto Congelante I");
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
		Player damager = (Player) e.getDamager();
		ItemStack item = damager.getItemInHand();
		if (item == null)
			return;
		if (item.getType() == Material.AIR)
			return;
		if (!item.hasItemMeta())
			return;
		if (!item.getItemMeta().hasLore())
			return;
		if (!item.getItemMeta().getLore().contains("§7Aspecto Congelante I"))
			return;
		Random random = new Random();
		int percent = random.nextInt(100) + 1;
		if (percent < config.getInt("aspecto-congelante.chance")) {
			Player p = (Player) e.getEntity();
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * config.getInt("aspecto-congelante.duracao"), 100));
		}
	}
}
