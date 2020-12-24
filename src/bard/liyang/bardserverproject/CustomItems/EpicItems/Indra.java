package bard.liyang.bardserverproject.CustomItems.EpicItems;

import java.util.Arrays;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class Indra extends EpicItem{

	public Indra() 
	{
		super(Material.BOW, "Indra", Arrays.asList("How exactly does lightning strike underground? Who knows..."));
		this.addUnsafeEnchantment(CustomEnchants.INDRA, 1);
	}

}
