package com.nlogneg.SOJaC.enums;

public enum BlockModeEnum {
	CBC ("CBC"),
	CFB ("CFB"),
	ECB ("ECB"),
	OFB ("OFB"),
	PCBC ("PCBC");
	
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
}
