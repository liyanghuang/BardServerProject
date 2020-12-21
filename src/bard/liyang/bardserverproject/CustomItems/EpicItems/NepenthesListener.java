package bard.liyang.bardserverproject.CustomItems.EpicItems;


import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomMobs.AggressiveCreeper;
import bard.liyang.bardserverproject.CustomMobs.AggressiveSkeleton;
import bard.liyang.bardserverproject.CustomMobs.AggressiveSpider;
import bard.liyang.bardserverproject.CustomMobs.AggressiveWitherSkeleton;
import bard.liyang.bardserverproject.CustomMobs.AggressiveZombie;
import bard.liyang.bardserverproject.Util.RNGesus;
import net.minecraft.server.v1_16_R3.WorldServer;

public class NepenthesListener implements Listener{
	
	// this is needed to set metadata for the creeper until i figure out a better way to determine which creepers came from this bow
	BardServerProject plugin;

	public NepenthesListener(BardServerProject plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler 
    public void onArrowShoot(EntityShootBowEvent event)
    {
		ItemMeta im = event.getBow().getItemMeta();
    	if(im.hasLore() && 
    			im.getLore().get(0).substring(4, 14).equals("A bow said")) // skip first 4 characters b/c they will be mod chars
    	{
    		// we are for sure spawning something 
    		Vector whereToSpawn = event.getProjectile().getVelocity().multiply(0.2);
			int whichMob = RNGesus.rng.getRandom(5);
    		if(whichMob == 0)
    		{
				AggressiveCreeper creeper = new AggressiveCreeper(event.getProjectile().getLocation());
				
				WorldServer	world =((CraftWorld)event.getEntity().getWorld()).getHandle();
				world.addEntity(creeper);

				Creeper craftCreeper = (Creeper)creeper.getBukkitEntity();
				if(RNGesus.rng.getRandom() < .25)
					craftCreeper.setPowered(true);
				craftCreeper.setMetadata("nep", new FixedMetadataValue(plugin, true));
				craftCreeper.setAbsorptionAmount(30);
				craftCreeper.setVelocity(event.getProjectile().getVelocity());
				event.getProjectile().remove();
    		}
    		else if(whichMob == 1)
    		{
    			AggressiveWitherSkeleton witherskeleton = new AggressiveWitherSkeleton(event.getProjectile().getLocation()
    					.add(whereToSpawn));
				
				WorldServer	world =((CraftWorld)event.getEntity().getWorld()).getHandle();
				world.addEntity(witherskeleton);

				WitherSkeleton craftWitherSkeleton = (WitherSkeleton)witherskeleton.getBukkitEntity();
				craftWitherSkeleton.setAbsorptionAmount(10);
				craftWitherSkeleton.setVelocity(event.getProjectile().getVelocity());
				event.getProjectile().remove();
    		}
    		else if(whichMob == 2)
    		{
     			AggressiveSkeleton skeleton = new AggressiveSkeleton(event.getProjectile().getLocation());
			
				WorldServer	world =((CraftWorld)event.getEntity().getWorld()).getHandle();
				world.addEntity(skeleton);

				Skeleton craftSkeleton = (Skeleton)skeleton.getBukkitEntity();
				craftSkeleton.setAbsorptionAmount(10);
				craftSkeleton.setVelocity(event.getProjectile().getVelocity());
				event.getProjectile().remove();
   			
    		}
    		else if(whichMob == 3)
    		{
    			AggressiveZombie zombie = new AggressiveZombie(event.getProjectile().getLocation()
    					.add(whereToSpawn));
			
				WorldServer	world =((CraftWorld)event.getEntity().getWorld()).getHandle();
				world.addEntity(zombie);

				Zombie craftZombie = (Zombie)zombie.getBukkitEntity();
				craftZombie.setAbsorptionAmount(10);
				craftZombie.setVelocity(event.getProjectile().getVelocity());
				event.getProjectile().remove();
    		}
    		else if(whichMob == 4)
    		{
    			AggressiveSpider spider = new AggressiveSpider(event.getProjectile().getLocation()
    					.add(whereToSpawn));
			
				WorldServer	world =((CraftWorld)event.getEntity().getWorld()).getHandle();
				world.addEntity(spider);

				Spider craftSpider = (Spider)spider.getBukkitEntity();
				craftSpider.setAbsorptionAmount(10);
				craftSpider.setVelocity(event.getProjectile().getVelocity());
				event.getProjectile().remove();
    		}
    	}

    }
	
	@EventHandler
	public void onExplode(EntityExplodeEvent event) // we want to make the creeper non-griefing
	{
		if(event.getEntity() instanceof Creeper)
		{
			Creeper creeper = (Creeper)event.getEntity();
			if(creeper.hasMetadata("nep"))
			{
				creeper.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, creeper.getLocation(), 0);
				creeper.getWorld().playSound(creeper.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 4, 1);
				event.setCancelled(true);
			}
		}
	}
}
