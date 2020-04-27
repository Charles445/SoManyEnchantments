package com.Shultrea.Rin.Ench0_4_5;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentSweepingEdge;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentMastery extends EnchantmentBase {
	public EnchantmentMastery(){
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("SwordMastery");
		this.setRegistryName("SwordMastery");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AncientSwordMastery;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 80 + 80 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + par1 * 80;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment e)
    {    			
    	return super.canApplyTogether(e) && !(e instanceof EnchantmentSweepingEdge);
    }
    
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void HandleEnchant(LivingDamageEvent e)
    {
    	if(e.getSource().damageType != "player" && e.getSource().damageType != "mob")
			return;
 	
    	if(!(e.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)e.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	ItemStack stack = ((EntityLivingBase)e.getSource().getTrueSource()).getHeldItemMainhand();
		if(stack.isEmpty() || stack == null)
			return;
		
		EntityLivingBase victim = e.getEntityLiving();
		if(victim == null)
			return;

		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		
		int level = EnchantmentHelper.getEnchantmentLevel(this, stack);
		
		if(EnchantmentHelper.getEnchantmentLevel(this, stack) <= 0)
			return;
		
		if(stack.getTagCompound().getBoolean("flag")) {
			stack.getTagCompound().setBoolean("flag", false);
			return;
		}
		
		stack.getTagCompound().setBoolean("flag", true);
		
		if(EnchantmentsUtility.isLevelMax(stack, this) || EnchantmentsUtility.RANDOM.nextInt(3) < level - 1)
		if(stack.getItem() instanceof ItemSword && attacker instanceof EntityPlayer) {
			ItemSword sword = (ItemSword) stack.getItem();
			sword.onLeftClickEntity(stack, (EntityPlayer)attacker, victim);
			sword.hitEntity(stack, victim, attacker);
			
			NBTTagList nbt = stack.getEnchantmentTagList();
			for(int x = 0; x < nbt.tagCount(); x++) {
				NBTTagCompound tag = nbt.getCompoundTagAt(x);
				int enchId = tag.getShort("id");
				int currEnchLevel = tag.getShort("lvl");
				Enchantment enchantment = Enchantment.getEnchantmentByID(enchId);
				if(enchantment == null)
					continue;
				//System.out.println(enchantment);
				enchantment.onEntityDamaged(attacker, victim, currEnchLevel);
				//System.out.println("is IT WORKING");
			}
		}
		
		if(EnchantmentsUtility.isLevelMax(stack, this)) {
			float damage = net.minecraftforge.common.ForgeHooks.onLivingHurt(e.getEntityLiving(), e.getSource(), 1);
			damage += net.minecraftforge.common.ForgeHooks.onLivingDamage(e.getEntityLiving(), e.getSource(), 1);
		
			damage = (damage - 2) * (0.4f + (level * 0.1f));
			damage = damage + e.getAmount();
		
			e.setAmount(damage);
		}
		
    }

	@Override
	public String getPrefix()
	{
		return TextFormatting.YELLOW.toString();
	}

}