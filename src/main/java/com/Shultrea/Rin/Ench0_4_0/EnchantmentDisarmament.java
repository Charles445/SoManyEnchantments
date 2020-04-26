package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentDisarmament extends EnchantmentBase {
	public EnchantmentDisarmament()
	{
		super(Rarity.RARE, EnumList.COMBAT_AXE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Disarmament");
		this.setRegistryName("Disarmament");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Disarmament;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 10 + 20 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem().canApplyAtEnchantingTable(stack, this) && ModConfig.enabled.Disarmament;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.Disarmament;
    }
    
    @SubscribeEvent
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
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_040.Disarmament, dmgSource) <= 0)
			return;
		int leveldisarm = EnchantmentHelper.getEnchantmentLevel(Smc_040.Disarmament, dmgSource);
		
		if(Math.random() * 100 < 25)
		{
			((EntityLivingBase)fEvent.getEntityLiving()).addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 1));
			((EntityLivingBase)fEvent.getEntityLiving()).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 40 + (leveldisarm * 20), 254));
		
			if(Math.random() * 100 < (leveldisarm * 5)){
				EnchantmentsUtility.Disarm(fEvent.getEntityLiving());
			}
		
		}
    }
}
