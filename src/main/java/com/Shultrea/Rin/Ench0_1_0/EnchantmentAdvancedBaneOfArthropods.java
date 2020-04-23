package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Interfaces.IEnhancedEnchantment;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;




public class EnchantmentAdvancedBaneOfArthropods extends Enchantment implements IEnchantmentDamage, IEnhancedEnchantment {
	public EnchantmentAdvancedBaneOfArthropods()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("AdvancedBaneOfArthropods");
		this.setRegistryName("AdvancedBaneOfArthropods");
		
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof EnchantmentDamage) && !(fTest instanceof IEnchantmentDamage);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return fTest.getItem() instanceof ItemAxe ? true : super.canApply(fTest);
    }
    
    @Override
    public String getTranslatedName(int level)
    {
        String s = I18n.translateToLocal(this.getName());
        if(!somanyenchantments.config.AdvancedBaneOfArthropodsEnable){
        s = TextFormatting.STRIKETHROUGH + "" + TextFormatting.DARK_RED + s;       
        return level == 1 && this.getMaxLevel() == 1 ? s : s + " " + I18n.translateToLocal("enchantment.level." + level) + "(Disabled)";
    }
        return level == 1 && this.getMaxLevel() == 1 ? s : s + " " + I18n.translateToLocal("enchantment.level." + level);
    }

    @Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType)
    {
        return creatureType == EnumCreatureAttribute.ARTHROPOD ? (float)level * 3.25F : 0.0f;
    }
    
    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity entiti, int level)
    {
    	if(entiti instanceof EntityLivingBase){
    	EntityLivingBase entity = (EntityLivingBase) entiti;
    
    	 if(entity.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD)
 		{				
    		 int i = 30 + user.getRNG().nextInt(15 * level);
 			entity.addPotionEffect(new PotionEffect(Potion.getPotionById(2), i, 4));		
 		}
    }
    }
    }
