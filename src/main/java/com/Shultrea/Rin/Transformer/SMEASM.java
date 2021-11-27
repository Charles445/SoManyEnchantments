package com.Shultrea.Rin.Transformer;

import javax.annotation.Nullable;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import com.Shultrea.Rin.Transformer.helper.ASMHelper;
import com.Shultrea.Rin.Transformer.util.TransformUtil;

import net.minecraft.launchwrapper.IClassTransformer;

public class SMEASM implements IClassTransformer
{

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		/*
		if(transformedName.equals("net.minecraft.entity.Entity"))
		{
			System.out.println("Patching Entity");
			return patchEntity(basicClass);
		}
		
		if(transformedName.equals("net.minecraft.entity.player.EntityPlayer"))
		{
			System.out.println("Patching EntityPlayer");
			return patchPlayer(basicClass);
		}
		
		if(transformedName.equals("bettercombat.mod.util.Helpers"))
		{
			System.out.println("Patching BetterCombat Helpers");
			return patchBetterCombat(basicClass);
		}
		*/
		
		if(transformedName.equals("net.minecraft.enchantment.EnchantmentHelper"))
		{
			System.out.println("Patching EnchantmentHelper");
			return patchEnchantmentHelper(basicClass);
		}
		
		if(transformedName.equals("net.minecraft.enchantment.EnchantmentProtection"))
		{
			System.out.println("Patching EnchantmentProtection");
			return patchEnchantmentProtection(basicClass);
		}
		
		return basicClass;
	}
	
	private byte[] patchEnchantmentHelper(byte[] basicClass)
	{
		ClassNode clazzNode = ASMHelper.readClassFromBytes(basicClass);
		
		for(MethodNode m : clazzNode.methods)
		{
			if(m.name.equals("func_151385_b") || m.name.equals("applyArthropodEnchantments"))
			{
				hookArthropod(m);
			}
			else if(m.name.equals("func_191529_b") || m.name.equals("getFishingLuckBonus"))
			{
				hookAllIntegerReturn(m, "com/Shultrea/Rin/Hook/HookHelper", "modifyFishingLuckBonus", "(ILnet/minecraft/item/ItemStack;)I", aload(0));
			}
			else if(m.name.equals("func_191528_c") || m.name.equals("getFishingSpeedBonus"))
			{
				hookAllIntegerReturn(m, "com/Shultrea/Rin/Hook/HookHelper", "modifyFishingSpeedBonus", "(ILnet/minecraft/item/ItemStack;)I", aload(0));
			}
			else if(m.name.equals("func_185283_h") || m.name.equals("getLootingModifier"))
			{
				hookAllIntegerReturn(m, "com/Shultrea/Rin/Hook/HookHelper", "modifyLootingModifier", "(ILnet/minecraft/entity/EntityLivingBase;)I", aload(0));
			}
		}
		
		return ASMHelper.writeClassToBytes(clazzNode, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
	}
	
	private byte[] patchEnchantmentProtection(byte[] basicClass)
	{
		ClassNode clazzNode = ASMHelper.readClassFromBytes(basicClass);
		
		for(MethodNode m : clazzNode.methods)
		{
			//TODO fire duration, I guess
			
			if(m.name.equals("func_92092_a") || m.name.equals("getBlastDamageReduction"))
			{
				hookAllDoubleReturn(m, "com/Shultrea/Rin/Hook/HookHelper", "modifyBlastDamageReduction", "(DLnet/minecraft/entity/EntityLivingBase;)D", aload(0));
			}
		}
		
		return ASMHelper.writeClassToBytes(clazzNode, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
	}
	
	private void hookAllIntegerReturn(MethodNode m, String owner, String name, String desc, AbstractInsnNode... loads)
	{
		AbstractInsnNode anchor = m.instructions.getFirst();
		while(anchor.getNext() != null)
		{
			if(anchor.getOpcode() == Opcodes.IRETURN)
			{
				InsnList insert = new InsnList();
				for(AbstractInsnNode node : loads)
					insert.add(node);
				insert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc, false));
				m.instructions.insertBefore(anchor, insert);
				
				//(int on stack)
				//IRETURN
				
				//-->
				
				//(int on stack)
				//ALOAD 0 (ItemStack)
				//HookHelper.modifyFishingLuckBonus(intonstack, stack)
				//IRETURN
			}
			anchor = anchor.getNext();
		}
	}
	
	private void hookAllDoubleReturn(MethodNode m, String owner, String name, String desc, AbstractInsnNode... loads)
	{
		AbstractInsnNode anchor = m.instructions.getFirst();
		while(anchor.getNext() != null)
		{
			if(anchor.getOpcode() == Opcodes.DRETURN)
			{
				InsnList insert = new InsnList();
				for(AbstractInsnNode node : loads)
					insert.add(node);
				insert.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc, false));
				m.instructions.insertBefore(anchor, insert);
			}
			anchor = anchor.getNext();
		}
	}
	
	private VarInsnNode aload(int loadvalue)
	{
		return new VarInsnNode(Opcodes.ALOAD, loadvalue);
	}
	
	private void hookArthropod(MethodNode m)
	{
		InsnList insert = new InsnList();
		insert.add(new VarInsnNode(Opcodes.ALOAD, 0)); //entityLivingBaseIn
		insert.add(new VarInsnNode(Opcodes.ALOAD, 1)); //entityIn
		insert.add(new MethodInsnNode(
			Opcodes.INVOKESTATIC, 
			"com/Shultrea/Rin/Hook/HookArthropod",
			"hookArthropod",
			"(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/Entity;)V",
			false
		));
		TransformUtil.insertBeforeFirst(m, insert);
	}

	private byte[] patchBetterCombat(byte[] basicClass)
	{
		ClassNode clazzNode = ASMHelper.readClassFromBytes(basicClass);
		
		for(MethodNode m : clazzNode.methods)
		{
			if(m.name.equals("attackTargetEntityItem"))
			{
				hookBetterCombatArthropod(m);
			}
		}
		
		return ASMHelper.writeClassToBytes(clazzNode, ClassWriter.COMPUTE_MAXS);
	}
	
	private byte[] patchEntity(byte[] basicClass)
	{
		ClassNode clazzNode = ASMHelper.readClassFromBytes(basicClass);
		
		for(MethodNode m : clazzNode.methods)
		{
			if(m.name.equals("func_174815_a") || m.name.equals("applyEnchantments"))
			{
				hookEntityArthropod(m);
			}
		}
		
		return ASMHelper.writeClassToBytes(clazzNode, ClassWriter.COMPUTE_MAXS);
	}

	private byte[] patchPlayer(byte[] basicClass)
	{
		ClassNode clazzNode = ASMHelper.readClassFromBytes(basicClass);
		
		for(MethodNode m : clazzNode.methods)
		{
			if(m.name.equals("func_71059_n") || m.name.equals("attackTargetEntityWithCurrentItem"))
			{
				hookPlayerArthropod(m);
			}
		}
		
		return ASMHelper.writeClassToBytes(clazzNode, ClassWriter.COMPUTE_MAXS);
	}
	
	private void hookEntityArthropod(MethodNode m)
	{
		//Hook to be false only
		
		AbstractInsnNode anchor = getArthropodInvoke(m);
		if(anchor == null)
			return;
		
		InsnList insert = new InsnList();
		insert.add(new VarInsnNode(Opcodes.ALOAD, 1)); //entityLivingBaseIn
		insert.add(new VarInsnNode(Opcodes.ALOAD, 2)); //entityIn
		insert.add(new InsnNode(Opcodes.ICONST_0)); //false
		insert.add(getNewArthropodHook());
		m.instructions.insert(anchor, insert);
	}

	private void hookPlayerArthropod(MethodNode m)
	{
		//Hook to be false only
		
		AbstractInsnNode anchor = getArthropodInvoke(m);
		if(anchor == null)
			return;
		
		InsnList insert = new InsnList();
		insert.add(new VarInsnNode(Opcodes.ALOAD, 0)); //this
		insert.add(new VarInsnNode(Opcodes.ALOAD, 1)); //targetEntity
		insert.add(new InsnNode(Opcodes.ICONST_0)); //false
		insert.add(getNewArthropodHook());
		m.instructions.insert(anchor, insert);
	}

	private void hookBetterCombatArthropod(MethodNode m)
	{
		//Hook to be any
		
		AbstractInsnNode anchor = getArthropodInvoke(m);
		if(anchor == null)
			return;
		
		InsnList insert = new InsnList();
		insert.add(new VarInsnNode(Opcodes.ALOAD, 0)); //this
		insert.add(new VarInsnNode(Opcodes.ALOAD, 1)); //targetEntity
		insert.add(new VarInsnNode(Opcodes.ILOAD, 2)); //offhand
		insert.add(getNewArthropodHook());
		m.instructions.insert(anchor, insert);
	}
	
	@Nullable
	private AbstractInsnNode getArthropodInvoke(MethodNode m)
	{
		return TransformUtil.findNextCallWithOpcodeAndName(m.instructions.getFirst(), Opcodes.INVOKESTATIC, "func_151385_b","applyArthropodEnchantments");
	}
	
	private AbstractInsnNode getNewArthropodHook()
	{
		return new MethodInsnNode(
				Opcodes.INVOKESTATIC, 
				"com/Shultrea/Rin/Hook/HookArthropod",
				"handleArthropod",
				"(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/entity/Entity;Z)V",
				false);
	}

}
