package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Interfaces.IDamageMultiplier;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;




public class EnchantmentDifficultyScaled extends EnchantmentBase implements IDamageMultiplier{
	public EnchantmentDifficultyScaled()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("difficultyscaled");
		this.setRegistryName("difficultyscaled");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.DifficultyScaled;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 10 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 20;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return fTest.getItem() instanceof ItemSword ? true : super.canApply(fTest);
    }
    
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void CancelAttack(LivingAttackEvent fEvent){
    	if(!(EnchantmentsUtility.checkEventCondition(fEvent, this))) return;
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();

    	ItemStack dmgSource = attacker.getHeldItemMainhand();

		if(EnchantmentHelper.getEnchantmentLevel(Smc_040.difficultyscaled, dmgSource) <= 0)
			return;
		if(attacker.getEntityWorld().getDifficulty() == EnumDifficulty.PEACEFUL){
			fEvent.setCanceled(true);
		}
		
    }
    
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void HandleEnchant(LivingDamageEvent fEvent)
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
		
		if(EnchantmentHelper.getEnchantmentLevel(this, dmgSource) <= 0)
			return;
		
		int level = EnchantmentHelper.getEnchantmentLevel(this, dmgSource);
		
		if(attacker.getEntityWorld().getDifficulty() == EnumDifficulty.HARD){
			fEvent.setAmount(fEvent.getAmount() * (1 + 0.1f * level));
		}
		else if(attacker.getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL){
			return;
		}
		else if(attacker.getEntityWorld().getDifficulty() == EnumDifficulty.EASY){
			fEvent.setAmount(fEvent.getAmount() * 0.1f * level);
		}
		
    }
}