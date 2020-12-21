package bard.liyang.bardserverproject.CustomItems.UncommonItems;

import java.util.List;

import org.bukkit.Material;

import bard.liyang.bardserverproject.CustomItems.CustomItem;

public class UncommonItem extends CustomItem{

	public UncommonItem(Material type, String name, List<String> lore)
	{
		super(type, "&d", "&b&o", name, lore, true);
	}
}

