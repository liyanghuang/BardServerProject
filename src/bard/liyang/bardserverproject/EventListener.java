package bard.liyang.bardserverproject;


import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Random;

public class EventListener implements Listener{
	
	private static Random rand = new Random();

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
			im.setDisplayName(bard.liyang.bardserverproject.ColorConverter.color("&5&k&lnep"));
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
			im.setDisplayName(bard.liyang.bardserverproject.ColorConverter.color("&4&n&ltnt"));
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
    public void onProjectileHit(ProjectileHitEvent event)
    {
    	World thisWorld = event.getEntity().getWorld();
    	if(event.getEntity() instanceof Arrow)
    	{
    		if(event.getEntity().getShooter() instanceof Player)
    		{
    			PlayerInventory inv = ((Player)event.getEntity().getShooter()).getInventory();
    			if(inv.getItemInMainHand().getItemMeta().getDisplayName().equals("indra"))
    			{
    				
					if(event.getHitBlock() != null)
					{
						for(int i = 0; i < 10; i++)
						{
							Location newLoc = new Location(thisWorld, 
									event.getHitBlock().getLocation().getX() + rand.nextInt(5),
									event.getHitBlock().getLocation().getY(),
									event.getHitBlock().getLocation().getZ() + rand.nextInt(5));
							thisWorld.strikeLightning(newLoc);
						}
					}
					if(event.getHitEntity() != null)
					{
						for(int i = 0; i < 10; i++)
						{
							Location newLoc = new Location(thisWorld, 
									event.getHitEntity().getLocation().getX() + rand.nextInt(5),
									event.getHitEntity().getLocation().getY(),
									event.getHitEntity().getLocation().getZ() + rand.nextInt(5));
							thisWorld.strikeLightning(newLoc);
						}
					}
    			}
    		}
/*
			if(event.getHitBlock() != null)
			{
				for(int i = 0; i < 10; i++)
					event.getEntity().getWorld().strikeLightning(event.getHitBlock().getLocation());
			}
			if(event.getHitEntity() != null)
				for(int i = 0; i < 10; i++)
					event.getEntity().getWorld().strikeLightning(event.getHitEntity().getLocation());
					*/
    	}
    }
    
    @EventHandler
    public void handleLightningDamage(EntityDamageEvent event)
    {
    	if(event.getCause() == EntityDamageEvent.DamageCause.LIGHTNING)
    	{
    		event.setDamage(20);
    	}
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
    	Nepenthes nep = new Nepenthes();
    	event.getPlayer().getInventory().addItem(nep);
    	event.getPlayer().updateInventory();
    }
    
}
