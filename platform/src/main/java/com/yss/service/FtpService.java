package com.yss.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface FtpService {
	
	/**
	 * 上传文件到ftp
	 * @param inputStream 输入流
	 * @param fileName 新的文件名，包含拓展名
	 * @param filePath 保存路径
	 * @return
	 */
	public Boolean uploadFile(InputStream inputStream, String fileName, String filePath);

	/**
	 * 下载ftp文件，直接转到输出流
	 * @param ftpFilePath
	 * @param out
	 */
	public void downloadFileTo(String ftpFilePath, OutputStream out);
	
	/**
	 * 删除ftp文件
	 * @param ftpFilePath ftp下文件路径，根目录开始
	 * @return
	 */
	public Boolean deleteFile(String ftpFilePath);
}