package com.Shultrea.Rin.Prop_Sector;

public class ArrowProperties implements IArrowProperties{
	
	private float ExplosionPower;
	private boolean CanDestroyBLocks;
	private boolean NoDrag;
	private boolean CanRecover;
    private boolean didStarFall;
	private int     level;
    private boolean isStarFallMade;
    private int     flameLevel;
    private boolean pierceInvuln;
	private int piercingLevel;
	private float pullPower;
	
	@Override
	public boolean getIsStarFallMade(){
		return isStarFallMade;
	}
	
	@Override
	public void setIsStarFallMade(boolean isStarFall){
		this.isStarFallMade = isStarFall;
	}
	
	
	@Override
	public int getLevel(){
		return this.level;
	}
	
	@Override
	public int setLevel(int level){
		return this.level = level;
	}
	
    @Override
    public void setDidStarFall(boolean didStarFall){
    	this.didStarFall = didStarFall;
    }
    
    @Override
    public boolean didStarFall(){
    	return this.didStarFall; 
    }
    
	@Override
	public void setExplosion(float power, boolean canDestroyBlocks) {
		this.ExplosionPower = power;
		this.CanDestroyBLocks = canDestroyBlocks;
		
	}

	@Override
	public void setNoDrag(boolean noDrag) {
		this.NoDrag = noDrag;
		
	}

	@Override
	public float getExplosionPower() {
		return this.ExplosionPower;
	}

	@Override
	public boolean getCanDestroyBlocks() {
		return this.CanDestroyBLocks;
	}

	@Override
	public boolean getNoDrag() {
		return this.NoDrag;
	}

	@Override
	public void setCanRecover(boolean canRecover) {
		this.CanRecover = canRecover;
	}

	@Override
	public boolean getCanRecover() {
		return this.CanRecover;
		
	}

	@Override
	public void setFlameLevel(int flameLevel) {
		this.flameLevel = flameLevel;
	
	}

	@Override
	public int getFlameLevel() {
		return this.flameLevel;
	}

	@Override
	public void setArrowRapidDamage(boolean flag) {
		this.pierceInvuln = flag;
	}

	@Override
	public boolean isArrowRapidDamage() {	
		return this.pierceInvuln;
	}

	@Override
	public int getArmorPiercingLevel() {
		return this.piercingLevel;
	}

	@Override
	public void setPiercingLevel(int level) {
		this.piercingLevel = level;
		
	}

	@Override
	public float getPullPower() {
		return this.pullPower;
		
	}

	@Override
	public void setPullPower(float p) {
		this.pullPower = p;
		
	}

}
