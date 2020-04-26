package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentSpellBreaker extends EnchantmentBase implements IEnchantmentDamage{
		
	public EnchantmentSpellBreaker()
	{
		super(Rarity.RARE, EnumList.COMBAT, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("SpellBreaker");
		this.setRegistryName("SpellBreaker");
		
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.SpellBreakerEnable;
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
        return super.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment e)
    {
    	return super.canApplyTogether(e);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    /**
    @SubscribeEvent
    public void onTick(PlayerTickEvent e) {
    	
    	if(e.phase != Phase.END)
    		return;
    	
    	EntityPlayer p = e.player;
        
    	if(p == null)
    		return;
    	
    	if(e.side == Side.CLIENT)
    		return;
    	
    	ItemStack stack = p.getHeldItemMainhand();
    	
    	if(EnchantmentHelper.getEnchantmentLevel(this, stack) <= 0)
    		return;
    	
    	int level = EnchantmentHelper.getEnchantmentLevel(this, stack);
    	
    	Collection<PotionEffect> collectPotion = p.getActivePotionEffects();
		int size = collectPotion.size();
    	
		if(p.getRNG().nextInt(500) < level)
		for(int i = 0; i < size; i++){
			
			if(!collectPotion.iterator().hasNext())
				return;
			
			PotionEffect Potions = collectPotion.iterator().next();
			
			while(Potions.getPotion().isBadEffect()) {
				if(collectPotion.iterator().hasNext()){
				Potions = collectPotion.iterator().next();
				}
				
				else return;
				
			}
				
		Potions.combine(new PotionEffect(Potions.getPotion(), 0 , 256));
			
			//int potionLevel = Potions.getAmplifier();
			
				
			
		}
    }
    */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onHurt(LivingHurtEvent e) {
		if(!(EnchantmentsUtility.checkEventCondition(e, this))) return;			
		EntityLivingBase attacker = (EntityLivingBase)e.getSource().getTrueSource();
		ItemStack stack = attacker.getHeldItemMainhand();	
		int level = EnchantmentHelper.getEnchantmentLevel(this, stack);	
		if(level <= 0)
			return;
		
		if(e.getEntityLiving().getActivePotionEffects().size() > 0)
			e.setAmount(e.getAmount() + (0.625f * level) * e.getEntityLiving().getActivePotionEffects().size());
		
		if(e.getEntityLiving() instanceof EntityWitch || e.getEntityLiving() instanceof EntityEvoker)
			e.setAmount(e.getAmount() + 1.5f * level);
    }
}