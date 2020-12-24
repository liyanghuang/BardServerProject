package bard.liyang.bardserverproject.CustomItems.RareItems;

import java.util.Arrays;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class SnowmanBow extends RareItem{

	public SnowmanBow()
	{
		super(Material.BOW, "Frosty", Arrays.asList("Who the fuck taught Frosty how to build snowmen?!?!?"));
		this.addUnsafeEnchantment(CustomEnchants.SNOWMANBOW, 1);
	}
}
