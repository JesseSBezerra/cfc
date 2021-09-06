package br.com.jdsb.cfc;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class CfcApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		Application.launch(Main.class, args);
	}


}
