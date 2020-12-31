package bard.liyang.bardserverproject.CustomMobs.MongolSkeleton;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.Util.RNGesus;

public class MongolSkeletonListener implements Listener{

	FixedMetadataValue meta = new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true);
	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getEntity().hasMetadata("MongolSkeleton"))
			// tag the projectile with a label to spawn wither skeles
			event.getProjectile().setMetadata("WitherSpawn", meta);
	}
	
	@EventHandler
	public void onProjectileLand(ProjectileHitEvent event)
	{
		if(event.getEntity().hasMetadata("WitherSpawn") && event.getHitBlock()!= null)
		{
			Location origLoc = event.getHitBlock().getLocation().add(0, 1, 0);
			for(int i = 0; i < 3; i++)
				new MongolSkeletonWitherSkeleton(origLoc.add(new Vector(RNGesus.rng.getRandom(7) - 3, 2, RNGesus.rng.getRandom(7) - 3)));	
		}
	}
}
