package com.Shultrea.Rin.Ench0_4_0;

import java.util.List;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IAncientEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentLister;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class EnchantmentAncient_CurseInflicter extends EnchantmentBase implements IAncientEnchantment{

	public EnchantmentAncient_CurseInflicter() {
		super(Rarity.VERY_RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("AncientCurseInflicter");
		this.setRegistryName("AncientCurseInflicter");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AncientCurseInflicter;
	}
	
	@Override
	public int getMaxLevel(){
		return 1;
	}
	
	@Override
    public int getMinEnchantability(int ench)
    {
        return 240 * ench;
    }

	@Override
    public int getMaxEnchantability(int ench)
    {
        return 720 * ench;
    }
	
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return false;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return false;
    }
    
    @Override
    public boolean isTreasureEnchantment() {
    	return true;
    }
	
	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity target, int level){
		
		if(target instanceof EntityLivingBase){
			EntityLivingBase entity = (EntityLivingBase) target;
			Iterable<ItemStack> iter = entity.getEquipmentAndArmor();
			
			for(ItemStack stack : iter){
				if(user.getRNG().nextInt(8) < 1){
					List<Enchantment> list = EnchantmentLister.CURSE;
					
					int random = user.getRNG().nextInt(list.size());
					Enchantment ench = list.get(random);
					int randLevel = user.getRNG().nextInt(ench.getMaxLevel());
					
					if(!ench.canApply(stack))
						continue;
					
					boolean isCompatible = true;
					NBTTagList nbt = stack.getEnchantmentTagList();
					for(int z = 0; z < nbt.tagCount(); z++) {
						NBTTagCompound tag = nbt.getCompoundTagAt(z);
						int enchId = tag.getShort("id");
						int currEnchLevel = tag.getShort("lvl");
						Enchantment enchantment = Enchantment.getEnchantmentByID(enchId);
						if(enchantment == null)
							continue;
						if(enchantment == this)
							continue;
						if(enchantment.isCompatibleWith(ench))
							continue;
						
						else isCompatible = false;
						break;
					}
					
					if(EnchantmentHelper.getEnchantmentLevel(ench, stack) <= 0 && isCompatible)
					stack.addEnchantment(list.get(random), randLevel + 1);
					
		
				}
			}
		}
	}

	@Override
	public String getPrefix()
	{
		return TextFormatting.YELLOW.toString();
	}
}
