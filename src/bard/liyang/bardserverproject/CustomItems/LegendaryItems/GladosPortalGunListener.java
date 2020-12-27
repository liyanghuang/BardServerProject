package bard.liyang.bardserverproject.CustomItems.LegendaryItems;

import org.bukkit.Axis;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Orientable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class GladosPortalGunListener implements Listener{
	
	BardServerProject plugin;
	FixedMetadataValue meta;
	Block[] portals;
	BlockFace [] blockFaces;
	BlockFace [] bottomOrientations;
	long timer = 0;
	long portalTimer = 0;
	
	public GladosPortalGunListener(BardServerProject plugin)
	{
		this.plugin = plugin;
		meta = new FixedMetadataValue(plugin, true);
		portals = new Block[4];
		blockFaces = new BlockFace[2];
		bottomOrientations = new BlockFace[2];
	}

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent event)
	{
		if(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())
		{
			if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.GLADOSPORTALGUN))
			{
				if(event.getAction() == Action.LEFT_CLICK_AIR)
				{
					SmallFireball fb = (SmallFireball)event.getPlayer()
						.launchProjectile(SmallFireball.class, event.getPlayer().getEyeLocation().getDirection().multiply(.4));
					fb.setMetadata("GladosLeft", meta);
				}
				if(event.getAction() == Action.RIGHT_CLICK_AIR)
				{
					SmallFireball fb = (SmallFireball)event.getPlayer()
						.launchProjectile(SmallFireball.class, event.getPlayer().getEyeLocation().getDirection().multiply(.4));
					fb.setMetadata("GladosRight", meta);
				}
			}
		}
	}
	
	@EventHandler
	public void onPortalHit(ProjectileHitEvent event)
	{

		if(System.currentTimeMillis() - timer > 1000 && event.getEntity() instanceof SmallFireball && event.getHitBlock() != null)
		{
			SmallFireball fb = (SmallFireball)event.getEntity();
			Block blockHit = event.getHitBlock();
			if(fb.hasMetadata("GladosLeft"))
			{
				// purple portal
				BlockFace hitFace = event.getHitBlockFace();
				blockFaces[0] = hitFace;
				if(hitFace == BlockFace.UP || hitFace == BlockFace.DOWN)
				{
					Block target = blockHit.getRelative(hitFace);
					Block target2 = target.getRelative(BlockFace.NORTH);
					if(event.getEntity().getShooter() instanceof Player)
					{
						Player p = (Player)event.getEntity().getShooter();
						double x = p.getEyeLocation().getDirection().getX();
						double z = p.getEyeLocation().getDirection().getZ();

						if(x >= 0 && z >= 0 && x > z || z < 0 && x >= 0 && x > -z)
						{
							target2 = target.getRelative(BlockFace.EAST);
							bottomOrientations[0] = BlockFace.EAST;
						}
						else if(x < 0 && z >= 0 && -x > z || x < 0 && z < 0 && -x > -z)
						{
							target2 = target.getRelative(BlockFace.WEST);
							bottomOrientations[0] = BlockFace.WEST;
						}
						else if(z >= 0 && x >= 0 && z > x || z >= 0 && x < 0 && z > -x)
						{
							target2 = target.getRelative(BlockFace.SOUTH);
							bottomOrientations[0] = BlockFace.SOUTH;
						}
						else if(z < 0 && x >= 0 && -z > x || z < 0 && x < 0 && -z > -x)
						{
							target2 = target.getRelative(BlockFace.NORTH);
							bottomOrientations[0] = BlockFace.NORTH;
						}
					}
					target.setType(Material.AIR);
					target2.setType(Material.AIR);
					target.setType(Material.END_PORTAL);
					target2.setType(Material.END_PORTAL);
					target.setMetadata("Portal1", meta);
					target2.setMetadata("Portal1", meta);
					if(portals[0] != null && portals[1] != null)
					{
						portals[0].setType(Material.AIR);
						portals[1].setType(Material.AIR);
						portals[0].removeMetadata("Portal1", plugin);
						portals[1].removeMetadata("Portal1", plugin);
					}
					portals[0] = target;
					portals[1] = target2;
				}
				else if(hitFace == BlockFace.EAST || hitFace == BlockFace.WEST)
				{
					Block target = blockHit.getRelative(hitFace);
					Block target2 = target.getRelative(BlockFace.UP);
					target.setType(Material.AIR);
					target2.setType(Material.AIR);
					target.setType(Material.NETHER_PORTAL);
					target2.setType(Material.NETHER_PORTAL);
					BlockData bd = target.getBlockData();
					Orientable orientable = (Orientable) bd;
					orientable.setAxis(Axis.Z);
					target.setBlockData(orientable);
					target2.setBlockData(orientable);				
					target.setMetadata("Portal1", meta);
					target2.setMetadata("Portal1", meta);
					if(portals[0] != null && portals[1] != null)
					{
						portals[0].setType(Material.AIR);
						portals[1].setType(Material.AIR);
						portals[0].removeMetadata("Portal1", plugin);
						portals[1].removeMetadata("Portal1", plugin);
					}
					portals[0] = target;
					portals[1] = target2;
				}
				else // North and South
				{
					Block target = blockHit.getRelative(hitFace);
					Block target2 = target.getRelative(BlockFace.UP);
					target.setType(Material.AIR);
					target2.setType(Material.AIR);
					target.setType(Material.NETHER_PORTAL);
					target2.setType(Material.NETHER_PORTAL);
					BlockData bd = target.getBlockData();
					Orientable orientable = (Orientable) bd;
					orientable.setAxis(Axis.X);
					target.setBlockData(orientable);
					target2.setBlockData(orientable);
					target.setMetadata("Portal1", meta);
					target2.setMetadata("Portal1", meta);
					if(portals[0] != null && portals[1] != null)
					{
						portals[0].setType(Material.AIR);
						portals[1].setType(Material.AIR);
						portals[0].removeMetadata("Portal1", plugin);
						portals[1].removeMetadata("Portal1", plugin);
					}
					portals[0] = target;
					portals[1] = target2;
				}
			}
			else if(fb.hasMetadata("GladosRight"))
			{
				BlockFace hitFace = event.getHitBlockFace();
				blockFaces[1] = hitFace;
				if(hitFace == BlockFace.UP || hitFace == BlockFace.DOWN)
				{
					Block target = blockHit.getRelative(hitFace);
					Block target2 = target.getRelative(BlockFace.NORTH);
					if(event.getEntity().getShooter() instanceof Player)
					{
						Player p = (Player)event.getEntity().getShooter();
						double x = p.getEyeLocation().getDirection().getX();
						double z = p.getEyeLocation().getDirection().getZ();

						if(x >= 0 && z >= 0 && x > z || z < 0 && x >= 0 && x > -z)
						{
							target2 = target.getRelative(BlockFace.EAST);
							bottomOrientations[1] = BlockFace.EAST;
						}
						else if(x < 0 && z >= 0 && -x > z || x < 0 && z < 0 && -x > -z)
						{
							target2 = target.getRelative(BlockFace.WEST);
							bottomOrientations[1] = BlockFace.WEST;
						}
						else if(z >= 0 && x >= 0 && z > x || z >= 0 && x < 0 && z > -x)
						{
							target2 = target.getRelative(BlockFace.SOUTH);
							bottomOrientations[1] = BlockFace.SOUTH;
						}
						else if(z < 0 && x >= 0 && -z > x || z < 0 && x < 0 && -z > -x)
						{
							target2 = target.getRelative(BlockFace.NORTH);
							bottomOrientations[1] = BlockFace.NORTH;
						}
					}
					target.setType(Material.AIR);
					target2.setType(Material.AIR);
					target.setType(Material.END_PORTAL);
					target2.setType(Material.END_PORTAL);
					target.setMetadata("Portal2", meta);
					target2.setMetadata("Portal2", meta);
					if(portals[2] != null && portals[3] != null)
					{
						portals[2].setType(Material.AIR);
						portals[3].setType(Material.AIR);
						portals[2].removeMetadata("Portal2", plugin);
						portals[3].removeMetadata("Portal2", plugin);
					}
					portals[2] = target;
					portals[3] = target2;
				}
				else if(hitFace == BlockFace.EAST || hitFace == BlockFace.WEST)
				{
					Block target = blockHit.getRelative(hitFace);
					Block target2 = target.getRelative(BlockFace.UP);
					target.setType(Material.AIR);
					target2.setType(Material.AIR);
					target.setType(Material.NETHER_PORTAL);
					target2.setType(Material.NETHER_PORTAL);
					BlockData bd = target.getBlockData();
					Orientable orientable = (Orientable) bd;
					orientable.setAxis(Axis.Z);
					target.setBlockData(orientable);
					target2.setBlockData(orientable);				
					target.setMetadata("Portal2", meta);
					target2.setMetadata("Portal2", meta);
					if(portals[2] != null && portals[3] != null)
					{
						portals[2].setType(Material.AIR);
						portals[3].setType(Material.AIR);
						portals[2].removeMetadata("Portal2", plugin);
						portals[3].removeMetadata("Portal2", plugin);
					}
					portals[2] = target;
					portals[3] = target2;
				}
				else // North and South
				{
					Block target = blockHit.getRelative(hitFace);
					Block target2 = target.getRelative(BlockFace.UP);
					target.setType(Material.AIR);
					target2.setType(Material.AIR);
					target.setType(Material.NETHER_PORTAL);
					target2.setType(Material.NETHER_PORTAL);
					BlockData bd = target.getBlockData();
					Orientable orientable = (Orientable) bd;
					orientable.setAxis(Axis.X);
					target.setBlockData(orientable);
					target2.setBlockData(orientable);
					target.setMetadata("Portal2", meta);
					target2.setMetadata("Portal2", meta);
					if(portals[2] != null && portals[3] != null)
					{
						portals[2].setType(Material.AIR);
						portals[3].setType(Material.AIR);
						portals[2].removeMetadata("Portal2", plugin);
						portals[3].removeMetadata("Portal2", plugin);
					}
					portals[2] = target;
					portals[3] = target2;
				}
			}
			timer = System.currentTimeMillis();
		}
	}
	
	@EventHandler
	public void onPortalEnter(EntityPortalEnterEvent event)
	{
		if(System.currentTimeMillis() - portalTimer > 100 && event.getLocation().getBlock().hasMetadata("Portal1") 
				&& portals[2] != null && portals[3] != null)
		{
			if(event.getEntity() instanceof LivingEntity)
			{
				// attempt to teleport to other portal
				new BukkitRunnable()
				{

					@Override
					public void run()
					{
						LivingEntity le = (LivingEntity)event.getEntity();
						double x = .5, y = 0, z = .5;

						if(blockFaces[1] == BlockFace.DOWN)
							y = -2.5;
						else if(blockFaces[1] == BlockFace.UP)
							y = 1.5;
						else if(blockFaces[1] == BlockFace.EAST)
							x = 1.5;
						else if(blockFaces[1] == BlockFace.WEST)
							x = -.5;
						else if(blockFaces[1] == BlockFace.SOUTH)
							z = 1.5;
						else if(blockFaces[1] == BlockFace.NORTH)
							z = -.5;

						double vx = 0;
						double vy = 0;
						double vz = 0;
						
						double leVX = le.getVelocity().getX();
						double leVY = le.getVelocity().getY();
						double leVZ = le.getVelocity().getZ();
						
						double ldx = 0;
						double ldy = 0;
						double ldz = 0;
						
						double tldx = le.getEyeLocation().getDirection().getX();
						double tldy = le.getEyeLocation().getDirection().getY();
						double tldz = le.getEyeLocation().getDirection().getZ();
						
						if(blockFaces[0] == BlockFace.UP)
						{
							ldx = -tldy;
							vx = -leVY;
							if(bottomOrientations[0] == BlockFace.EAST)
							{
								ldz = tldz;
								vz = leVZ;
								ldy = tldx;
								vy = leVX;
							}
							else if(bottomOrientations[0] == BlockFace.WEST)
							{
								ldz = -tldz;
								vz = -leVZ;
								ldy = -tldx;
								vy = -leVX;
							}
							else if(bottomOrientations[0] == BlockFace.SOUTH)
							{
								ldz = -tldx;
								vz = -leVX;
								ldy = tldz;
								vy = leVZ;
							}
							else if(bottomOrientations[0] == BlockFace.NORTH)
							{
								ldz = tldx;
								vz = leVX;
								ldy = -tldz;
								vy = -leVZ;
							}
						}
						else if(blockFaces[0] == BlockFace.DOWN)
						{
							ldx = tldy;
							vx = leVY;
							if(bottomOrientations[0] == BlockFace.EAST)
							{
								ldz = tldz;
								vz = leVZ;
								ldy = tldx;
								vy = leVX;
							}
							else if(bottomOrientations[0] == BlockFace.WEST)
							{
								ldz = -tldz;
								vz = -leVZ;
								ldy = -tldx;
								vy = -leVX;
							}
							else if(bottomOrientations[0] == BlockFace.SOUTH)
							{
								ldz = -tldx;
								vz = -leVX;
								ldy = tldz;
								vy = leVZ;
							}
							else if(bottomOrientations[0] == BlockFace.NORTH)
							{
								ldz = tldx;
								vz = leVX;
								ldy = -tldz;
								vy = -leVZ;
							}
						}
						else if(blockFaces[0] == BlockFace.EAST)
						{
							ldy = tldy;
							vy = leVY;
							ldz = -tldz;
							vz = -leVZ;
							ldx = -tldx;
							vx = -leVX;
						}
						else if(blockFaces[0] == BlockFace.WEST)
						{
							ldy = tldy;
							vy = leVY;
							ldz = tldz;
							vz = leVZ;
							ldx = tldx;
							vx = leVX;
						}
						else if(blockFaces[0] == BlockFace.SOUTH)
						{
							ldy = tldy;
							vy = leVY;
							ldz = tldx;
							vz = leVX;
							ldx = -tldz;
							vx = -leVZ;
						}
						else if(blockFaces[0] == BlockFace.NORTH)
						{
							ldy = tldy;
							vy = leVY;
							ldz = -tldx;
							vz = -leVX;
							ldx = tldz;
							vx = leVZ;
						}

						Vector vel = new Vector();
						Vector look = new Vector();

						if(blockFaces[1] == BlockFace.DOWN)
						{
							look.setY(-ldx);
							vel.setY(-vx);
							if(bottomOrientations[1] == BlockFace.EAST)
							{
								look.setX(ldy);
								vel.setX(vy);
								look.setZ(-ldz);
								vel.setZ(-vz);
							}
							else if(bottomOrientations[1] == BlockFace.WEST)
							{
								look.setX(-ldy);
								vel.setX(-vy);
								look.setZ(ldz);
								vel.setZ(vz);
							}
							else if(bottomOrientations[1] == BlockFace.SOUTH)
							{
								look.setX(ldz);
								vel.setX(vz);
								look.setZ(ldy);
								vel.setZ(vy);
							}
							else if(bottomOrientations[1] == BlockFace.NORTH)
							{
								look.setX(-ldz);
								vel.setX(-vz);
								look.setZ(-ldy);
								vel.setZ(-vy);
							}
						}
						else if(blockFaces[1] == BlockFace.UP)
						{
							look.setY(ldx);
							vel.setY(vx);
							if(bottomOrientations[1] == BlockFace.EAST)
							{
								look.setX(ldy);
								vel.setX(vy);
								look.setZ(-ldz);
								vel.setZ(-vz);
							}
							else if(bottomOrientations[1] == BlockFace.WEST)
							{
								look.setX(-ldy);
								vel.setX(-vy);
								look.setZ(ldz);
								vel.setZ(vz);
							}
							else if(bottomOrientations[1] == BlockFace.SOUTH)
							{
								look.setX(ldz);
								vel.setX(vz);
								look.setZ(ldy);
								vel.setZ(vy);
							}
							else if(bottomOrientations[1] == BlockFace.NORTH)
							{
								look.setX(-ldz);
								vel.setX(-vz);
								look.setZ(-ldy);
								vel.setZ(-vy);
							}
						}
						else if(blockFaces[1] == BlockFace.WEST)
						{
							look.setX(-ldx);
							vel.setX(-vx);
							look.setY(ldy);
							vel.setY(vy);
							look.setZ(-ldz);
							vel.setZ(-vz);
						}
						else if(blockFaces[1] == BlockFace.EAST)
						{
							look.setX(ldx);
							vel.setX(vx);
							look.setY(ldy);
							vel.setY(vy);
							look.setZ(ldz);
							vel.setZ(vz);
						}
						else if(blockFaces[1] == BlockFace.NORTH)
						{
							look.setX(ldz);
							vel.setX(vz);
							look.setY(ldy);
							vel.setY(vy);
							look.setZ(-ldx);
							vel.setZ(-vx);
						}
						else if(blockFaces[1] == BlockFace.SOUTH)
						{
							look.setX(-ldz);
							vel.setX(-vz);
							look.setY(ldy);
							vel.setY(vy);
							look.setZ(ldx);
							vel.setZ(vx);
						}
						
						Location l = portals[2].getLocation();
						l.add(x, y, z);
						l.setDirection(look);
						l.setPitch(l.getPitch());
						l.setYaw(l.getYaw());
						le.teleport(l);
						le.setVelocity(vel);
					}

				}.runTaskLater(plugin, 1);
			}
			portalTimer = System.currentTimeMillis();
		}
		if(System.currentTimeMillis() - portalTimer > 100 && event.getLocation().getBlock().hasMetadata("Portal2") 
				&& portals[0] != null && portals[1] != null)
		{
			if(event.getEntity() instanceof LivingEntity)
			{
				// attempt to teleport to other portal
				
				new BukkitRunnable()
				{
					@Override
					public void run() 
					{
						LivingEntity le = (LivingEntity)event.getEntity();
						double x = .5, y = 0, z = .5;

						if(blockFaces[0] == BlockFace.DOWN)
							y = -2.5;
						else if(blockFaces[0] == BlockFace.UP)
							y = 1.5;
						else if(blockFaces[0] == BlockFace.EAST)
							x = 1.5;
						else if(blockFaces[0] == BlockFace.WEST)
							x = -.5;
						else if(blockFaces[0] == BlockFace.SOUTH)
							z = 1.5;
						else if(blockFaces[0] == BlockFace.NORTH)
							z = -.5;

						double vx = 0;
						double vy = 0;
						double vz = 0;
						
						double leVX = le.getVelocity().getX();
						double leVY = le.getVelocity().getY();
						double leVZ = le.getVelocity().getZ();
						
						double ldx = 0;
						double ldy = 0;
						double ldz = 0;
						
						double tldx = le.getEyeLocation().getDirection().getX();
						double tldy = le.getEyeLocation().getDirection().getY();
						double tldz = le.getEyeLocation().getDirection().getZ();
						
						if(blockFaces[1] == BlockFace.UP)
						{
							ldx = -tldy;
							vx = -leVY;
							if(bottomOrientations[1] == BlockFace.EAST)
							{
								ldz = tldz;
								vz = leVZ;
								ldy = tldx;
								vy = leVX;
							}
							else if(bottomOrientations[1] == BlockFace.WEST)
							{
								ldz = -tldz;
								vz = -leVZ;
								ldy = -tldx;
								vy = -leVX;
							}
							else if(bottomOrientations[1] == BlockFace.SOUTH)
							{
								ldz = -tldx;
								vz = -leVX;
								ldy = tldz;
								vy = leVZ;
							}
							else if(bottomOrientations[1] == BlockFace.NORTH)
							{
								ldz = tldx;
								vz = leVX;
								ldy = -tldz;
								vy = -leVZ;
							}
						}
						else if(blockFaces[1] == BlockFace.DOWN)
						{
							ldx = tldy;
							vx = leVY;
							if(bottomOrientations[1] == BlockFace.EAST)
							{
								ldz = tldz;
								vz = leVZ;
								ldy = tldx;
								vy = leVX;
							}
							else if(bottomOrientations[1] == BlockFace.WEST)
							{
								ldz = -tldz;
								vz = -leVZ;
								ldy = -tldx;
								vy = -leVX;
							}
							else if(bottomOrientations[1] == BlockFace.SOUTH)
							{
								ldz = -tldx;
								vz = -leVX;
								ldy = tldz;
								vy = leVZ;
							}
							else if(bottomOrientations[1] == BlockFace.NORTH)
							{
								ldz = tldx;
								vz = leVX;
								ldy = -tldz;
								vy = -leVZ;
							}
						}
						else if(blockFaces[1] == BlockFace.EAST)
						{
							ldy = tldy;
							vy = leVY;
							ldz = -tldz;
							vz = -leVZ;
							ldx = -tldx;
							vx = -leVX;
						}
						else if(blockFaces[1] == BlockFace.WEST)
						{
							ldy = tldy;
							vy = leVY;
							ldz = tldz;
							vz = leVZ;
							ldx = tldx;
							vx = leVX;
						}
						else if(blockFaces[1] == BlockFace.SOUTH)
						{
							ldy = tldy;
							vy = leVY;
							ldz = tldx;
							vz = leVX;
							ldx = -tldz;
							vx = -leVZ;
						}
						else if(blockFaces[1] == BlockFace.NORTH)
						{
							ldy = tldy;
							vy = leVY;
							ldz = -tldx;
							vz = -leVX;
							ldx = tldz;
							vx = leVZ;
						}

						Vector vel = new Vector();
						Vector look = new Vector();

						if(blockFaces[0] == BlockFace.DOWN)
						{
							look.setY(-ldx);
							vel.setY(-vx);
							if(bottomOrientations[0] == BlockFace.EAST)
							{
								look.setX(ldy);
								vel.setX(vy);
								look.setZ(-ldz);
								vel.setZ(-vz);
							}
							else if(bottomOrientations[0] == BlockFace.WEST)
							{
								look.setX(-ldy);
								vel.setX(-vy);
								look.setZ(ldz);
								vel.setZ(vz);
							}
							else if(bottomOrientations[0] == BlockFace.SOUTH)
							{
								look.setX(ldz);
								vel.setX(vz);
								look.setZ(ldy);
								vel.setZ(vy);
							}
							else if(bottomOrientations[0] == BlockFace.NORTH)
							{
								look.setX(-ldz);
								vel.setX(-vz);
								look.setZ(-ldy);
								vel.setZ(-vy);
							}
						}
						else if(blockFaces[0] == BlockFace.UP)
						{
							look.setY(ldx);
							vel.setY(vx);
							if(bottomOrientations[0] == BlockFace.EAST)
							{
								look.setX(ldy);
								vel.setX(vy);
								look.setZ(-ldz);
								vel.setZ(-vz);
							}
							else if(bottomOrientations[0] == BlockFace.WEST)
							{
								look.setX(-ldy);
								vel.setX(-vy);
								look.setZ(ldz);
								vel.setZ(vz);
							}
							else if(bottomOrientations[0] == BlockFace.SOUTH)
							{
								look.setX(ldz);
								vel.setX(vz);
								look.setZ(ldy);
								vel.setZ(vy);
							}
							else if(bottomOrientations[0] == BlockFace.NORTH)
							{
								look.setX(-ldz);
								vel.setX(-vz);
								look.setZ(-ldy);
								vel.setZ(-vy);
							}
						}
						else if(blockFaces[0] == BlockFace.WEST)
						{
							look.setX(-ldx);
							vel.setX(-vx);
							look.setY(ldy);
							vel.setY(vy);
							look.setZ(-ldz);
							vel.setZ(-vz);
						}
						else if(blockFaces[0] == BlockFace.EAST)
						{
							look.setX(ldx);
							vel.setX(vx);
							look.setY(ldy);
							vel.setY(vy);
							look.setZ(ldz);
							vel.setZ(vz);
						}
						else if(blockFaces[0] == BlockFace.NORTH)
						{
							look.setX(ldz);
							vel.setX(vz);
							look.setY(ldy);
							vel.setY(vy);
							look.setZ(-ldx);
							vel.setZ(-vx);
						}
						else if(blockFaces[0] == BlockFace.SOUTH)
						{
							look.setX(-ldz);
							vel.setX(-vz);
							look.setY(ldy);
							vel.setY(vy);
							look.setZ(ldx);
							vel.setZ(vx);
						}
						
						Location l = portals[0].getLocation();
						l.add(x, y, z);
						l.setDirection(look);
						l.setPitch(l.getPitch());
						l.setYaw(l.getYaw());
						le.teleport(l);
						le.setVelocity(vel);
					}

				}.runTaskLater(plugin, 1);
			}
			portalTimer = System.currentTimeMillis();
		}
	}
	
	@EventHandler
	public void stopEntsPorting(EntityPortalEvent event)
	{
		if(event.getFrom().getBlock().hasMetadata("Portal1") || event.getFrom().getBlock().hasMetadata("Portal2"))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{
		if(event.getCause() == TeleportCause.NETHER_PORTAL || event.getCause() == TeleportCause.END_PORTAL)
		{
			if(event.getFrom().getBlock().hasMetadata("Portal1"))
				event.setCancelled(true);
				
			if(event.getFrom().getBlock().hasMetadata("Portal2"))
				event.setCancelled(true);
		}
	}
	/*

	@EventHandler (priority = EventPriority.HIGH)
	public void stopPlayerPorting(PlayerPortalEvent event)
	{
		if(event.getFrom().getBlock().hasMetadata("Portal1"))
		{
			double x = 0, y = 0, z = 0;
			if(blockFaces[1] == BlockFace.DOWN)
				y = -2.5;
			if(blockFaces[1] == BlockFace.WEST)
				x = -1.5;
			if(blockFaces[1] == BlockFace.NORTH)
				z = -1.5;
			if(blockFaces[1] == BlockFace.SOUTH)
				z = 1.5;
			if(blockFaces[1] == BlockFace.EAST)
				x = 1.5;
			if(blockFaces[1] == BlockFace.UP)
				y = 1.5;
			event.setTo(portals[2].getLocation().add(x, y, z));
		}
		else if(event.getFrom().getBlock().hasMetadata("Portal2"))
		{
			double x = 0, y = 0, z = 0;
			if(blockFaces[0] == BlockFace.DOWN)
				y = -2.5;
			if(blockFaces[0] == BlockFace.WEST)
				x = -1.5;
			if(blockFaces[0] == BlockFace.NORTH)
				z = -1.5;
			if(blockFaces[0] == BlockFace.SOUTH)
				z = 1.5;
			if(blockFaces[0] == BlockFace.EAST)
				x = 1.5;
			if(blockFaces[0] == BlockFace.UP)
				y = 1.5;
			event.setTo(portals[0].getLocation().add(x, y, z));
		}
	}
	*/
	
	public void destroyCurrentPortals()
	{
		for(int i = 0; i < 4; i++)
		{
			if(portals[i] != null)
			{
				portals[i].setType(Material.AIR);
				portals[i].removeMetadata("Portal1", plugin);
				portals[i].removeMetadata("Portal2", plugin);
			}
		}
	}

}
