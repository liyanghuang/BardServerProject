package bard.liyang.bardserverproject.CustomMobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntityCat;
import net.minecraft.server.v1_16_R3.EntityCreeper;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;


public class Yumi extends EntityCat{

	private BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);
	private FixedMetadataValue meta = new FixedMetadataValue(plugin, true);

	public Yumi(Location loc)
	{
		super(EntityTypes.CAT, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Cat craftCat = (Cat)this.getBukkitEntity();
		
		craftCat.setCustomName(ChatColor.GOLD + ""+ ChatColor.BOLD + "Yumi");
		craftCat.setCustomNameVisible(true);
		craftCat.setMetadata("Yumi", meta);
		craftCat.setRemoveWhenFarAway(true);
		craftCat.setCatType(Type.BRITISH_SHORTHAIR);
		craftCat.setInvulnerable(true);
		this.goalSelector.a(5, new PathfinderGoalMeleeAttack(this, 1.0D, true));
		this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityCreeper>(this, EntityCreeper.class, true));
		this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(100);
		this.getWorld().addEntity(this);
	}

}
