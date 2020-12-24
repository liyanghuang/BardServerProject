package bard.liyang.bardserverproject;

import org.bukkit.plugin.java.JavaPlugin;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.CustomItems.CustomItemListener;
import bard.liyang.bardserverproject.CustomItems.EpicItems.IndraListener;
import bard.liyang.bardserverproject.CustomItems.EpicItems.NepenthesListener;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.BlackholeListener;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.GladosPortalGunListener;
import bard.liyang.bardserverproject.CustomItems.RareItems.SnowmanBowListener;
import bard.liyang.bardserverproject.Util.RandomLootGenerator;
import bard.liyang.bardserverproject.Util.RarityManager;


public class BardServerProject extends JavaPlugin{

	GladosPortalGunListener gpgl;
	
	public static void main(String [] args){}
	
	// Fired when plugin is first enabled
	@Override
	public void onEnable()
	{
		CustomEnchants.registerAllEnchants();
		addListeners(this);
		CommandManager cm = new CommandManager();
		this.getCommand("removeusers").setExecutor(cm);
	}
	
	// Fired when plugin is disabled
	@Override
	public void onDisable()
	{
		RarityManager.rm.saveCustomYml(); // save yml data
		gpgl.destroyCurrentPortals();
	}
	
	public void addListeners(BardServerProject plugin)
	{
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new NepenthesListener(this), this);
		getServer().getPluginManager().registerEvents(new RandomLootGenerator(this), this);
		getServer().getPluginManager().registerEvents(new CustomItemListener(), this);
		getServer().getPluginManager().registerEvents(new IndraListener(this), this);
		getServer().getPluginManager().registerEvents(new SnowmanBowListener(), this);
		getServer().getPluginManager().registerEvents(new BlackholeListener(this), this); 
		gpgl = new GladosPortalGunListener(this);
		getServer().getPluginManager().registerEvents(gpgl, this); 
	}
}
