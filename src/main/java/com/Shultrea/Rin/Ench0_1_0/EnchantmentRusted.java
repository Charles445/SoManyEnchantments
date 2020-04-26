package com.Shultrea.Rin.Ench0_1_0;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class EnchantmentRusted extends EnchantmentBase implements IEnchantmentCurse {

	public EnchantmentRusted()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.BREAKABLE , new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Rusted");
		this.setRegistryName("Rusted");
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.RustedEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 25 + 25 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Enchantments.UNBREAKING;
    }
    
    @Override
    public boolean canApply(ItemStack i)
    {
    	return super.canApply(i) && !i.getUnlocalizedName().contains("gold");
    }
    
    @Override
    public boolean isTreasureEnchantment()
    {
    	return true;
    }
    @Override
    public boolean isCurse()
    {
    	return true;
    }
    
    @SubscribeEvent
    public void rustEvent(PlayerTickEvent e){
    	
    	if(e.phase != Phase.END)
    		return;
    	
    	EntityPlayer p = e.player;
        
    	if(p == null)
    	return;
    	
    	if(e.side == Side.CLIENT)
    		return;
    	
    	p.inventory.mainInventory.stream().forEach(is -> fireRustItemDamage(is,p));
    	p.inventory.armorInventory.stream().forEach(is -> fireRustItemDamage(is,p));
    	p.inventory.offHandInventory.stream().forEach(is -> fireRustItemDamage(is,p));
  	
    }
    
    private void fireRustItemDamage(ItemStack is, EntityPlayer e) {
		if (!is.isEmpty()) {
			Map<Enchantment,Integer> enchs = EnchantmentHelper.getEnchantments(is);
			Map<Enchantment,Integer> newEnchs = new HashMap<Enchantment,Integer>();
			for (Enchantment en:enchs.keySet()) {
			   if(en == Smc_010.Rusted){
					if (enchs.get(en)>1) {
						int l = enchs.get(en);
						//System.out.print(l + " - Level");
						Random rand = new Random();
					
						int divisor = 100 - l;
						if(divisor <= 75)
					    divisor = 75;
				    	
				    	if(rand.nextInt(100) > divisor) {
				    		double percentage = is.getMaxDamage() - is.getItemDamage();
				    		percentage = percentage / is.getMaxDamage();
				    		if(is.isItemStackDamageable() && percentage > 0.15f * l + 0.05f)
				    		is.damageItem(rand.nextInt(l * 2 + 2), e);
						
				    	}
						//System.out.println("End, damaged!");
				
				}
			}
		
		}
		}
		
		}
   
    private int getLevel(ItemStack is, Enchantment enchantment) {
			int c = 0;
			if(!is.isEmpty()) {
				Map<Enchantment,Integer> enchs = EnchantmentHelper.getEnchantments(is);
				Map<Enchantment,Integer> newEnchs = new HashMap<Enchantment,Integer>();
				for (Enchantment en:enchs.keySet()) {
					if(en == enchantment && enchs.get(en) > 1){
						if(c<enchs.get(en))
							c=enchs.get(en);
				   
			   }
			  
			   }
			}
		
		return c;
		}
    
    
    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity entiti, int level)
    {
    	EntityLivingBase entity = (EntityLivingBase) user;
    	user.getHeldItemMainhand().damageItem(1 + user.getRNG().nextInt(level * 2), user);
    }
		
    }
