package de.ng.cloud.core.logger;

import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CloudLoggerFormatter extends Formatter {
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	@Override
	public String format(LogRecord record) {
		String format = "";
	
		if(record.getLevel().intValue() >= Level.WARNING.intValue())
			format += "Warning! ";
		
		format += record.getLevel() + ": ";
		format += DATE_FORMAT.format(record.getMillis()) + " ";
		format += record.getMessage();
		format += "\r\n";
	
	return format;
	}
}