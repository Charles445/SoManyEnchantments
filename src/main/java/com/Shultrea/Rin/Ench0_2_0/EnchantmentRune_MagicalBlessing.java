package com.Shultrea.Rin.Ench0_2_0;


import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IEnchantmentRune;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentRune_MagicalBlessing extends EnchantmentBase implements IEnchantmentRune {
	public EnchantmentRune_MagicalBlessing()
	{
		super(Rarity.VERY_RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Rune_MagicalBlessing");
		this.setRegistryName("Rune_MagicalBlessing");
		
	}@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Rune_MagicalBlessingEnable;
	}

	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Rune_MagicalBlessing;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 25 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof IEnchantmentRune);	
    }
     
    @SubscribeEvent(priority = EventPriority.LOWEST) 
    public void HandleEnchant(LivingHurtEvent fEvent)
    {
    	//System.out.println("Magical Blessing");
    	
    	if(!fEvent.getSource().damageType.equals("player") && !fEvent.getSource().damageType.equals("mob"))
			return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	EntityLivingBase entity = (EntityLivingBase)fEvent.getEntity();
    	
    	if(entity == null)
    		return;
	
    	ItemStack stack = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if(stack == null)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(this, stack) <= 0)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.Rune_PiercingCapabilities, stack) != 0)
			return;
	
		int level = EnchantmentHelper.getEnchantmentLevel(Smc_020.Rune_MagicalBlessing, stack);
		   
		float LevelDamage = fEvent.getAmount();
		
		fEvent.setAmount(fEvent.getAmount() - (fEvent.getAmount() * (level * 0.25f)));		
		LevelDamage = LevelDamage * (level * 0.25f);
	
		if(fEvent.getEntityLiving() instanceof EntityWitch)
			LevelDamage = LevelDamage * 0.15f;
		
		UtilityAccessor.damageTargetEvent(fEvent.getEntityLiving(), new EntityDamageSource("player", attacker).setMagicDamage(), LevelDamage);
	
		    
		if(EnchantmentsUtility.RANDOM.nextBoolean()){
			Potion negaPotion = EnchantmentsUtility.getNonInstantNegativePotion();
			if(negaPotion != null)
				fEvent.getEntityLiving().addPotionEffect(new PotionEffect(negaPotion, (MathHelper.clamp(EnchantmentsUtility.RANDOM.nextInt(6), 0, Integer.MAX_VALUE) + 1) * 20 * level, MathHelper.clamp(EnchantmentsUtility.RANDOM.nextInt(level) - 1, 0, Integer.MAX_VALUE)));			
		}
		
		else {
			Potion negaIPotion = EnchantmentsUtility.getInstantNegativePotion();
			if(negaIPotion != null)
			{
				if(negaIPotion == MobEffects.INSTANT_DAMAGE && fEvent.getEntityLiving().isEntityUndead())
					negaIPotion = MobEffects.INSTANT_HEALTH;
						
				fEvent.getEntityLiving().addPotionEffect(new PotionEffect(negaIPotion, 1, MathHelper.clamp(EnchantmentsUtility.RANDOM.nextInt(level) - 1, 0, Integer.MAX_VALUE)));
			}
		}
				
}
    
    @Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType)
    {
        return (0.75f * level);
    }
    
    @Override
	public String getPrefix()
	{
		return TextFormatting.GREEN.toString();
	}
}