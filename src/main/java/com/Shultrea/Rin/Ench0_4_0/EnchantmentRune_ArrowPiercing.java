package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Interfaces.IEnchantmentRune;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Prop_Sector.ArrowPropertiesProvider;
import com.Shultrea.Rin.Prop_Sector.IArrowProperties;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentRune_ArrowPiercing extends EnchantmentBase implements IEnchantmentRune {
	public EnchantmentRune_ArrowPiercing()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("Rune_ArrowPiercing");
		this.setRegistryName("Rune_ArmorPiercing");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Rune_PiercingArrows;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Rune_PiercingArrows;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 25 + (par1 - 1) * 15;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
    
    @Override
	public String getPrefix()
	{
		return TextFormatting.GREEN.toString();
	}

    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public void onEvent(EntityJoinWorldEvent event)
    {
    	if (event.getEntity() instanceof EntityArrow)
    	{
		
    		EntityArrow arrow = (EntityArrow) event.getEntity();
    		EntityLivingBase shooter = (EntityLivingBase) arrow.shootingEntity;
    		if(shooter == null)
    			return;
		
    		ItemStack bow = shooter.getActiveItemStack();
		
    		if(bow == null || bow == ItemStack.EMPTY){	
    			bow = shooter.getHeldItemOffhand();
    			if(bow == null || bow == ItemStack.EMPTY)				
    				return;
    			
    		}
	
    		int p = EnchantmentHelper.getEnchantmentLevel(Smc_040.Rune_PiercingArrows, bow);
		
    		if(p <= 0)
    			return;
		 
    		if(!arrow.hasCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null))
    			return;
			
    		IArrowProperties properties = arrow.getCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null);
			
    		properties.setPiercingLevel(p);;
			  
}
}
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onArrowPierce(LivingDamageEvent e){
    	if(e.getSource().damageType != "arrow" && e.getSource().isUnblockable())
    		return;
    	
    	if(!(e.getSource().getImmediateSource() instanceof EntityArrow))
    		return;
    	
    	if(!(e.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityArrow arrow = (EntityArrow) e.getSource().getImmediateSource();
    	
		if(!arrow.hasCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null))
			return;
		
		IArrowProperties properties = arrow.getCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null);
		
		int pierceLevel = properties.getArmorPiercingLevel();
		
		if(pierceLevel <= 0)
			return;
		
		EntityLivingBase shooter = (EntityLivingBase) e.getSource().getTrueSource();
		
		float damage = e.getAmount() * 0.25f * pierceLevel;
		
		e.setAmount(e.getAmount() - (e.getAmount() * pierceLevel * 0.25f));
		
		UtilityAccessor.damageTarget(e.getEntityLiving(), new EntityDamageSource("arrow", shooter).setDamageBypassesArmor(), damage);
		
		
    }
}