package bard.liyang.bardserverproject.CustomItems.RareItems;

import java.util.List;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomItems.CustomItem;

public class RareItem extends CustomItem{

	public RareItem(Material type, String name, List<String> lore)
	{
		super(type, "&e", "&9&o", name, lore, true);
	}
}
