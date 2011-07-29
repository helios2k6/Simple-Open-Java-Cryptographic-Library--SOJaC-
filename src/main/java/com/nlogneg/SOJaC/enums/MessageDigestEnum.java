package com.nlogneg.SOJaC.enums;

public enum MessageDigestEnum {
	MD5 ("MD5"),
	SHA1 ("SHA-1"),
	SHA256 ("SHA-256"),
	SHA384 ("SHA-384"),
	SHA512 ("SHA-512");
	
	private String name;
	
	private MessageDigestEnum(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	public static MessageDigestEnum getStrongestDigest(int largestKeyLength){
		if(largestKeyLength >= 512){
			return SHA512;
		}else if(largestKeyLength >= 384){
			return SHA384;
		}else if(largestKeyLength >= 256){
			return SHA256;
		}else if(largestKeyLength >= 160){
			return SHA1;
		}else{
			return MD5;
		}
	}
}
