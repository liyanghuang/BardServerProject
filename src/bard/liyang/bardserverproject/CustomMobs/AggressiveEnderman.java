package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import net.minecraft.server.v1_16_R3.EntityEnderman;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;

public class AggressiveEnderman extends EntityEnderman{
	
	public AggressiveEnderman(Location loc)
	{
		super(EntityTypes.ENDERMAN, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		
		//setting yaw, doesn't prevent crash but maybe is useful
		this.h(loc.getYaw());
		this.i(loc.getYaw());

		this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityLiving.class, true));

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.5);
	}
}
