package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentDefusion extends EnchantmentBase implements IEnchantmentDamage {
	public EnchantmentDefusion()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Defusion");
		this.setRegistryName("Defusion");
		
	}

	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.DefusionEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return  10 + 12 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return  super.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof EnchantmentDamage) && !(fTest instanceof IEnchantmentDamage);
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
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.Defusion, dmgSource) <= 0)
			return;
		
		int levelDefusing = EnchantmentHelper.getEnchantmentLevel(Smc_010.Defusion, dmgSource);
		if(fEvent.getEntity() instanceof EntityCreeper)
		{				
			float Damage = fEvent.getAmount();
			UtilityAccessor.damageTarget(fEvent.getEntityLiving(), somanyenchantments.PhysicalDamage, levelDefusing * 2.5f);
			fEvent.setAmount(Damage);
			
			if(Math.random() < 0.02f * levelDefusing){
				EntityCreeper creeper = (EntityCreeper) fEvent.getEntityLiving();
				NBTTagCompound fuse = new NBTTagCompound();
				creeper.writeEntityToNBT(fuse);
				short fuseTime = 32767;
				fuse.setShort("Fuse", fuseTime);
				creeper.readEntityFromNBT(fuse);
				
				
				
			}
			
		}
    }
    
}
