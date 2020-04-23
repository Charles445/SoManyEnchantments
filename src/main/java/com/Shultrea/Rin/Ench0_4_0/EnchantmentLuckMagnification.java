package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IDamageMultiplier;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EnchantmentLuckMagnification extends Enchantment implements IDamageMultiplier {
	
	public EnchantmentLuckMagnification(){
		super(Rarity.RARE , EnumList.COMBAT, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("LuckMagnification");
		this.setRegistryName("LuckMagnification");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof IDamageMultiplier);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onCritical(CriticalHitEvent e){
    	if(e.getEntityPlayer() == null)
    		return;
    	
    	if(e.getTarget() == null)
    		return;
    	
    	IAttributeInstance luck = e.getEntityPlayer().getAttributeMap().getAttributeInstance(SharedMonsterAttributes.LUCK);
    	
    	float amount = (float) luck.getAttributeValue();
    	
    	//I know this is obsolete but for safety checks hehe.
    	if(amount == 0)
    		return;
    	
    	if(e.getEntityPlayer().getHeldItemMainhand() == null)
    		return;
    		
    	int level = EnchantmentHelper.getEnchantmentLevel(this, e.getEntityPlayer().getHeldItemMainhand());
    	
    	if(level <= 0)
    		return;
    	
    	if(e.getEntityPlayer().getRNG().nextInt(100) < Math.abs(amount * level)){
    			e.setDamageModifier(e.getDamageModifier() + amount * level * 0.05f);   		
    	}
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST) 
	public void HandleEnchant(LootingLevelEvent fEvent)
	{	
		if(!(fEvent.getDamageSource().getTrueSource() instanceof EntityPlayer))
			return;
		
		EntityPlayer player = (EntityPlayer) fEvent.getDamageSource().getTrueSource();
		ItemStack sword = player.getHeldItemMainhand();
					
		if(sword == null)
			return;
		
		int level = EnchantmentHelper.getEnchantmentLevel(this, sword);
			
		if(level <= 0)
			return;
		
		IAttributeInstance luck = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.LUCK);
		
		int modifier = (int) (fEvent.getLootingLevel() + (luck.getAttributeValue() * level / 2));
		
		fEvent.setLootingLevel(modifier);

	}
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void magnifyLuck(PlayerTickEvent e){
    	if(e.player == null)
    		return;
    	
    	if(e.phase == Phase.START)
    		return;
 
   	
    	if(e.player.getHeldItemMainhand() == null)
    		return;
    		
    	int level = EnchantmentHelper.getEnchantmentLevel(this, e.player.getHeldItemMainhand());
    	
    	if(level > 0) 	
    	e.player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 10, level - 1, true, false));
    }
}
