package bard.liyang.bardserverproject.CustomItems.EpicItems;

import java.util.Arrays;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;

public class Nepenthes extends EpicItem{

	public Nepenthes()
	{
		super(Material.BOW, "Nepenthes", Arrays.asList("A bow said to summon things from various realms..."));
		this.addUnsafeEnchantment(CustomEnchants.NEPENTHES, 1);
	}
}
