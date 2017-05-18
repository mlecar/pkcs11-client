package com.mlc.pkcs11;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import iaik.pkcs.pkcs11.TokenException;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PKCS11Executor pkcs11Executor;

	public void run(String... args) {
		try {
			pkcs11Executor.execute();
		} catch (TokenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
