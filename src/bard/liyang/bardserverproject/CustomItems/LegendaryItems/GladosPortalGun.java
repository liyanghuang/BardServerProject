package bard.liyang.bardserverproject.CustomItems.LegendaryItems;

import java.util.Arrays;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class GladosPortalGun extends LegendaryItem{

	public GladosPortalGun()
	{
		super(Material.IRON_HORSE_ARMOR, "GLaDOS's Portal Gun", Arrays.asList("The cake is a lie..."));
		this.addUnsafeEnchantment(CustomEnchants.GLADOSPORTALGUN, 1);
	}
}
