package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentBlessedEdge extends EnchantmentBase {
	public EnchantmentBlessedEdge()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("BlessedEdge");
		this.setRegistryName("BlessedEdge");
	
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.BlessedEdgeEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 20 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Enchantments.SMITE && fTest != Smc_010.CursedEdge && fTest != Smc_010.AdvancedSmite && !(fTest instanceof IEnchantmentDamage);
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL) 
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
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.BlessedEdge, dmgSource) <= 0)
			return;
		
		int levelBless = EnchantmentHelper.getEnchantmentLevel(Smc_010.BlessedEdge, dmgSource);
		
		attacker.heal( fEvent.getAmount() * (levelBless * 0.03f));
		
		if(fEvent.getEntityLiving().getCreatureAttribute() == EnumCreatureAttribute.UNDEAD){
		
		
		float FDamage = EnchantmentsUtility.CalculateDamageIgnoreSwipe(fEvent.getAmount(), 1.00f, 0.60f, 1 + 0.04f * levelBless, attacker, Smc_010.BlessedEdge);
		
		//UtilityAccessor.damageEntity(fEvent.getEntityLiving(), somanyenchantments.PhysicalDamage, Damage - 0.001f);
		fEvent.setAmount(FDamage);
		
		}
		
    }
}
