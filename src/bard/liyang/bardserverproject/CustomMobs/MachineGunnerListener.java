package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import bard.liyang.bardserverproject.BardServerProject;

public class MachineGunnerListener implements Listener{

	private FixedMetadataValue meta = new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true);

	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getEntity().hasMetadata("MachineGunner"))
		{
			new BukkitRunnable()
			{
				LivingEntity mg = (LivingEntity)event.getEntity();
				int numShots = 0;
				@Override
				public void run()
				{
					if(numShots > 20 || mg.isDead())
					{
						cancel();
						return;
					}
					Arrow arrow = mg.launchProjectile(Arrow.class);
					arrow.setMetadata("MachineGunArrow", meta);
					numShots++;
				}
			}.runTaskTimer(BardServerProject.getPlugin(BardServerProject.class), 2, 2);
		}
	}
	
	@EventHandler
	public void onProjectileLand(ProjectileHitEvent event)
	{
		if(event.getEntity().hasMetadata("MachineGunArrow"))
		{
			if(event.getHitBlock() != null)
				event.getEntity().remove();
		}
	}
}
