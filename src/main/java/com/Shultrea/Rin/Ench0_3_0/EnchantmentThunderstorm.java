package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Interfaces.IWeatherEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentThunderstorm extends EnchantmentBase implements IWeatherEnchantment{
	public EnchantmentThunderstorm()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Thunderstorm");
		this.setRegistryName("Thunderstorm");
		
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.ThunderstormEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 6;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 20 + (par1 - 1) * 15;
    }


    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {	
    	 return super.canApplyTogether(fTest) && !(fTest instanceof IWeatherEnchantment);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }

    public boolean isValidPlayer(Entity entity) {

        if (entity instanceof EntityPlayer) {

            if (((EntityPlayer) entity).getHeldItemMainhand() != null) {

                if (level(((EntityPlayer) entity).getHeldItemMainhand()) > 0) {

                    return true;
                }
            }
        }

        return false;
    }
  
  public boolean isValidMob(Entity entity) {

        if (entity instanceof EntityMob || entity instanceof EntityAnimal) {

            if (((EntityMob) entity).getHeldItemMainhand() != null) {

                if (level(((EntityMob) entity).getHeldItemMainhand()) > 0) {

                    return true;
                }
            }
            if (((EntityAnimal) entity).getHeldItemMainhand() != null) {

                if (level(((EntityAnimal) entity).getHeldItemMainhand()) > 0) {

                    return true;
                }
            }
        }

        return false;
    } 
  
  public int level(ItemStack stack) {

        return EnchantmentHelper.getEnchantmentLevel(Smc_030.Thunderstorm, stack);
    }

 /** @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onEntityHit(LivingAttackEvent fEvent) {
	  if(!(fEvent.getSource().equals("player") && fEvent.getSource().equals("mob")))
		  return;
	  if(fEvent.getEntityLiving())

	  
        	 ItemStack stack = fEvent.getEntityLiving().getHeldItemMainhand();
        	 if(!(EnchantmentHelper.getEnchantmentLevel(Smc_030.Thunderstorm, stack) != 0))
        		 return;
        	int levelWeather = EnchantmentHelper.getEnchantmentLevel(Smc_030.Thunderstorm, stack);

          
        	 if(fEvent.getEntity().world.isRaining() == false ||fEvent.getEntity().world.isThundering() == false){
             	
        	}
        	if(fEvent.getTarget().world.isThundering()){
        	if(fEvent.getEntity().world.rand.nextInt(225) < 19 + (levelWeather)){
        		
        	    
        		float var4 = 1.0F;
        		int i = (int)(fEvent.getTarget().prevPosX + ((fEvent.getTarget().posX - (fEvent.getTarget().prevPosX) * (double)var4)));
        		int j = (int)(fEvent.getTarget().prevPosY + ((fEvent.getTarget().posY - (fEvent.getTarget().prevPosY) * (double)var4 + 1.30D - (double)(fEvent.getTarget().getYOffset()))));
        		int k = (int)((fEvent.getTarget().prevPosZ + ((fEvent.getTarget().posZ - (fEvent.getTarget().prevPosZ) * (double)var4))));
        		
        		
        		fEvent.getEntityLiving().world.newExplosion(fEvent.getEntityLiving(), fEvent.getTarget().posX, fEvent.getTarget().posY + 1, fEvent.getTarget().posZ, 1.50f + (levelWeather * 0.10f), true, false);
        		((EntityLivingBase) fEvent.getEntityLiving()).addPotionEffect(new PotionEffect(Potion.getPotionById(12), 40, 0));
        		fEvent.getTarget().world.spawnEntity(new EntityLightningBolt(fEvent.getTarget().world, i, j, k, false));
        		BlockPos pos = new BlockPos(i + 0.1D,j + 0.1D,k + 0.1D);
        		fEvent.getTarget().world.playSound(fEvent.getEntityPlayer(),pos, SoundEvents.ENTITY_LIGHTNING_THUNDER ,SoundCategory.AMBIENT,1.0F, 1.0F);
        		
        		
        		
        		
        	}
        	}
        	}
         */
                    
                



@SubscribeEvent(priority=EventPriority.HIGHEST)
public void HandleEnchant(LivingHurtEvent fEvent){
	float SkyDamage = 0.0f;
	
	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
		return;
	
	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
		return;
	
	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
	if(attacker == null)
		return;
	
	ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
	if(dmgSource == null)
		return;
	
	if(EnchantmentHelper.getEnchantmentLevel(Smc_030.Thunderstorm, dmgSource) <= 0)
		return;
	
	
	int levelWeather = EnchantmentHelper.getEnchantmentLevel(Smc_030.Thunderstorm, dmgSource);
	
	float Damage = fEvent.getAmount();
	
	if(attacker.world.isThundering() == true && EnchantmentsUtility.noBlockLight(attacker))
	{
	
	
	//SkyDamage = (levelWeather * 1.30f);
	//fEvent.setAmount(Damage + SkyDamage);
	
		float FDamage = EnchantmentsUtility.CalculateDamageIgnoreSwipe(Damage, 0.0f, 1.25f, 1.00f, attacker, Smc_030.Thunderstorm);
	    fEvent.setAmount(FDamage);
		}
	
	else if(attacker.world.isRaining() == true){
		fEvent.setAmount(Damage);
	}
		
	else if(EnchantmentsUtility.noBlockLight(attacker))
		
	{   
		
		float Fin = EnchantmentsUtility.CalculateDamageForNegativeSwipe(Damage, 0.00f, -0.5f, 1.0f, attacker, Smc_030.Thunderstorm);
		fEvent.setAmount(Fin);
		
		if(fEvent.getEntity().world.rand.nextInt(800) < 2 + (levelWeather * 2)){
     		EnchantmentsUtility.setThunderstorm(fEvent.getEntityLiving().getEntityWorld());
		}
	}
	else if(EnchantmentsUtility.noBlockLight(attacker) == false)		
		{   
			
			float FI = EnchantmentsUtility.CalculateDamageForNegativeSwipe(Damage, -0.05f, -0.75f, 1.0f, attacker, Smc_030.Thunderstorm);
			fEvent.setAmount(FI);
			
			if(fEvent.getEntity().world.rand.nextInt(800) < 2 + (levelWeather * 2)){
	     		
			}
}
}
}
