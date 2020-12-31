package bard.liyang.bardserverproject.CustomItems.RareItems;

import java.util.Arrays;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class BookOfWater extends RareItem{

	public BookOfWater()
	{
		super(Material.BOOK, "Book of Water", Arrays.asList("They say water washes away everything"));
		this.addUnsafeEnchantment(CustomEnchants.BOOKOFWATER, 1);
	}
}
