package com.nlogneg.SOJaC.enums;

public enum BlockModeEnum {
	CBC ("CBC"), //IV
	CFB ("CFB"), //IV
	ECB ("ECB"), //NO IV
	OFB ("OFB"), //IV
	PCBC ("PCBC"); //IV
	
	private String name;
	
	private BlockModeEnum(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	public static BlockModeEnum getDefault(){
		return CBC;
	}
	
	public static boolean requiresIV(BlockModeEnum mode){
		if(mode.equals(ECB)){
			return false;
		}
		return true;
	}
}
