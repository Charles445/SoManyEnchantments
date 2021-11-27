package com.Shultrea.Rin.Enchantment_Base_Sector;

import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public abstract class EnchantmentBase extends Enchantment
{
	private boolean registered;
	
	//Current Registry issues
	
	//EnchantmentData constructing
	
	//TODO canApply from EntityVillager.ListEnchantedBookForEmeralds (grabs from entire registry), might be able to do this with event shenanigans
	//TODO apply from EnchantRandomly (grabs from entire registry, applies to Items.BOOK or canApply)
	
	//(RESOLVED) getEnchantmentDatas from EnchantmentHelper (checks any of canApplyAtEnchantingTable and isAllowedOnBooks)
	
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
	
	//Enchantment Overrides
	
	//Overridden by most things
	//Same as vanilla
	@Override
	public int getMaxLevel()
	{
		return 1;
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
	
	//Current Overrides List
	//Advanced Bane of Arthropods
	//Advanced Sharpness
	//Advanced Smite
	//Bluntness
	//Butchering
	//Defusion
	//Rusted
	//Culling
	//Empowered Defence
	//Adept
	//DifficultyScaled
	//Inhumanity
	//Subject Enchantments
	//Tier Damage
	//Upgraded Potentials
	@Override
	public boolean canApply(ItemStack stack)
	{
		return isEnabled() && super.canApply(stack);
	}
	
	//Current Overrides List
	//Pandora's Curse
	@Override
	@SuppressWarnings("deprecation")
	public String getTranslatedName(int level)
    {
		String s = I18n.translateToLocal(this.getName());
		
		//Formatting
		
		if(!this.isEnabled())
		{
			s = TextFormatting.DARK_RED + "" + TextFormatting.STRIKETHROUGH + s;
		}
		else if (this.isCurse())
        {
            s = TextFormatting.RED + s;
        }
		else
		{
			s = getPrefix() + s;
		}

		//Content
        return level == 1 && this.getMaxLevel() == 1 ? s : s + " " + I18n.translateToLocal("enchantment.level." + level);
    }
	
	//Current Overrides List
	//Rune Piercing Capabilities
	//Rune Magical Blessing
	//Rune Revival
	//Ancient Curse Inflicter
	//Rune Arrow Piercing
	//Rune Resurrection
	//Ancient Sword Mastery
	public String getPrefix()
	{
		return "";
	}
	
	public void onEntityDamagedAlt(EntityLivingBase user, Entity target, ItemStack weapon, int level)
	{
		
	}
	
	/** Use onEntityDamagedAlt **/
	@Override
	@Deprecated
	public final void onEntityDamaged(EntityLivingBase user, Entity target, int level)
	{
		super.onEntityDamaged(user, target, level);
	}

	/** Returns whether the combination of sources is disallowed for all enchantments **/
	public static boolean isOffensivePetDisallowed(Entity immediateSource, Entity trueSource)
	{
		if(immediateSource == null || trueSource == null)
			return false;

		if(!ModConfig.miscellaneous.enablePetAttacks)
		{
			//Immediate is not true source
			//Immediate is a living entity
			//True source is a player
			//Immediate is not a player
			if(immediateSource != trueSource && immediateSource instanceof EntityLivingBase && trueSource instanceof EntityPlayer && !(immediateSource instanceof EntityPlayer))
			{
				return true;
			}
		}
		
		//Passed all checks
		return false;
	}
}
