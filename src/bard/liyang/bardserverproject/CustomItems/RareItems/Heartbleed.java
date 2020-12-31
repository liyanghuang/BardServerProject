package bard.liyang.bardserverproject.CustomItems.RareItems;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class Heartbleed extends RareItem{

	public Heartbleed()
	{
		super(Material.DIAMOND_AXE, "Heartbleed", Arrays.asList("Life must be taken away to be given"));
		this.addUnsafeEnchantment(CustomEnchants.HEARTBLEED, 1);
		this.addEnchantment(Enchantment.DAMAGE_ALL, 5);
	}
}
