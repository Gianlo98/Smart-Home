package com.unimib.smarthome.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CLIService extends Thread{

	private Logger logger = LogManager.getLogger();
	final static Level CLI = Level.forName("CLI", 350);
	
	@Override
	public void run() {
		super.run();
		
		logger.log(CLI, "Starting cli interface");
        BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!Thread.interrupted()) {
        	try {
        		logger.log(CLI, "Enter the entity identifier:");
        		input = reader.readLine();
        		logger.log(CLI, "Entity selected: " + input);
        	} catch (IOException e) {
				e.printStackTrace();
			}
        }  
	}
	
}
