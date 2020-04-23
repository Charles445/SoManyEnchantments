package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentFireAspect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;




public class EnchantmentInefficient extends Enchantment {
	public EnchantmentInefficient()
	{
		super(Rarity.RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Inefficent");
		this.setRegistryName("Inefficent");
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
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
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