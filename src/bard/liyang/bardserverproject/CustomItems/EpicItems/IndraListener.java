package bard.liyang.bardserverproject.CustomItems.EpicItems;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.Util.RNGesus;

public class IndraListener implements Listener{
	
	BardServerProject plugin;
	
	public IndraListener(BardServerProject plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasEnchant(CustomEnchants.INDRA))
			event.getProjectile().setMetadata("indra", new FixedMetadataValue(plugin, true));
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event)
	{
		World thisWorld = event.getEntity().getWorld();
		if(event.getEntity().hasMetadata("indra"))
		{
			if(event.getHitBlock() != null)
			{
				for(int i = 0; i < 10; i++)
				{
					Location newLoc = new Location(thisWorld, 
							event.getHitBlock().getLocation().getX() - 5 + RNGesus.rng.getRandom(10),
							event.getHitBlock().getLocation().getY(),
							event.getHitBlock().getLocation().getZ() - 5 + RNGesus.rng.getRandom(10));
					thisWorld.strikeLightning(newLoc);
				}
			}
			if(event.getHitEntity() != null)
			{
				for(int i = 0; i < 10; i++)
				{
					Location newLoc = new Location(thisWorld, 
							event.getHitEntity().getLocation().getX() - 5 + RNGesus.rng.getRandom(10),
							event.getHitEntity().getLocation().getY(),
							event.getHitEntity().getLocation().getZ() - 5 + RNGesus.rng.getRandom(10));
					thisWorld.strikeLightning(newLoc);
				}
			}
		}
	}
	
	@EventHandler
	public void handleLightningDamage(EntityDamageEvent event)
	{
		if(event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING)
		{
			event.setDamage(10);
		}
	}
}
