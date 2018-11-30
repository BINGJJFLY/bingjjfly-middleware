package com.wjz.logger;

import org.apache.ibatis.logging.Log;

/**
 * 日志记录服务
 * 
 * @author iss002
 *
 */
public interface LogService extends Log {
	
	String MARKER = "LOGSERVICE";

	/**
	 * 日志记录
	 * 
	 * @param data
	 */
	void log(String data);
	
}
