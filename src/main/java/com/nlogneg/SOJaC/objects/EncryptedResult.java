package com.nlogneg.SOJaC.objects;

import java.io.Serializable;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.enums.CipherEnum;

public class EncryptedResult implements Serializable{

	private static final long serialVersionUID = 6978660336157522342L;
	
	private byte[] iv;
	private CipherEnum cipher;
	private BlockModeEnum blockMode;
	private byte[] cipherText;
	
	public EncryptedResult(byte[] iv, CipherEnum cipher,
			BlockModeEnum blockMode, byte[] cipherText) {
		super();
		this.iv = iv;
		this.cipher = cipher;
		this.blockMode = blockMode;
		this.cipherText = cipherText;
	}

	public byte[] getIv() {
		return iv;
	}

	public CipherEnum getCipher() {
		return cipher;
	}

	public BlockModeEnum getBlockMode() {
		return blockMode;
	}

	public byte[] getCipherText() {
		return cipherText;
	}
	
}
