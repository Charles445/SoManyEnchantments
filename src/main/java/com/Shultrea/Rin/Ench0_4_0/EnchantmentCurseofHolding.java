package com.Shultrea.Rin.Ench0_4_0;

import java.util.UUID;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EnchantmentCurseofHolding extends EnchantmentBase implements IEnchantmentCurse {
	
	//TODO proper routing and delay handling
	int interval;
	
	public EnchantmentCurseofHolding()
	{
	super(Rarity.VERY_RARE, EnumList.ALL, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
    this.setName("CurseofHolding");
	this.setRegistryName("CurseofHolding");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.CurseofHolding;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.CurseofHolding;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 20 * par1;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return false;
    }
    
	@Override
	public boolean isCurse(){
		return true;
	}
	
	@Override
	public boolean isTreasureEnchantment(){
		return true;
	}
	
	@SubscribeEvent
	public void onExist(PlayerTickEvent e){
		
		if(e.phase == Phase.START)
			return;
	
		if(e.player == null)
			return;
		
		if(interval > 10){	
			int level = EnchantmentHelper.getEnchantmentLevel(this, e.player.getHeldItemMainhand());
			int level2 = EnchantmentHelper.getEnchantmentLevel(this, e.player.getHeldItemOffhand());
			
			if(level2 > level)
			level = level2;
			
			if(level > 0){
				
				e.player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, level - 1, false, false));
				e.player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 20, level > 1 ? 1 : 0, false, false));
				if(level > 1){
				e.player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 10, level - 1, false, false));
				e.player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 10, level - 1, false, false));
				}
			}	
			
			interval = 0;
			
			if(level > 0)
				curseUnluck(e.player, level);
			else
				removeCurse(e.player, level);
			
		}
		else interval++;	
		}

	private void curseUnluck(EntityLivingBase fEntity, int level)
	{
		IAttributeInstance speedAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.LUCK);
	
		AttributeModifier modSpeed = new AttributeModifier(UUID.fromString("e2125897-134f-4c14-a535-19c2ae4c7a21"),"luckModifier", (((double)level) *-1), 0);
		speedAttr.removeModifier(modSpeed);
		speedAttr.applyModifier(modSpeed);
		
		if(speedAttr.getModifier(UUID.fromString("e2125897-134f-4c14-a535-19c2ae4c7a21")) != null)
			return;

	
}

	private void removeCurse(EntityLivingBase fEntity, int level)
	{
		IAttributeInstance speedAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.LUCK);
			
		if(speedAttr.getModifier(UUID.fromString("e2125897-134f-4c14-a535-19c2ae4c7a21")) == null)
			return;
		
		AttributeModifier modSpeed = new AttributeModifier(UUID.fromString("e2125897-134f-4c14-a535-19c2ae4c7a21"),"luckModifier",(((double)level) *-1), 1);
	    speedAttr.removeModifier(modSpeed);
}
	  }