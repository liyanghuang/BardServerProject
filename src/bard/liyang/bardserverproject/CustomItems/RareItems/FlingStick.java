package bard.liyang.bardserverproject.CustomItems.RareItems;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class FlingStick extends RareItem{
	
	public FlingStick()
	{
		super(Material.STICK, "FlingStick", Arrays.asList("Probably the most annoying thing to be hit with"));
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
	}
}
