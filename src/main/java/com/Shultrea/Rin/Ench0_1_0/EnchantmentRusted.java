package com.Shultrea.Rin.Ench0_1_0;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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

public class EnchantmentRusted extends EnchantmentBase implements IEnchantmentCurse 
{
	//Delay in ticks for each run
	int delayFactor = 10;
	int divisorMin = 75 / delayFactor;
	
	public EnchantmentRusted()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.BREAKABLE , new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Rusted");
		this.setRegistryName("Rusted");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.RustedEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Rusted;
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
    public void rustEvent(PlayerTickEvent e)
    {
    	
    	if(e.phase != Phase.END)
    		return;
    	
    	EntityPlayer p = e.player;
        
    	if(p == null)
    	return;
    	
    	if(e.side == Side.CLIENT)
    		return;
    	
    	//Run every 10 ticks
    	if(p.getEntityWorld().getTotalWorldTime() % delayFactor == 0L)
    	{
	    	p.inventory.mainInventory.stream().forEach(is -> fireRustItemDamage(is,p));
	    	p.inventory.armorInventory.stream().forEach(is -> fireRustItemDamage(is,p));
	    	p.inventory.offHandInventory.stream().forEach(is -> fireRustItemDamage(is,p));
    	}
    	
  	
    }
    
    private void fireRustItemDamage(ItemStack is, EntityPlayer player)
    {
    	//Reworked to (hopefully) prevent the crashes and run a little bit faster
    	
    	if(is == null || player == null)
    		return;
    	
		if (!is.isEmpty() && is.isItemStackDamageable() && is.getMaxDamage()>0)
		{
			//Stack exists, is damageable, and max damage is greater than zero
			
			for(Entry<Enchantment, Integer> entry : EnchantmentHelper.getEnchantments(is).entrySet())
			{
				if(entry.getKey() == Smc_010.Rusted)
				{
					int lvl = entry.getValue();
					if(lvl >= 1)
					{
						//System.out.print(l + " - Level");
						Random rand = player.getRNG();
						
						//This isn't mathematically accurate, but it's quite close
						int divisor = 100 - (lvl*delayFactor);
						if(divisor < divisorMin)
							divisor = divisorMin;
				    	
				    	if(rand.nextInt(100) > divisor)
				    	{
				    		double percentage = is.getMaxDamage() - is.getItemDamage();
				    		percentage = percentage / is.getMaxDamage();
				    		if(percentage > 0.15f * lvl + 0.05f)
				    			is.damageItem(rand.nextInt(lvl * 2 + 2), player);
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
				//Map<Enchantment,Integer> newEnchs = new HashMap<Enchantment,Integer>();
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
