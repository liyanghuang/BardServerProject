package bard.liyang.bardserverproject.CustomMobs.AggressiveMonsters;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.EntitySkeletonWither;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;

public class AggressiveWitherSkeleton extends EntitySkeletonWither{

	public AggressiveWitherSkeleton(Location loc)
	{
		super(EntityTypes.WITHER_SKELETON, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
		sword.addEnchantment(Enchantment.FIRE_ASPECT, 1);	

		this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
		
		//setting yaw, doesn't prevent crash but maybe is useful
		this.h(loc.getYaw());
		this.i(loc.getYaw());

		this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityLiving.class, true));

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.3);	
		this.getWorld().addEntity(this);
	}
}
