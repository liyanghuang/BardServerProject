package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;

public class BossIntegrityListener implements Listener{
	
	private BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);
	private FixedMetadataValue meta = new FixedMetadataValue(plugin, true);	
	
	/*
	@EventHandler
	public void onBossHit(EntityDamageByEntityEvent event)
	{
		if(event.getEntity().hasMetadata("LegendLoot") || event.getEntity().hasMetadata("EpicLoot"))
		{
			if( (!event.getEntity().hasMetadata("AttackedFirst")) && event.getDamager() instanceof Player)
			{
				Player damager = (Player)event.getDamager();
				damager.teleport(event.getEntity().getLocation().
								add(3 , 0, 0));
				damager.sendMessage(((LivingEntity)event.getEntity()).getCustomName() + ": Come down here and fight like a man");
				event.getEntity().setMetadata("AttackedFirst", meta);
			}
		}
	}

	@EventHandler
	public void onBossHit(ProjectileHitEvent event)
	{
		if(event.getHitEntity() != null && (event.getHitEntity().hasMetadata("LegendLoot") || event.getHitEntity().hasMetadata("EpicLoot")))
		{
			if( (!event.getHitEntity().hasMetadata("AttackedFirst")) && event.getEntity().getShooter() instanceof Player)
			{
				Player damager = (Player)event.getEntity().getShooter();
				damager.teleport(event.getHitEntity().getLocation().
								add(3 , 0, 0));
				damager.sendMessage(((LivingEntity)event.getHitEntity()).getCustomName() + ": Come down here and fight like a man");
				event.getHitEntity().setMetadata("AttackedFirst", meta);
			}
		}
	}
	*/
}
