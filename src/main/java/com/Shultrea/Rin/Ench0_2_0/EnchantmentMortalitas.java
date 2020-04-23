package com.Shultrea.Rin.Ench0_2_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IConditionalDamage;
import com.Shultrea.Rin.Interfaces.IDamageMultiplier;
import com.Shultrea.Rin.Prop_Sector.IPlayerProperties;
import com.Shultrea.Rin.Prop_Sector.PlayerPropertiesProvider;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentMortalitas extends Enchantment {
	
	public EnchantmentMortalitas()
	{
		super(Rarity.VERY_RARE, EnumList.COMBAT, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Mortalitas");
		this.setRegistryName("Mortalitas");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 8;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 18 + 9 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment e)
    {
    	return super.canApplyTogether(e) && !(e instanceof IDamageMultiplier) && !(e instanceof IConditionalDamage);
    }
    
    @Override
    public boolean canApply(ItemStack e)
    {
    	return super.canApply(e);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDamage(LivingHurtEvent e){
    	if(e.getSource().getTrueSource() != null && e.getSource().getTrueSource() instanceof EntityPlayer && e.getSource().damageType.equals("player")){
    		
    		EntityPlayer attacker = (EntityPlayer)e.getSource().getTrueSource();
    		ItemStack stack = attacker.getHeldItemMainhand();
    		
    		if(stack.isEmpty() || stack == null)
    			return;
    		
    		int level = EnchantmentHelper.getEnchantmentLevel(Smc_020.Mortalitas, stack);
    		
    		if(level <= 0)
    			return;
    		
    		if(!stack.hasTagCompound())
    		    stack.setTagCompound(new NBTTagCompound());	
    			
    		float damage = stack.getTagCompound().getFloat("additionalDamage");
    		
    		e.setAmount(e.getAmount() + damage);
    		
    	}
    }
    
       
    @SubscribeEvent(priority = EventPriority.HIGHEST) 
	public void Death(LivingDeathEvent fEvent){
    	
    if(fEvent.getSource().getTrueSource() == null)
    	return;
    	
    if(!(fEvent.getSource().getTrueSource() instanceof EntityPlayer))
		return;
    
    EntityLivingBase victim = fEvent.getEntityLiving();	
    if(victim == null)
    	return;
    
    EntityPlayer attacker = (EntityPlayer)fEvent.getSource().getTrueSource();
 
    ItemStack stack = ((EntityLivingBase) fEvent.getSource().getTrueSource()).getHeldItemMainhand();
    
	if(stack.isEmpty() || stack == null)
		return;
		
	int level = EnchantmentHelper.getEnchantmentLevel(Smc_020.Mortalitas, stack);
	
	if(level <= 0)
		return;
	
	if(!stack.hasTagCompound())
	    stack.setTagCompound(new NBTTagCompound());	
	
	float amount = stack.getTagCompound().getFloat("additionalDamage");
	
	amount = amount + (0.05f / this.getMaxLevel()) * level;
	amount = MathHelper.clamp(amount, 0, level);
	
	stack.getTagCompound().setFloat("additionalDamage", amount);
	
	
		}
    	
    

}
	    	
	            
			
		
    
    



