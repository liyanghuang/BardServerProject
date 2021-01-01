package bard.liyang.bardserverproject.CustomItems.EpicItems;
import java.util.function.Predicate;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class DesertEagleListener implements Listener{
	
	public static NamespacedKey nk = new NamespacedKey(BardServerProject.getPlugin(BardServerProject.class), "deagletimer");

	@EventHandler
	public void onPlayerFire(PlayerInteractEvent event)
	{
		if((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
		{
			if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.DESERTEAGLE))
			{
				ItemStack is = event.getPlayer().getInventory().getItemInMainHand();
				ItemMeta im = is.getItemMeta();
				long timer = 0;
				if(im.getPersistentDataContainer().has(nk, PersistentDataType.LONG))
					timer = im.getPersistentDataContainer().get(nk, PersistentDataType.LONG);
				

				if(System.currentTimeMillis() - timer > 2000)
				{
					// Shoot bullet
					Predicate<Entity> isCurrentPlayer = i -> (!i.getName().equals(event.getPlayer().getName()));
					
					Location eyeLoc = event.getPlayer().getEyeLocation();
					Vector eyeDir = event.getPlayer().getEyeLocation().getDirection();
					
					World world = event.getPlayer().getWorld();

					//world.spawnParticle(Particle.EXPLOSION_NORMAL, eyeLoc.add(eyeDir), 0);
					world.playSound(eyeLoc.add(eyeDir), Sound.ENTITY_GENERIC_EXPLODE, 4, 1);

					RayTraceResult rt = event.getPlayer().getWorld().rayTrace(eyeLoc, eyeDir, 
							40, FluidCollisionMode.NEVER, true, 1, isCurrentPlayer);

					if(rt != null && rt.getHitEntity() != null)
					{
						if(rt.getHitEntity() instanceof LivingEntity)
						{
							// head shot detection
							LivingEntity le = (LivingEntity)rt.getHitEntity();
							if(le instanceof Creeper && le.getLocation().getY() + 1.15 < rt.getHitPosition().getY())
							{
								world.spawnParticle(Particle.EXPLOSION_LARGE, rt.getHitPosition().toLocation(world), 0);
								world.spawnParticle(Particle.DAMAGE_INDICATOR, rt.getHitPosition().toLocation(world), 10);
								le.damage(20, event.getPlayer());
							}
							else if(le instanceof Player)
							{
								if(((Player)le).isSneaking() && le.getLocation().getY() + 1.1 < rt.getHitPosition().getY())
								{
									world.spawnParticle(Particle.EXPLOSION_LARGE, rt.getHitPosition().toLocation(world), 0);
									world.spawnParticle(Particle.DAMAGE_INDICATOR, rt.getHitPosition().toLocation(world), 10);
									le.damage(20, event.getPlayer());	
								}
								else if(le.getLocation().getY() + 1.35 < rt.getHitPosition().getY())
								{
									world.spawnParticle(Particle.EXPLOSION_LARGE, rt.getHitPosition().toLocation(world), 0);
									world.spawnParticle(Particle.DAMAGE_INDICATOR, rt.getHitPosition().toLocation(world), 10);
									le.damage(20, event.getPlayer());
								}
								else
								{
									world.spawnParticle(Particle.DAMAGE_INDICATOR, rt.getHitPosition().toLocation(world), 10);
									le.damage(5, event.getPlayer());
								}
							}
							else if(le.getLocation().getY() + 1.5 < rt.getHitPosition().getY())
							{
								world.spawnParticle(Particle.EXPLOSION_LARGE, rt.getHitPosition().toLocation(world), 0);
								world.spawnParticle(Particle.DAMAGE_INDICATOR, rt.getHitPosition().toLocation(world), 10);
								le.damage(20, event.getPlayer());
							}
							else
							{
								// normal shot
								world.spawnParticle(Particle.DAMAGE_INDICATOR, rt.getHitPosition().toLocation(world), 10);
								le.damage(5, event.getPlayer());
							}
						}
					}
					else if(rt != null)
						world.spawnParticle(Particle.EXPLOSION_LARGE, rt.getHitPosition().toLocation(world), 0);

					im.getPersistentDataContainer().set(nk, PersistentDataType.LONG, System.currentTimeMillis());
					is.setItemMeta(im);
				}
			}
		}
	}
}
