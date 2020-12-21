package bard.liyang.bardserverproject.CustomItems.RareItems;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class SnowmanBowListener implements Listener{
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getBow().getItemMeta().hasLore() 
				&& event.getBow().getItemMeta().getLore().get(0).substring(4, 16).equals("Who the fuck"))
		{
			LivingEntity frosty = (LivingEntity)event.getEntity().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.SNOWMAN);
			frosty.setVelocity(event.getProjectile().getVelocity());
			frosty.setAbsorptionAmount(30);
			event.getProjectile().remove();
		}
	}
}
