package bard.liyang.bardserverproject;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;


public class BardServerProject extends JavaPlugin{
	
	public static void main(String [] args){}
	
	// Fired when plugin is first enabled
	@Override
	public void onEnable()
	{
		registerGlow();
		Map<String,Integer> rarityMap = new HashMap<String, Integer>();
		rarityMap.put("Nepenthes", 80);
		RarityManager.rm.setRarities(rarityMap);
		RarityManager.rm.loadData(); // load used data saved from yaml
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new NepenthesListener(this), this);
		getServer().getPluginManager().registerEvents(new RandomLootGenerator(), this);
		CommandManager cm = new CommandManager();
		this.getCommand("removeusers").setExecutor(cm);
	}
	
	// Fired when plugin is disabled
	@Override
	public void onDisable()
	{
		RarityManager.rm.saveCustomYml(); // save yml data
	}
	
	public void registerGlow() {
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
}
