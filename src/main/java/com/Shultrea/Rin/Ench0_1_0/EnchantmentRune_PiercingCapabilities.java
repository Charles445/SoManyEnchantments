package com.Shultrea.Rin.Ench0_1_0;




import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Interfaces.IEnchantmentRune;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentRune_PiercingCapabilities extends EnchantmentBase implements IEnchantmentRune{
	public EnchantmentRune_PiercingCapabilities()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Rune_PiercingCapabilities");
		this.setRegistryName("Rune_PiercingCapabilities");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Rune_PiercingCapabilitiesEnable;
	}
    
	@Override
	public int getMaxLevel()
    {
        return 4;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 20 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof IEnchantmentRune);
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST) 
    public void HandleEnchant(LivingHurtEvent fEvent)
    {
    	//System.out.println("Piercing Capabilities");   	
    	if(!(EnchantmentsUtility.checkEventCondition(fEvent, this))) return;
    	
    	if(fEvent.getSource().isUnblockable())
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();

    	ItemStack dmgSource = attacker.getHeldItemMainhand();
	
		int pierceLevel = EnchantmentHelper.getEnchantmentLevel(this, dmgSource);

		if(pierceLevel <= 0)
			return;
		
		float damage = fEvent.getAmount() * 0.25f * pierceLevel;
		
		fEvent.setAmount(fEvent.getAmount() - (fEvent.getAmount() * pierceLevel * 0.25f));
		
		if(attacker instanceof EntityPlayer)
		UtilityAccessor.damageTargetEvent(fEvent.getEntityLiving(), new EntityDamageSource("player", attacker).setDamageBypassesArmor(), damage);
		
		else UtilityAccessor.damageTargetEvent(fEvent.getEntityLiving(), new EntityDamageSource("mob", attacker).setDamageBypassesArmor(), damage);
	
	
    }

    @Override
	public String getPrefix()
	{
		return TextFormatting.GREEN.toString();
	}
}
