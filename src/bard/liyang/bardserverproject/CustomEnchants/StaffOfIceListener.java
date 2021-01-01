package bard.liyang.bardserverproject.CustomEnchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import bard.liyang.bardserverproject.BardServerProject;

public class StaffOfIceListener implements Listener{

	public static NamespacedKey nk = new NamespacedKey(BardServerProject.getPlugin(BardServerProject.class), "staffoficetimer");
	public FixedMetadataValue meta = new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true);
	private boolean [][][] iceSculpture = {
			{	
				{true, true, true, true, true},
				{true, false, false, false, true},
				{true, false, false, false, true},
				{false, true, false, true, false},
				{true, true, true, false, true}
			},
			{	
				{false, true, true, true, false},
				{true, false, false, false, true},
				{true, false, false, false, true},
				{true, false, false, true, false},
				{false, true, true, false, false}
			},
			{	
				{false, true, false, false, false},
				{true, false, true, true, false},
				{true, false, false, true, true},
				{false, true, false, true, false},
				{false, false, true, false, false}
			},
			{	
				{false, false, false, false, false},
				{false, true, true, true, false},
				{true, true, false, true, true},
				{false, true, true, false, false},
				{false, false, false, false, false}
			},
			{	
				{false, false, false, false, false},
				{false, false, true, false, false},
				{false, true, true, true, false},
				{false, true, false, false, false},
				{false, false, false, false, false}
			},
			{	
				{false, false, false, false, false},
				{false, false, false, false, false},
				{false, false, true, true, false},
				{false, false, false, false, false},
				{false, false, false, false, false}
			},
			{	
				{false, false, false, false, false},
				{false, false, false, false, false},
				{false, false, true, false, false},
				{false, false, false, false, false},
				{false, false, false, false, false}
			}
	};

	@EventHandler
	public void playerInteract(PlayerInteractEvent event)
	{
		if((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
		{
			if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.STAFFOFICE))
			{
				ItemStack is = event.getPlayer().getInventory().getItemInMainHand();
				ItemMeta im = is.getItemMeta();
				long timer = 0;
				if(im.getPersistentDataContainer().has(nk, PersistentDataType.LONG))
					timer = im.getPersistentDataContainer().get(nk, PersistentDataType.LONG);

				if(System.currentTimeMillis() - timer > 2000)
				{
					Snowball snow = event.getPlayer().launchProjectile(Snowball.class, event.getPlayer().getEyeLocation().getDirection().multiply(2));
					snow.setMetadata("StaffOfIceSnow", meta);

					im.getPersistentDataContainer().set(nk, PersistentDataType.LONG, System.currentTimeMillis());
					is.setItemMeta(im);
				}
			}	
		}
	}
	
	@EventHandler
	public void projectileHit(ProjectileHitEvent event)
	{
		if(event.getEntity().hasMetadata("StaffOfIceSnow") && event.getHitEntity() != null)
		{
			List<Block> blocks = collectBlocks(event.getHitEntity().getLocation());
			for(Block b : blocks)
			{
				if(b.getType() == Material.AIR || b.getType() == Material.GRASS || b.getType() == Material.SNOW)
					b.setType(Material.PACKED_ICE);
			}
			new BukkitRunnable()
			{
				public void run()
				{
					for(Block b : blocks)
					{
						if(b.getType() == Material.PACKED_ICE)
							b.setType(Material.AIR);
					}
				}
			}.runTaskLater(BardServerProject.getPlugin(BardServerProject.class), 100);
		}
	}
	
	private List<Block> collectBlocks(Location loc)
	{
		List<Block> blocks = new ArrayList<Block>();
		for(int h = 0; h < 7; h++)
		{
			for(int i = -2; i < 3; i++)
			{
				for(int j = -2; j < 3; j++)
				{
					if(iceSculpture[h][i+2][j+2])
					{
						blocks.add(loc.getWorld().getBlockAt(loc.add(i, h, j)));
						loc.subtract(i, h, j);
					}
				}
			}
		}
		return blocks;
	}
}
