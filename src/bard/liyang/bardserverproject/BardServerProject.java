package bard.liyang.bardserverproject;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;


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
