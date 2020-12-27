package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Silverfish;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntitySilverfish;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;

public class HydraSilverfish extends EntitySilverfish{

	private FixedMetadataValue meta = new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true);

	public HydraSilverfish(Location loc)
	{
		super(EntityTypes.SILVERFISH, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Silverfish craftSilverfish = (Silverfish)this.getBukkitEntity();
		
		craftSilverfish.setCustomName(ChatColor.DARK_BLUE+ "Hydra Silverfish");
		craftSilverfish.setMetadata("HydraSilverfish", meta);
		craftSilverfish.setRemoveWhenFarAway(true);
		// this needs to be set to prevent crash
		this.h(loc.getYaw());
		this.i(loc.getYaw());

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.4);	
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(40);
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(2.5);
		this.setHealth(40);
		this.getWorld().addEntity(this);
	}
}
