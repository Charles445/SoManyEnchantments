/**

//UNUSED

package com.Shultrea.Rin.Ench0_4_0;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;
import com.Shultrea.Rin.Utility_Sector.MsgSP_Particle;
import com.Shultrea.Rin.Utility_Sector.SMEnetwork;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentSweepingEdge;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;




public class EnchantmentPierceThrough extends EnchantmentBase {
	public EnchantmentPierceThrough(){
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("PierceThrough");
		this.setRegistryName("PierceThrough");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {    			
    	return super.canApplyTogether(fTest) && !(fTest instanceof EnchantmentSweepingEdge) && !(fTest instanceof EnchantmentSwiper);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
   @SubscribeEvent(priority=EventPriority.LOW)
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
		
		int levelPierce = EnchantmentHelper.getEnchantmentLevel(Smc_040.PierceThrough, dmgSource);
		
		int lf = EnchantmentHelper.getEnchantmentLevel(Smc_010.FieryEdge, dmgSource) * 2;
		
		lf += EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, dmgSource);
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_040.PierceThrough, dmgSource) <= 0)
			return;
				
		EntityLivingBase victim = fEvent.getEntityLiving();
			
		Vec3d attackerCheck = EnchantmentsUtility.Cleave(attacker.posX-victim.posX, attacker.posY-victim.posY, attacker.posZ-victim.posZ);	
		
		double angleRad = Math.acos(attackerCheck.normalize().dotProduct(victim.getLookVec()));
		double oppositeRad = angleRad + Math.PI;
		double angle = Math.toDegrees(angleRad);
		
		double consX = Math.cos(oppositeRad) * 3;
		double consZ = -Math.sin(oppositeRad) * 3;
		//if(angle <= 25)
		
		double rX = attacker.posX - victim.posX;
		double rY = attacker.posY - victim.posY;
		double rZ = attacker.posZ - victim.posZ;
		
		double finalPitch = Math.atan2(Math.sqrt(rX * rX + rZ * rZ), rY) + Math.PI;
		
		// We have a cleaving level, let's figure out our damage value.
		float splashDamage = fEvent.getAmount();
		
		// Next, find our entities to hit.
		AxisAlignedBB boundBox = new AxisAlignedBB(attacker.posX - consX - 1, victim.posY - Math.toDegrees(Math.sin(finalPitch)) * 3, attacker.posZ - consZ - 1, attacker.posX + consX + 1, victim.posY + Math.toDegrees(Math.sin(finalPitch)) * 3, attacker.posZ + consZ + 1);
		@SuppressWarnings("unchecked")
		ArrayList<Entity> targetEntities = new ArrayList<Entity>(attacker.getEntityWorld().getEntitiesWithinAABBExcludingEntity(fEvent.getEntity(), boundBox));
		

		// Let's remove all the entries that aren't within range of our attacker
		ListIterator<Entity> itr = targetEntities.listIterator();
		
		
		
		int p = 0;
		int limit = 2 + levelPierce;
		while(itr.hasNext() && p < (limit))
		{	
			Entity target = itr.next();
			
			if(!(target instanceof EntityLivingBase))
				continue;
			
			if(target == attacker)
				continue;
			
			if(target == fEvent.getEntityLiving())
				continue;
			
			if(target.getDistance(attacker) > 3.25F + levelPierce * 1.25f)
				continue;
			
			{
				
				p = p + 1;
				// This is within our arc, let's deal our damage.
				DamageSource source = null;
				if(attacker instanceof EntityPlayer){
					source = new EntityDamageSource("playerCleave", attacker).setDamageBypassesArmor();
				    target.attackEntityFrom(source, splashDamage);
				    target.setFire(4 * lf);	
				}
				if(attacker instanceof EntityMob){
					source = new EntityDamageSource("mobCleave", attacker).setDamageBypassesArmor();
				    target.attackEntityFrom(source, splashDamage);
				    target.setFire(4 * lf);	
				}
				
				if(source == null)
				{
					target.attackEntityFrom(DamageSource.GENERIC, splashDamage);
				}
				
				if(attacker instanceof EntityLivingBase)
				{
					// Apply knockback
					int modKnockback = 1;
					modKnockback += EnchantmentHelper.getKnockbackModifier(attacker) * 0.5f;
					if(attacker.isSprinting())
						modKnockback++;
					
					//Entity victim = fEvent.getEntityLiving();
					
					if(modKnockback > 0)
						//target.setVelocity((double)(-MathHelper.sin(attacker.rotationYaw * (float)Math.PI / 180.0F) * (float)modKnockback * 0.85F), 0.2D, (double)(MathHelper.cos(attacker.rotationYaw * (float)Math.PI / 180.0F) * (float)modKnockback * 0.85F));
						EnchantmentsUtility.ImprovedKnockBack(target, 0.3F * modKnockback, rX, rZ);
						
				}
			}
		}
	
		// Stop the player sprinting
		attacker.setSprinting(false);
    }

}
*/