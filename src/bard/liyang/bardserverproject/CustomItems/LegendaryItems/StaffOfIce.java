package bard.liyang.bardserverproject.CustomItems.LegendaryItems;

import java.util.Arrays;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class StaffOfIce extends LegendaryItem{
	
	public StaffOfIce()
	{
		super(Material.BLAZE_ROD, "Lissandra's Embrace", Arrays.asList("Damn it's cold in here..."));
		this.addUnsafeEnchantment(CustomEnchants.STAFFOFICE, 1);
	}

}
