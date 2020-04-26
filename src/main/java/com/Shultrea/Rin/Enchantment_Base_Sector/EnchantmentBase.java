package com.Shultrea.Rin.Enchantment_Base_Sector;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public abstract class EnchantmentBase extends Enchantment
{
	private boolean registered;
	
	public EnchantmentBase(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots)
	{
		super(rarityIn, typeIn, slots);
		this.registered = false;
	}
	
	/** Returns the ModConfig.enabled for the enchantment
	 * 
	 */
	public abstract boolean isConfigEnabled();
	
	/** Whether the enchantment is both enabled in the config and registered
	 * 
	 */
	public boolean isEnabled()
	{
		return isRegistered() && isConfigEnabled();
	}
	
	public void setRegistered()
	{
		this.registered = true;
	}
	
	/** Whether the enchantment is registered or not. Ignores config.
	 * 
	 */
	public boolean isRegistered()
	{
		return this.registered;
	}
	
	//Used for something? Not quite sure how this is used
	//Current Overrides List
	//Curse of Holding
	//Curse of Possession
	//Curse of Vulnerability
	//Ancient Curse Inflicter
	@Override
	public boolean isAllowedOnBooks()
	{
		return isEnabled();
	}
	
	//Current Overrides List
	//Rune Revival
	//Advanced Mending
	//Ancient Curse Inflicter
	//Scythe Damage
	@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return isEnabled() && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
	
}
