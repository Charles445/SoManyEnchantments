package com.Shultrea.Rin.Transformer.util;

import javax.annotation.Nullable;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import com.Shultrea.Rin.Transformer.helper.ASMHelper;

/**
 * From RLTweaker
 * 
 * https://github.com/Charles445/RLTweaker
 */
public class TransformUtil
{
	/** Search for a FieldInsnNode after the provided anchor. May specify multiple names for obfuscation purposes */
	@Nullable
	public static FieldInsnNode findNextFieldWithOpcodeAndName(AbstractInsnNode anchor, int opcode, String... names)
	{
		AbstractInsnNode search = ASMHelper.findNextInstructionWithOpcode(anchor, opcode);
		
		while(search!=null)
		{
			if(search.getOpcode()==opcode)
			{
				for(String name : names)
				{
					if(name.equals(((FieldInsnNode)search).name))
					{
						return (FieldInsnNode)search;
					}
				}
			}
			search = ASMHelper.findNextInstructionWithOpcode(search, opcode);
		}
		
		return null;
	}
	
	/** Search for a FieldInsnNode before the provided anchor. May specify multiple names for obfuscation purposes */
	@Nullable
	public static FieldInsnNode findPreviousFieldWithOpcodeAndName(AbstractInsnNode anchor, int opcode, String... names)
	{
		AbstractInsnNode search = ASMHelper.findPreviousInstructionWithOpcode(anchor, opcode);
		
		while(search!=null)
		{
			if(search.getOpcode()==opcode)
			{
				for(String name : names)
				{
					if(name.equals(((FieldInsnNode)search).name))
					{
						return (FieldInsnNode)search;
					}
				}
			}
			search = ASMHelper.findPreviousInstructionWithOpcode(search, opcode);
		}
		
		return null;
	}
	
	/** Search for an IntInsnNode after the provided anchor. */
	@Nullable
	public static IntInsnNode findNextIntInsnNodeWithValue(AbstractInsnNode anchor, int value)
	{
		AbstractInsnNode search = ASMHelper.findNextInstruction(anchor);
		
		while(search!=null)
		{
			if(search.getType()==AbstractInsnNode.INT_INSN)
			{
				if(value==(((IntInsnNode)search).operand))
				{
					return (IntInsnNode) search;
				}
			}
			search = ASMHelper.findNextInstruction(search);
		}
		
		return null;
	}
	
	/** Search for an IntInsnNode before the provided anchor. */
	@Nullable
	public static IntInsnNode findPreviousIntInsnNodeWithValue(AbstractInsnNode anchor, int value)
	{
		AbstractInsnNode search = ASMHelper.findPreviousInstruction(anchor);
		
		while(search!=null)
		{
			if(search.getType()==AbstractInsnNode.INT_INSN)
			{
				if(value==(((IntInsnNode)search).operand))
				{
					return (IntInsnNode) search;
				}
			}
			search = ASMHelper.findPreviousInstruction(search);
		}
		
		return null;
	}

	/** Search for a MethodInsnNode after the provided anchor. May specify multiple names for obfuscation purposes */
	@Nullable
	public static MethodInsnNode findNextCallWithOpcodeAndName(AbstractInsnNode anchor, int opcode, String... names)
	{
		AbstractInsnNode search = ASMHelper.findNextInstructionWithOpcode(anchor, opcode);
			
		while(search!=null)
		{
			if(search.getOpcode()==opcode)
			{
				for(String name : names)
				{
					if(name.equals(((MethodInsnNode)search).name))
					{
						return (MethodInsnNode)search;
					}
				}
			}
			search = ASMHelper.findNextInstructionWithOpcode(search, opcode);
		}
		
		return null;
	}
	
	/** Search for a MethodInsnNode before the provided anchor. May specify multiple names for obfuscation purposes */
	@Nullable
	public static MethodInsnNode findPreviousCallWithOpcodeAndName(AbstractInsnNode anchor, int opcode, String... names)
	{
		AbstractInsnNode search = ASMHelper.findPreviousInstructionWithOpcode(anchor, opcode);
			
		while(search!=null)
		{
			if(search.getOpcode()==opcode)
			{
				for(String name : names)
				{
					if(name.equals(((MethodInsnNode)search).name))
					{
						return (MethodInsnNode)search;
					}
				}
			}
			search = ASMHelper.findPreviousInstructionWithOpcode(search, opcode);
		}
		
		return null;
	}
	
	public static void insertBeforeFirst(MethodNode mNode, InsnList insnList)
	{
		mNode.instructions.insertBefore(ASMHelper.findFirstInstruction(mNode), insnList);
	}
	
	/** Search for the LocalVariableNode with a matching desc*/
	@Nullable
	public static LocalVariableNode findLocalVariableWithDesc(final MethodNode methodNode, final String desc)
	{
		int count = 0;
		LocalVariableNode result = null;
		
		for(LocalVariableNode lvNode : methodNode.localVariables)
		{
			if(lvNode.desc.equals(desc))
			{
				result = lvNode;
				count++;
			}
		}
		
		return result;
	}
	
	/** Search for the LocalVariableNode with a matching name*/
	@Nullable
	public static LocalVariableNode findLocalVariableWithName(final MethodNode methodNode, final String name)
	{
		int count = 0;
		LocalVariableNode result = null;
		
		for(LocalVariableNode lvNode : methodNode.localVariables)
		{
			if(lvNode.name.equals(name))
			{
				result = lvNode;
				count++;
			}
		}
		
		return result;
	}
	
	@Nullable
	/** REQUIRES MAXS : Creates, but does not add, a new local variable node */
	public static LocalVariableNode createNewLocalVariable(final MethodNode methodNode, String name, String desc)
	{
		int index=0;
		for(LocalVariableNode lvn : methodNode.localVariables)
		{
			if(lvn.index >= index)
				index = lvn.index + 1;
		}
		
		AbstractInsnNode anchor = methodNode.instructions.getFirst();
		LabelNode l1 = null;
		LabelNode l2 = null;
		
		//First and last labels of the method
		while(anchor!=null)
		{
			if(anchor instanceof LabelNode)
			{
				if(l1==null)
				{
					l1 = (LabelNode)anchor;
				}
				
				l2 = (LabelNode)anchor;
			}
			
			anchor = anchor.getNext();
		}
		
		if(l1 == null | l2 == null)
		{
			//Failure, didn't manage the labels
			return null;
		}
		
		return new LocalVariableNode(name,desc,null,l1,l2,index);
	}
}
