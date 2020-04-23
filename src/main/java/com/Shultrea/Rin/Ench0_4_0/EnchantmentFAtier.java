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
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentFAtier extends Enchantment{
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
    	return 2;
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
    public boolean canApply(ItemStack stack)
    {
        return super.canApply(stack);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	switch(this.damageType) {
    	case 0:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.LesserFireAspect;
    	case 1:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.AdvancedFireAspect;
    	case 2:
    		return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.SupremeFireAspect;
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
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level)
    {
    if(this.damageType == 2 && somanyenchantments.config.SupremeFireAspect){
      if(level > 0)
    	  target.setFire(16 * level);
    }
    if(this.damageType == 1 && somanyenchantments.config.AdvancedFireAspect){
        if(level > 0)
      	  target.setFire(8 * level);
      }
    if(this.damageType == 0 && somanyenchantments.config.LesserFireAspect){
        if(level > 0)
      	  target.setFire(2 * level);
      }
    }
  
    
}