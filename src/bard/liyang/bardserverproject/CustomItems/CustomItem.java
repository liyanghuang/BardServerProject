package bard.liyang.bardserverproject.CustomItems;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bard.liyang.bardserverproject.CustomEnchants.Glow;

public class CustomItem extends ItemStack{
	
	private String loreColorMods;
	
	// Item adding checklist:
	// Make item class
	// Make listener class
	// Register listener
	// add item to rarity list (if legendary or epic)
	
	public CustomItem(Material Type, String nameColorMods, String loreColorMods, String name, List<String> lore, boolean shiny)
	{
		super(Type, 1);
		this.loreColorMods = loreColorMods;
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(bard.liyang.bardserverproject.Util.ColorConverter.color(nameColorMods + name));
		for(int i = 0; i < lore.size(); i++)
			lore.set(i, bard.liyang.bardserverproject.Util.ColorConverter.color(loreColorMods + lore.get(i)));
		im.setLore(lore);
		if(shiny)
			im.addEnchant(new Glow("glow"), 1, true);
		this.setItemMeta(im);
	}
	
	public void addRarityLore(int itemNum, int available)
	{
		System.out.println("Attempting to add rarity lore");
		ItemMeta im = this.getItemMeta();
		List<String> lore = im.getLore();
		lore.add(bard.liyang.bardserverproject.Util.ColorConverter.color(loreColorMods+ itemNum + " of " + available));
		im.setLore(lore);
		System.out.println(this.setItemMeta(im));
	}
}
