package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

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

}
