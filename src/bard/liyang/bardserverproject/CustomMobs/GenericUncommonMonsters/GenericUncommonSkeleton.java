package bard.liyang.bardserverproject.CustomMobs.GenericUncommonMonsters;

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
import bard.liyang.bardserverproject.CustomItems.UncommonItems.GenericUncommonItem;
import bard.liyang.bardserverproject.Util.RNGesus;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntitySkeleton;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;

public class GenericUncommonSkeleton extends EntitySkeleton{

	private FixedMetadataValue meta = new FixedMetadataValue(BardServerProject.getPlugin(BardServerProject.class), true);

	public GenericUncommonSkeleton(Location loc)
	{
		super(EntityTypes.SKELETON, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Skeleton craftSkeleton = (Skeleton)this.getBukkitEntity();
		
		craftSkeleton.setCustomName(ChatColor.DARK_GREEN + GenericNameGenerator.generateGenericName());
		craftSkeleton.setCustomNameVisible(true);
		craftSkeleton.setMetadata("UncommonLoot", meta);
		craftSkeleton.setRemoveWhenFarAway(true);
		
		ItemStack bow = new GenericUncommonItem(2);
		ItemStack head; 
		switch(RNGesus.rng.getRandom(6))
		{
			case 0:
				head = new ItemStack(Material.LEATHER_HELMET);
				break;
			case 1:
				head = new ItemStack(Material.CHAINMAIL_HELMET);
				break;
			case 2:
				head = new ItemStack(Material.GOLDEN_HELMET);
				break;
			case 3:
				head = new ItemStack(Material.IRON_HELMET);
				break;
			case 4:
				head = new ItemStack(Material.DIAMOND_HELMET);
				break;
			default:
				head = new ItemStack(Material.NETHERITE_HELMET);
				break;
		}
		int level = RNGesus.rng.getRandom(4) + 1;
		head.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		ItemMeta im = head.getItemMeta();
		im.setUnbreakable(true);
		head.setItemMeta(im);
		ItemStack chest;
		switch(RNGesus.rng.getRandom(6))
		{
			case 0:
				chest = new ItemStack(Material.LEATHER_CHESTPLATE);
				break;
			case 1:
				chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
				break;
			case 2:
				chest = new ItemStack(Material.GOLDEN_CHESTPLATE);
				break;
			case 3:
				chest = new ItemStack(Material.IRON_CHESTPLATE);
				break;
			case 4:
				chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
				break;
			default:
				chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
				break;
		}
		level = RNGesus.rng.getRandom(4) + 1;
		chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		ItemStack legs;
		switch(RNGesus.rng.getRandom(6))
		{
			case 0:
				legs = new ItemStack(Material.LEATHER_LEGGINGS);
				break;
			case 1:
				legs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
				break;
			case 2:
				legs = new ItemStack(Material.GOLDEN_LEGGINGS);
				break;
			case 3:
				legs = new ItemStack(Material.IRON_LEGGINGS);
				break;
			case 4:
				legs = new ItemStack(Material.DIAMOND_LEGGINGS);
				break;
			default:
				legs = new ItemStack(Material.NETHERITE_LEGGINGS);
				break;
		}
		level = RNGesus.rng.getRandom(4) + 1;
		legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		ItemStack boots;
		switch(RNGesus.rng.getRandom(6))
		{
			case 0:
				boots = new ItemStack(Material.LEATHER_BOOTS);
				break;
			case 1:
				boots = new ItemStack(Material.CHAINMAIL_BOOTS);
				break;
			case 2:
				boots = new ItemStack(Material.GOLDEN_BOOTS);
				break;
			case 3:
				boots = new ItemStack(Material.IRON_BOOTS);
				break;
			case 4:
				boots = new ItemStack(Material.DIAMOND_BOOTS);
				break;
			default:
				boots = new ItemStack(Material.NETHERITE_BOOTS);
				break;
		}
		level = RNGesus.rng.getRandom(4) + 1;
		boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		
		this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(bow));
		this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(head));
		this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chest));
		this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(legs));
		this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));
		
		this.goalSelector.a(1, new PathfinderGoalFloat(this));

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.4);
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(40);
		this.setHealth(40);
		this.getWorld().addEntity(this);
	}
}
