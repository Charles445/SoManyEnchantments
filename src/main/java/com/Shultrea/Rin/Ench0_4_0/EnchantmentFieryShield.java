package com.Shultrea.Rin.Ench0_4_0;
import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentFieryShield extends EnchantmentBase{
	public EnchantmentFieryShield()
	{
		super(Rarity.RARE, EnumList.SHIELD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("fieryshield");
		this.setRegistryName("fieryshield");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.FieryShield;
	}
	
	@Override
	public int getMaxLevel()
	{
		return 4;
	}
		
	@Override
	public int getMinEnchantability(int par1)
	{
		return 18 + 12 * (par1 - 1);
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return this.getMinEnchantability(par1) + 40;
	}
	    
	@Override
    public boolean canApplyTogether(Enchantment fTest)
	{
    	return super.canApplyTogether(fTest);		    
    }
	
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem().canApplyAtEnchantingTable(stack, this) && ModConfig.enabled.FieryShield;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return true;
    }
	   
    @SubscribeEvent(priority = EventPriority.LOWEST) 
    public void shieldBurn(LivingAttackEvent fEvent){
    	
    	if(!(fEvent.getEntity() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase victim = (EntityLivingBase)fEvent.getEntity();
		ItemStack shield = victim.getHeldItemMainhand();
				
		if(shield.isEmpty() || !shield.getItem().isShield(shield, victim)){
			shield = victim.getHeldItemOffhand();
			if(shield.isEmpty())
			return;	
		}
				
		if(EnchantmentHelper.getEnchantmentLevel(Smc_040.fieryshield, shield) <= 0)
			return;
		
		int levelfs = EnchantmentHelper.getEnchantmentLevel(Smc_040.fieryshield, shield);
		
		Entity attacker = fEvent.getSource().getImmediateSource();
	
		if(shield.getItem().isShield(shield, victim)){
		if(fEvent.getEntityLiving().world.rand.nextInt(100) < 40 + (levelfs * 10) && EnchantmentsUtility.canBlockDamageSource(fEvent.getSource(), victim)){
	
		attacker.attackEntityFrom(new EntityDamageSource("player", victim).setFireDamage(), fEvent.getAmount() * (levelfs * 0.1f));
    	attacker.setFire(4 + levelfs * 2);
    	
    	if(EnchantmentsUtility.isLevelMax(shield, Smc_030.EmpoweredDefence)){
    		
    	}
		
    }
    }
		
    }
  
}

    //@Override
   /** public void onUserHurt(EntityLivingBase user, Entity attacker, int level){
    	
    	if(GotHit == true){
    		
    		double XMot = attacker.motionX;
        	double ZMot = attacker.motionZ;
        	double YMot = attacker.motionY;
    		
    		XMot += (double)(-MathHelper.sin(attacker.rotationYaw * (float)Math.PI / 180.0F) * (float) 0.02f + level * 0.03125F);
        	ZMot += (double)(MathHelper.cos(attacker.rotationYaw * (float)Math.PI / 180.0F) * (float)0.02f + level * 0.03125F);
        	attacker.motionX = XMot /1.1D;
        	attacker.motionZ = ZMot /1.1D;
        	attacker.motionY = YMot + level * 0.125;
    		
    	}
    	
    }
}
    */