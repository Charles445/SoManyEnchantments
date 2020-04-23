package com.Shultrea.Rin.Prop_Sector;

public interface IArrowProperties {
	
	public void setArrowRapidDamage(boolean flag);
	
	public boolean isArrowRapidDamage();
	
	public void setFlameLevel(int flameLevel);
	
	public int getFlameLevel();
	
	public void setExplosion(float power, boolean canDestroyBlocks);
	
	public void setNoDrag(boolean noDrag);
	
	public void setCanRecover(boolean canRecover);
	
	public float getExplosionPower();
	
	public boolean getCanDestroyBlocks();
	
	public boolean getNoDrag();
	
	public boolean getCanRecover();
	
	public void setDidStarFall(boolean didStarFall);
	
	public boolean didStarFall();
	
	public int setLevel(int level);
	
	public int getLevel();
	
	public boolean getIsStarFallMade();
	
	public void setIsStarFallMade(boolean IsStarFallMade);
	
	public int getArmorPiercingLevel();
	
	public void setPiercingLevel(int level);
	
	public float getPullPower();
	
	public void setPullPower(float level);
	

	

}
