package com.Shultrea.Rin.Ench0_3_0;

import java.util.Random;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.Config;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.SMEnetwork;
import com.Shultrea.Rin.Utility_Sector.MsgSP_Particle;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentSmelter extends Enchantment{
	public EnchantmentSmelter()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Smelter");
		this.setRegistryName("Smelter");

	}
	
	@Override
	public int getMaxLevel()
    {
        return 1;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 30 * par1;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	
    	return fTest == Enchantments.SILK_TOUCH ? false : super.canApplyTogether(fTest);
    	
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
	
	
	Random random = new Random();
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onBlockDropSmelt(HarvestDropsEvent fEvent) {
		if(fEvent.getHarvester() != null && somanyenchantments.config.SmelterEnable) {
			int fortuneLevel = fEvent.getFortuneLevel();
			
			// get the held item and make sure it isn't null!
			// 1.1.2 fix
			ItemStack tool = fEvent.getHarvester().getHeldItemMainhand();
			if(fEvent.getHarvester().getHeldItemMainhand() == null) {
				return;
			}
			
			if(EnchantmentHelper.getEnchantmentLevel(Smc_030.Smelter, tool) <= 0)
				return;
			
			
			if(!fEvent.isSilkTouching() && !fEvent.getHarvester().isSneaking()) {
				
				ItemStack result = FurnaceRecipes.instance().getSmeltingResult(
				new ItemStack(fEvent.getState().getBlock(), 1, fEvent.getState().getBlock().getMetaFromState(fEvent.getState())));
				
				//System.out.println(result);
				
				if(fEvent.getDrops().size() <= 0)
					return;
				
				if(result.getItem() instanceof ItemAir)
					return;
				
				
				if (result != null && !(result.getItem() instanceof ItemAir)) {
					// depending on fortune, release smelted ores
					// 1.4.1 fix: no fortune on block items!
					int nItems = 0;
					int nParticles = 2;
					if(fortuneLevel > 0 && !(result.getItem() instanceof ItemBlock)) {
						nItems = 1 + random.nextInt(fortuneLevel + 1);
					} 
					else if(fortuneLevel <= 0 && !(result.getItem() instanceof ItemBlock)) {
						nItems = 1;
					} 
					
					if(result.getItem() instanceof ItemBlock){
						nItems = 1;
					}
					
					for(int i = 0; i < nItems; i++) {
						float f = random.nextFloat() * 0.6F + 0.1F;
						float f1 = random.nextFloat() * 0.6F + 0.1F;
						float f2 = random.nextFloat() * 0.6F + 0.1F;
						float f3 = 0.025F;
						EntityItem eitem = new EntityItem(fEvent.getWorld(), 
						fEvent.getPos().getX() + f, fEvent.getPos().getY() + f1, fEvent.getPos().getZ() + f2, result.copy());
						eitem.motionX = random.nextGaussian() * f3;
						eitem.motionY = random.nextGaussian() * f3 + 0.2F;
						eitem.motionZ = random.nextGaussian() * f3;
						fEvent.getWorld().spawnEntity(eitem);
						
						// spawn particles at this position
						for(int j = 0; j < nParticles; j++) {
							double rx = eitem.posX + random.nextFloat() - 0.5;
							double ry = eitem.posY + (random.nextFloat() + 0.5) * 0.15;
							double rz = eitem.posZ + random.nextFloat() - 0.5;							
							SMEnetwork.net.sendToAll(new MsgSP_Particle("flame", rx, ry, rz, 0, 0, 0));
						}
					}
					
					// cancel the default drops
					fEvent.setDropChance(0);
					return;
				}
				
			}
			
		}
		return;
	}
}
