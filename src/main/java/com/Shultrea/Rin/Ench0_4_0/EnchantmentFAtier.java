package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentFAtier extends EnchantmentBase{
	private static final String[] DAMAGE_NAMES = new String[] {"lfa", "afa","sfa"};
    /** Holds the base factor of enchantability needed to be able to use the enchant. */
	
    private static final int[] MIN_COST = new int[] {5, 20, 160};
    /** None */
    
    private static final int[] LEVEL_COST = new int[] {4, 10, 60};
    /** None */
    
    private static final int[] LEVEL_COST_SPAN = new int[] {15, 30, 140};
    
    /** Defines the type of damage of the enchantment, 0 = lesserfasp, 1 = advfasp, 2 = supfasp */
    public final int damageType;

    public EnchantmentFAtier(Enchantment.Rarity rarityIn, EnumEnchantmentType type, int damageTypeIn, EntityEquipmentSlot... slots)
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
			case 0: return ModConfig.enabled.LesserFireAspect;
			case 1: return ModConfig.enabled.AdvancedFireAspect;
			case 2: return ModConfig.enabled.SupremeFireAspect;
			default: return false;
		}
	}
	
	@Override
    public int getMaxLevel()
    {
    	switch(this.damageType)
    	{
    		case 0: return ModConfig.level.LesserFireAspect;
    		case 1: return ModConfig.level.AdvancedFireAspect;
    		case 2: return ModConfig.level.SupremeFireAspect;
    		default: return 2;
    	}
    }
	
	@Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return MIN_COST[this.damageType] + (enchantmentLevel - 1) * LEVEL_COST[this.damageType];
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + LEVEL_COST_SPAN[this.damageType];
    }
    
    

    @Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType)
    {
    	return 0.0f;
    }

    @Override
    public String getName()
    {
        return "enchantment." + DAMAGE_NAMES[this.damageType];
    }

    @Override
    public boolean canApplyTogether(Enchantment ench)
    {
        return !(ench instanceof EnchantmentFAtier || ench == Smc_010.FieryEdge || ench == Smc_010.WaterAspect);
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

    /**
     * Called whenever a mob is damaged with an item that has this enchantment on it.
     */
    @Override
    public void onEntityDamagedAlt(EntityLivingBase user, Entity target, ItemStack stack, int level)
    {
    	if(!isEnabled())
    		return;
    	
    	//Check if target is EntityLivingBase
    	if(target instanceof EntityLivingBase)
    	{
		    if(this.damageType == 2)
		    {
		      if(level > 0)
		    	  target.setFire(16 * level);
		    }
		    else if(this.damageType == 1)
		    {
		        if(level > 0)
		      	  target.setFire(8 * level);
		    }
		    else if(this.damageType == 0 )
		    {
		        if(level > 0)
		      	  target.setFire(2 * level);
		    }
    	}
    }
  
    
}