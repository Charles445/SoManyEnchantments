package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class Enchantmentflametier extends EnchantmentBase
{
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

	@Override
	public boolean isConfigEnabled()
	{
		switch(this.damageType)
		{
			case 0: return ModConfig.enabled.LesserFlame;
			case 1: return ModConfig.enabled.AdvancedFlame;
			case 2: return ModConfig.enabled.SupremeFlame;
			default: return false;
		}
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
  
}