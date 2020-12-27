package bard.liyang.bardserverproject.CustomItems.EpicItems;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.Util.RNGesus;

public class IndraListener implements Listener{
	
	public static NamespacedKey nk = new NamespacedKey(BardServerProject.getPlugin(BardServerProject.class), "indratimer");
	private FixedMetadataValue meta = new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true);
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasEnchant(CustomEnchants.INDRA))
		{
			ItemMeta im = event.getBow().getItemMeta();
			long timer = 0;
			if(im.getPersistentDataContainer().has(nk, PersistentDataType.LONG))
				timer = im.getPersistentDataContainer().get(nk, PersistentDataType.LONG);
			if(System.currentTimeMillis() - timer < 3000)
			{
				event.setCancelled(true);
				return;
			}
			event.getProjectile().setMetadata("indra", meta);
			im.getPersistentDataContainer().set(nk, PersistentDataType.LONG, System.currentTimeMillis());
			event.getBow().setItemMeta(im);
		}
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event)
	{
		World thisWorld = event.getEntity().getWorld();
		if(event.getEntity().hasMetadata("indra"))
		{
			if(event.getHitBlock() != null)
			{
				for(int i = 0; i < 7; i++)
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
				for(int i = 0; i < 7; i++)
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
