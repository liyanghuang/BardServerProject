package bard.liyang.bardserverproject.CustomMobs.GenericUncommonMonsters;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GenericUncommonSpiderListener implements Listener{

	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof LivingEntity && event.getDamager().hasMetadata("GenericSpider"))
		{
			((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1, false, true, true));
			((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0, false, true, true));

		}
	}
}
