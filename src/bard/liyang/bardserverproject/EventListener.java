package bard.liyang.bardserverproject;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import bard.liyang.bardserverproject.CustomItems.EpicItems.DesertEagle;
import bard.liyang.bardserverproject.CustomItems.EpicItems.Indra;
import bard.liyang.bardserverproject.CustomItems.EpicItems.Nepenthes;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.Blackhole;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.GladosPortalGun;
import bard.liyang.bardserverproject.CustomItems.RareItems.FlingStick;
import bard.liyang.bardserverproject.CustomItems.RareItems.SnowmanBow;
import bard.liyang.bardserverproject.CustomItems.UncommonItems.GenericUncommonItem;

public class EventListener implements Listener{
	

    @EventHandler 
    public void removeCustomMobs(ChunkUnloadEvent event)
    {
    	for(Entity e : event.getChunk().getEntities())
    	{
    		if(e instanceof LivingEntity && ((LivingEntity)e).getRemoveWhenFarAway())
    			e.remove();
    	}
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
    	event.getPlayer().getInventory().addItem(new Nepenthes());
    	event.getPlayer().getInventory().addItem(new Indra());
    	event.getPlayer().getInventory().addItem(new SnowmanBow());
    	event.getPlayer().getInventory().addItem(new Blackhole());
    	event.getPlayer().getInventory().addItem(new GladosPortalGun());
    	event.getPlayer().getInventory().addItem(new DesertEagle());
		event.getPlayer().getInventory().addItem(new GenericUncommonItem());
		event.getPlayer().getInventory().addItem(new FlingStick());
		//new ZombieGeneral(event.getPlayer().getLocation());
		//new MongolSkeleton(event.getPlayer().getLocation());
    	//event.getPlayer().updateInventory();
    }
    
}
