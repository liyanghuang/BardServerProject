package bard.liyang.bardserverproject.CustomItems.EpicItems;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class AirJordansListener implements Listener{
	
	@EventHandler
	public void onToggleFlight(PlayerToggleFlightEvent event)
	{
		Player p = event.getPlayer();
		event.setCancelled(true);
		p.setFlying(false);
		p.setAllowFlight(false);
		if(p.getInventory().getBoots() != null)
		{
			if(p.getInventory().getBoots().hasItemMeta() && 
					event.getPlayer().getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchants.AIRJORDANS))
			{
				Vector launchDir = event.getPlayer().getEyeLocation().getDirection().multiply(4);
				launchDir.setY(4);
				p.setVelocity(launchDir);
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		LivingEntity le = event.getPlayer();
		Player p = event.getPlayer();
		if(p.getInventory().getBoots() != null)
		{
			if(p.getInventory().getBoots().hasItemMeta() && 
					event.getPlayer().getInventory().getBoots().getItemMeta().hasEnchant(CustomEnchants.AIRJORDANS))
			{
				if(le.isOnGround())
				{
					if(!p.getAllowFlight())
						p.setAllowFlight(true);
				}
			}
		}
	}
}
