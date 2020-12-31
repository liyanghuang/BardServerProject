package bard.liyang.bardserverproject.CustomItems.RareItems;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class HeartbleedListener implements Listener{

	@EventHandler
	public void onEntityDamaged(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof Player)
		{
			Player damager = (Player)event.getDamager();
			if(damager.getInventory().getItemInMainHand().hasItemMeta() 
					&& damager.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.HEARTBLEED))
			{
				damager.setHealth(Math.min(damager.getHealth() + event.getDamage(), 20));
			}
		}
	}
}
