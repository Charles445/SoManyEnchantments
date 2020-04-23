package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
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

public class Enchantmentflametier extends Enchantment{
	private static final String[] DAMAGE_NAMES = new String[] {"lfl", "afl","sfl"};
    /** Holds the base factor of enchantability needed to be able to use the enchant. */
	
    private static final int[] MIN_COST = new int[] {10, 35, 160};
    /** None */
    
    private static final int[] LEVEL_COST = new int[] {0, 0, 0};
    /** None */
    
    private static final int[] LEVEL_COST_SPAN = new int[] {30, 70, 140};
    
    /** Defines the type of damage of the enchantment, 0 = lesserfla, 1 = advfla, 2 = supfla */
    public final int damageType;

 
    
    public Enchantmentflametier(Enchantment.Rarity rarityIn, EnumEnchantmentType type, int damageTypeIn, EntityEquipmentSlot... slots)
    {
        super(rarityIn, EnumEnchantmentType.WEAPON, slots);
        this.damageType = damageTypeIn;
        this.type = type;
      
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int enchantmentLevel)
    {
        return MIN_COST[this.damageType] + (enchantmentLevel - 1) * LEVEL_COST[this.damageType];
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + LEVEL_COST_SPAN[this.damageType];
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
    	return 1;
    }


    /**
     * Return the name of key in translation table of this enchantment.
     */
    @Override
    public String getName()
    {
        return "enchantment." + DAMAGE_NAMES[this.damageType];
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    @Override
    public boolean canApplyTogether(Enchantment ench)
    {
        return !(ench instanceof Enchantmentflametier);
    }

    /**
     * Determines if this enchantment can be applied to a specific ItemStack.
     */
    @Override
    public boolean canApply(ItemStack stack)
    {
        return super.canApply(stack);
    }
    
    @Override
    public boolean isTreasureEnchantment() {
    	switch(this.damageType) {
    	case 0:
    		return false;
    	case 1:
    		return false;
    	case 2:
    		return true;
    	default:
    		return false;
    	}
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	switch(this.damageType) {
    	case 0:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.LesserFlame;
    	case 1:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.AdvancedFlame;
    	case 2:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.SupremeFlame;
    	default:
    		return false;
    	}
      
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
    	switch(this.damageType) {
    	case 0:
    		return somanyenchantments.config.LesserFireAspect;
    	case 1:
    		return somanyenchantments.config.AdvancedFireAspect;
    	case 2:
    		return somanyenchantments.config.SupremeFireAspect;
    	default:
    		return false;
    	}
      
    }
  
    
}