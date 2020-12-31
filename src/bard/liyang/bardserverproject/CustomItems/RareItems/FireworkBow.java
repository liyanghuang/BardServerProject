package bard.liyang.bardserverproject.CustomItems.RareItems;

import java.util.Arrays;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class FireworkBow extends RareItem{

	public FireworkBow()
	{
		super(Material.BOW, "Boom Boom", Arrays.asList("Wow... pretty"));
		this.addUnsafeEnchantment(CustomEnchants.FIREWORKBOW, 1);
	}
}
