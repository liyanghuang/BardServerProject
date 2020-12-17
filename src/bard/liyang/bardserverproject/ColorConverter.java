package bard.liyang.bardserverproject;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ColorConverter {
	
	public static String color(String coloredString)
	{
		return ChatColor.translateAlternateColorCodes('&', coloredString);
	}
	
	@Deprecated
	public static String convertBack(String coloredString)
	{
		for (int i = 0; i < coloredString.length(); i++) {
			if (coloredString.charAt(i) == ChatColor.COLOR_CHAR) {
				char c = coloredString.charAt(i + 1);
				coloredString= coloredString.replaceAll("" + ChatColor.COLOR_CHAR + c, ""); 
		 	} 
		}
		return coloredString;
	}
	
	public static ItemMeta colorItemMeta(ItemStack item, String coloredString)
	{
		ItemMeta itemMeta = item.getItemMeta();
		String coloredName = color(coloredString);
		itemMeta.setDisplayName(coloredName);
		itemMeta.setLore(itemMeta.getLore());
		return itemMeta;
	}
	
	public static ItemStack colorItem(ItemStack item, String coloredString)
	{
		ItemMeta itemMeta = item.getItemMeta();
		String coloredName = color(coloredString);
		itemMeta.setDisplayName(coloredName);
		itemMeta.setLore(itemMeta.getLore());
		item.setItemMeta(itemMeta);
		return item;
	}
}
