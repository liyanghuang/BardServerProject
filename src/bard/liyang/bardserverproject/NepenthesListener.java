package bard.liyang.bardserverproject;


import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class NepenthesListener implements Listener{
	
	// this is needed to set metadata for the creeper until i figure out a better way to determine which creepers came from this bow
	BardServerProject plugin;
	

	NepenthesListener(BardServerProject plugin)
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
			Creeper creeper = (Creeper)event.getEntity().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.CREEPER); 
			creeper.setPowered(true);
			creeper.setMetadata("nep", new FixedMetadataValue(plugin, true));
			creeper.ignite();
			creeper.setAbsorptionAmount(30);
			creeper.setVelocity(event.getProjectile().getVelocity());
			event.getProjectile().remove();
    	}

    }
	
	@EventHandler
	public void onExplode(EntityExplodeEvent event) // we want to make the creeper non-griefing
	{
		if(event.getEntity() instanceof Creeper)
		{
			Creeper creeper = (Creeper)event.getEntity();
			if(creeper.isPowered())
			{
				if(creeper.getMetadata("nep").get(0).asBoolean())
				{
					creeper.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, creeper.getLocation(), 0);
					creeper.getWorld().playSound(creeper.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 4, 1);
					event.setCancelled(true);
				}
			}
		}
	}
}
