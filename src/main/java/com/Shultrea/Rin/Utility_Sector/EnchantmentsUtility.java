package com.Shultrea.Rin.Utility_Sector;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.google.common.collect.Lists;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * Recreated by Rin on 13/05/2017. This is an utility to be used for some enchantments.
 */
public class EnchantmentsUtility {
	
	
	public static final Random RANDOM = new Random();

    public static int getFood(EntityPlayer player)
    {
        return player.getFoodStats().getFoodLevel();
    }
    public static float getSat(EntityPlayer player)
    {
        return player.getFoodStats().getSaturationLevel();
    }
    public static void setFood(EntityPlayer player, int foodLevel, float saturationLevel)
    {
        player.getFoodStats().addStats(foodLevel, saturationLevel);
    }

    public static boolean tryPercentage (double percent) {
        
        return Math.random() < percent;
    }
    
    
    public static boolean seeSky(EntityPlayer player)
    {BlockPos pos = new BlockPos(player.posX ,player.posY,player.posZ);
        if(player.world.canBlockSeeSky(pos))
            return true;
        else
            return false;
    }
    public static ItemStack[] getTools(EntityPlayer player)
    {
        List<ItemStack> tools = new ArrayList();
        for (int i = 0; i < (somanyenchantments.hotbarOnly ? 9 : 36); i++)
        {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if ((stack != null) && (stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemBow)) {
                tools.add(stack);
            }
        }
        return (ItemStack[])tools.toArray(new ItemStack[tools.size()]);
    }
 
    
    public static boolean noBlockLight(EntityLivingBase attacker)
    {
    	BlockPos pos = new BlockPos(attacker.posX ,attacker.posY,attacker.posZ);
        if(attacker.world.canBlockSeeSky(pos))
            return true;
        else
            return false;
    }
    public static void Disarm(EntityLivingBase entityLiving)
	{
		if(entityLiving.getHeldItemMainhand() != null){
			entityLiving.entityDropItem(entityLiving.getHeldItemMainhand(), 4.5f);
			ItemStack Weapon = ItemStack.EMPTY;
			
			entityLiving.setHeldItem(EnumHand.MAIN_HAND, Weapon);
			

		}
		else if(entityLiving.getHeldItemOffhand() != null){
			entityLiving.entityDropItem(entityLiving.getHeldItemOffhand(), 4.5f);
			ItemStack Weapon = ItemStack.EMPTY;
			
			entityLiving.setHeldItem(EnumHand.OFF_HAND, Weapon);
			

		}

	}
    
    		
    public static void Raining(World world) {
    		    		 {
    		    		
    		    		world.getWorldInfo().setRainTime(0);
    		    		world.getWorldInfo().setRaining(true);
    		    		
    		    		}
    		    		
    		    		
    		    		
    		    	
    		    		}
    		    		

    public static void setThunderstorm(World world) 
    {
		world.getWorldInfo().setThunderTime(0);
		world.getWorldInfo().setRaining(true);
		world.getWorldInfo().setThundering(true);
    }
		  
    public static boolean noDayLight(EntityLivingBase attacker)
    {
    	
       int light = attacker.world.getSkylightSubtracted();
       if(light > 5)
            return true;
        else
            return false;
    }
    /**Used to calculate damage without intentionally inflicting extra damage fot the sweeping attack around the target. 
     * 
     * @param damage - The original damage.
     * @param attacker - The attacker.
     * @param enchantment - The enchantment used for the calculation of damage based on the level.
     * @param multiplier - Multiplies the given value based on the enchantment's level you want to add in the calculation.
     * @param constant - The constant damage you want to add in the calculation.
     * @param TrueMultiplier - Multiplies the damage based on the total damage and not the level of the enchantment you want to add in the calculation. Avoid using zero
     * as this negates your damage.
     * @return The finished calculated damage.
     */
    public static float CalculateDamageIgnoreSwipe(float damage, float constant, float multiplier, float TrueMultiplier, EntityLivingBase attacker, Enchantment enchantment){
    	float afterCalculated = 0;
    	
    	float Damager = damage;
		float SecondDamage = damage;
		float damageReducer = 0;
		
		ItemStack weapon = attacker.getHeldItemMainhand();
		
		if(!(SecondDamage > 1.0f))
			return 1.0f;
		
		int level = 1;
		
		if(enchantment != null)
		level = EnchantmentHelper.getEnchantmentLevel(enchantment, weapon);
		
		SecondDamage = (SecondDamage + (constant + level * multiplier)) * TrueMultiplier;
		
		Damager = (Damager + (constant + multiplier * level) - 2.0f) * TrueMultiplier;
		
		if(Damager == 0.0f){
			Damager = SecondDamage;
		}
		
		float percentage = SecondDamage / Damager;
		
		
		float newDamage = Damager * percentage;
		
		afterCalculated = newDamage;
    	
    	return afterCalculated;
    }
    
    /**Used to calculate damage reduction in some enchantments. Forces swipe damage back to 1.0f.
     * 
     * @param damage - The original damage.
     * @param attacker - The attacker.
     * @param enchantment - The enchantment used for the calculation of damage based on the level.
     * @param multiplier - Multiplies the given value based on the enchantment's level you want to add in the calculation.
     * @param constant - The constant damage you want to add in the calculation.
     * @param TrueMultiplier - Multiplies the damage based on the total damage and not the level of the enchantment you want to add in the calculation. Avoid using zero
     * as this negates your damage.
     * @return The finished calculated damage.
     */
    public static float CalculateDamageForNegativeSwipe(float damage, float constant, float multiplier, float TrueMultiplier, EntityLivingBase attacker, Enchantment enchantment){
    	float afterCalculated = 0;
    	float Damager = damage;
		float SecondDamage = damage;
		float damageReducer = 0;
		
		ItemStack weapon = attacker.getHeldItemMainhand();
		
		if(!(SecondDamage > 1.0f))
			return 1.0f;
			
		int level = EnchantmentHelper.getEnchantmentLevel(enchantment, weapon);
		
		SecondDamage = (SecondDamage + (constant + level * multiplier)) * TrueMultiplier;
		
		Damager = (Damager + (constant + multiplier * level) - 2.0f) * TrueMultiplier;
		
		if(Damager <= 0.0f){
			Damager = SecondDamage;
		}
		
		float percentage = SecondDamage / Damager;
		
		
		float newDamage = Damager * percentage;
		
		afterCalculated = newDamage;
    	
    	return afterCalculated;
    }
    
  
    /**Used to calculate the final LivingHurtEvent's swipe damage.*/
    public static float ExclusiveCalculateDamageForNegativeSwipe(float damage, float constant, EntityLivingBase attacker){
    	float afterCalculated = 0;
        
    	float returnity = 0;
    	float PrimaryDamage = 0.0f;
    	float safe1 = damage + 1000;
    	
    	//boolean a = false;
    
    	
    	float Damager = damage;
		float SecondDamage = damage;
		
		float Orig1 = damage;
		float Orig2 = damage;
		
		float damageReducer = 0;
		float swipeDamageCheck = 2.0f + 1.0f + (EnchantmentHelper.getSweepingDamageRatio(attacker) * damage);
		//System.out.println(EnchantmentHelper.func_191527_a(attacker));
		float safe2 = swipeDamageCheck + 1000;
		
		if(damage <= 0)
		return 0.0f;
		
		IAttributeInstance attackAttr = attacker.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
		float genericAtt = (float) attackAttr.getAttributeValue();
		
		if(genericAtt <= 1.0f){
			return -2.0f;
		}
		//System.out.println(firstCheck + " - The First Counter"); ///================================== 1111
		
		
		    
		
		        if(SecondDamage <= 4){
		    	//System.out.println("Beginsssssssssssssss");
		    	
			    return -2.0f;		
			   
		         }
		
		    
		    
		       if(SecondDamage == 1.0f){
				return 0.0f;
		    	}
			
		   
		
		
	   /** if(swipeDamageCheck + constant <= 1.0f && damage <= 4.0f){
			System.out.println("1st Argument");
			return 1.0f;
		
		}
		else if(swipeDamageCheck + constant > 1.0f && damage > 4.0f){
			return swipeDamageCheck + constant;
		}
		*/
		
		
		
		
		
		
	
		
		//1.0F - 1.0F / (float)(p_191526_0_ + 1)
		
	    Orig1 = (Orig1 + (constant));
		
		Orig2 = (Orig2 + (constant) - 2.0f);
		
		if(Orig2 <= 0.0f){
			Orig2 = Orig1;
		}
		
		float percentage = Orig1 / Orig2;
		
		float newDamage = Orig2 * percentage;
		
		afterCalculated = newDamage;
		
    	return afterCalculated;
    }
    
    
    /** An improved vanilla knockback mechanic that ignores the knockback resistance of a mob*/
    public static void ImprovedKnockBack(Entity entityIn, float strength, double xRatio, double zRatio)
    {
        
        {
            entityIn.isAirBorne = true;
            float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
            entityIn.motionX /= 2.0D;
            entityIn.motionZ /= 2.0D;
            entityIn.motionX -= xRatio / (double)f * (double)strength;
            entityIn.motionZ -= zRatio / (double)f * (double)strength;

            if (entityIn.onGround)
            {
            	entityIn.motionY /= 2.0D;
            	entityIn.motionY += (double)strength;

                if (entityIn.motionY > 0.4000000059604645D)
                {
                	entityIn.motionY = 0.4000000059604645D;
                }
            }
        }
        
        entityIn.velocityChanged = true;
    }
  
    public static boolean canBlockDamageSource(DamageSource damageSourceIn, EntityLivingBase entity)
    {
        if (!damageSourceIn.isUnblockable() && entity.isActiveItemStackBlocking())
        {
            Vec3d vec3d = damageSourceIn.getDamageLocation();

            if (vec3d != null)
            {
                Vec3d vec3d1 = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(entity.posX, entity.posY, entity.posZ)).normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

                if (vec3d2.dotProduct(vec3d1) < 0.0D)
                {
                    return true;
                }
            }
        }

        return false;
    }
    
    public static boolean canBlockDamageSourceIgnoreUnblockable(DamageSource damageSourceIn, EntityLivingBase entity)
    {
        if ( entity.isActiveItemStackBlocking())
        {
            Vec3d vec3d = damageSourceIn.getDamageLocation();

            if (vec3d != null)
            {
                Vec3d vec3d1 = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(entity.posX, entity.posY, entity.posZ)).normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

                if (vec3d2.dotProduct(vec3d1) < 0.0D)
                {
                    return true;
                }
            }
        }

        return false;
    }
    
    public static double getAdvancedKnockBackReduction(EntityLivingBase entityLivingBaseIn)
    {
    	double digit = 0;
        int i = EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.AdvancedBlastProtection, entityLivingBaseIn);

        if (i > 0)
        {
           digit = 0.95 - (i/5 + (1/40 * i));
          
        }

        return digit;
    }
    
    public static int getMaxEnchantmentLevel(Enchantment theEnchantment, EntityLivingBase user)
    {
        Iterable<ItemStack> iterable = theEnchantment.getEntityEquipment(user);

        if (iterable == null)
        {
            return 0;
        }
        else
        {
            int i = 0;

            for (ItemStack itemstack : iterable)
            {
                int j = EnchantmentHelper.getEnchantmentLevel(theEnchantment, itemstack);

                if (j > i)
                {
                    i = j;
                }
            }

            return i;
        }
    }
 /**   public static float Reflection(Field field) throws Exception{
    	field.setAccessible(true);
    	Field modifiersField = Field.class.getDeclaredField("modifiers");
    	modifiersField.setAccessible(true);
        //modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        Object obj = new Object();

        //field.set(null, value);
        return field.getFloat(obj);
        
    }
    */
    
	public static void clearSky(World world) {
		if((world.getWorldInfo().isRaining() == true)){
    		world.getWorldInfo().setRaining(false);
    		
		}
				
		if((world.getWorldInfo().isThundering() == true)){
			world.getWorldInfo().setThundering(false);
			world.getWorldInfo().setRaining(false);
			
		}
	
	
		
		
			
		}
	
	  public static ItemStack getEnchantedItemInInventory(Enchantment p_92099_0_, EntityLivingBase p_92099_1_)
	    {
	        List<ItemStack> list = p_92099_0_.getEntityEquipment(p_92099_1_);

	        if (list.isEmpty())
	        {
	            return ItemStack.EMPTY;
	        }
	        else
	        {
	            List<ItemStack> list1 = Lists.<ItemStack>newArrayList();

	            for (ItemStack itemstack : list)
	            {
	                if (!itemstack.isEmpty() && EnchantmentHelper.getEnchantmentLevel(p_92099_0_, itemstack) > 0)
	                {
	                    list1.add(itemstack);
	                }
	            }

	            return list1.isEmpty() ? ItemStack.EMPTY : (ItemStack)list1.get(p_92099_1_.getRNG().nextInt(list1.size()));
	        }
	    }

	public static boolean isInASpecificBiome(Biome biome, EntityLivingBase attacker){
		Biome cB = attacker.world.getBiome(attacker.getPosition());
		if(biome==cB)
		return true;
		
		return false;
	}

	  
	public static boolean isInColdTemperature(EntityLivingBase attacker, Biome biome, boolean isColder){
		
		BlockPos pos = new BlockPos(attacker.posX ,attacker.posY,attacker.posZ);
		float temperature = biome.getTemperature(pos);
	
	
		if(isColder == true)
		if(temperature <= 0.05){
		return true;
		}
		
		else if(temperature < 1.51f)
		return true;
		
        return false;


}
	
		public static  Vec3d Cleave(double i, double j, double y){
			
			return new Vec3d(i, j, y);
		}
		
		public static boolean IsInCave(EntityLivingBase victim){
			double Height = victim.posY;
			if(Height < 41){
				return true;
			}
			return false;
		}
		
		public static boolean IsInEnd(EntityLivingBase attacker){
			
			 BlockPos pos = new BlockPos(attacker.posX ,attacker.posY,attacker.posZ);
			if(attacker.world.provider.getDimension() == 1){
			return true;
	}
	return false;
	

}
		
		public static boolean IsInNether(EntityLivingBase attacker){
			
			 BlockPos pos = new BlockPos(attacker.posX ,attacker.posY,attacker.posZ);
			if(attacker.world.provider.getDimension() == -1){
			return true;
	}
	return false;
	

}
		public static boolean IsInMountains(EntityLivingBase attacker){
			
			 BlockPos pos = new BlockPos(attacker.posX ,attacker.posY,attacker.posZ);
			    if(attacker.world.getBiome(pos) == Biome.getBiome(28)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(31)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(17)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(3)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(34)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(20)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(18)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(22)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(33)){return true;}
				if(attacker.world.getBiome(pos) == Biome.getBiome(161)){return true;}
			    if(attacker.world.getBiome(pos) == Biome.getBiome(19)){return true;}
			    if(attacker.world.getBiome(pos) == Biome.getBiome(13)){return true;}
			    if(attacker.world.getBiome(pos) == Biome.getBiome(162)){return true;}
			    if(attacker.world.getBiome(pos) == Biome.getBiome(156)){return true;}
			    if(attacker.world.getBiome(pos) == Biome.getBiome(131)){return true;}
			    
	return false;
	
				}
	
		
	
		
		public static boolean IsInTaiga(EntityLivingBase attacker){
			
			 BlockPos pos = new BlockPos(attacker.posX ,attacker.posY,attacker.posZ);
			if(attacker.world.getBiome(pos) == Biome.getBiome(5)){return true;}
			if(attacker.world.getBiome(pos) == Biome.getBiome(133)){return true;}
			if(attacker.world.getBiome(pos) == Biome.getBiome(19)){return true;}
		    if(attacker.world.getBiome(pos) == Biome.getBiome(30)){return true;}
			if(attacker.world.getBiome(pos) == Biome.getBiome(158)){return true;}
			if(attacker.world.getBiome(pos) == Biome.getBiome(31)){return true;}
			if(attacker.world.getBiome(pos) == Biome.getBiome(32)){return true;}
			if(attacker.world.getBiome(pos) == Biome.getBiome(33)){return true;}
			if(attacker.world.getBiome(pos) == Biome.getBiome(160)){return true;}
			if(attacker.world.getBiome(pos) == Biome.getBiome(161)){return true;}
									
			
	
	return false;
	

}	
		   
	   public static void enchHandler(){
		   if(somanyenchantments.config.EnableFixEnchantment){
				MinecraftForge.EVENT_BUS.register(new HurtPatchHandler());
				}
	   }
	   /** For armors, similar to calcModifier*/
	   public static int CalcModgetTotalLevel(float modifier, Enchantment theEnchantment, EntityLivingBase user)
	    {
	        Iterable<ItemStack> iterable = theEnchantment.getEntityEquipment(user);

	        if (iterable == null)
	        {
	            return 0;
	        }
	        else
	        {
	            int i = 0;
	        

	            for (ItemStack itemstack : iterable)
	            {
	                int j = EnchantmentHelper.getEnchantmentLevel(theEnchantment, itemstack);
              	         
	                j *= modifier;   
	                
	                i += j;
	                    	                
	            }

	            return i;
	        }
	    }
	 
   
	   public static int getInput2EnchLevel(ItemStack item, Enchantment enchantment){
	   int level = 0;
	   Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(item);
	   
	   for (Enchantment enchantment1 : map.keySet())
       {
           if (enchantment1 != null && enchantment1 == enchantment)
           {
               int i2 = map.containsKey(enchantment1) ? ((Integer)map.get(enchantment1)).intValue() : 0;
               level = i2;
               return level;
           }

	   }
	   return level;
	   }
	   
	   /**
	   public boolean isEnchantmentRestricted(ItemStack stack){
		   Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
		   
		   for(Enchantment enchantment : map.keySet()){
			   		boolean flag = false;
			   if(enchantment != null){		
				   flag = enchantment.canApplyAtEnchantingTable(stack);
				   if(flag == true)
					   return flag;
   
			   }
		   }
		   
		   return false;
	   }
	   */
	   public static Map<Enchantment, Boolean> getEnchantmentInformation(ItemStack stack){
		   Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
		   Map<Enchantment, Boolean> values = new HashMap<Enchantment, Boolean>();
		   boolean flag = false;
		   for (Enchantment enchantment : map.keySet())
	       {	   
			   if(enchantment != null){		
				   flag = enchantment.canApplyAtEnchantingTable(stack);			 
			   }
			   values.put(enchantment, flag);
	       }
		   
		   return values;
	   }
	   
	   
	   public static float getDamageAfterMagicAbsorb(float damage, float enchantModifiers)
	    {
	        float f = MathHelper.clamp(enchantModifiers * 1.5f, 0.0F, 60.0F);
	        return damage * (1.0F - f / 80.0F);
	    }
	   
	   
	   public static Potion getNonInstantNegativePotion(){
		   
		   int cycles = RANDOM.nextInt(PotionLister.debuff_ids.size());
		   
		   return Potion.getPotionById(PotionLister.debuff_ids.get(cycles));
		   
	   }
	   
	   public static Potion getInstantNegativePotion(){
		   
		   int cycles = RANDOM.nextInt(PotionLister.debuff_instant_ids.size());
		   
		   return Potion.getPotionById(PotionLister.debuff_instant_ids.get(cycles));
		   
	   }
	   public static Potion getNonInstantPositivePotion(){
	   
		   int cycles = RANDOM.nextInt(PotionLister.buff_ids.size());
	   
		   return Potion.getPotionById(PotionLister.buff_ids.get(cycles));
	   
	   }
	   public static Potion getInstantPositivePotion(){
	   
		   int cycles = RANDOM.nextInt(PotionLister.buff_instant_ids.size());
	   
		   return Potion.getPotionById(PotionLister.buff_instant_ids.get(cycles));
	   
	   }
	   
	   //For sunshine and moonlight
	   public static float reduceDamage(EntityLivingBase attacker, boolean mustBeDaytime, ItemStack stack, Enchantment enchantment){
		   
		   boolean flag = attacker.world.isDaytime();
		   flag = flag == mustBeDaytime;
		   int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
		   float damage = level * 0.5f + 1.5f;
		   	  
		   if(!flag)
			  damage *= -1f;
		   
		   if(!EnchantmentsUtility.noBlockLight(attacker)){
			   if(flag)
			   damage = damage - 0.75f * level;		   
			   else damage = damage - 0.5f * level;
		   }
			  		 
		   if(attacker.world.isRaining())
			  damage = damage - 0.5f * level;
		   
		   
		   return damage;
	   }
	   
	   public static boolean isLevelMax(ItemStack stack, Enchantment ench){
		   if(EnchantmentHelper.getEnchantmentLevel(ench, stack) >= ench.getMaxLevel())
			   return true;
		   
		   else return false;
		   
	   }
	   
	   public static boolean checkEventCondition(LivingHurtEvent e, Enchantment ench){
			if(!e.getSource().damageType.equals("player") && !e.getSource().damageType.equals("mob"))
				return false;
	    	
	    	if(!(e.getSource().getTrueSource() instanceof EntityLivingBase))
	    		return false;
	    	
	    	EntityLivingBase attacker = (EntityLivingBase)e.getSource().getTrueSource();
	    	if(attacker == null)
	    		return false;
	    	
	    	ItemStack dmgSource = attacker.getHeldItemMainhand();
			if(dmgSource == null)
				return false;
					
			return true;
			
	   }
	   
	   public static boolean checkEventCondition(LivingAttackEvent e, Enchantment ench){
		    if(!e.getSource().damageType.equals("player") && !e.getSource().damageType.equals("mob"))
				return false;
	    	
	    	if(!(e.getSource().getTrueSource() instanceof EntityLivingBase))
	    		return false;
	    	
	    	EntityLivingBase attacker = (EntityLivingBase)e.getSource().getTrueSource();
	    	if(attacker == null)
	    		return false;
	    	
	    	ItemStack dmgSource = ((EntityLivingBase)e.getSource().getTrueSource()).getHeldItemMainhand();
			if(dmgSource == null)
				return false;
			
			return true;
			
	   }

	   public static boolean checkEventCondition(LivingDamageEvent e, Enchantment ench){
		    if(!e.getSource().damageType.equals("player") && !e.getSource().damageType.equals("mob"))
				return false;
	    	
	    	if(!(e.getSource().getTrueSource() instanceof EntityLivingBase))
	    		return false;
	    	
	    	EntityLivingBase attacker = (EntityLivingBase)e.getSource().getTrueSource();
	    	if(attacker == null)
	    		return false;
	    	
	    	ItemStack dmgSource = ((EntityLivingBase)e.getSource().getTrueSource()).getHeldItemMainhand();
			if(dmgSource == null)
				return false;
			
			return true;
			
	   }

	    /**
	     * Adds the item stack to the inventory, returns false if it is impossible.
	     */
	    public static boolean addItemStackToInventoryWithoutHolding(ItemStack itemStackIn, InventoryPlayer e)
	    {
	        return addItemStackToInventorySpecificSlot(-1, itemStackIn, e);
	    }

	    public static boolean addItemStackToInventorySpecificSlot(int slot, final ItemStack stack, InventoryPlayer e)
	    {
	        if (stack.isEmpty())
	        {
	            return false;
	        }
	        else
	        {
	            try
	            {
	                if (stack.isItemDamaged())
	                {
	                    if (slot == -1)
	                    {
	                        slot = getFirstEmptyStackWithoutHolding(e.mainInventory, e);
	                    }

	                    if (slot >= 0)
	                    {
	                        e.mainInventory.set(slot, stack.copy());
	                       // ((ItemStack)e.mainInventory.get(slot)).setAnimationsToGo(0);
	                        stack.setCount(0);
	                        return true;
	                    }
	                    else if (e.player.capabilities.isCreativeMode)
	                    {
	                        stack.setCount(0);
	                        return true;
	                    }
	                    else
	                    {
	                        return false;
	                    }
	                }
	                else
	                {
	                    int i;

	                    while (true)
	                    {
	                        i = stack.getCount();

	                        if (slot == -1)
	                        {
	                            stack.setCount(storePartialItemStack(stack, e));
	                        }
	                        else
	                        {
	                            stack.setCount(addResource(slot, stack, e));
	                        }

	                        if (stack.isEmpty() || stack.getCount() >= i)
	                        {
	                            break;
	                        }
	                    }

	                    if (stack.getCount() == i && e.player.capabilities.isCreativeMode)
	                    {
	                        stack.setCount(0);
	                        return true;
	                    }
	                    else
	                    {
	                        return stack.getCount() < i;
	                    }
	                }
	            }
	            catch (Throwable throwable)
	            {
	                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding item to inventory");
	                CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being added");
	                crashreportcategory.addCrashSection("Item ID", Integer.valueOf(Item.getIdFromItem(stack.getItem())));
	                crashreportcategory.addCrashSection("Item data", Integer.valueOf(stack.getMetadata()));
	                crashreportcategory.addDetail("Registry Name", () -> String.valueOf(stack.getItem().getRegistryName()));
	                crashreportcategory.addDetail("Item Class", () -> stack.getItem().getClass().getName());
	                crashreportcategory.addDetail("Item name", new ICrashReportDetail<String>()
	                {
	                    public String call() throws Exception
	                    {
	                        return stack.getDisplayName();
	                    }
	                });
	                throw new ReportedException(crashreport);
	            }
	        }
	    }
	    
	    private static int storePartialItemStack(ItemStack itemStackIn, InventoryPlayer e)
	    {
	        int i = e.storeItemStack(itemStackIn);

	        if (i == -1)
	        {
	            i = getFirstEmptyStackWithoutHolding(e.mainInventory, e);
	        }

	        return i == -1 ? itemStackIn.getCount() : addResource(i, itemStackIn, e);
	    }
	   
	    private static int addResource(int p_191973_1_, ItemStack p_191973_2_, InventoryPlayer e)
	    {
	        Item item = p_191973_2_.getItem();
	        int i = p_191973_2_.getCount();
	        ItemStack itemstack = e.getStackInSlot(p_191973_1_);

	        if (itemstack.isEmpty())
	        {
	            itemstack = p_191973_2_.copy(); // Forge: Replace Item clone above to preserve item capabilities when picking the item up.
	            itemstack.setCount(0);

	            if (p_191973_2_.hasTagCompound())
	            {
	                itemstack.setTagCompound(p_191973_2_.getTagCompound().copy());
	            }

	            e.setInventorySlotContents(p_191973_1_, itemstack);
	        }

	        int j = i;

	        if (i > itemstack.getMaxStackSize() - itemstack.getCount())
	        {
	            j = itemstack.getMaxStackSize() - itemstack.getCount();
	        }

	        if (j > e.getInventoryStackLimit() - itemstack.getCount())
	        {
	            j = e.getInventoryStackLimit() - itemstack.getCount();
	        }

	        if (j == 0)
	        {
	            return i;
	        }
	        else
	        {
	            i = i - j;
	            itemstack.grow(j);
	            itemstack.setAnimationsToGo(5);
	            return i;
	        }
	    }
	    
	    public static int getFirstEmptyStackWithoutHolding(List list, InventoryPlayer e){
	    	int slot = e.currentItem;
	    	
    		for (int i = 0; i < list.size(); ++i)
            {
                if (((ItemStack)list.get(i)).isEmpty() && i != slot)
                {
                   // e.mainInventory.set(i, newTool);
                	return i;
                }
            }
    		
    		return -1;
	    }
	    
	    
	    
	  /**Used to replicate an explosion damage. 
	  * @param list - The list of the affected entities in an explosion.
	  * @param explosion - The explosion in question.
	  * @param power - The power of the explosion. Note that it is private so you will have to use reflection
	  * @param world - The world where an explosion occurred.
	  * @param ench - The enchantment.
	  */
	       
     public static void damageExplosion(Entity victim, Explosion explosion, float power, World world, Enchantment ench) {
    	 
    	 float explosionPower = power * 2;
    	 
    	 Vec3d vec3d = explosion.getPosition();
    	 
    	 double x = vec3d.x;
    	 double y = vec3d.y;
    	 double z = vec3d.z;

         Entity entity = victim;

             if (!entity.isImmuneToExplosions())
             {
                 double d12 = entity.getDistance(x, y, z) / (double)explosionPower;

                 if (d12 <= 1.0D)
                 {
                     double d5 = entity.posX - x;
                     double d7 = entity.posY + (double)entity.getEyeHeight() - y;
                     double d9 = entity.posZ - z;
                     double d13 = (double)MathHelper.sqrt(d5 * d5 + d7 * d7 + d9 * d9);

                     if (d13 != 0.0D)
                     {
                         d5 = d5 / d13;
                         d7 = d7 / d13;
                         d9 = d9 / d13;
                         double d14 = (double)world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
                         double d10 = (1.0D - d12) * d14;
                         entity.attackEntityFrom(DamageSource.causeExplosionDamage(explosion), (float)((int)((d10 * d10 + d10) / 2.0D * 7.0D * (double)explosionPower + 1.0D)));
                         double d11 = d10;
                         int level = 0;
                         if (entity instanceof EntityLivingBase)
                         {
                             //d11 = EnchantmentsUtility.getAdvancedKnockBackReduction((EntityLivingBase)entity);
                        	 level = EnchantmentHelper.getMaxEnchantmentLevel(ench, (EntityLivingBase)entity);
                         }

                         entity.motionX = entity.motionX + ( (d5 * d11) - (d5 * d11 * (0.75D + level * 0.05D) ));
                         entity.motionY = entity.motionY + ( (d7 * d11) - (d7 * d11 * (0.75D + level * 0.05D) ));
                         entity.motionZ = entity.motionZ + ( (d9 * d11) - (d9 * d11 * (0.75D + level * 0.05D) ));

                     }
                 }
             }
         
     }
	    
}
	   
	  
	   
				
				
			
			
			
		
	


	



    		
    	
    		
    		


