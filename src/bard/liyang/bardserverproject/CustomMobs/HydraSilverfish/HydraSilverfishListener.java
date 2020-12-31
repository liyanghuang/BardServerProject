package bard.liyang.bardserverproject.CustomMobs.HydraSilverfish;

import org.bukkit.Material;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.Util.RNGesus;

public class HydraSilverfishListener implements Listener{

	int currSilverFish = 0;
	@EventHandler
	public void onEntityDamaged(EntityDamageByEntityEvent event)
	{
		if(event.getEntity().hasMetadata("HydraSilverfish"))
		{
			Silverfish sf = (Silverfish)event.getEntity();
			if(event.getDamage() < sf.getHealth())
			{
				// dead
				for(int i = 0; i < 2; i++)
				{
					if(currSilverFish < 20)
					{
						HydraSilverfish hs = new HydraSilverfish(event.getEntity().getLocation());
						hs.setHealth(4);
						currSilverFish++;
					}
				}
			}
			else
				currSilverFish--;
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(event.getBlock().getType() == Material.STONE)
		{
			if(RNGesus.rng.getRandom() < 0.0005)
			{
				HydraSilverfish hs = new HydraSilverfish(event.getBlock().getLocation().add(0.5, 0, 0.5));
				Silverfish crafths = (Silverfish)hs.getBukkitEntity();
				crafths.setMetadata("RareLoot", new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true));
			}
		}
	}
}
