package com.Shultrea.Rin.Ench0_2_0;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;

public class EnchantmentUnpredictable extends Enchantment implements IEnchantmentCurse {
	public EnchantmentUnpredictable()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Unpredictable");
		this.setRegistryName("Unpredictable");
	
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 20 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {

    	return super.canApplyTogether(fTest);
  
    }
    
    @Override
    public boolean isCurse() {
    	return true;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @SubscribeEvent(priority = EventPriority.LOW) 
    public void HandleEnchant(LivingHurtEvent fEvent)
    {
    	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
			return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if(dmgSource == null)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_020.Unpredictable, dmgSource) <= 0)
			return;
		
		int levelDamageRandomizer = EnchantmentHelper.getEnchantmentLevel(Smc_020.Unpredictable, dmgSource);
		{				
			float random = (float) Math.random() * (fEvent.getAmount() * (levelDamageRandomizer * 1.25f));
			random = (float) (Math.floor(random) + 1);
			
			
			fEvent.setAmount(random + 2.0f);
			
			if(fEvent.getEntity().world.rand.nextInt(100) <= 45){
	            fEvent.setAmount(0.0f);
				fEvent.getEntityLiving().heal((random));
			}
			
		}
    }
   
}