package bard.liyang.bardserverproject.CustomItems.RareItems;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class BookOfWaterListener implements Listener{

	
	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		if(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta() &&
				event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.BOOKOFWATER))
		{
			if(event.hasBlock() && event.getClickedBlock().getType() != Material.AIR)
			{
				Block b = event.getClickedBlock().getRelative(event.getBlockFace());
				if(b.getType() == Material.AIR)
					b.setType(Material.WATER);
			}
		}
	}
}
