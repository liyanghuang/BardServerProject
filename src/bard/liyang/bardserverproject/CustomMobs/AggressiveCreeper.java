package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import net.minecraft.server.v1_16_R3.EntityCreeper;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;

public class AggressiveCreeper extends EntityCreeper{
	
	public AggressiveCreeper(Location loc)
	{
		super(EntityTypes.CREEPER, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityLiving>(this, EntityLiving.class, true));
		
		// this needs to be set to prevent crash
		this.h(loc.getYaw());
		this.i(loc.getYaw());

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.5);

	}
}
