package bard.liyang.bardserverproject;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import bard.liyang.bardserverproject.CustomEnchants.Glow;
import bard.liyang.bardserverproject.CustomItems.CustomItemListener;
import bard.liyang.bardserverproject.CustomItems.EpicItems.IndraListener;
import bard.liyang.bardserverproject.CustomItems.EpicItems.NepenthesListener;
import bard.liyang.bardserverproject.CustomItems.RareItems.SnowmanBowListener;
import bard.liyang.bardserverproject.Util.RandomLootGenerator;
import bard.liyang.bardserverproject.Util.RarityManager;


public class BardServerProject extends JavaPlugin{
	
	public static void main(String [] args){}
	
	// Fired when plugin is first enabled
	@Override
	public void onEnable()
	{
		Glow.registerGlow();
		Map<String,Integer> rarityMap = new HashMap<String, Integer>();
		rarityMap.put("Nepenthes", 3);
		RarityManager.rm.setRarities(rarityMap);
		RarityManager.rm.loadData(); // load used data saved from yaml
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new NepenthesListener(this), this);
		getServer().getPluginManager().registerEvents(new RandomLootGenerator(this), this);
		getServer().getPluginManager().registerEvents(new CustomItemListener(), this);
		getServer().getPluginManager().registerEvents(new IndraListener(), this);
		getServer().getPluginManager().registerEvents(new SnowmanBowListener(), this);
		CommandManager cm = new CommandManager();
		this.getCommand("removeusers").setExecutor(cm);
	}
	
	// Fired when plugin is disabled
	@Override
	public void onDisable()
	{
		RarityManager.rm.saveCustomYml(); // save yml data
	}
	
	
}
