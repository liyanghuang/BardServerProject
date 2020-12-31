package bard.liyang.bardserverproject.CustomMobs.GenericUncommonMonsters;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Enderman;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntityEnderman;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;

public class GenericUncommonEnderman extends EntityEnderman{

	private FixedMetadataValue meta = new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true);

	public GenericUncommonEnderman(Location loc)
	{
		super(EntityTypes.ENDERMAN, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Enderman craftEnderman = (Enderman)this.getBukkitEntity();
		
		craftEnderman.setCustomName(ChatColor.DARK_GREEN + GenericNameGenerator.generateGenericName());
		craftEnderman.setCustomNameVisible(true);
		craftEnderman.setMetadata("UncommonLoot", meta);
		craftEnderman.setMetadata("GenericSpider", meta); // spider applies wither so we can just do this
		craftEnderman.setRemoveWhenFarAway(true);
		
		this.goalSelector.a(1, new PathfinderGoalFloat(this));

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.4);
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(40);
		this.setHealth(40);
		this.getWorld().addEntity(this);
	}
}
