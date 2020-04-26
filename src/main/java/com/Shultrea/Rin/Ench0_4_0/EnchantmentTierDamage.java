package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Interfaces.IEnhancedEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;
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

public class EnchantmentTierDamage extends EnchantmentBase implements IEnchantmentDamage, IEnhancedEnchantment{
	private static final String[] DAMAGE_NAMES = new String[] {"LesserSharpness", "SupremeSharpness", "LesserSmite", "SupremeSmite", "LesserBaneOfArthropods", "SupremeBaneOfArthropods"};
    private static final int[] MIN_COST = new int[] {1, 120, 1, 120, 1, 120}; 
    private static final int[] LEVEL_COST = new int[] {4, 80, 5, 80, 4, 80};
    private static final int[] LEVEL_COST_SPAN = new int[] {20, 180, 20, 180, 20, 180};
   
    /** Defines the type of damage of the enchantment, 0 = LesserSharpness, 1 = SupremeSharpness, 2 = LesserSmite, 3 = SupremeSmite, 4 = LesserBaneOfArthropods, 5 = SupremeBaneOfArthropods */
    public final int damageType;

 
   
    public EnchantmentTierDamage(Enchantment.Rarity rarityIn, EnumEnchantmentType type, int damageTypeIn, EntityEquipmentSlot... slots)
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
	    	case 0:
	    		return ModConfig.enabled.LesserSharpness;
	    	case 1:
	    		return ModConfig.enabled.SupremeSharpness;
	    	case 2:
	    		return ModConfig.enabled.LesserSmite;
	    	case 3:
	    		return ModConfig.enabled.SupremeSmite;
	    	case 4:
	    		return ModConfig.enabled.LesserBaneOfArthropods;
	    	case 5:
	    		return ModConfig.enabled.SupremeBaneOfArthropods;
	    	default:
	    		return false;
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
    public int getMaxLevel()
    {	
    	return 5;
    }

    @Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType)
    {
    	return damageType == 0 ? 0.25f + level * 0.25f : damageType == 1 ? 4.0f + level * 1.6f : (damageType == 2 && creatureType == EnumCreatureAttribute.UNDEAD) ? 1.25f * level : (damageType == 3 && creatureType == EnumCreatureAttribute.UNDEAD) ? 5.00f * level :(damageType == 4 && creatureType == EnumCreatureAttribute.ARTHROPOD) ? 1.25f * level : (damageType == 5 && creatureType == EnumCreatureAttribute.ARTHROPOD) ? 5.0f * level : 0;
    }

    @Override
    public String getName()
    {
        return "enchantment." + DAMAGE_NAMES[this.damageType];
    }

    @Override
    public boolean canApplyTogether(Enchantment ench)
    {
        return !(ench instanceof EnchantmentTierDamage) && !(ench instanceof EnchantmentDamage) && !(ench instanceof IEnchantmentDamage);
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

            if (this.damageType == 5 && entitylivingbase.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)
            {
                int i = 10 + user.getRNG().nextInt(5 * level);
                entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, i, 1));
            }
            
            if (this.damageType == 6 && entitylivingbase.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)
            {
                int i = 40 + user.getRNG().nextInt(20 * level);
                entitylivingbase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, i, 5));
            }
        }
            
            
        }
    
    @Override
    public boolean isTreasureEnchantment() {
    	switch(this.damageType) {
    	case 0:
    		return false;
    	case 1:
    		return true;
    	case 2:
    		return false;
    	case 3:
    		return true;
    	case 4:
    		return false;
    	case 5:
    		return true;
    	default:
    		return false;
    	}
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
  
        
        
    

