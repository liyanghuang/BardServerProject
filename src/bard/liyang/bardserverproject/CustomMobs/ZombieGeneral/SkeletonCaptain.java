package bard.liyang.bardserverproject.CustomMobs.ZombieGeneral;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntitySkeleton;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;

public class SkeletonCaptain extends EntitySkeleton{

	private BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);
	private FixedMetadataValue meta = new FixedMetadataValue(plugin, true);

	public SkeletonCaptain(Location loc)
	{
		super(EntityTypes.SKELETON, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Skeleton craftSkeleton = (Skeleton)this.getBukkitEntity();
		
		craftSkeleton.setCustomName(ChatColor.GOLD + ""+ ChatColor.BOLD + "Skeleton Captain");
		craftSkeleton.setMetadata("ZombieCaptain", meta);
		craftSkeleton.setMetadata("ZombieCaptainMinion", new FixedMetadataValue(plugin, 0));	
		craftSkeleton.setRemoveWhenFarAway(true);

		ItemStack bow = new ItemStack(Material.BOW);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 4);
		bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
		ItemStack head = new ItemStack(Material.IRON_HELMET);
		head.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		ItemMeta im = head.getItemMeta();
		im.setUnbreakable(true);
		head.setItemMeta(im);
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		ItemStack legs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		
		this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(bow));
		this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(head));
		this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chest));
		this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(legs));
		this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));
		
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.35);
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(30);
		this.setHealth(30);
		
		this.getWorld().addEntity(this);
	}
}
