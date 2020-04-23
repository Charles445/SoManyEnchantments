package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.ISubjectEnchantment;
import com.Shultrea.Rin.Main_Sector.Config;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentSubjectEnchantments extends Enchantment implements ISubjectEnchantment{
	
	Config c = somanyenchantments.config;
	
	private static final String[] DAMAGE_NAMES = new String[] {"Mathematics", "Science", "History", "Physics", "English", "PE"};
    /** Holds the base factor of enchantability needed to be able to use the enchant. */
	
    private static final int[] MIN_COST = new int[] {8, 9, 7, 11, 5, 6};

    private static final int[] LEVEL_COST = new int[] {13, 14, 10, 15, 9, 10};

    private static final int[] LEVEL_COST_SPAN = new int[] {25, 28, 23, 30, 21, 20};
    
    /** Defines the type of damage of the enchantment, 0 = Math, 1 = Science, 2 = History, 3 = Physics, 4 = English, 5 = PE */
    public final int damageType;

    public EnchantmentSubjectEnchantments(Enchantment.Rarity rarityIn, EnumEnchantmentType type, int damageTypeIn, EntityEquipmentSlot... slots)
    {
        super(rarityIn, EnumList.SWORD, slots);
        this.damageType = damageTypeIn;
        this.type = type;
      
    }

    public int getMinEnchantability(int enchantmentLevel)
    {
        return MIN_COST[this.damageType] + (enchantmentLevel - 1) * LEVEL_COST[this.damageType];
    }

    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + LEVEL_COST_SPAN[this.damageType];
    }

    public int getMaxLevel()
    {
    	//if(this.damageType == 5)
       // return 5;
    	
    	return this.damageType == 5 ? 5 : 4;
    }

    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType)
    {
       /** if (this.damageType == 0)
        {
            return 1.0F + (float)Math.max(0, level - 1) * 0.5F;
        }
        else if (this.damageType == 1 && creatureType == EnumCreatureAttribute.UNDEAD)
        {
            return (float)level * 2.5F;
        }
        else
        {
            return this.damageType == 2 && creatureType == EnumCreatureAttribute.ARTHROPOD ? (float)level * 2.5F : 0.0F;
        }
        */
    	if(damageType == 5)
    	return 0.75f + level * 0.25f;
    	
    	return 0.80f + level * 0.30f;
    }

    @Override
    public String getName()
    {
        return "enchantment." + DAMAGE_NAMES[this.damageType];
    }

    @Override
    public boolean canApplyTogether(Enchantment ench)
    {
        return !(ench instanceof EnchantmentSubjectEnchantments);
    }
    

    @Override
    public boolean canApply(ItemStack stack)
    {
        return stack.getItem() instanceof ItemAxe ? true : super.canApply(stack);
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level)
    {
        if (target instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)target;

            if (this.damageType == 1 && c.Mathematics)
            {
            	if(user.getRNG().nextDouble() * 100D < 20){
               user.getEntityWorld().newExplosion(user, target.posX, target.posY - 1.5D, target.posZ, 1.1f + (level * 0.4f), false, false);
               
            }
            }
            if (this.damageType == 3)
            {
            	if(user.getRNG().nextDouble() * 180D < 15D){
               user.getEntityWorld().newExplosion(user, target.posX, target.posY - 1.5D, target.posZ, 1.0f + (level * 0.65f), true, false);
               user.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 60, 0, true, true));
               
            	}
            }
            if( this.damageType == 5 && c.PE){
            	
            	if(user.getRNG().nextDouble() * 100D < 8.5D){
            	if(level == 1 || level == 2){
         			user.addPotionEffect(new PotionEffect(MobEffects.HASTE, 150 + (level * 30), level -1));
         			user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50 + (level * 5), level -1 ));
         		}
         			if(level == 3||level == 4){
         			user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 10 + (level * 5), level - 4 ));
         			user.addPotionEffect(new PotionEffect(MobEffects.HASTE, 150 + (level * 30), level - 2 ));
         			user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50 + (level * 5), level - 2));
         			user.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20 + (level * 5), level - 3 ));
         	}
         			if(level >= 5){
         			user.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10 + (level * 5), level -1 ));
         			user.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20 + (level * 5), level -2 ));
         			user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 10 + (level * 5), level - 4 ));
         			user.addPotionEffect(new PotionEffect(MobEffects.HASTE, 150 + (level * 5), level - 2 ));
         			user.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50 + (level * 5), level - 2 ));
         			user.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20 + (level * 5), level -3 ));
         	
         			
         		}
            	
            }
            }
        }
    }
  
    @Override
    public boolean isTreasureEnchantment() {
    	return true;
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	switch(this.damageType) {
    	case 0:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.Mathematics;
    	case 1:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.Science;
    	case 2:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.History;
    	case 3:
    		return false;
    	case 4:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.English;
    	case 5:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.PE;
    	default:
    		return false;
    	}
      
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
    	switch(this.damageType) {
    	case 0:
    		return somanyenchantments.config.Mathematics;
    	case 1:
    		return somanyenchantments.config.Science;
    	case 2:
    		return somanyenchantments.config.History;
    	case 3:
    		return false;
    	case 4:
    		return somanyenchantments.config.English;
    	case 5:
    		return somanyenchantments.config.PE;
    	default:
    		return false;
    	}
      
    }
    
}