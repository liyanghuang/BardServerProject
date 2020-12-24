package bard.liyang.bardserverproject;


import java.util.Arrays;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;

import bard.liyang.bardserverproject.CustomItems.UncommonItems.GenericUncommonItem;

public class EventListener implements Listener{
	

    @EventHandler 
    public void onArrowShoot(EntityShootBowEvent event)
    {
    	if(event.getBow().getItemMeta().getDisplayName().equals("nep") || 
    			(event.getBow().getItemMeta().getLore() != null && event.getBow().getItemMeta().getLore().get(0).equals("nep bow")))
    	{
			Creeper creeper = (Creeper)event.getEntity().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.CREEPER); 
			creeper.setPowered(true);
			//creeper.setInvulnerable(true);
			creeper.setAbsorptionAmount(30);
			creeper.setVelocity(event.getProjectile().getVelocity());
			ItemMeta im = event.getBow().getItemMeta();
			im.setLore(Arrays.asList("nep bow"));
			im.setDisplayName(bard.liyang.bardserverproject.Util.ColorConverter.color("&5&k&lnep"));
			event.getBow().setItemMeta(im);
			event.getProjectile().remove();
    	}
    	
    	else if(event.getBow().getItemMeta().getDisplayName().equals("tnt") || 
    			(event.getBow().getItemMeta().getLore() != null && event.getBow().getItemMeta().getLore().get(0).equals("tnt bow")))
    	{
			Entity tnt = event.getEntity().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.WITHER); 
			tnt.setVelocity(event.getProjectile().getVelocity());
			ItemMeta im = event.getBow().getItemMeta();
			im.setLore(Arrays.asList("tnt bow"));
			im.setDisplayName(bard.liyang.bardserverproject.Util.ColorConverter.color("&4&n&ltnt"));
			event.getBow().setItemMeta(im);
			event.getProjectile().remove();
    	}
    	
    	/*
    	
    	UncommonItem u = new UncommonItem(Material.BOW, "Nepenthes", Arrays.asList("A bow said to summon things from various realms..."));
    	RareItem r = new RareItem(Material.BOW, "Nepenthes", Arrays.asList("A bow said to summon things from various realms..."));
    	EpicItem e = new EpicItem(Material.BOW, "Nepenthes", Arrays.asList("A bow said to summon things from various realms..."));
    	LegendaryItem l = new LegendaryItem(Material.BOW, "Nepenthes", Arrays.asList("A bow said to summon things from various realms..."));

    	event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), u);
    	event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), r);
    	event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), e);
    	event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), l);
    	*/

    }
    
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
    	/*
    	Indra indra = new Indra();
    	Nepenthes nep = new Nepenthes();
    	event.getPlayer().getInventory().addItem(nep);
    	event.getPlayer().getInventory().addItem(indra);
    	event.getPlayer().getInventory().addItem(new SnowmanBow());
    	event.getPlayer().getInventory().addItem(new Blackhole());
    	event.getPlayer().updateInventory();
    	event.getPlayer().getInventory().addItem(new GladosPortalGun());
    	*/
    	event.getPlayer().getInventory().addItem(new GenericUncommonItem());
    }
    
}
