package bard.liyang.bardserverproject.CustomItems.UncommonItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import bard.liyang.bardserverproject.Util.RNGesus;
import bard.liyang.bardserverproject.Util.RomanNumber;
import net.md_5.bungee.api.ChatColor;

public class GenericUncommonItem extends UncommonItem{
	
	public GenericUncommonItem()
	{
		super(Material.DIAMOND_SWORD, "Placeholder Name", Arrays.asList("Placeholder lore"));
		
		// I am putting all the strings in the constructor b/c i'm not sure if it's good to keep these arrays in memory for the entire
		// time the plugin is running. This sacrifices some overhead whenever an uncommon item is created (which shouldn't be too common)
		// but saves us having to keep these huge arrays in memory

		String[] creatorList = {
				"Ironclad", 
				"Smithen", 
				"Smelting Pot", 
				"The Clinker Shop", 
				"The Smithy",
				"The Ancient Anvil",
				"Hammer Time",
				"The Clank Tank",
				"Striking Hot",
				"Sparks and Flames",
				"The Dawnforge of Queen Almaraan",
				"The First Enchanter’s Shame",
				"Catalyst Manuscript",
				"The Worldcarver",
				"The Shaper of Wyrms",
				"Protean Indexer",
				"The Voidlight Anvil",
				"The Pyreforge",
				"The Swordgift Stone",
				"The Scaffold of All Things",
				"Crowned-in-Iron Industries",
				"Genesis Formworks",
				"Futureworks Limited",
				"Carbon Throne",
				"The Metal Man Foundries",
				"Horizon Wrights",
				"Men of the First Mountain",
				"Corestriker Platforms",
				"Chasm and Hammer",
				"The East Arcanite Company",
				"Keepers of the New Spark",
				"Auditors of the Hilt",
				"The Authors of Iron",
				"The School of Titanium",
				"Dreadnaught Drakesteel",
				"Warbreaker Arms",
				"The Doompause Emporium",
				"Sacrificing Steel",
				"Friends of the First Sword",
				"Titanstone Blacksmiths",
				"Heatwaker",
				"Advantager’s Swordstock",
				"Metal from the Dark Mile",
				"Fable Bladed",
				"Bimbooboo the Frog",
				"Frog the Toad"
		};
		String[] adjectiveList = {
				"old-style automatic",
				"short-range selective",
				"now undetectable",
				"not-quite-lethal",
				"antique and archaic",
				"murderous serpentine",
				"potent and very useful",
				"merely remote-controlled",
				"admittedly irresistible",
				"weighted and padded",
				"indisputably finest",
				"remarkably stupid",
				"flimsy and homemade",
				"nuclear, chemical or biological",
				"new and illegal",
				"useful and usable",
				"medieval and oriental",
				"heavy-duty military",
				"battle-special",
				"strange but hardly effective",
				"scaled-down automatic",
				"stubby and squat",
				"one-size-fits-all military",
				"lighter and sharper",
				"limited thermonuclear",
				"mobile and effective",
				"devastating incendiary",
				"long-range personal",
				"effective hybrid",
				"wondrously mighty",
				"peculiar tubular",
				"ornate traditional",
				"sub-lateral",
				"life-saving secret",
				"intercontinental",
				"powerful and projective",
				"hitherto invincible and invulnerable",
				"fatal norwegian",
				"rare automatic",
				"terrible rhetorical",
				"simple and seemingly insignificant",
				"personal automatic",
				"probably indifferent",
				"nipponese nuclear",
				"simple but destructive",
				"deadliest surface-to-air",
				"efficacious revolutionary",
				"new and uncommon",
				"formidable and most respectable",
				"poor lethal",
				"ponderous and severe-looking",
				"few unconventional",
				"nuclear, chemical and biological",
				"magical and intelligent",
				"biggest-ever",
				"soviet-made automatic",
				"primitive explosive",
				"small and destructive",
				"sophisticated automatic",
				"explosive-metal",
				"long, huge and heavy",
				"archaic but still serviceable",
				"ancient explosive",
				"silent modern",
				"horrible technological",
				"beautiful and vicious",
				"useless exotic",
				"irresistible and sexy",
				"better in every way",
				"differently built",
				"safest but powerful",
				"personal and rudimentary",
				"effective and deployable",
				"horrifying biological",
				"last and most effective",
				"exotic personal",
				"short-of-war",
				"lighter and handier",
				"quintessential australian",
				"mobile high-tech",
				"potent yet dazzling",
				"french-made durandal",
				"deadly biochemical",
				"offensive strategic",
				"efficient and irresistible",
				"actually strategic",
				"possibly short-range",
				"more lethal",
				"efficient but unspectacular",
				"curved and milky",
				"automatic and invincible",
				"relatively old-fashioned",
				"third-generation nuclear",
				"old and unyielding",
				"admittedly effective",
				"enormously destructive",
				"modern high-quality",
				"impromptu magnetic",
				"overwhelmingly defensive",
				"deliberate and directional",
				"mostly physical",
				"heavier hacking",
				"last in-house",
				"outlandishly ornate",
				"new rapid-fire",
				"great all-purpose",
				"terribly weird"	
		};
		String[] capitalAdjectives = {
				"Old-Style Automatic",
				"Short-Range Selective",
				"Now Undetectable",
				"Not-Quite-Lethal",
				"Antique and Archaic",
				"Murderous Serpentine",
				"Potent and Very Useful",
				"Merely Remote-Controlled",
				"Admittedly Irresistible",
				"Weighted and Padded",
				"Indisputably Finest",
				"Remarkably Stupid",
				"Flimsy and Homemade",
				"Nuclear, Chemical Or Biological",
				"New and Illegal",
				"Useful and Usable",
				"Medieval and Oriental",
				"Heavy-Duty Military",
				"Battle-Special",
				"Strange but Hardly Effective",
				"Scaled-Down Automatic",
				"Stubby and Squat",
				"One-Size-Fits-All Military",
				"Lighter and Sharper",
				"Limited Thermonuclear",
				"Mobile and Effective",
				"Devastating Incendiary",
				"Long-Range Personal",
				"Effective Hybrid",
				"Wondrously Mighty",
				"Peculiar Tubular",
				"Ornate Traditional",
				"Sub-Lateral",
				"Life-Saving Secret",
				"Intercontinental",
				"Powerful and Projective",
				"Hitherto Invincible and Invulnerable",
				"Fatal Norwegian",
				"Rare Automatic",
				"Terrible Rhetorical",
				"Simple and Seemingly Insignificant",
				"Personal Automatic",
				"Probably Indifferent",
				"Nipponese Nuclear",
				"Simple but Destructive",
				"Deadliest Surface-To-Air",
				"Efficacious Revolutionary",
				"New and Uncommon",
				"Formidable and Most Respectable",
				"Poor Lethal",
				"Ponderous and Severe-Looking",
				"Few Unconventional",
				"Nuclear, Chemical and Biological",
				"Magical and Intelligent",
				"Biggest-Ever",
				"Soviet-Made Automatic",
				"Primitive Explosive",
				"Small and Destructive",
				"Sophisticated Automatic",
				"Explosive-Metal",
				"Long, Huge and Heavy",
				"Archaic but Still Serviceable",
				"Ancient Explosive",
				"Silent Modern",
				"Horrible Technological",
				"Beautiful and Vicious",
				"Useless Exotic",
				"Irresistible and Sexy",
				"Better in Every Way",
				"Differently Built",
				"Safest but Powerful",
				"Personal and Rudimentary",
				"Effective and Deployable",
				"Horrifying Biological",
				"Last and Most Effective",
				"Exotic Personal",
				"Short-Of-War",
				"Lighter and Handier",
				"Quintessential Australian",
				"Mobile High-Tech",
				"Potent Yet Dazzling",
				"French-Made Durandal",
				"Deadly Biochemical",
				"Offensive Strategic",
				"Efficient and Irresistible",
				"Actually Strategic",
				"Possibly Short-Range",
				"More Lethal",
				"Efficient but Unspectacular",
				"Curved and Milky",
				"Automatic and Invincible",
				"Relatively Old-Fashioned",
				"Third-Generation Nuclear",
				"Old and Unyielding",
				"Admittedly Effective",
				"Enormously Destructive",
				"Modern High-Quality",
				"Impromptu Magnetic",
				"Overwhelmingly Defensive",
				"Deliberate and Directional",
				"Mostly Physical",
				"Heavier Hacking",
				"Last In-House",
				"Outlandishly Ornate",
				"New Rapid-Fire",
				"Great All-Purpose",
				"Terribly Weird"	
		};
		String[] swordNames = {
				"Egyptian Khopesh",
				"Algerian Flyssa",
				"Moroccan Nimcha",
				"Somalian Billao",
				"Central African Mambele",
				"Chinese Butterfly Sword (蝴蝶雙刀)",
				"Chinese Dadao (大刀)",
				"Chinese Shuangshou Jian (雙手劍)",
				"Japanese Ōdachi (大太刀)",
				"Japanese Katana (刀)",
				"Japanese Wakizashi (脇差)",
				"Japanese Iaitō (居合刀)",
				"Japanese Chokutō (直刀)",
				"Korean Hwandudaedo (환두대도)",
				"Korean Saingeom (사인검)",
				"Indonesian Karambit",
				"Malaysian Parang",
				"Filipino Gulok",
				"Thai Krabi",
				"Indian Kayamkulam Vaal",
				"Arabian Scimitar",
				"Roman Gladius",
				"Greek Harpe",
				"Greco-Roman Spatha",
				"European Longsword",
				"Scottish Claymore",
				"French Rapier"
		};
		String[] axeNames = {
				"European Halberd",
				"French Francisca",
				"Viking Skeggox",
				"Greek Labrys",
				"Medieval War Axe",
				"English Pollaxe",
				"Persian Tabarzin",
				"Chinese Qi (鏚)",
				"Chinese Yue (鉞)",
				"Chinese Fu (斧)",
				"Indian Parashu",
				"Filipino Panabas",
				"European Bardiche",
				"Sri Lankan Keteriya",
				"Vietnamese Dong Son",
				"Roman Fasces",
				"Russian Sovnya",
				"North American Tomahawk",
				"Japanese Ono (斧)"
		};
		String[] tridentNames = {
				"Medieval Lance",
				"18th Century Bayonet",
				"Japanese Yari (槍)",
				"Korean Cheolpa (철파)",
				"Indian Trishula",
				"Japanese Naginata (薙刀)",
				"Chinese Ji (戟)",
				"Filipino Sibat",
				"Indonesian Arbir",
				"European Military Fork",
				"English Pike",
				"Macedonian Sarissa",
				"Greek Trident",
				"German Ranseur",
				"18th Century Harpoon",
				"Roman Javelin"
		};
		String[] bowNames = {
				"English Longbow",
				"Indian Sharanga",
				"Indian Vijaya",
				"Japanese Daikyū (大弓)",
				"Japanese Hankyū (半弓)",
				"Mongolian Recurve",
				"Modern Compound",
				"Korean Gakgung (각궁)",
				"Native American Flatbow",
				"Manchurian Manchu"
		};
		String[] crossbowNames = {
				"Greek Gastraphetes",
				"European Arbalest",
				"Chinese Repeating Crossbow (連弩)",
				"Greek Scorpio",
				"Ancient Greek Ballista",
				"Roman Arcuballista",
				"French Sauterelle",
				"Modern Compound Crossbow"
		};
			
		String creator = creatorList[RNGesus.rng.getRandom(creatorList.length)];
		int adjectiveNum = RNGesus.rng.getRandom(adjectiveList.length);
		String adjective = adjectiveList[adjectiveNum];
		String name =  creator + "\'s " + capitalAdjectives[adjectiveNum] + " ";
		String lore = "";

		int itemType = RNGesus.rng.getRandom(5);
		switch(itemType)
		{
			case 0:
				name += swordNames[RNGesus.rng.getRandom(swordNames.length)];
				switch (RNGesus.rng.getRandom(6)) {
					case 0:
						this.setType(Material.WOODEN_SWORD);
						lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " wooden sword made by " + creator;
						break;
					case 1:
						this.setType(Material.GOLDEN_SWORD);
						lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " golden sword made by " + creator;
						break;
					case 2:
						this.setType(Material.IRON_SWORD);
						lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " iron sword made by " + creator;
						break;
					case 3:
						this.setType(Material.STONE_SWORD);
						lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " stone sword made by " + creator;
						break;
					case 4:
						this.setType(Material.DIAMOND_SWORD);
						lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " diamond sword made by " + creator;
						break;
					default:
						this.setType(Material.NETHERITE_SWORD);
						lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " netherite sword made by " + creator;
						break;
				}
				break;
			case 1:
				name += axeNames[RNGesus.rng.getRandom(axeNames.length)];
				switch (RNGesus.rng.getRandom(6)) {
				case 0:
					this.setType(Material.WOODEN_AXE);
					lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " wooden battle axe made by " + creator;
					break;
				case 1:
					this.setType(Material.GOLDEN_AXE);
					lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " golden battle axe made by " + creator;
					break;
				case 2:
					this.setType(Material.IRON_AXE);
					lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " iron battle axe made by " + creator;
					break;
				case 3:
					this.setType(Material.STONE_AXE);
					lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " stone battle axe made by " + creator;
					break;
				case 4:
					this.setType(Material.DIAMOND_AXE);
					lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " diamond battle axe made by " + creator;
					break;
				default:
					this.setType(Material.NETHERITE_AXE);
					lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " netherite battle axe made by " + creator;
					break;
				}
				break;
			case 2:
				name += tridentNames[RNGesus.rng.getRandom(tridentNames.length)];
				lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " polearm made by " + creator;
				this.setType(Material.TRIDENT);
				break;
			case 3:
				name += bowNames[RNGesus.rng.getRandom(bowNames.length)];
				lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " bow made by " + creator;
				this.setType(Material.BOW);
				break;
			default:
				name += crossbowNames[RNGesus.rng.getRandom(crossbowNames.length)];
				lore += adjective.substring(0, 1).toUpperCase() + adjective.substring(1) + " crossbow made by " + creator;
				this.setType(Material.CROSSBOW);
				break;
		}
		int level;
		switch(itemType)
		{
			case 0:
				level = RNGesus.rng.getRandom(3) + 1;
				if(level < 3)
					this.addEnchantment(Enchantment.FIRE_ASPECT, level);
				level = RNGesus.rng.getRandom(5) + 1;
				if(level < 4)
					this.addEnchantment(Enchantment.SWEEPING_EDGE, level);
			case 1:
				level = RNGesus.rng.getRandom(5) + 1;
				switch(RNGesus.rng.getRandom(4))
				{
					case 0:
						this.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, level);
						break;
					case 1:
						this.addEnchantment(Enchantment.DAMAGE_UNDEAD, level);
						break;
					case 2:
						this.addEnchantment(Enchantment.DAMAGE_ALL, level);
						break;
				}
				level = RNGesus.rng.getRandom(3) + 1;
				if(level < 3)
					this.addUnsafeEnchantment(Enchantment.KNOCKBACK, level);
				break;
			case 2:
				level = RNGesus.rng.getRandom(7) + 1;
				if (level < 6)
					this.addEnchantment(Enchantment.IMPALING, level);
				level = RNGesus.rng.getRandom(5) + 1;
				if (level < 4)
					this.addEnchantment(Enchantment.LOYALTY, level);
				break;
			case 3:
				level = RNGesus.rng.getRandom(7) + 1;
				if (level < 6)
					this.addEnchantment(Enchantment.ARROW_DAMAGE, level);
				level = RNGesus.rng.getRandom(2);
				if (level == 1)
					this.addEnchantment(Enchantment.ARROW_FIRE, 1);
				level = RNGesus.rng.getRandom(2);
				if (level == 1)
					this.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				level = RNGesus.rng.getRandom(4) + 1;
				if (level < 3)
					this.addEnchantment(Enchantment.ARROW_KNOCKBACK, level);
				break;
			case 4:
				level = RNGesus.rng.getRandom(4) + 1;
				if (level < 4)
					this.addEnchantment(Enchantment.QUICK_CHARGE, level);
				switch(RNGesus.rng.getRandom(3))
				{
					case 0:
						level = RNGesus.rng.getRandom(4) + 1;
						this.addEnchantment(Enchantment.PIERCING, level);
						break;
					case 1:
						if(RNGesus.rng.getRandom(2) == 1)
							this.addEnchantment(Enchantment.QUICK_CHARGE, 1);
						break;
				}
				break;
		}
		
		List<String> loreArray = new ArrayList<String>();

		level = RNGesus.rng.getRandom(4) + 1;
		if (level < 3)
		{
			this.addUnsafeEnchantment(CustomEnchants.POISON, level);
			loreArray.add(ChatColor.GRAY + "Poison " + RomanNumber.toRoman(level));
		}
		level = RNGesus.rng.getRandom(4) + 1;
		if (level < 3)
		{
			this.addUnsafeEnchantment(CustomEnchants.WITHER, level);
			loreArray.add(ChatColor.GRAY + "Wither " + RomanNumber.toRoman(level));
		}
		level = RNGesus.rng.getRandom(4) + 1;
		if (level < 3)
		{
			this.addUnsafeEnchantment(CustomEnchants.SLOWNESS, level);
			loreArray.add(ChatColor.GRAY + "Slow " + RomanNumber.toRoman(level));
		}
		level = RNGesus.rng.getRandom(4) + 1;
		if (level < 3)
		{
			this.addUnsafeEnchantment(CustomEnchants.BLINDNESS, level);
			loreArray.add(ChatColor.GRAY + "Blind " + RomanNumber.toRoman(level));
		}

		ItemMeta im = this.getItemMeta();
		loreArray.add(im.getLore().get(0).substring(0,4) + lore);
		im.setDisplayName(im.getDisplayName().substring(0,2) + name);
		im.setLore(loreArray);
		this.setItemMeta(im);
	}
}
