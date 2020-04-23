package com.Shultrea.Rin.Ench0_4_0;

import java.util.List;
import java.util.UUID;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EnchantmentCurseofHolding extends Enchantment implements IEnchantmentCurse {
	
	int interval;
	
	public EnchantmentCurseofHolding()
	{
	super(Rarity.VERY_RARE, EnumList.ALL, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
    this.setName("CurseofHolding");
	this.setRegistryName("CurseofHolding");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
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
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return fTest.getItem() instanceof Item ? super.canApply(fTest) : false;
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.CurseofHolding;
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