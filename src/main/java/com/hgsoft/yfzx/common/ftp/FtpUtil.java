package com.hgsoft.yfzx.common.ftp;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * 功能描述：ftp服务器相关管理，ftp服务器的连接，关闭，下载文件等；
 */
public class FtpUtil {

    private static Log logger = LogFactory.getLog(FtpUtil.class);

    private String ip;
    private String username;
    private String password;
    private int port = -1;
    FTPClient ftpClient = null;

    /**
     * 功能描述：根据ip,username,password,port构造一个实例
     *
     * @param ip
     * @param username
     * @param password
     * @param port
     */
    public FtpUtil(String ip, String username, String password, int port) {
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     * 功能描述：连接FTP服务器
     */
    public boolean connectServer() {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            ftpClient.login(username, password);
            logger.info("ftp连接成功");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 功能描述：断开与ftp服务器连接
     */
    public boolean closeServer() {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
                logger.info("ftp连接已关闭");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 功能描述：获取某一个路径下的文件。
     *
     * @param path
     * @return
     */
    public FTPFile[] getFileList(String path) {
        FTPFile[] files = null;
        try {
            ftpClient.changeWorkingDirectory(path);
            files = ftpClient.listFiles();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return files;
    }

    /**
     * 功能描述：下载一个文件。
     *
     * @param os
     * @param fileName
     */
    public void downLoadFile(OutputStream os, String fileName) {
        try {
            ftpClient.retrieveFile(fileName, os);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) {
        FtpUtil ftp = new FtpUtil("192.168.1.130", "Centerftp", "Centerftp", 6009);
        ftp.connectServer();
        FTPFile[] list = ftp.getFileList("/CSCB/UploadFiles");
        ftp.closeServer();
        if (list != null && list.length > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (FTPFile file : list) {
                System.out.println(file.getName() + "\t" + sdf.format(file.getTimestamp().getTime()));
            }
        }
    }
}
