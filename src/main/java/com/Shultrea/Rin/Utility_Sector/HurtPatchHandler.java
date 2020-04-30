package com.Shultrea.Rin.Utility_Sector;

import java.util.UUID;

import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HurtPatchHandler
{
	
	//TODO this reimplementation doesn't remove the 1 or so damage that ends up applying like the old one does
	//So this isn't quite finished
	
	
	//Slightly different UUID just in case there's a weird overlap with old stuff somewhere
	private static final UUID TWEAKER_UUID = UUID.fromString("e6123481-134f-4c54-a535-29c3a241c7a22");
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void applyHurtPatch(AttackEntityEvent event)
	{
		if(!ModConfig.miscellaneous.EnableFixEnchantment)
			return;
		
		EntityPlayer player = event.getEntityPlayer();
		if(player==null)
			return;
		
		World world = player.getEntityWorld();
		
		//Only apply to server
		if(world.isRemote)
			return;
		
		Entity target = event.getTarget();
		if(target==null)
			return;
		
		//Step through vanilla handling first
		if (target.canBeAttackedWithItem())
        {
            if (!target.hitByEntity(player))
            {
            	IAttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
            	
				float sma = (float)attribute.getAttributeValue();
				
		        float f1;
		
		        if (target instanceof EntityLivingBase)
		        {
		            f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((EntityLivingBase)target).getCreatureAttribute());
		        }
		        else
		        {
		            f1 = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), EnumCreatureAttribute.UNDEFINED);
		        }
		
		        float f2 = player.getCooledAttackStrength(0.5F);
		        float f = sma * (0.2F + f2 * f2 * 0.8F);
		        f1 = f1 * f2;
				
		        if(f > 0.0f || f1 > 0.0f)
		        	return;
		        
		        //The attack will whiff if this is reached
		        
		        double operationZeroAmount = 0;
		        
		        for(AttributeModifier mod : attribute.getModifiers())
		        {
		        	//An attack failing is most likely going to be due to weakness, or something that simply adds a negative value
		        	//Which is going to be operation 0
		        	if(mod.getOperation() == 0)
		        	{
		        		operationZeroAmount += mod.getAmount();
		        	}
		        }
		        
		        //New modifier to add to make it sum to a positive number
		        double newMod = 0.01d - operationZeroAmount;
		        
		        //Make sure there isn't a duplicate, remove the old one first
		        attribute.removeModifier(TWEAKER_UUID);
		        attribute.applyModifier(new AttributeModifier(TWEAKER_UUID, "SMEHurtPatch", newMod, 0));
            }
        }
	}
	
	//Critical Hit Event is the earliest event called after the application of the hurt patch
	//Will probably be replaced by something in LivingHurtEvent later
	
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled=true)
	public void revertHurtPatch(CriticalHitEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		if(player!=null)
			player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(TWEAKER_UUID);
	}
	
	/*
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void enforceLivingEvents(LivingAttackEvent event)
	{
		if(!ModConfig.miscellaneous.EnableFixEnchantment)
			return;
		
		float amount = event.getAmount();
		
		DebugUtil.messageAll("LA "+amount+" "+event.getSource()+" "+event.getEntityLiving().getEntityWorld().isRemote);
		
		if(amount > 0.0f)
			return;
		
		DamageSource source = event.getSource();
		if(source == null || source.damageType != "player")
			return;
		
		
		//TODO safer implementation
		event.setCanceled(true);
		
		EntityLivingBase target = event.getEntityLiving();
		
		float damageHurt = net.minecraftforge.common.ForgeHooks.onLivingHurt(target, source, 0.0f);
		float damageDamage = net.minecraftforge.common.ForgeHooks.onLivingDamage(target, source, damageHurt);
	}
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void enforceLivingHurt(LivingHurtEvent event)
	{
		if(!ModConfig.miscellaneous.EnableFixEnchantment)
			return;
		
		float amount = event.getAmount();
		
		DebugUtil.messageAll("LD "+amount+" "+event.getSource());
	}
	*/
	/*
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
	public void enforceLivingDamageEvent(LivingHurtEvent event)
	{
		if(!ModConfig.miscellaneous.EnableFixEnchantment)
			return;
		
		float amount = event.getAmount();
		DebugUtil.messageAll(""+amount+" "+event.getSource());
		if(amount > 0.0f)
			return;
		
		DamageSource source = event.getSource();
		if(source == null || source.damageType != "player")
			return;
		
		//damageType is player
		
		//TODO safer implementation
		event.setCanceled(true);
		
		//Run things past the hurt event manually
		
		EntityLivingBase target = event.getEntityLiving();
		
		//From forge
		//TODO alternative for EntityPlayer
    	DebugUtil.messageAll("Hurtpatch hooking onLivingDamage");
		float damageAmount = net.minecraftforge.common.ForgeHooks.onLivingDamage(target, source, 0.0f);

        if (damageAmount != 0.0F)
        {
            float f1 = target.getHealth();
            target.getCombatTracker().trackDamage(source, f1, damageAmount);
            target.setHealth(f1 - damageAmount); // Forge: moved to fix MC-121048
            target.setAbsorptionAmount(target.getAbsorptionAmount() - damageAmount);
        }
		
	}
	*/
	/*
	//float b = 0;
	int leveLs = 0;
    float OldDamage = 0.0f;
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled=true)
	public void Patch(LivingHurtEvent fEvent)
	{
		if(!ModConfig.miscellaneous.EnableFixEnchantment)
			return;
			
		fEvent.setCanceled(false);
		
		if(fEvent.getSource().damageType != "player")
			return;
		
		float Counter = fEvent.getAmount();
		
		//Potion weakness = MobEffects.WEAKNESS;
		EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();
		
		if(!(attacker != null))
		return;
		
		if(!(attacker.isPotionActive(MobEffects.WEAKNESS)))
			return;
		
	    float SecondDamage = fEvent.getAmount();
	    
	    NBTTagCompound nbt = new NBTTagCompound();
	    attacker.writeEntityToNBT(nbt);
	    
	    Potion potion = MobEffects.WEAKNESS;
			
		PotionEffect potyon = null;
			
		potyon = attacker.getActivePotionEffect(potion);
			
		leveLs = potyon.getAmplifier() + 1;
	    	
		ItemStack weapon = attacker.getHeldItemMainhand();
		if(weapon==ItemStack.EMPTY||weapon==null)
		{
			fEvent.setAmount(fEvent.getAmount() - (leveLs * 4.0f));
			return;
		}

		float FinalDamage = EnchantmentsUtility.ExclusiveCalculateDamageForNegativeSwipe(fEvent.getAmount(), -6.0f, attacker);


		fEvent.setAmount(FinalDamage + 2.0f);

	}
	*/
}
