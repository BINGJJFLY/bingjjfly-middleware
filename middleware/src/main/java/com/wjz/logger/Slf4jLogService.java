package com.wjz.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.spi.LocationAwareLogger;

public class Slf4jLogService implements LogService {
	
	private static final String FQCN = Slf4jLogService.class.getName();
	private static final Logger log = LoggerFactory.getLogger(FQCN);
	private Marker marker = null;
	
	public Slf4jLogService() {
		try {
			log.getClass().getMethod("log", Marker.class, String.class, int.class, String.class, Object[].class, Throwable.class);
			marker = MarkerFactory.getMarker(LogService.MARKER);
		} catch (Exception e) {
			// ignore
		}
	}
	
	@Override
	public void log(String data) {
		if (marker != null) {
			((LocationAwareLogger) log).log(marker, FQCN, LocationAwareLogger.INFO_INT, data, null, null);
		} else {
			log.info(data);
		}
	}

}
