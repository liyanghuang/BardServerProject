package bard.liyang.bardserverproject.CustomEnchants;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchants {

	public static Enchantment GLOW = new Glow("glow", "Glow", 1);
	public static Enchantment NEPENTHES = new NepenthesEnchant("nepenthes", "Nepenthes", 1);
	public static Enchantment SNOWMANBOW = new SnowmanBowEnchant("snowmanbow", "SnowmanBow", 1);
	public static Enchantment INDRA = new IndraEnchant("indra", "Indra", 1);
	public static Enchantment BLACKHOLE = new BlackholeEnchant("blackhole", "Blackhole", 1);
	public static Enchantment GLADOSPORTALGUN = new GladosPortalGunEnchant("gladosportalgun", "GladosPortalGun", 1);
	public static Enchantment DESERTEAGLE = new DesertEagleEnchant("deserteagle", "DesertEagle", 1);
	public static Enchantment POISON = new PoisonEnchant("poison", "Poison", 2);
	public static Enchantment SLOWNESS = new SlownessEnchant("slowness", "Slowness", 2);
	public static Enchantment BLINDNESS = new BlindnessEnchant("blindness", "Blindness", 2);
	public static Enchantment WITHER = new WitherEnchant("wither", "Wither", 2);
	public static Enchantment HEARTBLEED = new HeartbleedEnchant("heartbleed", "Heartbleed", 1);
	public static Enchantment BOOKOFWATER = new BookOfWaterEnchant("bookofwater", "BookOfWater", 1);
	public static Enchantment FIREWORKBOW = new FireworkBowEnchant("fireworkbow", "FireworkBow", 1);



	
	public static void registerAllEnchants()
	{
		registerEnchant(GLOW);
		registerEnchant(NEPENTHES);
		registerEnchant(SNOWMANBOW);
		registerEnchant(INDRA);
		registerEnchant(BLACKHOLE);
		registerEnchant(GLADOSPORTALGUN);
		registerEnchant(DESERTEAGLE);
		registerEnchant(POISON);
		registerEnchant(SLOWNESS);
		registerEnchant(BLINDNESS);
		registerEnchant(WITHER);
		registerEnchant(HEARTBLEED);
		registerEnchant(BOOKOFWATER);
		registerEnchant(FIREWORKBOW);
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
