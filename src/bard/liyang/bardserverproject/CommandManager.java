package bard.liyang.bardserverproject;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import bard.liyang.bardserverproject.CustomMobs.HydraSilverfish.HydraSilverfish;
import bard.liyang.bardserverproject.CustomMobs.MongolSkeleton.MongolSkeleton;
import bard.liyang.bardserverproject.CustomMobs.ZombieGeneral.ZombieGeneral;
import bard.liyang.bardserverproject.Util.RarityManager;

public class CommandManager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(label.equals("removeusers"))
		{
			RarityManager.rm.removeYMLData();
			return true;
		}
		if(label.equals("armor"))
		{
			ItemStack head = new ItemStack(Material.NETHERITE_HELMET, 1);
			ItemStack chest = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
			ItemStack legs = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
			ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS, 1);
			
			head.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			head.addEnchantment(Enchantment.DURABILITY, 3);
			chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			chest.addEnchantment(Enchantment.DURABILITY, 3);
			legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			legs.addEnchantment(Enchantment.DURABILITY, 3);
			boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
			boots.addEnchantment(Enchantment.DURABILITY, 3);
			
			Player player = Bukkit.getPlayer(sender.getName());
			player.getInventory().addItem(head);
			player.getInventory().addItem(chest);
			player.getInventory().addItem(legs);
			player.getInventory().addItem(boots);
			player.updateInventory();
			return true;
		}
		if(label.equals("general"))
		{
			new ZombieGeneral(Bukkit.getPlayer(sender.getName()).getLocation().add(4, 0, 0));
			return true;
		}
		if(label.equals("mongol"))
		{
			new MongolSkeleton(Bukkit.getPlayer(sender.getName()).getLocation().add(4, 0, 0));
			return true;
		}
		if(label.equals("hydra"))
		{
			new HydraSilverfish(Bukkit.getPlayer(sender.getName()).getLocation().add(4, 0, 0));
			return true;
		}
		if(label.equals("loadyml"))
		{
			if(sender.isOp())
				RarityManager.rm.loadData();
			return true;
		}
		if(label.equals("printyml"))
		{
			if(sender.isOp())
				RarityManager.rm.printData();
			return true;
		}
		return false;
	}
}
