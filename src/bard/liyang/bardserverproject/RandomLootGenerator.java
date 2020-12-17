package bard.liyang.bardserverproject;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RandomLootGenerator implements Listener{
	
	@EventHandler
	public void onInteractWithChest(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasBlock() && event.getClickedBlock().getType() == Material.CHEST)
		{
			Chest c = (Chest)event.getClickedBlock().getState();
			if(c.getLootTable() != null)
			{
				if(RarityManager.rm.getRarity("Nepenthes") > RarityManager.rm.getUsed("Nepenthes"))
				{
					Nepenthes nep = new Nepenthes();
					nep.addRarityLore(RarityManager.rm.getUsed("Nepenthes"), RarityManager.rm.getRarity("Nepenthes"));
					if(c.getInventory().addItem(nep).isEmpty()) // we only add a user if we know the store was successful
						RarityManager.rm.addUser("Nepenthes");
				}
			}
		}
	}
	
}
