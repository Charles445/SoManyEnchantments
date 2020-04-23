package com.Shultrea.Rin.Ench0_3_0;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentEmpoweredDefence extends Enchantment{
	public EnchantmentEmpoweredDefence()
	{
		super(Rarity.RARE, EnumList.SHIELD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("EmpoweredDefence");
		this.setRegistryName("EmpoweredDefence");
		
	}
  
	@Override
	public int getMaxLevel()
	{
	    return 4;
	}
		
    @Override
	public int getMinEnchantability(int par1)
	{
	    return 15 + 15 * (par1 - 1);
	}

    @Override
    public int getMaxEnchantability(int par1)
	{
	    return this.getMinEnchantability(par1) + 30;
	}
    
    @Override
    public boolean canApply(ItemStack e)
    {
    	return super.canApply(e) || e.getItem().isShield(e, null);
    }
	    
    @Override
	public boolean canApplyTogether(Enchantment fTest)
	{    	
    	return super.canApplyTogether(fTest);    	
	}
	        
    @SubscribeEvent(priority = EventPriority.LOW) 
    public void EmpoweredDefenceEvent(LivingAttackEvent fEvent){
    	
    	if(!(fEvent.getEntity() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase victim = (EntityLivingBase)fEvent.getEntity();
		ItemStack shield = victim.getHeldItemOffhand();
				
		if(shield == ItemStack.EMPTY){
			shield = victim.getHeldItemMainhand();
			if(shield == ItemStack.EMPTY)
			return;	
		}
				
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_030.EmpoweredDefence, shield) <= 0)
			return;
		
		int levelED = EnchantmentHelper.getEnchantmentLevel(Smc_030.EmpoweredDefence, shield);
		
		Entity attacker = fEvent.getSource().getImmediateSource();
		
		ItemStack shield2 = fEvent.getEntityLiving().getActiveItemStack();
		
		if(shield2.getItem().isShield(shield, victim)){
		if(fEvent.getEntityLiving().world.rand.nextInt(100) < 20 + (levelED * 5) && EnchantmentsUtility.canBlockDamageSource(fEvent.getSource(), victim)){
		fEvent.setCanceled(true);
		
    	EnchantmentsUtility.ImprovedKnockBack(attacker, 0.4f + 0.2F * levelED, victim.posX - attacker.posX, victim.posZ - attacker.posZ);
    	if(victim instanceof EntityPlayer){
    	attacker.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) victim), fEvent.getAmount() * (0.225f * levelED));
    	}
    	else {
    	attacker.attackEntityFrom(DamageSource.causeMobDamage(victim), fEvent.getAmount() * (0.225f * levelED));
    	}
		victim.hurtResistantTime = 15;
		
    }
    }
		
    }
   /** @SubscribeEvent
    public void OnShieldCooldown(LivingUpdateEvent fEvent){
    	if(!(fEvent.getEntityLiving() instanceof EntityPlayer))
    		return;
    	EntityPlayer player = (EntityPlayer) fEvent.getEntityLiving();
    	int levelED = EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.EmpoweredDefence, player);
		float cooldown = player.getCooldownTracker().getCooldown(Items.SHIELD, 100f);
		if(cooldown == 0)
			return;
		if(cooldown <= 100){
		player.getCooldownTracker().setCooldown(Items.SHIELD, (int)(100 / levelED));
		player.resetActiveHand();
		fEvent.getEntityLiving().getEntityWorld().setEntityState(player, (byte)30);
		}
    }
    */
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