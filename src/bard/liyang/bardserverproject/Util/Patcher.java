package bard.liyang.bardserverproject.Util;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import net.md_5.bungee.api.ChatColor;

public class Patcher {

	public static void patch()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			for(ItemStack i : p.getInventory())
			{
				if(i != null && i.hasItemMeta())
				{
					if(i.getItemMeta().hasEnchant(CustomEnchants.AIRJORDANS) ||
					(i.getItemMeta().hasEnchant(CustomEnchants.BLACKHOLE)) ||
					(i.getItemMeta().hasEnchant(CustomEnchants.INDRA)) ||
					(i.getItemMeta().hasEnchant(CustomEnchants.NEPENTHES)) ||
					(i.getItemMeta().hasEnchant(CustomEnchants.DESERTEAGLE)) ||
					(i.getItemMeta().hasEnchant(CustomEnchants.GLADOSPORTALGUN)) ||
					(i.getItemMeta().hasEnchant(CustomEnchants.STAFFOFICE)))
					{
						i.addUnsafeEnchantment(CustomEnchants.VANISHING, 1);
						ItemMeta im = i.getItemMeta();
						List<String> l = im.getLore();
						l.add(0, ChatColor.RED + "" + ChatColor.ITALIC + "Will return to the world upon death"); // add our own pseudo enchant text
						im.setLore(l);
						i.setItemMeta(im);
					}
				}
			}
		}
	}
}
