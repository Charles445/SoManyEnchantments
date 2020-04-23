package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IConditionalDamage;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentDarkShadows extends Enchantment implements IConditionalDamage {
	public EnchantmentDarkShadows()
	{
		super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("DarkShadows");
		this.setRegistryName("DarkShadows");
		
	}

	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 16 + (par1 - 1) * 12;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }
    
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {	
    	 return super.canApplyTogether(fTest) && !(fTest instanceof IConditionalDamage);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.DarkShadows;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.DarkShadows;
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity entiti, int level)
    {
    	if(level >= 3 && entiti instanceof EntityLivingBase){
    		EntityLivingBase e = (EntityLivingBase) entiti;
    		if(user.getBrightness() <= 0.1f && e.getBrightness() <= 0.1f)
    		e.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 160));
    		if(user.getRNG().nextInt(10) < level)
    		e.setRevengeTarget(null);
    	}
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onEvent(LivingHurtEvent e){
    	if(!EnchantmentsUtility.checkEventCondition(e, this)) return;
    	EntityLivingBase attacker = (EntityLivingBase) e.getSource().getTrueSource();
    	int level = EnchantmentHelper.getEnchantmentLevel(this, attacker.getHeldItemMainhand());
    	if(level <= 0) return;
    	if(attacker.getBrightness() <= 0.1f && e.getEntityLiving().getBrightness() <= 0.1f)
    	e.setAmount(e.getAmount() + level * 0.75f);
    	
    	System.out.println("TEST");
    }
}