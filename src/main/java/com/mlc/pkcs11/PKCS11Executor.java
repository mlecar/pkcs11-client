package com.mlc.pkcs11;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import iaik.pkcs.pkcs11.Module;
import iaik.pkcs.pkcs11.Slot;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.TokenInfo;

@Component
public class PKCS11Executor {
	
	@Value(value="${criptoki.lib.location}")
	private String criptokiLibLocation;
	
	public void execute() throws TokenException, IOException{
		Properties properties = new Properties();
		properties.put("PKCS11_NATIVE_MODULE", "cryptoki.dll");
		Module module = Module.getInstance(criptokiLibLocation);//IAIKPkcs11.getModule(properties);
		Slot[] slots = module.getSlotList(true);
		TokenInfo[] infos = new TokenInfo[slots.length];
		for (int i = 0; i < slots.length; i++) {
		  infos[i] = slots[i].getToken().getTokenInfo();
		  System.out.println(infos[i]);
		}
		if (slots.length == 0) {
		  System.err.println("No token available!");
		  return;
		}
		Slot selectedSlot = slots[0]; // select one slot
		properties.put("SLOT_ID", Long.toString(selectedSlot.getSlotID()));
/*		IAIKPkcs11 provider = new IAIKPkcs11(properties);
		Security.addProvider(provider);*/
	}

}
