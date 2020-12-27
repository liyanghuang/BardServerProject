package bard.liyang.bardserverproject.CustomMobs.ZombieGeneral;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.Util.RNGesus;

public class ZombieGeneralListener implements Listener{
	
	BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);

	@EventHandler
	public void onTarget(EntityTargetLivingEntityEvent event)
	{
		if(event.getEntity().hasMetadata("ZombieGeneral") && !event.getEntity().hasMetadata("ZombieGeneralRun"))
		{
			event.getEntity().setMetadata("ZombieGeneralRun", new FixedMetadataValue(plugin, true));
			new BukkitRunnable()
			{
				LivingEntity le = (LivingEntity)event.getEntity();
				List<LivingEntity> minions = new ArrayList<LivingEntity>();

				@Override
				public void run()
				{
					if(le.isDead())
					{
						for(LivingEntity z : minions)
							z.damage(10000);
						cancel();
						return;
					}
					for(Iterator<LivingEntity> it = minions.iterator(); it.hasNext();)
					{
						LivingEntity z = it.next();
						if(z.isDead())
							it.remove();
					}
					if(minions.size() < 2)
					{
						Location loc = le.getLocation();
						loc.add(new Vector(RNGesus.rng.getRandom(7) - 3, 2, RNGesus.rng.getRandom(7) - 3));
						if(RNGesus.rng.getRandom(2) == 0)
						{
							ZombieLieutenant z = new ZombieLieutenant(loc);
							minions.add((LivingEntity)z.getBukkitEntity());
						}
						else
						{
							SkeletonLieutenant z = new SkeletonLieutenant(loc);
							minions.add((LivingEntity)z.getBukkitEntity());
						}
					}
				}
				
			}.runTaskTimer(BardServerProject.getPlugin(BardServerProject.class), 200, 200);
		}
		else if(event.getEntity().hasMetadata("ZombieLieutenant") && !event.getEntity().hasMetadata("ZombieLieutenantRun"))
		{
			event.getEntity().setMetadata("ZombieLieutenantRun", new FixedMetadataValue(plugin, true));
			new BukkitRunnable()
			{
				LivingEntity le = (LivingEntity)event.getEntity();
				List<LivingEntity> minions = new ArrayList<LivingEntity>();

				@Override
				public void run()
				{
					if(le.isDead())
					{
						for(LivingEntity z : minions)
							z.damage(10000);
						cancel();
						return;
					}
					for(Iterator<LivingEntity> it = minions.iterator(); it.hasNext();)
					{
						LivingEntity z = it.next();
						if(z.isDead())
							it.remove();
					}
					if(minions.size() < 2)
					{
						Location loc = le.getLocation();
						loc.add(new Vector(RNGesus.rng.getRandom(7) - 3, 2, RNGesus.rng.getRandom(7) - 3));
						if(RNGesus.rng.getRandom(2) == 0)
						{
							ZombieCaptain z = new ZombieCaptain(loc);
							minions.add((LivingEntity)z.getBukkitEntity());
						}
						else
						{
							SkeletonCaptain z = new SkeletonCaptain(loc);
							minions.add((LivingEntity)z.getBukkitEntity());
						}
					}
				}
				
			}.runTaskTimer(BardServerProject.getPlugin(BardServerProject.class), 200, 200);
		}
		else if(event.getEntity().hasMetadata("ZombieCaptain") && !event.getEntity().hasMetadata("ZombieCaptainRun"))
		{
			event.getEntity().setMetadata("ZombieCaptainRun", new FixedMetadataValue(plugin, true));
			new BukkitRunnable()
			{
				LivingEntity le = (LivingEntity)event.getEntity();
				List<LivingEntity> minions = new ArrayList<LivingEntity>();

				@Override
				public void run()
				{
					if(le.isDead())
					{
						for(LivingEntity z : minions)
							z.damage(10000);
						cancel();
						return;
					}
					for(Iterator<LivingEntity> it = minions.iterator(); it.hasNext();)
					{
						LivingEntity z = it.next();
						if(z.isDead())
							it.remove();
					}
					if(minions.size() < 2)
					{
						Location loc = le.getLocation();
						loc.add(new Vector(RNGesus.rng.getRandom(7) - 3, 2, RNGesus.rng.getRandom(7) - 3));
						if(RNGesus.rng.getRandom(2) == 0)
						{
							ZombiePrivate z = new ZombiePrivate(loc);
							minions.add((LivingEntity)z.getBukkitEntity());
						}
						else
						{
							SkeletonPrivate z = new SkeletonPrivate(loc);
							minions.add((LivingEntity)z.getBukkitEntity());
						}
					}
				}
				
			}.runTaskTimer(BardServerProject.getPlugin(BardServerProject.class), 200, 200);
		}
	}
}
