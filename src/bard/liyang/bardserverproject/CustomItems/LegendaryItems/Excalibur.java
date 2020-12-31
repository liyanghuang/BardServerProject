package bard.liyang.bardserverproject.CustomItems.LegendaryItems;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Excalibur extends LegendaryItem{

	public Excalibur()
	{
		super(Material.NETHERITE_SWORD, "Excalibur", Arrays.asList("Nothing too special... just a very sharp sword"));

		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 30); // add sharp 30

		ItemMeta im = this.getItemMeta();
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		//im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 15.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		List<String> lore = im.getLore();
		lore.add(0, ChatColor.GRAY + "Sharpness XXX"); // add our own pseudo enchant text
		im.setLore(lore);
		this.setItemMeta(im);
	}
}
