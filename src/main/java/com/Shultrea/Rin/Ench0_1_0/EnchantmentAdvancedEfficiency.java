package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IEnchantmentDamage;
import com.Shultrea.Rin.Interfaces.IEnhancedEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;




public class EnchantmentAdvancedEfficiency extends EnchantmentBase implements IEnchantmentDamage, IEnhancedEnchantment {
	public EnchantmentAdvancedEfficiency()
	{
		super(Rarity.RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("AdvancedEfficency");
		this.setRegistryName("AdvancedEfficency");
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.AdvancedEfficency;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 5 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }
   
    
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Enchantments.EFFICIENCY && fTest != Smc_010.Inefficient;
    }
    
    @SubscribeEvent
    public void HandleEnchant(PlayerEvent.BreakSpeed fEvent)
    {

    	
    	ItemStack tool = fEvent.getEntityLiving().getHeldItemMainhand();
		if(tool == null)
			return;
		
		float OrigSpeed = fEvent.getOriginalSpeed();
		
		if(!((tool.getItem() instanceof ItemTool)))
			return;
		
		
		
		
		
		
			
		
		
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.ExtremeEfficency, tool) <= 0)
			return;
		
		int levelExtremeEfficency = EnchantmentHelper.getEnchantmentLevel(Smc_010.ExtremeEfficency, tool);
		

        if((tool.getItem().canHarvestBlock(fEvent.getState()))){
        float Speed = ((levelExtremeEfficency + 1) * levelExtremeEfficency * levelExtremeEfficency) + 3;     
        fEvent.setNewSpeed(OrigSpeed + Speed);
        }
		
        else if(ForgeHooks.isToolEffective(fEvent.getEntityLiving().world, fEvent.getPos(), tool))
        {
		 float Speed = ((levelExtremeEfficency + 1) * levelExtremeEfficency * levelExtremeEfficency) + 3; 
         fEvent.setNewSpeed(OrigSpeed + Speed);
         
	
		}
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onAxeAttackEfficiency(LivingHurtEvent fEvent)
    {
    	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
			return;
    		
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if(dmgSource.getItem() instanceof ItemAxe)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.ExtremeEfficency, dmgSource) <= 0)
			return;
	
		if(this.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
			return;
		
		int levelEE = EnchantmentHelper.getEnchantmentLevel(Smc_010.ExtremeEfficency, dmgSource);
		
		float Chance =  levelEE * 0.15f;
		
		if(fEvent.getEntityLiving() instanceof EntityPlayer)
		if (fEvent.getEntityLiving().getEntityWorld().rand.nextFloat() < Chance + 0.15f){
			EntityPlayer player = (EntityPlayer) fEvent.getEntityLiving();
			player.getCooldownTracker().setCooldown(Items.SHIELD, 100);
			player.resetActiveHand();
			fEvent.getEntityLiving().getEntityWorld().setEntityState(player, (byte)30);
		}
		
		
		
		
			
			
		
		
		
		}

	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AdvancedEfficencyEnable;
	}
    
   
    
}