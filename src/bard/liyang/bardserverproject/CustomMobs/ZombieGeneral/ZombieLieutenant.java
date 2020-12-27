package bard.liyang.bardserverproject.CustomMobs.ZombieGeneral;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntityHorseZombie;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EntityZombie;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.PathfinderGoalHurtByTarget;

public class ZombieLieutenant extends EntityZombie{

	private BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);
	
	private FixedMetadataValue meta = new FixedMetadataValue(plugin, true);

	public ZombieLieutenant(Location loc)
	{
		super(EntityTypes.ZOMBIE, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Zombie craftZombie = (Zombie)this.getBukkitEntity();
		
		craftZombie.setCustomName(ChatColor.GOLD + ""+ ChatColor.BOLD + "Zombie Lieutenant");
		craftZombie.setMetadata("ZombieLieutenant", meta);
		craftZombie.setMetadata("ZombieLieutenantMinion", new FixedMetadataValue(plugin, 0));
		craftZombie.setRemoveWhenFarAway(true);
		
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
		sword.addEnchantment(Enchantment.KNOCKBACK, 2);
		sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		sword.addUnsafeEnchantment(CustomEnchants.POISON, 1);
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
		
		this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
		this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(head));
		this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chest));
		this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(legs));
		this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));
		
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.targetSelector.a(4, new PathfinderGoalHurtByTarget(this));

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.35);
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(50);
		this.setHealth(50);
		
		EntityHorseZombie mount = new EntityHorseZombie(EntityTypes.ZOMBIE_HORSE, ((CraftWorld)loc.getWorld()).getHandle());
		mount.setPosition(loc.getX(), loc.getY(), loc.getZ());
		mount.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.5);
		mount.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(30);
		mount.setHealth(30);

		ZombieHorse craftMount = (ZombieHorse)mount.getBukkitEntity();
		craftMount.setRemoveWhenFarAway(true);
		craftMount.addPassenger(craftZombie);

		this.getWorld().addEntity(mount);
		this.getWorld().addEntity(this);
	}
}
