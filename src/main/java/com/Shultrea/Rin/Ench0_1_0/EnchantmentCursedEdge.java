package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IDamageMultiplier;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentCursedEdge extends Enchantment implements IEnchantmentCurse{
	public EnchantmentCursedEdge()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("CursedEdge");
		this.setRegistryName("CursedEdge");
		
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 20 + 12 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof IDamageMultiplier) && fTest != Smc_010.BlessedEdge;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    @Override
    public boolean isTreasureEnchantment()
    {
    	return true;
    }
    @Override
    public boolean isCurse()
    {
    	return true;
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST) 
    public void HandleEnchant(LivingDamageEvent fEvent)
    {
    	if(!fEvent.getSource().damageType.equals("player") && !fEvent.getSource().damageType.equals("mob"))
			return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if(dmgSource == null)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.CursedEdge, dmgSource) <= 0)
			return;
		
		int levelCurse = EnchantmentHelper.getEnchantmentLevel(Smc_010.CursedEdge, dmgSource);
		
		float Damage = fEvent.getAmount() * ((levelCurse * 0.20f) + 1.00f);
		fEvent.setAmount(Damage);
		
		attacker.attackEntityFrom(DamageSource.GENERIC, Damage * 0.25f + 1F);
		
		
    }
    
}
