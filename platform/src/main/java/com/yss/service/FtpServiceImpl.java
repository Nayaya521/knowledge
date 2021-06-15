package com.yss.service;

import com.alibaba.druid.util.StringUtils;
import com.yss.FtpInteranceEntity;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FtpServiceImpl implements FtpService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	FtpInteranceEntity ftpInteranceEntity;

	@Override
	public Boolean uploadFile(InputStream inputStream, String fileName, String filePath) {
		System.out.println("调用文件上传接口");
		// 定义保存结果
		boolean iaOk = false;
		// 初始化连接
		FTPClient ftp = connectFtpServer();
		if (ftp != null) {
			try {
				// 设置文件传输模式为二进制，可以保证传输的内容不会被改变
				System.out.println("ftp不为空");
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.enterLocalPassiveMode();	//注：上传文件都为0字节，设置为被动模式即可
				// 跳转到指定路径，逐级跳转，不存在的话就创建再进入
				toPathOrCreateDir(ftp, filePath);
				// 上传文件 参数：上传后的文件名，输入流,,返回Boolean类型，上传成功返回true
				iaOk = ftp.storeFile(fileName, inputStream);
				System.out.println("iaok"+iaOk);
				// 关闭输入流
				inputStream.close();
				// 退出ftp
				ftp.logout();
			} catch (IOException  e) {
				logger.error(e.toString());
			} finally {
				if (ftp.isConnected()) {
					try {
						// 断开ftp的连接
						ftp.disconnect();
					} catch (IOException ioe) {
						logger.error(ioe.toString());
					}
				}
			}
		}
		return iaOk;
	}


	@Override
	public void downloadFileTo(String ftpFilePath, OutputStream out) {
		FTPClient ftp = connectFtpServer();
		try{
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.retrieveFile(ftpFilePath, out);
			ftp.logout();
		} catch (Exception e) {
			logger.error("FTP文件下载失败！" + e.toString());
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					logger.error(ioe.toString());
				}
			}
		}
	}


	@Override
	public Boolean deleteFile(String ftpFilePath) {
		FTPClient ftp = connectFtpServer();
		boolean resu = false;
		try{
//			ftp.changeWorkingDirectory("文件路径");
//			ftp.dele("文件名称，如果写全路径就不需要切换目录了。");
//			deleteFile内同样实现了dele，只不多是对返回结果做了处理，相对方便一点
			resu = ftp.deleteFile(ftpFilePath);
			ftp.logout();
			return resu;
		} catch (Exception e) {
			logger.error("FTP文件删除失败！" + e.toString());
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					logger.error(ioe.toString());
				}
			}
		}
		return resu;
	}
	
	private FTPClient connectFtpServer() {
		System.out.println("进入连接");
		// 创建FTPClient对象（对于连接ftp服务器，以及上传和上传都必须要用到一个对象）
		FTPClient ftpClient = new FTPClient();
		// 设置连接超时时间
		ftpClient.setConnectTimeout(1000 * 30);
		// 设置ftp字符集
		ftpClient.setControlEncoding("utf-8");
		// 设置被动模式，文件传输端口设置,否则文件上传不成功，也不报错
		ftpClient.enterLocalPassiveMode();
		try {
			System.out.println(ftpInteranceEntity.getHost()+" "+ftpInteranceEntity.getUsername()+","+ftpInteranceEntity.getPassword());
			// 定义返回的状态码
			int replyCode;
			// 连接ftp(当前项目所部署的服务器和ftp服务器之间可以相互通讯，表示连接成功)
			ftpClient.connect("192.168.101.54");
			System.out.println("connect::"+ftpClient.isConnected());
			// 输入账号和密码进行登录
			ftpClient.login(ftpInteranceEntity.getUsername(), ftpInteranceEntity.getPassword());
			// 接受状态码(如果成功，返回230，如果失败返回503)
			replyCode = ftpClient.getReplyCode();
			// 根据状态码检测ftp的连接，调用isPositiveCompletion(reply)-->如果连接成功返回true，否则返回false
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				logger.info("connect ftp {} failed", ftpInteranceEntity.getHost());
				// 说明连接失败，需要断开连接
				ftpClient.disconnect();
				return null;
			}
			logger.info("replyCode:" + replyCode);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("状态码"+ftpClient.getReplyCode());
			logger.error("connect fail:" + e.toString());
			return null;
		}
		return ftpClient;
	}

	private void toPathOrCreateDir(FTPClient ftp, String filePath) throws IOException {
		String[] dirs = filePath.split("/");
		for (String dir : dirs) {
			if (StringUtils.isEmpty(dir)) {
				continue;
			}
			
			if (!ftp.changeWorkingDirectory(dir)) {
				ftp.makeDirectory(dir);
				ftp.changeWorkingDirectory(dir);
			}
		}
	}

}
