package bard.liyang.bardserverproject.CustomEnchants;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchants {

	public static Enchantment GLOW = new Glow("glow", "Glow", 1);
	public static Enchantment NEPENTHES = new NepenthesEnchant("nepenthes", "Nepenthes", 1);
	public static Enchantment SNOWMANBOW = new SnowmanBowEnchant("snowmanbow", "SnowmanBow", 1);
	public static Enchantment INDRA = new IndraEnchant("indra", "Indra", 1);
	public static Enchantment BLACKHOLE = new BlackholeEnchant("blackhole", "Blackhole", 1);
	public static Enchantment GLADOSPORTALGUN = new GladosPortalGunEnchant("gladosportalgun", "GladosPortalGun", 1);


	
	public static void registerAllEnchants()
	{
		registerEnchant(GLOW);
		registerEnchant(NEPENTHES);
		registerEnchant(SNOWMANBOW);
		registerEnchant(INDRA);
		registerEnchant(BLACKHOLE);
		registerEnchant(GLADOSPORTALGUN);
	}

	public static void registerEnchant(Enchantment enchant)
	{
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
			Enchantment.registerEnchantment(enchant);
		}
		catch (IllegalArgumentException e)
		{
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
