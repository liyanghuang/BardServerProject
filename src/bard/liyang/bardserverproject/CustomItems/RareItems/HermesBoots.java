package bard.liyang.bardserverproject.CustomItems.RareItems;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

public class HermesBoots extends RareItem{

	public HermesBoots()
	{
		super(Material.LEATHER_BOOTS, "Hermes' Old Leather Boots", Arrays.asList("They may be old but they can still be worn"));

		ItemMeta im = this.getItemMeta();
		im.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, 
				new AttributeModifier(UUID.randomUUID(), "generic.movementSpeed", 0.1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		this.setItemMeta(im);
	}

}
