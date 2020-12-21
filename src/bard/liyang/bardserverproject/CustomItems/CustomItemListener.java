package bard.liyang.bardserverproject.CustomItems;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class CustomItemListener implements Listener{
	
	// prevent renaming of custom items ... all custom items should have lore
	@EventHandler
	public void onAnvilEvent(InventoryClickEvent event)
	{
		if(event.getInventory().getType() == InventoryType.ANVIL)
		{
			if(event.getSlotType() == InventoryType.SlotType.RESULT)
			{
				if(event.getCurrentItem().getItemMeta().hasLore())
					event.setCancelled(true);
			}
		}
	}

}
