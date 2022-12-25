package de.ng.cloud.core.logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CloudLogger {
	
	private static Logger logger;
	
	public static void initializing() {
		System.out.println("Starting logger...");
		
		LogManager.getLogManager().reset();
		
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
		try {
			if(!new File("./Cloud/logs").exists())
				new File("./Cloud/logs").mkdirs();
			
			String fileName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			
			int count = 1;
			while(new File(String.format("./Cloud/logs/%s-%d.log", fileName, count)).exists())
				count++;
			
			Handler fileHandler = new FileHandler("./Cloud/logs/" + fileName + "-" + count + ".log");
			fileHandler.setFormatter(new CloudLoggerFormatter());
			
			logger.addHandler(fileHandler);
			
			fileHandler.setLevel(Level.ALL);
			logger.setLevel(Level.ALL);
			
			logger.config("Configuration done.");
			
			System.out.println("Logger started.");
		} catch (SecurityException | IOException ex) {
			ex.printStackTrace();
			logger.log(Level.SEVERE, "Error occur in CloudLogger", ex);
			System.out.println("Failed to start logger.");
			System.exit(0);
		}
	}
}