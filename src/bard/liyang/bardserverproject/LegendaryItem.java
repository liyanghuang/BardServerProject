package bard.liyang.bardserverproject;

import java.util.List;
import org.bukkit.Material;

public class LegendaryItem extends CustomItem{
	
	public LegendaryItem(Material type, String name, List<String> lore)
	{
		super(type, "&6&l", "&c&o", name, lore, true);
	}

}
