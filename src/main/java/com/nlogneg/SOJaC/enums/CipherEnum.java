package com.nlogneg.SOJaC.enums;

public enum CipherEnum {
	AES ("AES"),
	BLOWFISH ("Blowfish"),
	DES ("DES"),
	RSA ("RSA"),
	TRIPLE_DES ("DESede");
	
	private String name;
	
	private CipherEnum(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	public static CipherEnum getDefault(){
		return AES;
	}
}
