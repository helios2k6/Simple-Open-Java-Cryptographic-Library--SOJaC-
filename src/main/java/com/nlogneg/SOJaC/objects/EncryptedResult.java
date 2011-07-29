package com.nlogneg.SOJaC.objects;

import java.io.Serializable;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.enums.CipherEnum;
import com.nlogneg.SOJaC.enums.MessageDigestEnum;

public class EncryptedResult implements Serializable{

	private static final long serialVersionUID = 6978660336157522342L;
	
	private byte[] cipherText;
	private CipherEnum cipher;
	private BlockModeEnum blockMode;
	private byte[] iv;
	private byte[] salt;
	private MessageDigestEnum keyDigest;
	
	public EncryptedResult(byte[] cipherText, CipherEnum cipher,
			BlockModeEnum blockMode, byte[] iv, byte[] salt, MessageDigestEnum keyDigest) {
		super();
		this.cipherText = cipherText;
		this.cipher = cipher;
		this.blockMode = blockMode;
		this.iv = iv;
		this.salt = salt;
		this.keyDigest = keyDigest;
	}

	public byte[] getCipherText() {
		return cipherText;
	}

	public CipherEnum getCipher() {
		return cipher;
	}

	public BlockModeEnum getBlockMode() {
		return blockMode;
	}

	public byte[] getIv() {
		return iv;
	}

	public byte[] getSalt() {
		return salt;
	}
	
	public MessageDigestEnum getDigest(){
		return keyDigest;
	}
	
}
