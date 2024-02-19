package com.controleestorno.estornos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Vinícius Pelizzari
 * @version 1.0
 */
@SpringBootApplication
public class ControleEstornoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleEstornoApplication.class, args);
		System.out.println("EM EXECUÇÃO - OK!");
	}
}
