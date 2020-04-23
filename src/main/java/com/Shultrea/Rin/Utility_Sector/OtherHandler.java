package com.Shultrea.Rin.Utility_Sector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Prop_Sector.ArrowPropertiesHandler;
import com.Shultrea.Rin.Prop_Sector.ArrowPropertiesProvider;
import com.Shultrea.Rin.Prop_Sector.IArrowProperties;
import com.Shultrea.Rin.Prop_Sector.PlayerPropertiesProvider;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OtherHandler {

	
	@SubscribeEvent
	public void dealwithit(EntityJoinWorldEvent fEvent){
		
		{
			if (fEvent.getEntity() instanceof EntityArrow)
			{
				EntityArrow arrow = (EntityArrow) fEvent.getEntity();
				EntityLivingBase shooter = (EntityLivingBase) arrow.shootingEntity;
				if(shooter == null)
				return;
				
				ItemStack bow = shooter.getActiveItemStack();
				
				if(bow == null || bow == ItemStack.EMPTY){
					
					bow = shooter.getHeldItemOffhand();
					if(bow == null || bow == ItemStack.EMPTY){
						
						return;
					}
				}
				
				  if(!arrow.hasCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null))
					  return;
				
				  IArrowProperties properties = arrow.getCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null);

				  int p = EnchantmentHelper.getEnchantmentLevel(Smc_040.supremeflame, bow);
				  
				  int l = EnchantmentHelper.getEnchantmentLevel(Smc_030.Strafe, bow);
				  
				  if(Math.random() < 0.125f * l && l > 0)
				  properties.setArrowRapidDamage(true);
				  
				  //int pAlt = 
				  if(p > 0){
					  properties.setFlameLevel(3);
					  arrow.setFire(400);
				  } 
					 
				  int s = EnchantmentHelper.getEnchantmentLevel(Smc_040.advancedflame, bow);
                   if(s > 0){
                	  properties.setFlameLevel(2);
					  arrow.setFire(200);
				  } 
                   
                  int t = EnchantmentHelper.getEnchantmentLevel(Smc_040.lesserflame, bow);
                  if(t > 0){
                	  properties.setFlameLevel(1);
					  arrow.setFire(50);
				  } 
			      //int j = EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.ExtremePunch, shooter);
			}
			
		}
		}
	
	
	
	
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void enchHand(LivingHurtEvent fEvent){
    		
    	//System.out.println("EVENT EEEEEEEEEEEEEEE");
    		
    	if(!(fEvent.getSource().damageType.equals("player") || fEvent.getSource().damageType.equals("mob")))
			return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
    	
		if(dmgSource == null)
			return;
		
		
		
		float levelmath = EnchantmentHelper.getEnchantmentLevel(Smc_040.Mathematics, dmgSource);
		float levelhistory = EnchantmentHelper.getEnchantmentLevel(Smc_040.History, dmgSource);
		float english = EnchantmentHelper.getEnchantmentLevel(Smc_040.English, dmgSource);
		
		
		
		
		if(levelmath > 0){
			//System.out.println("Check");
		float hp = fEvent.getEntityLiving().getMaxHealth() * (levelmath * 0.003125f);
		float currentHp = fEvent.getEntityLiving().getHealth() * (0.025f * levelmath + 0.025f);
		
	
		float FD = EnchantmentsUtility.CalculateDamageIgnoreSwipe(fEvent.getAmount(), hp + currentHp, 0.0f, 1.0f, attacker, Smc_040.Mathematics);
		fEvent.setAmount(FD);
		
		
    }
    
    if(levelhistory > 0){
    	float history = attacker.getEntityWorld().getTotalWorldTime() / 20;
    	history /= 60;
    	history /= 12;
    	history /= 5;
    	history = history / (6 - levelhistory);
    	if(history >= 5 * (levelhistory * 0.5f))
    		history = 5 * (levelhistory * 0.5f);
    	
    	float FD = EnchantmentsUtility.CalculateDamageIgnoreSwipe(fEvent.getAmount(), history, 0.0f, 1.0f, attacker, Smc_040.History);
    	fEvent.setAmount(FD);
    }
    
    if(english > 0){
    	if(fEvent.getEntity() instanceof EntityWitch || fEvent.getEntity() instanceof EntityVillager || fEvent.getEntity() instanceof EntityWolf || fEvent.getEntity() instanceof EntitySnowman || fEvent.getEntity() instanceof EntityIronGolem || fEvent.getEntity() instanceof EntityPigZombie || fEvent.getEntity() instanceof EntityVindicator || fEvent.getEntity() instanceof EntityIllusionIllager || fEvent.getEntity() instanceof EntityEvoker)
		{				
    		float FD = EnchantmentsUtility.CalculateDamageIgnoreSwipe(fEvent.getAmount(), 0.0f, 3.0f, 1.0f, attacker, Smc_040.English);
    		fEvent.setAmount(FD);
    		
    		if(Math.random() <= 0.4f)
			fEvent.getEntityLiving().setSilent(true);
		}
    }
    }
    /**
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onAnvilAttach(AnvilUpdateEvent fEvent){
    
    int cost = 2;
    	
    ItemStack tool = fEvent.getLeft();
    ItemStack finalTool = tool.copy();
    	
    Map<Enchantment, Boolean> map = EnchantmentsUtility.getEnchantmentInformation(fEvent.getRight());
    Map<Enchantment, Integer> finalMap =  EnchantmentHelper.getEnchantments(tool);
    
    List<Enchantment> enchantmentList = new ArrayList<Enchantment>();
    
    boolean flags = false;
    
    for(Enchantment enchantment : map.keySet()){
    
    	if(enchantment != null){
    		flags = map.containsKey(enchantment) ? ((Boolean) map.get(enchantment)).booleanValue() : false; 
    	} 
    	
    	//if(flags)
    	//forbiddenEnchantments.add(enchantment);
    	enchantmentList.add(enchantment);
    	
    	if(flags){
    		finalMap.remove(enchantment);
    	
    	}
    }
    
    boolean shouldEnd = false;
    for(int a = 0; a < enchantmentList.size(); a++){
    	Enchantment enchantment = enchantmentList.get(a);
    	
    	if(enchantment.getRegistryName().getResourceDomain().contains("minecraft"))
    		continue;
    	
    	else shouldEnd = true;
    	
    	if(a == enchantmentList.size() - 1 && !shouldEnd)
    		return;
    }
    
    
    for(Enchantment finalizedEnchantments : finalMap.keySet()){
    	if(finalizedEnchantments == null || finalMap.isEmpty())
    		fEvent.setCanceled(true);
    	
    	else {
    		int level = finalMap.containsKey(finalizedEnchantments) ? ((Integer)finalMap.get(finalizedEnchantments)).intValue() : 0;
    		if(level > 0)
    		EnchantmentHelper.setEnchantments(finalMap, finalTool);
    		
    		Rarity rarity = finalizedEnchantments.getRarity();
    		int x = 0;
    		
    		boolean flag = fEvent.getRight().getItem() == Items.ENCHANTED_BOOK && !ItemEnchantedBook.getEnchantments(fEvent.getRight()).hasNoTags();
    		
    		switch(rarity){
    		case COMMON:
    			x = 1;
    			break;
    		case UNCOMMON:
    			x = 2;
    			break;
    		case RARE:
    			x = 4;
    			break;
    		case VERY_RARE:
    			x = 8;
    			break;
    		}
    		if(flag)
    		x = Math.max(1, x / 2);
    		
    		int price = tool.getRepairCost();
    		
    		cost += x + price;
    		
    		
    		
    		price = price * 2 + 1;
    		
    		finalTool.setRepairCost(price);
    		
    	}
    	
    }
    
   
    
    fEvent.setOutput(finalTool);
    fEvent.setCost(cost);
    
    }
    */
    public static void enchHandler(){
    	MinecraftForge.EVENT_BUS.register(new OtherHandler());
    	//TODO remove register if not enabled.
    	MinecraftForge.EVENT_BUS.register(new ArrowPropertiesHandler());
    	
    	MinecraftForge.EVENT_BUS.register(new AdditionalProtectionEnchantmentsEffects());
    	
		MinecraftForge.EVENT_BUS.register(new ExtraEvent());

    }  
    
	@SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
	public void onArrowCapAttach(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityArrow && !event.getObject().hasCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null))
		{
			event.addCapability(new ResourceLocation(RefStrings.MODID + ":arrow_capabilities"), new ArrowPropertiesProvider());	
		}		
	}
	
	@SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=true)
	public void onPlayerCapAttach(AttachCapabilitiesEvent<Entity> event)
	{
		if (!event.getObject().hasCapability(PlayerPropertiesProvider.PLAYERPROPERTIES_CAP, null))
		{
			event.addCapability(new ResourceLocation(RefStrings.MODID + ":player_capabilities"), new PlayerPropertiesProvider());
		}
	}
}
