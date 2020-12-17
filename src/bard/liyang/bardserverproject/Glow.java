package bard.liyang.bardserverproject;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class Glow extends Enchantment {

	public static void registerGlow() {
		try 
		{
			java.lang.reflect.Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		try 
		{
			Glow glow = new Glow("glow");
			Enchantment.registerEnchantment(glow);
		}
		catch (IllegalArgumentException e)
		{
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
	
	public Glow(String id) {
		super(NamespacedKey.minecraft(id));
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return null;
	}

	@Override
	public int getMaxLevel() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public int getStartLevel() {
		return 0;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}
}
