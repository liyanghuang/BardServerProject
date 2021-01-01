package bard.liyang.bardserverproject.CustomItems.EpicItems;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.CustomItems.CustomItem;
import net.md_5.bungee.api.ChatColor;

public class EpicItem extends CustomItem{
	
	public EpicItem(Material type, String name, List<String> lore)
	{
		super(type, "&5&l", "&9&o", name, lore, true);
		this.addUnsafeEnchantment(CustomEnchants.VANISHING, 1);
		
		ItemMeta im = this.getItemMeta();
		List<String> l = im.getLore();
		l.add(0, ChatColor.RED + "" + ChatColor.ITALIC + "Will return to the world upon death"); // add our own pseudo enchant text
		im.setLore(l);
		this.setItemMeta(im);
	}
}
