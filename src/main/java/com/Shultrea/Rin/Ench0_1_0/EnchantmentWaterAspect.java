package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentWaterAspect extends EnchantmentBase implements IEnchantmentDamage{
	public EnchantmentWaterAspect()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("WaterAspect");
		this.setRegistryName("WaterAspect");
		
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.WaterAspectEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
	public int getMinEnchantability(int par1)
	    {
			return 5 + 10 * (par1 - 1);
	    }

	@Override
    public int getMaxEnchantability(int par1)
	    {
    return super.getMinEnchantability(par1) + 30;
	    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Smc_010.Bluntness && !(fTest instanceof EnchantmentDamage) && !(fTest instanceof IEnchantmentDamage) && fTest != Enchantments.FIRE_ASPECT;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST) 
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
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.WaterAspect, dmgSource) <= 0)
			return;
		
	
		int levelWaterAspect = EnchantmentHelper.getEnchantmentLevel(Smc_010.WaterAspect, dmgSource);
		
		
		
		if(fEvent.getEntity() instanceof EntityEnderman || fEvent.getEntity() instanceof EntityBlaze || fEvent.getEntity() instanceof EntityMagmaCube)
		{				
			float Damage = fEvent.getAmount();
			UtilityAccessor.damageTarget(fEvent.getEntityLiving(), somanyenchantments.PhysicalDamage, 2.5f * levelWaterAspect);
			fEvent.setAmount(Damage);
		
		}
    if(fEvent.getEntity().handleWaterMovement() || fEvent.getEntity().isInWater() || fEvent.getEntity().isWet())
			{
    	float Damager = fEvent.getAmount();
    	UtilityAccessor.damageTarget(fEvent.getEntityLiving(), somanyenchantments.PhysicalDamage, 0.75f * levelWaterAspect);
			fEvent.setAmount(Damager);
			 //  attacker.getEntityWorld().playSound(attacker.posX, attacker.posY, attacker.posZ, SMEsounds.wateraspecthit, SoundCategory.PLAYERS, 2f, 0f, true);
			//   attacker.getEntityWorld().playSound(null, attacker.posX, attacker.posY, attacker.posZ, SMEsounds.wateraspecthit, SoundCategory.PLAYERS, 0.4f, 1f);
		
				
		}
    if(fEvent.getEntity().isBurning())
		   {			
    	   if(levelWaterAspect <= 5){
    	   float Damagest = fEvent.getAmount();
		   fEvent.setAmount(Damagest - ((Damagest / (5)) * (5 - levelWaterAspect)));}
		   else if(levelWaterAspect > 5){
			   fEvent.setAmount(fEvent.getAmount());;
		   }
    	   if(fEvent.getEntity().world.rand.nextInt(50) < 10 + (levelWaterAspect * 5))
    	   fEvent.getEntityLiving().extinguish();
		 
		   }
			
   if(attacker.isWet() || attacker.isInWater() || attacker.handleWaterMovement())
   {
	   float Damet = fEvent.getAmount();
	   UtilityAccessor.damageTarget(fEvent.getEntityLiving(), somanyenchantments.PhysicalDamage, 0.75f * levelWaterAspect);
	   fEvent.setAmount(Damet);
	 //  attacker.getEntityWorld().playSound(null, attacker.posX, attacker.posY, attacker.posZ, SMEsounds.wateraspecthit, SoundCategory.PLAYERS, 0.4f, 1f);
		}
    }
    

}
