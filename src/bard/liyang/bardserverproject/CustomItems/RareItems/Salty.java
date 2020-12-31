package bard.liyang.bardserverproject.CustomItems.RareItems;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;


public class Salty extends RareItem{

	public Salty()
	{
		super(Material.FISHING_ROD, "Salty", Arrays.asList("What treasures lie in the depths?"));
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.addUnsafeEnchantment(Enchantment.LURE, 3);
		this.addUnsafeEnchantment(Enchantment.LUCK, 20);
		
		ItemMeta im = this.getItemMeta();
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		//im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 15.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		List<String> lore = im.getLore();
		lore.add(0, ChatColor.GRAY + "Unbreaking II"); // add our own pseudo enchant text
		lore.add(0, ChatColor.GRAY + "Lure III"); // add our own pseudo enchant text
		lore.add(0, ChatColor.GRAY + "Luck of the Sea XX"); // add our own pseudo enchant text

		im.setLore(lore);
		this.setItemMeta(im);
	}
}
