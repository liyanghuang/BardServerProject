package bard.liyang.bardserverproject.CustomMobs.ZombieGeneral;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntityHuman;
import net.minecraft.server.v1_16_R3.EntitySkeleton;
import net.minecraft.server.v1_16_R3.EntitySpider;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalAvoidTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;

public class SkeletonLieutenant extends EntitySkeleton{

	private BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);
	private FixedMetadataValue meta = new FixedMetadataValue(plugin, true);
	public SkeletonLieutenant(Location loc)
	{
		super(EntityTypes.SKELETON, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Skeleton craftSkeleton = (Skeleton)this.getBukkitEntity();
		
		craftSkeleton.setCustomName(ChatColor.GOLD + ""+ ChatColor.BOLD + "Skeleton Lieutenant");
		craftSkeleton.setMetadata("ZombieCaptain", meta);
		craftSkeleton.setMetadata("ZombieCaptainMinion", new FixedMetadataValue(plugin, 0));	
		craftSkeleton.setRemoveWhenFarAway(true);

		ItemStack bow = new ItemStack(Material.BOW);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
		bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		bow.addUnsafeEnchantment(CustomEnchants.BLINDNESS, 2);
		ItemStack head = new ItemStack(Material.IRON_HELMET);
		head.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		ItemMeta im = head.getItemMeta();
		im.setUnbreakable(true);
		head.setItemMeta(im);
		ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
		chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
		legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);
		boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		
		this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(bow));
		this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(head));
		this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chest));
		this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(legs));
		this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));
		
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.35);
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(50);
		
		EntitySpider mount = new EntitySpider(EntityTypes.SPIDER, ((CraftWorld)loc.getWorld()).getHandle());
		mount.setPosition(loc.getX(), loc.getY(), loc.getZ());
		mount.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.5);
		mount.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(30);
		mount.goalSelector.a(2, new PathfinderGoalAvoidTarget<EntityHuman>(this, EntityHuman.class, 15, 1.0D, 1.0));
		mount.setHealth(30);

		Spider craftMount = (Spider)mount.getBukkitEntity();
		craftMount.setRemoveWhenFarAway(true);
		craftMount.addPassenger(craftSkeleton);

		this.getWorld().addEntity(mount);
		this.getWorld().addEntity(this);
	}
}
