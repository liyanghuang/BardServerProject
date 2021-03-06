package bard.liyang.bardserverproject.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class RarityManager {

	public static RarityManager rm = new RarityManager();
	private Map<String, Integer> itemsUsed;
	private Map<String, Integer> rarityMap;
	private FileConfiguration usedConfig;
	private boolean destroyYML = false;
	
	public RarityManager()
	{
		Map<String,Integer> rarityMap = new HashMap<String, Integer>();

		// RARITIES NEED TO BE PUT INTO THE MAP FOR THE MAX
		// MAKE SURE THE NAME IS THE SAME AS THE NAME GIVEN TO THE WEAPON
		rarityMap.put("Nepenthes", 3);
		rarityMap.put("Indra", 3);
		rarityMap.put("Blackhole", 1);
		rarityMap.put("GLaDOS's Portal Gun", 1);
		rarityMap.put("Desert Eagle", 3);
		rarityMap.put("Excalibur", 1);
		rarityMap.put("Yumi", 1);
		rarityMap.put("Lissandra's Embrace", 1);
		rarityMap.put("Air Jordans", 3);
		// END RARITY SECTION

		this.rarityMap = rarityMap;
		loadData();
	}
	
	public void removeUser(String item)
	{
		System.out.println("Removing user for " + item);
		if(itemsUsed.containsKey(item))
		{
			if(itemsUsed.get(item) > 0)
			{
				itemsUsed.put(item, itemsUsed.get(item) - 1);
				usedConfig.set(item, itemsUsed.get(item)); // we don't sub one b/c it's subbed before
			}
		} // nothing happens if we try to remove a user that isn't there
	}
	
	public void addUser(String item)
	{
		System.out.println("Adding user for " + item);
		if(itemsUsed.containsKey(item))
		{
			itemsUsed.put(item, itemsUsed.get(item) + 1);
			usedConfig.set(item, itemsUsed.get(item)); // we don't add one b/c it's added before
		}
		else
		{
			itemsUsed.put(item, 1);
			usedConfig.createSection(item);
			usedConfig.set(item, 1);
		}
	}
	
	// returns -1 on not finding key
	public int getUsed(String item)
	{
		if(itemsUsed.containsKey(item))
			return itemsUsed.get(item);
		return 0;
	}

	public int getRarity(String item)
	{
		if(rarityMap.containsKey(item))
			return rarityMap.get(item);
		return -1;
	}
	
	public void printData()
	{
		System.out.println("Rarity map");
		for(String s: rarityMap.keySet())
		{
			System.out.println(s + ": " + rarityMap.get(s));
		}
		System.out.println("Rarity used");
		for(String s: itemsUsed.keySet())
		{
			System.out.println(s + ": " + itemsUsed.get(s));
		}
	}
	
	// set rarities before loading data
	public void setRarities(Map<String, Integer> rarityMap)
	{
		this.rarityMap = rarityMap;
	}
	
	// save yml data to file
	public void saveCustomYml() 
	{
		if(destroyYML)
			usedConfig = new YamlConfiguration(); // set it to empty config
		try 
		{
			usedConfig.save("used.yml");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	// destroys the YML file on next disable
	public void removeYMLData()
	{
		destroyYML = true;
	}
	
	// called to load the data, must be called after setting plugin
	public void loadData()
	{
		itemsUsed = new HashMap<String, Integer>();
		usedConfig = new YamlConfiguration();
		try 
		{
			usedConfig.load("used.yml");
		}
		catch(InvalidConfigurationException e)
		{
			
		}
		catch(FileNotFoundException e)
		{
			try 
			{
				File usedConfig = new File("used.yml");
				if(!usedConfig.createNewFile())
					System.out.println("file exists somehow"); // should never happen but if it does then lmao
			}
			catch(IOException ev)
			{
				ev.printStackTrace();
			}
			// attempt to load data again with created file
			loadData();
			return;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		// load yaml data into a map
		for(String item :rarityMap.keySet())
		{
			if(usedConfig.contains(item))
			{
				itemsUsed.put(item, usedConfig.getInt(item));
			}
		}
	}
}
