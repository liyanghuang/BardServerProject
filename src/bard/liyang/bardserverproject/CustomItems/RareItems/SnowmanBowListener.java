package bard.liyang.bardserverproject.CustomItems.RareItems;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.CustomMobs.AggressiveSnowman;
import net.minecraft.server.v1_16_R3.WorldServer;

public class SnowmanBowListener implements Listener{
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasEnchant(CustomEnchants.SNOWMANBOW))
		{
			AggressiveSnowman frosty= new AggressiveSnowman(event.getProjectile().getLocation());
    		
			WorldServer	world =((CraftWorld)event.getEntity().getWorld()).getHandle();
			world.addEntity(frosty);
			
			Snowman craftFrosty = (Snowman)frosty.getBukkitEntity();
			craftFrosty.setCustomName(ChatColor.YELLOW + "Frosty");
			craftFrosty.setCustomNameVisible(true);
			craftFrosty.setVelocity(event.getProjectile().getVelocity());
			craftFrosty.setAbsorptionAmount(30);
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
