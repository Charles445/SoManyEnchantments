package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Ench0_4_5.EnchantmentTrueStrike;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentCurseofInaccuracy extends Enchantment implements IEnchantmentCurse {

	public EnchantmentCurseofInaccuracy() {
		super(Rarity.VERY_RARE, EnumList.COMBAT_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("CurseOfInaccuracy");
		this.setRegistryName("CurseOfInaccuracy");
	}
//Curse that makes a melee weapon miss or a range weapon to be inaccurate
	 @Override
	 public int getMinEnchantability(int ench)
	 {
		 return 15 + (ench- 1) * 15;
	 }
	 @Override
	 public int getMaxEnchantability(int ench)
	 {
		 return this.getMinEnchantability(ench) + 30;
	 }
	
	 @Override
	 public int getMaxLevel()
     {
		 return 2;
     }
	 
	 @Override
	 public boolean isCurse(){
		 return true;
	 } 
	 
	 @Override
	 public boolean canApplyTogether(Enchantment fTest)
	 {
	   	return fTest instanceof EnchantmentTrueStrike ? false : super.canApplyTogether(fTest);
	 }
	 
	    @Override
	    public boolean canApply(ItemStack fTest)
	    {
	    	return fTest.getItem() instanceof Item ? super.canApply(fTest) : false;
	    }
	 
	 @Override
	 public boolean isTreasureEnchantment(){
		 return true;
	 }
	 
	 @Override
	 public boolean canApplyAtEnchantingTable(ItemStack stack)
	 {
	     return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.CurseOfInaccuracy;
	 }
	    
	 @Override
	 public boolean isAllowedOnBooks()
	 {
	     return true;
	 }
	 
	 @SubscribeEvent(priority = EventPriority.HIGHEST)
	 public void onAttack(LivingAttackEvent e){
		 if(!EnchantmentsUtility.checkEventCondition(e, this)) return;
		 EntityLivingBase eb = (EntityLivingBase) e.getSource().getTrueSource();
		 int level = EnchantmentHelper.getEnchantmentLevel(this, eb.getHeldItemMainhand());
		 
		 if(level <= 0)
			 return;
		 
		 if(eb.getRNG().nextInt(10) < level * 2)
			 e.setCanceled(true);
	 }
	 
	 
     @SubscribeEvent(priority = EventPriority.LOWEST)
     public void onRangeAttack(EntityJoinWorldEvent e){
    	 if(e.getEntity() instanceof EntityArrow){
    		 EntityArrow arrow = (EntityArrow) e.getEntity();
    		 
    		 if(arrow.shootingEntity == null)
    			 return;
    		 
    		 if(!(arrow.shootingEntity instanceof EntityLivingBase))
    			 return;
    		 
    		 EntityLivingBase shooter = (EntityLivingBase) arrow.shootingEntity;
    		 
    		 float level = EnchantmentHelper.getEnchantmentLevel(this, shooter.getHeldItemMainhand());
    		 
    		 if(level <= 0)
    			 level =  EnchantmentHelper.getEnchantmentLevel(this, shooter.getHeldItemOffhand());
    		 
    		 if(level <= 0)
    			 return;
    		   		 
    		 /**
    		 if(shooter.getRNG().nextInt(10) < level * 2){
    			 level = 0.99f - level * 0.065f;
    			 double minRandomX = MathHelper.clamp(EnchantmentsUtility.RANDOM.nextDouble() * arrow.motionX, arrow.motionX * level, arrow.motionX);
    			 double minRandomY = MathHelper.clamp(EnchantmentsUtility.RANDOM.nextDouble() * arrow.motionY, arrow.motionY * level, arrow.motionY);
    			 double minRandomZ = MathHelper.clamp(EnchantmentsUtility.RANDOM.nextDouble() * arrow.motionZ, arrow.motionZ * level, arrow.motionZ);
    		 	 
    			 arrow.motionX *= minRandomX;
    			 arrow.motionY *= minRandomY;
    			 arrow.motionZ *= minRandomZ;
    		 }
    		 */
    		 if(shooter.getRNG().nextInt(10) < level * 2){
    			 float velocity = (float) ((Math.abs(arrow.motionX) + Math.abs(arrow.motionY) + Math.abs(arrow.motionZ)) / 3);
    			 arrow.shoot(shooter, shooter.rotationPitch, shooter.rotationYaw, 0, velocity, level * 10);
    		 }
    		 
    	 }
     }
    
}
