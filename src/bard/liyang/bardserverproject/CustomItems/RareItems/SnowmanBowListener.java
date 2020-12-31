package bard.liyang.bardserverproject.CustomItems.RareItems;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.CustomMobs.AggressiveMonsters.AggressiveSnowman;

public class SnowmanBowListener implements Listener{
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasEnchant(CustomEnchants.SNOWMANBOW))
		{
			AggressiveSnowman frosty= new AggressiveSnowman(event.getProjectile().getLocation());
			
			Snowman craftFrosty = (Snowman)frosty.getBukkitEntity();
			craftFrosty.setCustomName(ChatColor.DARK_BLUE + "Frosty");
			craftFrosty.setCustomNameVisible(true);
			craftFrosty.setVelocity(event.getProjectile().getVelocity());
			craftFrosty.setAbsorptionAmount(30);
			craftFrosty.setRemoveWhenFarAway(true);
			event.getProjectile().remove();
		}
	}
	
	@EventHandler
	public void onSnowballHit(ProjectileHitEvent event)
	{
		if(event.getEntity() instanceof Snowball)
		{
			if(event.getHitEntity() instanceof LivingEntity)
			{
				((LivingEntity)event.getHitEntity()).damage(0.5);
			}
		}
	}
}
