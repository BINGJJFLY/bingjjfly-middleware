package com.wjz.logger;

/**
 * 日志记录服务
 * 
 * @author iss002
 *
 */
public interface LogService {
	
	String MARKER = "LOGSERVICE";

	/**
	 * 日志记录
	 * 
	 * @param data
	 */
	void log(String data);
	
}
