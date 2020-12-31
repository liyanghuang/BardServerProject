package bard.liyang.bardserverproject.CustomMobs.AggressiveMonsters;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.EntitySkeleton;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;

public class AggressiveSkeleton extends EntitySkeleton{

	
	public AggressiveSkeleton(Location loc)
	{
		super(EntityTypes.SKELETON, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		ItemStack bow = new ItemStack(Material.BOW);
		bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		ItemStack head = new ItemStack(Material.CHAINMAIL_HELMET);
		head.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ItemStack legs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
		boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		
		this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(bow));
		this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(head));
		this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chest));
		this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(legs));
		this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));

		
		// this needs to be set to prevent crash
		this.h(loc.getYaw());
		this.i(loc.getYaw());

		this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<>(this, EntityLiving.class, true));

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.35);	
		this.getWorld().addEntity(this);
	}
}
