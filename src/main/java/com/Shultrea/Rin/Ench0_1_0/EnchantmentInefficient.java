package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;




public class EnchantmentInefficient extends EnchantmentBase {
	public EnchantmentInefficient()
	{
		super(Rarity.RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Inefficent");
		this.setRegistryName("Inefficent");
	}

	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.InefficentEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Inefficent;
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
    public boolean isCurse()
    {
    	return true;
    }
    
    @Override
	 public boolean isTreasureEnchantment(){
		 return true;
	 }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Enchantments.EFFICIENCY && fTest != Smc_010.ExtremeEfficency;
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
		
		
		
	
		
		float blockHardness = fEvent.getState().getBlockHardness(fEvent.getEntityLiving().world, fEvent.getPos());
			
		
		
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.Inefficient, tool) <= 0)
			return;
		
		int levelInefficent = EnchantmentHelper.getEnchantmentLevel(Smc_010.Inefficient, tool);
		

        if((tool.getItem().canHarvestBlock(fEvent.getState()))){
        float Speed = (levelInefficent * 0.65f) + 2.0f;     
        fEvent.setNewSpeed((OrigSpeed / Speed) - (0.15f * levelInefficent));
        }
		
        else if(ForgeHooks.isToolEffective(fEvent.getEntityLiving().world, fEvent.getPos(), tool))
        {
		 float Speed = ((levelInefficent * 0.65f) + 2.0f); 
         fEvent.setNewSpeed((OrigSpeed / Speed) - (0.15f * levelInefficent));
         
	
		}
    }
    
}