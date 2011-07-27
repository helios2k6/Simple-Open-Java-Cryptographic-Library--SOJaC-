package com.nlogneg.SOJaC.engine;

import java.nio.ByteBuffer;

import com.nlogneg.SOJaC.enums.BlockModeEnum;
import com.nlogneg.SOJaC.interfaces.EncryptionEngine;

/**
 * This class is meant to act as the DES encryption engine. This engine does NOT do 
 * Triple DES. That's a separate engine
 * @author Andrew
 *
 */
public class DES_Engine implements EncryptionEngine{

	@Override
	public ByteBuffer encrypt(ByteBuffer message, ByteBuffer key,
			BlockModeEnum blockMode) {
		return null;
	}

	@Override
	public ByteBuffer decrypt(ByteBuffer message, ByteBuffer key,
			BlockModeEnum blockMode) {
		return null;
	}

}
