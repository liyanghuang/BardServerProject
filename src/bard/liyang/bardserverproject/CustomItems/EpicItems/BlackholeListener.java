package bard.liyang.bardserverproject.CustomItems.EpicItems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.Util.RNGesus;

public class BlackholeListener implements Listener{
	
	BardServerProject plugin;
	
	public BlackholeListener(BardServerProject plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onArrowHit(ProjectileHitEvent event)
	{
		if(event.getEntity().hasMetadata("blackhole"))
		{
			// this arrow is a block hole arrow
			Arrow arrow = (Arrow)event.getEntity();
			event.getEntity().remove(); // remove arrow to prevent bounces
			new BukkitRunnable()
			{
				
				int count = 0;
				List<Block> blocks = getNearBlocks(arrow.getLocation(), 7);
				List<FallingBlock> fblocks = new ArrayList<FallingBlock>();
				List<LivingEntity> ents = getNearEnts(arrow.getLocation(), 7);
				int blocksSize = blocks.size();
				Vector vectorUp = new Vector(0, 0.5f, 0);
				Vector entUp = new Vector(0, 0.2, 0);

				@Override
				public void run() {
					if(count == 100)
					{
						cancel();
						for(FallingBlock fb: fblocks)
						{
							fb.setGravity(true);
							fb.setVelocity(arrow.getLocation().toVector().subtract(fb.getLocation().toVector()).multiply(-.05f));
						}
						for(LivingEntity ent: ents)
						{
							ent.setVelocity(arrow.getLocation().toVector().subtract(ent.getLocation().toVector()).multiply(-.1f));
							ent.setGravity(true);
						}
						return;
					}
					
					for(LivingEntity ent : ents)
					{
						ent.setVelocity(entUp);
						ent.setGravity(false);
					}
					
					
					Block block = blocks.get(RNGesus.rng.getRandom(blocksSize));
					FallingBlock fblock = arrow.getWorld().spawnFallingBlock(block.getLocation(), block.getBlockData());
					block.setType(Material.AIR);
					fblock.setVelocity(vectorUp);
					fblock.setGravity(false);
					fblock.setDropItem(false);
					fblock.setHurtEntities(true);
					fblocks.add(fblock);

					count++;
				}
				
			}.runTaskTimer(BardServerProject.getPlugin(BardServerProject.class), 0, 0);
		}
	}
	
	
	@EventHandler
	public void onBowShoot(EntityShootBowEvent event)
	{
		if(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasLore() && 
				event.getBow().getItemMeta().getLore().get(0).substring(4, 15).equals("Spawn black"))
			event.getProjectile().setMetadata("blackhole", new FixedMetadataValue(plugin, true));
	}
	
	public List<Block> getNearBlocks(Location loc, int radius)
	{
		List<Block> blocks = new ArrayList<Block>();
		for(int x = loc.getBlockX() - radius; x < loc.getBlockX() + radius; x++)
		{
			for(int z = loc.getBlockZ() - radius; z <= loc.getBlockZ() + radius; z++)
			{
				blocks.add(loc.getWorld().getHighestBlockAt(x, z).getLocation().getBlock());
			}
		}
		return blocks;
	}
	
	public List<LivingEntity> getNearEnts(Location loc, int radius)
	{
		List<Entity> ents = (List<Entity>)loc.getWorld().getNearbyEntities(loc, radius, 400, radius);
		List<LivingEntity> liveEnts = new ArrayList<LivingEntity>();
		for(Entity ent :ents)
		{
			if(ent instanceof LivingEntity)
				liveEnts.add((LivingEntity)ent);
		}
		return liveEnts;
	}

}
