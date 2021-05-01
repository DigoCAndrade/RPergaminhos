package rodrigo.rpergaminhos.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import rodrigo.rpergaminhos.Main;

public class InventoryBlock implements Listener {

	FileConfiguration config = Main.instance.getConfig();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void inventoryBlock(InventoryClickEvent e) {

		if (e.getView().getTitle().equals(config.getString("menu-nome").replace("&", "§"))) {
			e.setCancelled(true);
		    Player p = (Player) e.getWhoClicked();
		    p.updateInventory();
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().getType().getId() == 0)
				return;
			if (!e.getCurrentItem().hasItemMeta())
				return;
		}
	}
}
