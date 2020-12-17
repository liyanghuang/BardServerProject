package bard.liyang.bardserverproject;

import java.util.List;

import org.bukkit.Material;

public class UncommonItem extends CustomItem{

	public UncommonItem(Material type, String name, List<String> lore)
	{
		super(type, "&d", "&b&o", name, lore, true);
	}
}

