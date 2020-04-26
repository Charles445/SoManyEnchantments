package com.Shultrea.Rin.Ench0_2_0;


import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;


public class EnchantmentCounterAttack extends EnchantmentBase {
	public EnchantmentCounterAttack()
	{
		super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("CounterAttack");
		this.setRegistryName("CounterAttack");
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.CounterAttackEnable;
	}
	
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 20 + 15 * (par1 - 1);
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
    
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @Override
    public void onUserHurt(EntityLivingBase user, Entity target, int level){
    	
    	if(target == null)
    		return;
    	
    	if(!(target instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase) target;
    	
    	if(attacker.getRNG().nextInt(100) < 20 + (level * 5))
    	if(user instanceof EntityPlayer){
    		EntityPlayer player = (EntityPlayer) user;
    		player.hurtResistantTime = 20;
    		player.attackTargetEntityWithCurrentItem(attacker);
    	}
    }
    /*
  @SubscribeEvent(priority = EventPriority.LOW) 
  public void HandleEnchant(LivingAttackEvent fEvent)
  {
	  	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
	  		return;
  	
   		if(!(fEvent.getEntityLiving().attackable()))
   			return;
  	
   		if(fEvent.getEntity() == null)
   			return;
   		
  		if(!(fEvent.getEntity() instanceof EntityLivingBase))
  			return;
  	
  		EntityLivingBase victim = (EntityLivingBase)fEvent.getEntity();
		ItemStack weaponSword = victim.getHeldItemMainhand();
				
		if(weaponSword == null)
			return;
		
		Entity attacker = fEvent.getSource().getTrueSource();
		
		if(attacker == null || !(attacker instanceof EntityLivingBase))
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_020.CounterAttack, weaponSword) <= 0)
			return;
		
		int levelCA = EnchantmentHelper.getEnchantmentLevel(Smc_020.CounterAttack, weaponSword);
		if(victim instanceof EntityPlayer){
		
		if(fEvent.getEntity().world.rand.nextInt(100) < 20 + (levelCA * 5)){
			
		EntityPlayer player = (EntityPlayer) fEvent.getEntityLiving();
		
		player.attackTargetEntityWithCurrentItem(attacker);
		
		player.hurtResistantTime = 15;
		
		
  
		}
		}
		else if(!(victim instanceof EntityPlayer)){
			if(fEvent.getEntity().world.rand.nextInt(100) < 16 + (levelCA * 8)){
			
			fEvent.getEntityLiving().attackEntityAsMob(attacker);
			
			fEvent.getEntityLiving().hurtResistantTime = 20;
			
			
		}
}
  }
  */
}


