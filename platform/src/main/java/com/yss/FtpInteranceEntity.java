package com.yss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
/*@ConfigurationProperties(prefix = "ftp")*/
/*@PropertySource(value = "classpath:ftp.properties", encoding = "UTF-8")*/
public class FtpInteranceEntity {
	/**
	 * ftp服务器的地址
	 */
	@Value("${ftp.host}")
	private String host;
	/**
	 * ftp服务器的端口号（连接端口号）
	 */
	@Value("${ftp.port}")
	private String port;
	/**
	 * ftp的用户名
	 */
	@Value("${ftp.username}")
	private String username;
	/**
	 * ftp的密码
	 */
	@Value("${ftp.password}")
	private String password;
	/**
	 * ftp上传的根目录
	 */
	@Value("${ftp.basePath}")
	private String basePath;
	/**
	 * 回显地址
	 */
	@Value("${ftp.httpPath}")
	private String httpPath;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getHttpPath() {
		return httpPath;
	}

	public void setHttpPath(String httpPath) {
		this.httpPath = httpPath;
	}
}
