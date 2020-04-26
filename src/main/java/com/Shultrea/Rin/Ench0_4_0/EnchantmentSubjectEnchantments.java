package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.ISubjectEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class EnchantmentSubjectEnchantments extends EnchantmentBase implements ISubjectEnchantment{
	
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
	
	@Override
	public boolean isEnabled()
	{
		switch(this.damageType)
		{
    		case 0:
    			return ModConfig.enabled.Mathematics;
    		case 1:
    			return ModConfig.enabled.Science;
    		case 2:
    			return ModConfig.enabled.History;
    		case 3:
    			return false;
    		case 4:
    			return ModConfig.enabled.English;
    		case 5:
    			return ModConfig.enabled.PE;
    		default:
    			return false;
    	}
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

            if (this.damageType == 1 && ModConfig.enabled.Mathematics)
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
            if( this.damageType == 5 && ModConfig.enabled.PE){
            	
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
    	return isEnabled() && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
    	return isEnabled();
    }
    
}