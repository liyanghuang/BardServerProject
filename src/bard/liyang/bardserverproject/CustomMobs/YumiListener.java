package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

import bard.liyang.bardserverproject.Util.RNGesus;
import bard.liyang.bardserverproject.Util.RarityManager;

public class YumiListener implements Listener{

	@EventHandler
	public void tameEvent(EntityTameEvent event)
	{
		if(event.getEntity().hasMetadata("Yumi"))
		{
			if(!(RNGesus.rng.getRandom(20) == 0))
				event.setCancelled(true);
			else
			{
				event.getEntity().setRemoveWhenFarAway(false);
				RarityManager.rm.addUser("Yumi");
			}
		}
	}
}
