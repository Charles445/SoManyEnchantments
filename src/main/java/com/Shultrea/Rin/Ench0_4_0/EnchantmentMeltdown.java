package com.Shultrea.Rin.Ench0_4_0;

import java.util.Random;
import java.util.UUID;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentMeltdown extends Enchantment{
	public EnchantmentMeltdown()
	{
	super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[]{EntityEquipmentSlot.CHEST});
    this.setName("meltdown");
	this.setRegistryName("meltdown");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
    }
	@Override
    public int getMinEnchantability(int par1)
    {
        return 20 + 20 * (par1 - 1);
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
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return somanyenchantments.config.Meltdown && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.Meltdown;
    }

	@Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level)
	{
		Random random = user.getRNG();
		ItemStack itemstack = EnchantmentHelper.getEnchantedItem(Smc_040.meltdown, user);

		if (Math.random() < 0.05f + 0.05f * level)
		{
			if (attacker != null)
			{
				attacker.getEntityWorld().newExplosion(user, user.posX, user.posY, user.posZ, 1.90f + 0.30f * level, true, false);
			}

			if (!itemstack.isEmpty())
			{
				damageArmor(itemstack, 10 + random.nextInt(15 * level + 1), user);
			}
		}
		else if (!itemstack.isEmpty())
		{
			damageArmor(itemstack, 1 + random.nextInt(3 * level), user);
	 	}
	 }
	 
	 private void damageArmor(ItemStack stack, int amount, EntityLivingBase entity)
	 {
		 int slot = -1;
		 int x = 0;
		 for (ItemStack i : entity.getArmorInventoryList())
		 {
			 if(i == stack){
				 slot = x;
				 break;
	            }
	            x++;
	        }
	        if (slot == -1 || !(stack.getItem() instanceof net.minecraftforge.common.ISpecialArmor))
	        {if(entity instanceof EntityPlayerMP)
	            stack.attemptDamageItem(amount, new Random(), (EntityPlayerMP) entity);
	        else stack.damageItem(amount, entity);
	            return;
	        }
	        net.minecraftforge.common.ISpecialArmor armor = (net.minecraftforge.common.ISpecialArmor)stack.getItem();
	        armor.damageArmor(entity, stack, DamageSource.causeExplosionDamage(entity), amount, slot);
	    }
	  
			}
	  
	  
