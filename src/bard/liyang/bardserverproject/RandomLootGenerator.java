package bard.liyang.bardserverproject;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class RandomLootGenerator implements Listener{
	
	private List<LegendaryItem> legendItems;
	private List<EpicItem> epicItems;
	private List<RareItem> rareItems;
	private List<UncommonItem> uncommonItems;
	private BardServerProject plugin;
	
	public RandomLootGenerator(BardServerProject plugin)
	{
		this.plugin = plugin;
		legendItems = new ArrayList<LegendaryItem>();
		epicItems = new ArrayList<EpicItem>();
		rareItems = new ArrayList<RareItem>();
		uncommonItems = new ArrayList<UncommonItem>();
		
		epicItems.add(new Nepenthes());

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
			if(item.getItemStack().getItemMeta().hasLore() && !item.hasMetadata("itemDestroy"))
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
				LegendaryItem li = generateLegendaryItem(.02f);
				if(li != null)
					if(c.getInventory().addItem(li).isEmpty()) // we only add a user if we know the store was successful
						RarityManager.rm.addUser(li.getItemMeta().getDisplayName().substring(4));

				EpicItem ei = generateEpicItem(0.66f);
				if(ei != null)
					if(c.getInventory().addItem(ei).isEmpty()) // we only add a user if we know the store was successful
						RarityManager.rm.addUser(ei.getItemMeta().getDisplayName().substring(4));

				RareItem ri = generateRareItem(0.15f);
				if(ri != null)
					c.getInventory().addItem(ri); // just try to add the item

				UncommonItem ui = generateUncommonItem(0.3f);
				if(ui != null)
					c.getInventory().addItem(ui); // just try to add the item
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
					LegendaryItem li = (LegendaryItem) legendItems.get(i).clone();
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
					EpicItem ei = (EpicItem) epicItems.get(i).clone();
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
			if(uncommonItems.size() != 0)
				return (UncommonItem)uncommonItems.get(RNGesus.rng.getRandom(uncommonItems.size())).clone();
		return null;
	}
}
