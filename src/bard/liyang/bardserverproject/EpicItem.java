package bard.liyang.bardserverproject;

import java.util.List;
import org.bukkit.Material;

public class EpicItem extends CustomItem{
	
	public EpicItem(Material type, String name, List<String> lore)
	{
		super(type, "&3&l", "&a&o", name, lore, true);
	}
}
