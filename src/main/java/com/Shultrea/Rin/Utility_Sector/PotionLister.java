package com.Shultrea.Rin.Utility_Sector;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class PotionLister {

	static final List<Integer> buff_instant_ids = new ArrayList();
	static final List<Integer> debuff_instant_ids = new ArrayList();
	static final List<Integer> buff_ids = new ArrayList();
	static final List<Integer> debuff_ids = new ArrayList();
	
	static final List<Integer> wildcard_debuff_ids = new ArrayList();
	static final List<Integer> wildcard_buff_ids = new ArrayList();
	
	public PotionLister(){

	}
	
	public static void Cycle(){		
		//Config Support in future		
		for(Potion p : Potion.REGISTRY){
		Potion potentialPotion = p;
		//if(potentialPotion == null)
		//	continue;
		
		//0 - buff 1 = insta buff not bad 2 = bad effect not insta  3 = bad eff + insta
		
		int flag = 0;
		
		if(potentialPotion.isBadEffect())
			flag += 2;
		
		if(potentialPotion.isInstant())
			flag += 1;
		
		int id = Potion.getIdFromPotion(potentialPotion);
		
		switch(flag){
			case 0:
				buff_ids.add(id);
				wildcard_buff_ids.add(id);
					break;
			case 1:
				buff_instant_ids.add(id);
				wildcard_buff_ids.add(id);
					break;
			case 2:
				debuff_ids.add(id);
				wildcard_debuff_ids.add(id);
					break;
			case 3:
				debuff_instant_ids.add(id);
				wildcard_debuff_ids.add(id);
					break;
		
		}
		}
	}
	
}
