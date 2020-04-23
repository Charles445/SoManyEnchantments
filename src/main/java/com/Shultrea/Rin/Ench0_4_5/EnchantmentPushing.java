package com.Shultrea.Rin.Ench0_4_5;

import java.util.List;

import com.Shultrea.Rin.Ench0_3_0.EnchantmentStrafe;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentPushing extends Enchantment
{
	public EnchantmentPushing()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Pushing");
		this.setRegistryName("Pushing");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 25 + 25 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return fTest instanceof EnchantmentStrafe ? false : super.canApplyTogether(fTest);  	
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return somanyenchantments.config.Pushing && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.Pushing;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
	public void onEvent(LivingEntityUseItemEvent.Tick event)
	{  	
		EntityLivingBase entity = event.getEntityLiving();
		
		if(entity == null)
			return;
		
		ItemStack bow = event.getItem();
		
		if(bow.isEmpty())
			return;
		
		int level = EnchantmentHelper.getEnchantmentLevel(this, bow);
		
		if (level <= 0)
			return;
		
		if (bow.getItem() instanceof ItemBow && (72000 - event.getDuration() <= 20 + level * 10) )
		{
			AxisAlignedBB axis = new AxisAlignedBB(entity.getPosition()).grow(4 + level * 2);
			repelEntitiesInAABBFromPoint(entity.world, axis, entity.posX, entity.posY, entity.posZ, level);
		}
	}
    
	public void repelEntitiesInAABBFromPoint(World world, AxisAlignedBB effectBounds, double x, double y, double z, int level)
	{
		List<Entity> list = world.getEntitiesWithinAABB(Entity.class, effectBounds);

		for (Entity ent : list)
		{
				if ((ent instanceof EntityLiving) || (ent instanceof IProjectile))
				{
						if (ent instanceof EntityArrow && ((EntityArrow) ent).onGround)
						{
							continue;
						}
						
						Vec3d p = new Vec3d(x, y, z);
						Vec3d t = new Vec3d(ent.posX, ent.posY, ent.posZ);
						double distance = p.distanceTo(t) + 0.1D;
						distance = 10 / distance;

						Vec3d r = new Vec3d(t.x - p.x, t.y - p.y, t.z - p.z);

						ent.motionX += (r.x / ((3.75D - ((level - 1) * 1.5D)) / distance)) / (MathHelper.clamp(50 - level * 10, 1, Integer.MAX_VALUE));
						ent.motionY += (r.y / ((5D - ((level - 1) * 1.25D))   / distance)) / (MathHelper.clamp(50 - level * 10, 1, Integer.MAX_VALUE));
						ent.motionZ += (r.z / ((3.75D - ((level - 1) * 1.5D)) / distance)) / (MathHelper.clamp(50 - level * 10, 1, Integer.MAX_VALUE));

					}		
			}
		}
}
