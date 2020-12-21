package bard.liyang.bardserverproject.CustomItems.LegendaryItems;

import java.util.List;
import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomItems.CustomItem;

public class LegendaryItem extends CustomItem{
	
	public LegendaryItem(Material type, String name, List<String> lore)
	{
		super(type, "&6&l", "&c&o", name, lore, true);
	}

}
