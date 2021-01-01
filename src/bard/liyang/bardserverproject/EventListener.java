package bard.liyang.bardserverproject;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import bard.liyang.bardserverproject.CustomItems.EpicItems.AirJordans;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.Excalibur;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.StaffOfIce;
import bard.liyang.bardserverproject.CustomItems.RareItems.Aegis;
import bard.liyang.bardserverproject.CustomItems.RareItems.BookOfWater;
import bard.liyang.bardserverproject.CustomItems.RareItems.FireworkBow;
import bard.liyang.bardserverproject.CustomItems.RareItems.Heartbleed;
import bard.liyang.bardserverproject.CustomItems.RareItems.HermesBoots;
import bard.liyang.bardserverproject.CustomItems.RareItems.Salty;
import bard.liyang.bardserverproject.CustomMobs.KillerBunny;
import bard.liyang.bardserverproject.CustomMobs.MachineGunner;
import bard.liyang.bardserverproject.CustomMobs.Swapper;
import bard.liyang.bardserverproject.CustomMobs.Yumi;
import bard.liyang.bardserverproject.CustomMobs.ZombieGeneral.ZombieGeneral;

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
    	//event.getPlayer().getInventory().addItem(new Nepenthes());
    	//event.getPlayer().getInventory().addItem(new Indra());
    	//event.getPlayer().getInventory().addItem(new SnowmanBow());
    	//event.getPlayer().getInventory().addItem(new Blackhole());
    	//event.getPlayer().getInventory().addItem(new GladosPortalGun());
    	//event.getPlayer().getInventory().addItem(new DesertEagle());
		//event.getPlayer().getInventory().addItem(new GenericUncommonItem());
		//event.getPlayer().getInventory().addItem(new FlingStick());
		//new ZombieGeneral(event.getPlayer().getLocation());
		//new MongolSkeleton(event.getPlayer().getLocation());
    	//new KillerBunny(event.getPlayer().getLocation());
    	//event.getPlayer().updateInventory();
    	//event.getPlayer().getInventory().addItem(new Excalibur());
    	//new MachineGunner(event.getPlayer().getLocation());
    	//new Swapper(event.getPlayer().getLocation());
    	//new Yumi(event.getPlayer().getLocation());
		//event.getPlayer().getInventory().addItem(new Heartbleed());
    	//event.getPlayer().getInventory().addItem(new Aegis());
    	//event.getPlayer().getInventory().addItem(new Salty());
		//event.getPlayer().getInventory().addItem(new StaffOfIce());
		//event.getPlayer().getInventory().addItem(new AirJordans());
    }
    
}
