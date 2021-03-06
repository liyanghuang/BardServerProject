package bard.liyang.bardserverproject.CustomItems.EpicItems;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class Indra extends EpicItem{

	public Indra() 
	{
		super(Material.BOW, "Indra", Arrays.asList("How exactly does lightning strike underground? Who knows..."));
		this.addUnsafeEnchantment(CustomEnchants.INDRA, 1);
		ItemMeta im = this.getItemMeta();
		im.getPersistentDataContainer().set(IndraListener.nk, PersistentDataType.LONG, 0l);
		this.setItemMeta(im);
	}

}
