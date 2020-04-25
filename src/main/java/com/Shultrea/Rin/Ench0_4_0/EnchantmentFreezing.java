package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IPotionDebuffer;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentFreezing extends Enchantment implements IPotionDebuffer{
	public EnchantmentFreezing()
	{
	super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
    this.setName("freezing");
	this.setRegistryName("freezing");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	@Override
    public int getMinEnchantability(int par1)
    {
        return 24 + 13 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }
	
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof IPotionDebuffer);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem().canApplyAtEnchantingTable(stack, this) && ModConfig.enabled.Freezing;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.Freezing;
    }
    
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onEntityDamaged(LivingDamageEvent fEvent){
    	
    	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
			return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase user = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(user == null)
    		return;
    	
    	ItemStack dmgSource = user.getHeldItemMainhand();
		if(dmgSource == null)
			return;
		
		int level = EnchantmentHelper.getEnchantmentLevel(Smc_040.freezing, dmgSource);
  
    	if(level <= 0)
    	    return;
    	
    	EntityLivingBase victim = fEvent.getEntityLiving();
    	
    	int ice = 0;
    	int numb = 0;
    	
    	
        	
    	if(victim.isPotionActive(MobEffects.SLOWNESS) == true & victim.isPotionActive(MobEffects.MINING_FATIGUE) == true){
    
    		PotionEffect pot1 = victim.getActivePotionEffect(MobEffects.SLOWNESS);
        	PotionEffect pot2 = victim.getActivePotionEffect(MobEffects.MINING_FATIGUE);
    		
        ice = pot1.getAmplifier() + 1;
    	numb = pot2.getAmplifier() + 1;
    	
    	
    	if(ice > 7)
        	ice = 7;
        if(numb > 7)
        	numb = 7;
    	
        if(victim instanceof EntityPlayer){
    	victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30 * level + 40, ice));
    	victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 30 * level + 40, numb));
    	
        }
        else if(victim instanceof EntityLivingBase){
        	
        	ice += 1;
        	numb += 1;
        	
        	if(ice > 7)
            	ice = 7;
            if(numb > 7)
            	numb = 7;	
        	
        victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30 * level + 40, ice));    	  
        victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 30 * level + 40, numb));
        }
    	}
    	
    	else if(victim instanceof EntityPlayer){
    		victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * level + 40, 0));
    		victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20 * level + 40, 0));
    		
    	}
    	else if(victim instanceof EntityLivingBase){
    		victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * level + 40, 1));
    		victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20 * level + 40, 1));
    		
    	}
    	
    	
    	
    	if(ice >= 7 && numb >= 7 && victim.getEntityWorld().getGameRules().getBoolean("mobGriefing") == true){
    	
    	float chance = (float) Math.random() * 100f;
    		
    	if(chance < (level * 10)){
    	victim.extinguish();
    	dmgSource.damageItem(6 - level, user);
    	
    	float damage = EnchantmentsUtility.CalculateDamageIgnoreSwipe(fEvent.getAmount(), 2.0f, 0.65f, 1.0f, user, Smc_040.freezing);
    
    	if(!(user instanceof EntityPlayer)){
    	EnchantmentsUtility.ImprovedKnockBack(user, 0.25f, user.posX - victim.posX, user.posZ - victim.posZ);
    	}
    	
    	
    	victim.getEntityWorld().playSound(null, victim.posX, victim.posY, victim.posZ, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.MASTER, 0.8f, -1f);
    	
    	BlockPos pos =                  new BlockPos(victim.posX,victim.posY,victim.posZ);
    	BlockPos pos1 =                 new BlockPos(victim.posX, victim.posY + 1,  victim.posZ);
    	BlockPos pos2 =                 new BlockPos(victim.posX,  victim.posY + 2,  victim.posZ);
    	BlockPos pos3 =                 new BlockPos( victim.posX + 1,  victim.posY,  victim.posZ);
    	BlockPos pos4 =                 new BlockPos(victim.posX,  victim.posY, victim.posZ + 1);
    	BlockPos pos5 =                 new BlockPos( victim.posX - 1,  victim.posY, victim.posZ);
    	BlockPos pos6 =                 new BlockPos( victim.posX, victim.posY,  victim.posZ - 1);
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
    	victim.getEntityWorld().scheduleUpdate(pos, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos1, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos1, Blocks.FROSTED_ICE.getDefaultState());
    	victim.getEntityWorld().scheduleUpdate(pos1, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos2, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos2, Blocks.FROSTED_ICE.getDefaultState());
    	victim.getEntityWorld().scheduleUpdate(pos2, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos3, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos3, Blocks.FROSTED_ICE.getDefaultState());
    	victim.getEntityWorld().scheduleUpdate(pos3, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos4, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos4, Blocks.FROSTED_ICE.getDefaultState());
    	victim.getEntityWorld().scheduleUpdate(pos4, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos5, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos5, Blocks.FROSTED_ICE.getDefaultState());
    	victim.getEntityWorld().scheduleUpdate(pos5, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos6, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos6, Blocks.FROSTED_ICE.getDefaultState());
    	victim.getEntityWorld().scheduleUpdate(pos6, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	
    	
    	if(level >= 3){
    		
    	damage += EnchantmentsUtility.CalculateDamageIgnoreSwipe(fEvent.getAmount(), 0.0f, 0.25f, 1.10f, user, Smc_040.freezing);
        fEvent.setAmount(damage);	
    		
    	BlockPos pos7  =                 new BlockPos( victim.posX - 1, victim.posY,  victim.posZ - 1);
    	BlockPos pos8  =                 new BlockPos( victim.posX + 1, victim.posY,  victim.posZ + 1);
    	BlockPos pos9  =                 new BlockPos( victim.posX - 1, victim.posY + 1,  victim.posZ);
    	BlockPos pos10 =                 new BlockPos( victim.posX + 1, victim.posY + 1,  victim.posZ);
    	BlockPos pos11 =                 new BlockPos( victim.posX, victim.posY + 1,  victim.posZ - 1);
    	BlockPos pos12 =                 new BlockPos( victim.posX, victim.posY + 1,  victim.posZ + 1);
    	BlockPos pos13 =                 new BlockPos( victim.posX + 2, victim.posY,  victim.posZ    );
    	BlockPos pos14 =                 new BlockPos( victim.posX - 2, victim.posY,  victim.posZ    );
    	BlockPos pos15 =                 new BlockPos( victim.posX, victim.posY,  victim.posZ - 2    );
    	BlockPos pos16 =                 new BlockPos( victim.posX, victim.posY,  victim.posZ + 2    );
    	BlockPos pos17 =                 new BlockPos( victim.posX + 1, victim.posY,  victim.posZ - 1    );
    	BlockPos pos18 =                 new BlockPos( victim.posX - 1, victim.posY,  victim.posZ + 1    );
    		
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos7, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos7, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos7, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    		
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos8, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos8, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos8, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos9, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos9, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos9, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos10, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos10, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos10, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos11, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos11, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos11, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos12, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos12, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos12, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos13, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos13, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos13, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos14, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos14, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos14, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos15, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos15, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos15, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos16, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos16, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos16, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos17, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos17, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos17, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.FROSTED_ICE, pos18, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos18, Blocks.FROSTED_ICE.getDefaultState());
    	    victim.getEntityWorld().scheduleUpdate(pos18, Blocks.FROSTED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	}
    	
    	else fEvent.setAmount(damage);
    	}
    	
    	
    }
    }
}