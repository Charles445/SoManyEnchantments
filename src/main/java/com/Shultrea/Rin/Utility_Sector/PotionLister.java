package com.Shultrea.Rin.Utility_Sector;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class PotionLister
{

	static final List<Integer> buff_instant_ids = new ArrayList<>();
	static final List<Integer> debuff_instant_ids = new ArrayList<>();
	static final List<Integer> buff_ids = new ArrayList<>();
	static final List<Integer> debuff_ids = new ArrayList<>();
	
	static final List<Integer> wildcard_debuff_ids = new ArrayList<>();
	static final List<Integer> wildcard_buff_ids = new ArrayList<>();
	
	static final List<String> potionBlacklist = new ArrayList<>();
	
	public PotionLister()
	{

	}
	
	public static void Cycle()
	{	
		//Create the blacklist
		potionBlacklist.clear();
		for(String str : ModConfig.miscellaneous.potionBlacklist)
		{
			potionBlacklist.add(str.toLowerCase(Locale.ENGLISH));
		}
		
		//Config Support in future		
		for(Potion p : Potion.REGISTRY)
		{
			Potion potentialPotion = p;
			//if(potentialPotion == null)
			//	continue;
			
			String potionName = p.getRegistryName().toString().toLowerCase(Locale.ENGLISH);
			boolean listMatch = potionBlacklist.contains(potionName);
			
			//If there is a match and using a whitelist
			//or if there is no match and using a blacklist

			if(listMatch == ModConfig.miscellaneous.potionBlacklistAsWhitelist)
			{
			
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
				
				SMElogM.logger.debug("PotionLister accepting potion : "+potionName);
			}
			else
			{
				//Potion was skipped
				SMElogM.logger.debug("PotionLister skipping potion : "+potionName);
			}
		}
		
		SMElogM.logger.debug("PotionLister Instant Debuffs: "+debuff_instant_ids.size());
		SMElogM.logger.debug("PotionLister Debuffs: "+debuff_ids.size());
		
		
	}	
}
