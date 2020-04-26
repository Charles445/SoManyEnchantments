package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;





public class EnchantmentButchering extends EnchantmentBase implements IEnchantmentDamage {
	public EnchantmentButchering()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Butchering");
		this.setRegistryName("Butchering");
		
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.ButcheringEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 12 + 12 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Smc_010.Bluntness && fTest != Smc_010.WaterAspect && fTest != Smc_010.SpellBreaker && fTest != Smc_010.Defusion && fTest != Enchantments.SHARPNESS && fTest != Enchantments.SMITE && fTest != Enchantments.BANE_OF_ARTHROPODS && fTest != Smc_010.AdvancedSharpness && fTest != Smc_010.AdvancedBaneOfArthropods && fTest != Smc_010.AdvancedSmite && fTest != Smc_010.CursedEdge && fTest != Smc_010.BlessedEdge && fTest != Smc_010.Purification;
    	
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return fTest.getItem() instanceof ItemAxe ? true : super.canApply(fTest);
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
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.Butchering, dmgSource) <= 0)
			return;
		
		int levelButchering = EnchantmentHelper.getEnchantmentLevel(Smc_010.Butchering, dmgSource);
		if(fEvent.getEntity() instanceof EntityAnimal)
		{					
			float Damage = 1.25f + levelButchering * 1.25f;
			UtilityAccessor.damageTarget(fEvent.getEntityLiving(), somanyenchantments.PhysicalDamage, Damage);
			fEvent.setAmount(fEvent.getAmount());
				
		}
    }
    
}