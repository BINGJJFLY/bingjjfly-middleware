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
			log.getClass().getMethod("log", Marker.class, String.class, int.class, String.class, Object[].class,
					Throwable.class);
			marker = MarkerFactory.getMarker(LogService.MARKER);
		} catch (Exception e) {
			// ignore
		}
	}

	@Override
	public void log(String data) {
		if (marker != null) {
			log((LocationAwareLogger) log, LocationAwareLogger.INFO_INT, data);
		} else {
			log.info(data);
		}
	}

	private void log(LocationAwareLogger log, int level, String data) {
		log.log(marker, FQCN, level, data, null, null);
	}

	@Override
	public boolean isDebugEnabled() {
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		return false;
	}

	@Override
	public void error(String s, Throwable e) {
		if (marker != null) {
			log((LocationAwareLogger) log, LocationAwareLogger.ERROR_INT, s);
		} else {
			log.error(s, e);
		}
	}

	@Override
	public void error(String s) {
		if (marker != null) {
			log((LocationAwareLogger) log, LocationAwareLogger.ERROR_INT, s);
		} else {
			log.error(s);
		}
	}

	@Override
	public void debug(String s) {
		if (marker != null) {
			log((LocationAwareLogger) log, LocationAwareLogger.DEBUG_INT, s);
		} else {
			log.debug(s);
		}
	}

	@Override
	public void trace(String s) {
		if (marker != null) {
			log((LocationAwareLogger) log, LocationAwareLogger.TRACE_INT, s);
		} else {
			log.trace(s);
		}
	}

	@Override
	public void warn(String s) {
		if (marker != null) {
			log((LocationAwareLogger) log, LocationAwareLogger.WARN_INT, s);
		} else {
			log.warn(s);
		}
	}

}
