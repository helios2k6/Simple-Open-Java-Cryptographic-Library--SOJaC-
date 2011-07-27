package com.nlogneg.SOJaC.interfaces;

import java.nio.ByteBuffer;

import com.nlogneg.SOJaC.enums.BlockModeEnum;

public interface EncryptionEngine {
	
	public static final String DEFAULT_PADDING = "PKCS5Padding";
	public static final String DEFAULT_RANDOM = "SHA1PRNG";
	
	public ByteBuffer encrypt(ByteBuffer message, ByteBuffer key, BlockModeEnum blockMode);
	public ByteBuffer decrypt(ByteBuffer message, ByteBuffer key, BlockModeEnum blockMode);
}
