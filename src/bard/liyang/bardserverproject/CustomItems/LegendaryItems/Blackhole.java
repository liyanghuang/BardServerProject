package bard.liyang.bardserverproject.CustomItems.LegendaryItems;

import java.util.Arrays;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class Blackhole extends LegendaryItem{

	public Blackhole()
	{
		super(Material.CROSSBOW, "Blackhole", Arrays.asList("Spawn black holes anywhere, but why?"));
		this.addUnsafeEnchantment(CustomEnchants.BLACKHOLE, 1);
	}
}
