package bard.liyang.bardserverproject;

import org.bukkit.plugin.java.JavaPlugin;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.CustomItems.CustomItemListener;
import bard.liyang.bardserverproject.CustomItems.EpicItems.DesertEagleListener;
import bard.liyang.bardserverproject.CustomItems.EpicItems.IndraListener;
import bard.liyang.bardserverproject.CustomItems.EpicItems.NepenthesListener;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.BlackholeListener;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.GladosPortalGunListener;
import bard.liyang.bardserverproject.CustomItems.RareItems.SnowmanBowListener;
import bard.liyang.bardserverproject.CustomItems.UncommonItems.GenericUncommonItemListener;
import bard.liyang.bardserverproject.CustomMobs.HydraSilverfishListener;
import bard.liyang.bardserverproject.CustomMobs.MongolSkeletonListener;
import bard.liyang.bardserverproject.CustomMobs.ZombieGeneral.ZombieGeneralListener;
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
		this.getCommand("armor").setExecutor(cm);
		this.getCommand("general").setExecutor(cm);
		this.getCommand("mongol").setExecutor(cm);
		this.getCommand("hydra").setExecutor(cm);
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
		getServer().getPluginManager().registerEvents(new IndraListener(), this);
		getServer().getPluginManager().registerEvents(new SnowmanBowListener(), this);
		getServer().getPluginManager().registerEvents(new BlackholeListener(this), this); 
		getServer().getPluginManager().registerEvents(new DesertEagleListener(), this);
		getServer().getPluginManager().registerEvents(new GenericUncommonItemListener(), this);
		getServer().getPluginManager().registerEvents(new ZombieGeneralListener(), this);
		getServer().getPluginManager().registerEvents(new MongolSkeletonListener(), this);
		getServer().getPluginManager().registerEvents(new HydraSilverfishListener(), this);
		gpgl = new GladosPortalGunListener(this);
		getServer().getPluginManager().registerEvents(gpgl, this); 
	}
}
