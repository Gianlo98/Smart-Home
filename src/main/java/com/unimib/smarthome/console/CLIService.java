package com.unimib.smarthome.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class CLIService extends Thread {

	private Logger logger = LogManager.getLogger();
	final static Level CLI = Level.getLevel("CLI");
	CLIEvaluation eval = new CLIEvaluation();
	
	@Override
	public void run() {
		logger.info("Starting client interface");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input;
		while (!Thread.interrupted()) {
			try {

				logger.log(CLI, "Enter a command:");
				input = reader.readLine();
				eval.evaluation(input); 

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}
