package com.nlogneg.SOJaC.engine;

import java.nio.ByteBuffer;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.interfaces.EncryptionEngine;
import com.nlogneg.SOJaC.objects.EncryptedResult;

/**
 * This class is meant to act as the DES encryption engine. This engine does NOT do 
 * Triple DES. That's a separate engine
 * @author Andrew
 *
 */
public class DES_Engine implements EncryptionEngine{

	@Override
	public EncryptedResult encrypt(ByteBuffer message, ByteBuffer key,
			BlockModeEnum blockMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer decrypt(EncryptedResult cipherText, ByteBuffer key) {
		// TODO Auto-generated method stub
		return null;
	}



}
