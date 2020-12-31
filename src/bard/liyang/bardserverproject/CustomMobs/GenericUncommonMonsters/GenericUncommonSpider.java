package bard.liyang.bardserverproject.CustomMobs.GenericUncommonMonsters;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Spider;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntitySpider;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;

public class GenericUncommonSpider extends EntitySpider{

	private FixedMetadataValue meta = new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true);

	public GenericUncommonSpider(Location loc)
	{
		super(EntityTypes.SPIDER, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Spider craftSpider = (Spider)this.getBukkitEntity();
		
		craftSpider.setCustomName(ChatColor.DARK_GREEN + GenericNameGenerator.generateGenericName());
		craftSpider.setMetadata("UncommonLoot", meta);
		craftSpider.setCustomNameVisible(true);
		craftSpider.setMetadata("GenericSpider", meta);
		craftSpider.setRemoveWhenFarAway(true);
		
		this.goalSelector.a(1, new PathfinderGoalFloat(this));

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.4);
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(40);
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(7);
		this.setHealth(40);
		this.getWorld().addEntity(this);
	}
}
