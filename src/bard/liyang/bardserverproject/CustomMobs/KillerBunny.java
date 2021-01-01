package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Rabbit;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntityRabbit;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;

public class KillerBunny extends EntityRabbit{

	public KillerBunny(Location loc)
	{
		super(EntityTypes.RABBIT, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Rabbit craftRabbit = (Rabbit)this.getBukkitEntity();
		
		craftRabbit.setCustomName(ChatColor.DARK_BLUE+ "Killer Bunny");
		craftRabbit.setCustomNameVisible(true);
		craftRabbit.setRemoveWhenFarAway(true);
		
		this.setRabbitType(99);

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5);
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(70);
		this.setHealth(100);

		this.getWorld().addEntity(this);
	}

}
