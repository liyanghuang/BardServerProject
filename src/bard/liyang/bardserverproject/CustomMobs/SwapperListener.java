package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import bard.liyang.bardserverproject.BardServerProject;


public class SwapperListener implements Listener{

	private BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);
	private FixedMetadataValue meta = new FixedMetadataValue(plugin, true);

	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getEntity().hasMetadata("Swapper"))
			event.getProjectile().setMetadata("SwapperArrow", meta);
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event)
	{
		if(event.getEntity().hasMetadata("SwapperArrow"))
		{
			if(event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity)
			{
				new BukkitRunnable()
				{
					LivingEntity ent1 = (LivingEntity)event.getHitEntity();
					LivingEntity ent2 = (LivingEntity)event.getEntity().getShooter();
					@Override
					public void run()
					{
						Location loc = ent1.getLocation();
						ent1.teleport(ent2.getLocation());
						ent2.teleport(loc);
					}
				}.runTaskLater(plugin, 1);
			}
			if(event.getHitBlock() != null)
			{
				((LivingEntity)event.getEntity().getShooter()).teleport(event.getHitBlock().getLocation().add(0, 1, 0));
				event.getEntity().remove();
			}
		}
	}
}
