package bard.liyang.bardserverproject;

import java.util.List;

import org.bukkit.Material;

public class RareItem extends CustomItem{

	public RareItem(Material type, String name, List<String> lore)
	{
		super(type, "&4", "&d&o", name, lore, true);
	}
}
