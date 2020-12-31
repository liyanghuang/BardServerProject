package bard.liyang.bardserverproject.CustomItems.UncommonItems;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftTrident;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import bard.liyang.bardserverproject.BardServerProject;
import bard.liyang.bardserverproject.CustomEnchants.CustomEnchants;
import net.minecraft.server.v1_16_R3.EntityThrownTrident;

public class GenericUncommonItemListener implements Listener{
	
	BardServerProject plugin = BardServerProject.getPlugin(BardServerProject.class);

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof LivingEntity && event.getEntity() instanceof LivingEntity)
		{
			LivingEntity attacked = (LivingEntity)event.getEntity();
			if(!((LivingEntity)event.getDamager()).getEquipment().getItemInMainHand().hasItemMeta())
				return;
			ItemMeta im = ((LivingEntity)event.getDamager()).getEquipment().getItemInMainHand().getItemMeta();
			if(im.hasEnchant(CustomEnchants.POISON))
				attacked.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, im.getEnchantLevel(CustomEnchants.POISON) -1, false, true, true));
			if(im.hasEnchant(CustomEnchants.WITHER))
				attacked.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, im.getEnchantLevel(CustomEnchants.WITHER) -1, false, true, true));
			if(im.hasEnchant(CustomEnchants.SLOWNESS))
				attacked.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, im.getEnchantLevel(CustomEnchants.SLOWNESS) -1, false, true, true));
			if(im.hasEnchant(CustomEnchants.BLINDNESS))
				attacked.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, im.getEnchantLevel(CustomEnchants.BLINDNESS) -1, false, true, true));
		}
	}
	
	@EventHandler
	public void bowShoot(EntityShootBowEvent event)
	{
		if(!event.getBow().hasItemMeta())
			return;
		ItemMeta im = event.getBow().getItemMeta();
		if(im.hasEnchant(CustomEnchants.POISON))
			event.getProjectile().setMetadata("poison", new FixedMetadataValue(plugin, im.getEnchantLevel(CustomEnchants.POISON)));
		if(im.hasEnchant(CustomEnchants.WITHER))
			event.getProjectile().setMetadata("wither", new FixedMetadataValue(plugin, im.getEnchantLevel(CustomEnchants.WITHER)));
		if(im.hasEnchant(CustomEnchants.BLINDNESS))
			event.getProjectile().setMetadata("blind", new FixedMetadataValue(plugin, im.getEnchantLevel(CustomEnchants.BLINDNESS)));
		if(im.hasEnchant(CustomEnchants.SLOWNESS))
			event.getProjectile().setMetadata("slow", new FixedMetadataValue(plugin, im.getEnchantLevel(CustomEnchants.SLOWNESS)));
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event)
	{
		if(event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity)
		{
			LivingEntity le = (LivingEntity)event.getHitEntity();
			if(event.getEntity().hasMetadata("poison"))
				le.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, event.getEntity().getMetadata("poison").get(0).asInt()-1, false, true, true));
			if(event.getEntity().hasMetadata("wither"))
				le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, event.getEntity().getMetadata("wither").get(0).asInt()-1, false, true, true));
			if(event.getEntity().hasMetadata("blind"))
				le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, event.getEntity().getMetadata("blind").get(0).asInt()-1, false, true, true));
			if(event.getEntity().hasMetadata("slow"))
				le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, event.getEntity().getMetadata("slow").get(0).asInt()-1, false, true, true));
			if(event.getEntity().getType() == EntityType.TRIDENT)
			{
				EntityThrownTrident t = ((CraftTrident)event.getEntity()).getHandle();
				ItemStack thrownTrident = CraftItemStack.asBukkitCopy(t.trident);
				if(!thrownTrident.hasItemMeta())
					return;
				ItemMeta im = thrownTrident.getItemMeta();
				if( im.hasEnchant(CustomEnchants.POISON))
					le.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, im.getEnchantLevel(CustomEnchants.POISON)-1, false, true, true));
				if( im.hasEnchant(CustomEnchants.WITHER))
					le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, im.getEnchantLevel(CustomEnchants.WITHER)-1, false, true, true));
				if( im.hasEnchant(CustomEnchants.SLOWNESS))
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, im.getEnchantLevel(CustomEnchants.SLOWNESS)-1, false, true, true));
				if( im.hasEnchant(CustomEnchants.BLINDNESS))
					le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, im.getEnchantLevel(CustomEnchants.BLINDNESS)-1, false, true, true));
			}
		}
	}
}
