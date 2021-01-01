package bard.liyang.bardserverproject.CustomMobs.ZombieGeneral;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.Util.RNGesus;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EntityZombie;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.PathfinderGoalHurtByTarget;

public class ZombieCaptain extends EntityZombie{

	private BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);
	private FixedMetadataValue meta = new FixedMetadataValue(plugin, true);

	public ZombieCaptain(Location loc)
	{
		super(EntityTypes.ZOMBIE, ((CraftWorld)loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		Zombie craftZombie = (Zombie)this.getBukkitEntity();
		
		craftZombie.setCustomName(ChatColor.GOLD + ""+ ChatColor.BOLD + "Zombie Captain");
		craftZombie.setMetadata("ZombieCaptain", meta);
		craftZombie.setMetadata("ZombieCaptainMinion", new FixedMetadataValue(plugin, 0));
		craftZombie.setRemoveWhenFarAway(true);
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
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

		if(RNGesus.rng.getRandom(2) == 0)
			craftZombie.setBaby();
		
		this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(sword));
		this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(head));
		this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chest));
		this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(legs));
		this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));
		
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.targetSelector.a(4, new PathfinderGoalHurtByTarget(this));

		this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(.35);
		this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(30);
		this.setHealth(30);
		
		this.getWorld().addEntity(this);
	}
}
