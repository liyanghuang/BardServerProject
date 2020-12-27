package bard.liyang.bardserverproject.CustomItems.EpicItems;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class DesertEagle extends EpicItem{
	
	public DesertEagle()
	{
		super(Material.GOLDEN_HORSE_ARMOR, "Desert Eagle", Arrays.asList("They say it only takes one shot to the head to kill..."));
		this.addUnsafeEnchantment(CustomEnchants.DESERTEAGLE, 1);
		ItemMeta im = this.getItemMeta();
		im.getPersistentDataContainer().set(DesertEagleListener.nk, PersistentDataType.LONG, 0l);
		this.setItemMeta(im);
	}
}
