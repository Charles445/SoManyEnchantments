package com.Shultrea.Rin.Ench0_3_0;

import java.util.List;

import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentQuarrying extends Enchantment{
	public EnchantmentQuarrying()
	{
		super(Rarity.VERY_RARE, EnumList.PICKAXE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Quarrying");
		this.setRegistryName("Quarrying");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 25 + 17 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Enchantments.FORTUNE && fTest != Enchantments.SILK_TOUCH;  	
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
	
	@SubscribeEvent(priority=EventPriority.LOWEST, receiveCanceled=true)
	public void onEvent(HarvestDropsEvent e) {
		if(e.getHarvester() != null) {
			ItemStack pick = e.getHarvester().getHeldItemMainhand();
			if(EnchantmentHelper.getEnchantmentLevel(this, pick) <= 0)
				return;
			
			if(e.getFortuneLevel() > 0)
				return;
			
			if(e.isSilkTouching())
				return;
			
			int quarry = EnchantmentHelper.getEnchantmentLevel(this, pick);
			
			List<ItemStack> list = e.getDrops();
				
			for(int x = 0; x < list.size(); x++) {
				ItemStack stack = list.get(x);
				Block block = Block.getBlockFromItem(stack.getItem());
				Block origBlock = e.getState().getBlock();
				IBlockState origState = e.getState();
				
				if(!e.getHarvester().getHeldItemMainhand().canHarvestBlock(e.getState()))
					return;
				
				if(!ForgeHooks.isToolEffective(e.getWorld(), e.getPos(), pick))
					return;
					
				if(origBlock instanceof BlockStone || origBlock instanceof BlockCrops)
					return;
				
				if(stack.isEmpty() || block == origBlock)
					continue;
				
				if(ModConfig.miscellaneous.QuarryingOreOnly)
					if(!(origBlock instanceof BlockOre))
						return;
						
				if(!ModConfig.miscellaneous.harvestTile) {
					if(origBlock.hasTileEntity(origState)) {
						return;
					}
				}
				
				if(stack.getCount() == 1) {
					if(e.getHarvester().getRNG().nextInt(100) < 25) {
						int quantity = e.getHarvester().getRNG().nextInt(quarry + 1);
						list.get(x).grow(quantity);
						pick.damageItem(e.getHarvester().getRNG().nextInt(quantity), e.getHarvester());
				}
				}
				
				else {
					if(e.getHarvester().getRNG().nextInt(100) < 20) {
					float mult = 1.25f + (e.getHarvester().getRNG().nextFloat() * quarry * 0.25f);
					int count = Math.round(stack.getCount() * mult);
					stack.grow(count);
					pick.damageItem(e.getHarvester().getRNG().nextInt(quarry) + 1, e.getHarvester());
					
				}
				}
				
			}
			
		
			
			
			}
			
		}
	
	
}

