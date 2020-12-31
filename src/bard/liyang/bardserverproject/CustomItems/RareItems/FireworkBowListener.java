package bard.liyang.bardserverproject.CustomItems.RareItems;

import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.Util.FireworkUtils;

public class FireworkBowListener implements Listener{

	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasEnchant(CustomEnchants.FIREWORKBOW))
		{
			event.getProjectile().remove();
			Firework fr = FireworkUtils.getManager().getRandomFirework(event.getProjectile().getLocation());
			fr.setVelocity(event.getEntity().getEyeLocation().getDirection().multiply(3));
			fr.setShotAtAngle(true);
		}
	}
}
