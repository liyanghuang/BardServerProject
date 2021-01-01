package bard.liyang.bardserverproject.CustomItems.EpicItems;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import net.md_5.bungee.api.ChatColor;

public class AirJordans extends EpicItem{

	public AirJordans()
	{
		super(Material.GOLDEN_BOOTS, "Air Jordans", Arrays.asList("Expensive shoes that can improve your vertical"));
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 100); // add sharp 30
		this.addUnsafeEnchantment(CustomEnchants.AIRJORDANS, 1);

		ItemMeta im = this.getItemMeta();
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		//im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 15.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		List<String> lore = im.getLore();
		lore.add(0, ChatColor.GRAY + "Feather Falling C"); // add our own pseudo enchant text
		im.setLore(lore);
		this.setItemMeta(im);
	}
}
