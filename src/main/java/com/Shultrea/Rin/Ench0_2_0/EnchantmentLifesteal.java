package com.Shultrea.Rin.Ench0_2_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class EnchantmentLifesteal extends EnchantmentBase 
{
	public EnchantmentLifesteal()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Lifesteal");
		this.setRegistryName("Lifesteal");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.LifestealEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Lifesteal;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 6 + 8 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Smc_010.BlessedEdge && fTest != Smc_010.CursedEdge;
    }
    
    @SubscribeEvent(priority = EventPriority.LOW) 
    public void HandlingFirst(LivingHurtEvent fEvent)
    {
		if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
			return;
		
		if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
			return;
		
		EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
		ItemStack weapon = attacker.getHeldItemMainhand();
		
		if(weapon == null)
			
			return;
		int levellifesteal = EnchantmentHelper.getEnchantmentLevel(Smc_020.Lifesteal, weapon);
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_020.Lifesteal, weapon) <= 0)
			return;
			
		attacker.heal(fEvent.getAmount() * (levellifesteal * 0.025f + 0.025f));
		UtilityAccessor.damageTarget(fEvent.getEntityLiving(), somanyenchantments.PhysicalDamage, fEvent.getAmount() * (0.05F + ((levellifesteal * 0.05F))));
		
    }
 
    

}
