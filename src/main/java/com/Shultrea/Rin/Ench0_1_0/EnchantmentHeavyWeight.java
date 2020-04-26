package com.Shultrea.Rin.Ench0_1_0;

import java.util.UUID;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;




public class EnchantmentHeavyWeight extends EnchantmentBase implements IEnchantmentCurse
{
	public EnchantmentHeavyWeight()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("HeavyWeight");
		this.setRegistryName("HeavyWeight");
	
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.HeavyWeight;
	}
	
	@Override
	public int getMaxLevel()
	{
		return 5;
	}
		
	@Override
    public int getMinEnchantability(int par1)
    {
        return 5 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 25;
    }
	    
	@Override
	public boolean canApplyTogether(Enchantment fTest)
	{
		return super.canApplyTogether(fTest) && fTest != Smc_010.SwifterSlashes;
		
		
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
	 
	  @SubscribeEvent
	    public void HandleEnchant(LivingUpdateEvent fEvent)
	    {	
        	if(!(fEvent.getEntity() instanceof EntityPlayer))
	    		return;
        	
	    	
	    	EntityLivingBase entity = (EntityLivingBase)fEvent.getEntity();
	    	ItemStack weapon = entity.getHeldItemMainhand();				
			if(weapon == null)
				
			{
				RemoveAttackSpeedBuff(entity);
				return;
			}
			
			int level = EnchantmentHelper.getEnchantmentLevel(Smc_010.HeavyWeight, weapon);
			
			if(level > 0)
				AddAttackSpeedBuff(entity);
			else
				RemoveAttackSpeedBuff(entity);
	    }
	    
	    private void AddAttackSpeedBuff(EntityLivingBase fEntity)
		{
	    	ItemStack weapon = fEntity.getHeldItemMainhand();	
	    	int level = EnchantmentHelper.getEnchantmentLevel(Smc_010.HeavyWeight, weapon);
			IAttributeInstance speedAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED);
			
			AttributeModifier modSpeed = new AttributeModifier(UUID.fromString("e2765897-134f-4c14-a535-29c3ae5c7a21"),"attackSpeed", (((double)level * 0.10D + 0.20D) *-1), 1);
			speedAttr.removeModifier(modSpeed);
			speedAttr.applyModifier(modSpeed);
		
			  if(speedAttr.getModifier(UUID.fromString("e2765897-134f-4c14-a535-29c3ae5c7a21")) != null)
				    return;
		
			
		}
		
		private void RemoveAttackSpeedBuff(EntityLivingBase fEntity)
		{
			ItemStack weapon = fEntity.getHeldItemMainhand();	
	    	int level = EnchantmentHelper.getEnchantmentLevel(Smc_010.HeavyWeight, weapon);
			IAttributeInstance speedAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_SPEED);
					
			if(speedAttr.getModifier(UUID.fromString("e2765897-134f-4c14-a535-29c3ae5c7a21")) == null)
				return;
				
			    AttributeModifier modSpeed = new AttributeModifier(UUID.fromString("e2765897-134f-4c14-a535-29c3ae5c7a21"),"attackSpeed",(((double)level * 0.10D + 0.20D) *-1), 1);
			    speedAttr.removeModifier(modSpeed);

		}
}