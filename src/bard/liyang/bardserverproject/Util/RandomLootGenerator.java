package bard.liyang.bardserverproject.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomItems.EpicItems.DesertEagle;
import bard.liyang.bardserverproject.CustomItems.EpicItems.EpicItem;
import bard.liyang.bardserverproject.CustomItems.EpicItems.Indra;
import bard.liyang.bardserverproject.CustomItems.EpicItems.Nepenthes;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.Blackhole;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.Excalibur;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.GladosPortalGun;
import bard.liyang.bardserverproject.CustomItems.LegendaryItems.LegendaryItem;
import bard.liyang.bardserverproject.CustomItems.RareItems.Aegis;
import bard.liyang.bardserverproject.CustomItems.RareItems.BookOfWater;
import bard.liyang.bardserverproject.CustomItems.RareItems.FireworkBow;
import bard.liyang.bardserverproject.CustomItems.RareItems.FlingStick;
import bard.liyang.bardserverproject.CustomItems.RareItems.Heartbleed;
import bard.liyang.bardserverproject.CustomItems.RareItems.HermesBoots;
import bard.liyang.bardserverproject.CustomItems.RareItems.RareItem;
import bard.liyang.bardserverproject.CustomItems.RareItems.Salty;
import bard.liyang.bardserverproject.CustomItems.RareItems.SnowmanBow;
import bard.liyang.bardserverproject.CustomItems.UncommonItems.GenericUncommonItem;
import bard.liyang.bardserverproject.CustomItems.UncommonItems.UncommonItem;
import bard.liyang.bardserverproject.CustomMobs.MachineGunner;
import bard.liyang.bardserverproject.CustomMobs.Swapper;
import bard.liyang.bardserverproject.CustomMobs.Yumi;
import bard.liyang.bardserverproject.CustomMobs.GenericUncommonMonsters.GenericUncommonEnderman;
import bard.liyang.bardserverproject.CustomMobs.GenericUncommonMonsters.GenericUncommonSkeleton;
import bard.liyang.bardserverproject.CustomMobs.GenericUncommonMonsters.GenericUncommonSpider;
import bard.liyang.bardserverproject.CustomMobs.GenericUncommonMonsters.GenericUncommonZombie;
import bard.liyang.bardserverproject.CustomMobs.MongolSkeleton.MongolSkeleton;
import bard.liyang.bardserverproject.CustomMobs.ZombieGeneral.ZombieGeneral;

public class RandomLootGenerator implements Listener{
	
	private List<LegendaryItem> legendItems;
	private List<EpicItem> epicItems;
	private List<RareItem> rareItems;
	
	private BardServerProject plugin;
	
	public RandomLootGenerator(BardServerProject plugin)
	{
		this.plugin = plugin;
		legendItems = new ArrayList<LegendaryItem>();
		epicItems = new ArrayList<EpicItem>();
		rareItems = new ArrayList<RareItem>();
		
		// ADD ITEMS TO THE LOOT GENERATOR HERE
		// Legendary items
		legendItems.add(new Blackhole());
		legendItems.add(new GladosPortalGun());
		legendItems.add(new Excalibur());
		
		// Epic items
		epicItems.add(new Nepenthes());
		epicItems.add(new Indra());
		epicItems.add(new DesertEagle());
		
		// Rare items
		rareItems.add(new SnowmanBow());
		rareItems.add(new FlingStick());
		rareItems.add(new Heartbleed());
		rareItems.add(new Aegis());
		rareItems.add(new Salty());
		rareItems.add(new HermesBoots());
		rareItems.add(new BookOfWater());
		rareItems.add(new FireworkBow());
	}
	
	// loot drops for powerful creature deaths
	@EventHandler
	public void onCreatureDeath(EntityDeathEvent event)
	{

		if(event.getEntity().hasMetadata("LegendLoot"))
		{
			LegendaryItem li = generateLegendaryItem(.25f); // .03
			if(li != null)
			{
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), li);
				RarityManager.rm.addUser(li.getItemMeta().getDisplayName().substring(4));
			}
			EpicItem ei = generateEpicItem(.75f); // .08
			if(ei != null)
			{
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ei);
				RarityManager.rm.addUser(ei.getItemMeta().getDisplayName().substring(4));
			}	
			RareItem ri = generateRareItem(1f); // .08
			if(ri != null)
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ri);
			UncommonItem ui = generateUncommonItem(1f); // .08
			if(ui != null)
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ui);	
		}
		if(event.getEntity().hasMetadata("EpicLoot"))
		{
			EpicItem ei = generateEpicItem(.25f); // .08
			if(ei != null)
			{
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ei);
				RarityManager.rm.addUser(ei.getItemMeta().getDisplayName().substring(4));
			}	
			RareItem ri = generateRareItem(.75f); // .08
			if(ri != null)
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ri);
			UncommonItem ui = generateUncommonItem(1f); // .08
			if(ui != null)
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ui);	
		}
		if(event.getEntity().hasMetadata("RareLoot"))
		{
			RareItem ri = generateRareItem(.25f); // .08
			if(ri != null)
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ri);
			UncommonItem ui = generateUncommonItem(.75f); // .08
			if(ui != null)
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ui);	
		}
		if(event.getEntity().hasMetadata("UncommonLoot"))
		{
			UncommonItem ui = generateUncommonItem(.5f); // .08
			if(ui != null)
				event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ui);	
		}
	}
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event)
	{
		if(event.getEntityType() == EntityType.CAT && RNGesus.rng.getRandom() < 0.001)
		{
			if(RarityManager.rm.getRarity("Yumi") > RarityManager.rm.getUsed("Yumi"))
			{
				new Yumi(event.getEntity().getLocation());
				event.setCancelled(true);
				return;
			}
		}

		if(event.getSpawnReason() == SpawnReason.NATURAL && event.getEntity() instanceof Monster) 
		{
			// The item method cannot work b/c living entities cannot be cloned so we must do it this way
			// legendary

			if(RNGesus.rng.getRandom() < 0.001f)
			{
				switch(RNGesus.rng.getRandom(1))
				{
					case 0:
						new ZombieGeneral(event.getLocation());
						break;
				}
				event.setCancelled(true);
				return;
			}
			// epic
			if(RNGesus.rng.getRandom() < 0.003f)
			{
				switch(RNGesus.rng.getRandom(1))
				{
					case 0:
						new MongolSkeleton(event.getLocation());
						break;
				}			
				event.setCancelled(true);
				return;
			}
			// rare
			if(RNGesus.rng.getRandom() < 0.02)
			{
				switch(RNGesus.rng.getRandom(2))
				{
					case 0:
						new MachineGunner(event.getLocation());
						break;
					case 1:
						new Swapper(event.getLocation());
				}
				event.setCancelled(true);
				return;
			}
			// uncommon
			if(RNGesus.rng.getRandom() < 0.05f)
			{
				switch(RNGesus.rng.getRandom(4))
				{
					case 0:
						new GenericUncommonZombie(event.getLocation());
						break;
					case 1:
						new GenericUncommonSkeleton(event.getLocation());
						break;
					case 2:
						new GenericUncommonSpider(event.getLocation());
						break;
					case 3:
						new GenericUncommonEnderman(event.getLocation());
						break;

				}
				event.setCancelled(true);
				return;
			}

		}
	}
	
	@EventHandler
	public void captureItemBreaks(PlayerItemBreakEvent event)
	{
		if(event.getBrokenItem().getItemMeta().hasLore())
			RarityManager.rm.removeUser(event.getBrokenItem().getItemMeta().getDisplayName().substring(4));
	}

    @EventHandler
    public void onItemDespawnEvent(ItemDespawnEvent event) 
    {
    	if(event.getEntity().getItemStack().getItemMeta().hasLore())
			RarityManager.rm.removeUser(event.getEntity().getItemStack().getItemMeta().getDisplayName().substring(4));
    }
	
	@EventHandler
	public void captureItemDestruction(EntityDamageEvent event)
	{
		if(event.getEntity() instanceof Item)
		{
			Item item = (Item) event.getEntity(); // add meta data so we don't have multiple removals
			if(item.getItemStack().hasItemMeta() && item.getItemStack().getItemMeta().hasLore() && !item.hasMetadata("itemDestroy"))
			{
				System.out.println("destroying item");
				item.setMetadata("itemDestroy", new FixedMetadataValue(plugin, true));
				RarityManager.rm.removeUser(item.getItemStack().getItemMeta().getDisplayName().substring(4));
			}
		}
	}
	
	@EventHandler
	public void onInteractWithChest(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.hasBlock() && event.getClickedBlock().getType() == Material.CHEST)
		{
			Chest c = (Chest)event.getClickedBlock().getState();
			if(c.getLootTable() != null)
			{
				 boolean[] chosen = new boolean[c.getInventory().getSize()]; // This checks which slots are already taken in the inventory.
			     
				int slot;
				int maxTries = 10; // only try a certain number of times
				do 
				{
					slot = RNGesus.rng.getRandom(c.getInventory().getSize());
					if(c.getInventory().getItem(slot) != null)
						chosen[slot] = true;
					maxTries--;
				}
				while(chosen[slot] && maxTries > 0); // Make sure the slot does not already have an item in it.
				
				LegendaryItem li = generateLegendaryItem(.03f); // .03
				if(li != null)
				{
					c.getInventory().setItem(slot, li); // we only add a user if we know the store was successful
					RarityManager.rm.addUser(li.getItemMeta().getDisplayName().substring(4));
				}

				maxTries = 10; // only try a certain number of times
				do 
				{
					slot = RNGesus.rng.getRandom(c.getInventory().getSize());
					if(c.getInventory().getItem(slot) != null)
						chosen[slot] = true;
					maxTries--;
				}
				while(chosen[slot] && maxTries > 0); // Make sure the slot does not already have an item in it.

				EpicItem ei = generateEpicItem(.08f); // .08
				if(ei != null)
				{
					c.getInventory().setItem(slot, ei); // we only add a user if we know the store was successful
					RarityManager.rm.addUser(ei.getItemMeta().getDisplayName().substring(4));
				}

				maxTries = 10; // only try a certain number of times
				do 
				{
					slot = RNGesus.rng.getRandom(c.getInventory().getSize());
					if(c.getInventory().getItem(slot) != null)
						chosen[slot] = true;
					maxTries--;
				}
				while(chosen[slot] && maxTries > 0); // Make sure the slot does not already have an item in it.

				RareItem ri = generateRareItem(.15f); // .15
				if(ri != null)
					c.getInventory().setItem(slot, ri); // just try to add the item

				maxTries = 10; // only try a certain number of times
				do 
				{
					slot = RNGesus.rng.getRandom(c.getInventory().getSize());
					if(c.getInventory().getItem(slot) != null)
						chosen[slot] = true;
					maxTries--;
				}
				while(chosen[slot] && maxTries > 0); // Make sure the slot does not already have an item in it.

				UncommonItem ui = generateUncommonItem(.3f); // .3
				if(ui != null)
					c.getInventory().setItem(slot, ui); // just try to add the item
			}
		}
	}
	
	// this function should only return null if we don't hit the percentage or all the legendary items are used
	public LegendaryItem generateLegendaryItem(float percent)
	{
		if(RNGesus.rng.getRandom() < percent)
		{
			int legendItemsLength = legendItems.size(); // save some function calls
			int [] shuffleArray = new int[legendItemsLength]; // make array to do random shuffles
			for(int i = 0; i < legendItemsLength; i++)
				shuffleArray[i] = i;
			RNGesus.rng.shuffleArray(shuffleArray); // shuffle the array
			for(int i = 0; i < legendItemsLength; i++)
			{
				// reference the shuffle array to go through the list elements in "random" order
				String itemName = legendItems.get(shuffleArray[i]).getItemMeta().getDisplayName().substring(4); // stripping color mods we know it's 4 b/c it's legend item
				if(RarityManager.rm.getRarity(itemName) > RarityManager.rm.getUsed(itemName)) // we can make the item
				{
					LegendaryItem li = (LegendaryItem) legendItems.get(shuffleArray[i]).clone();
					li.addRarityLore(RarityManager.rm.getUsed(itemName) + 1, RarityManager.rm.getRarity(itemName));
					return li;
				}
			}
		}
		return null;
	}
	public EpicItem generateEpicItem(float percent) 
	{
		if(RNGesus.rng.getRandom() < percent)
		{
			int epicItemsLength = epicItems.size(); // save some function calls
			int [] shuffleArray = new int[epicItemsLength]; // make array to do random shuffles
			for(int i = 0; i < epicItemsLength; i++)
				shuffleArray[i] = i;
			RNGesus.rng.shuffleArray(shuffleArray); // shuffle the array
			for(int i = 0; i < epicItemsLength; i++)
			{
				String itemName = epicItems.get(shuffleArray[i]).getItemMeta().getDisplayName().substring(4); // stripping color mods we know it's 4 b/c it's epic item
				if(RarityManager.rm.getRarity(itemName) > RarityManager.rm.getUsed(itemName)) // we can make the item
				{
					EpicItem ei = (EpicItem) epicItems.get(shuffleArray[i]).clone();
					ei.addRarityLore(RarityManager.rm.getUsed(itemName) + 1, RarityManager.rm.getRarity(itemName));
					return ei;
				}
			}
		}
		return null; // return null if percentage is not right
	}
	public RareItem generateRareItem(float percent)
	{
		if(RNGesus.rng.getRandom() < percent)
			if(rareItems.size() != 0)
				return (RareItem)rareItems.get(RNGesus.rng.getRandom(rareItems.size())).clone();
		return null;
	}
	public UncommonItem generateUncommonItem(float percent) // simple function, no need to check usage
	{
		if(RNGesus.rng.getRandom() < percent)
			return new GenericUncommonItem(0);
		return null;
	}
}
