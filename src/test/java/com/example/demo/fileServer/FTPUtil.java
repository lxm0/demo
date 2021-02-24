package com.example.demo.fileServer;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @wsh
 * 实现连接FTP服务器，实现文件的上传和下载
 */
public class FTPUtil {

    private FTPClient ftpClient;

    /**
     * 创建目录
     *
     * @param ftpConfig  配置
     * @param remotePath 需要创建目录的目录
     * @param makePath   需要创建的目录
     * @return 是否创建成功
     */
    public  boolean makeDirectory(FTPConfig ftpConfig, String remotePath, String makePath) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(remotePath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            boolean result = ftpClient.makeDirectory(makePath);
            // 退出FTP
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移动文件
     *
     * @param ftpConfig 配置
     * @param fromPath  待移动目录
     * @param fromName  待移动文件名
     * @param toPath    移动后目录
     * @param toName    移动后文件名
     * @return 是否移动成功
     */
    public  boolean moveFile(FTPConfig ftpConfig, String fromPath, String fromName, String toPath, String toName) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(fromPath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            boolean result = ftpClient.rename(fromName, toPath + File.separator + toName);
            // 退出FTP
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除文件
     *
     * @param ftpConfig  配置
     * @param remotePath 远程目录
     * @param fileName   文件名
     * @return 是否删除成功
     */
    public  boolean deleteFile(FTPConfig ftpConfig, String remotePath, String fileName) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(remotePath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            boolean result = ftpClient.deleteFile(fileName);
            // 退出FTP
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 下载文件
     *
     * @param ftpConfig  配置
     * @param remotePath 远程目录
     * @param fileName   文件名
     * @param localPath  本地目录
     * @param localName  本地文件名
     * @return 是否下载成功
     */
    public  boolean download(FTPConfig ftpConfig, String remotePath, String fileName, String localPath, String localName) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(remotePath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            File file = new File(localPath, localName);
            if (!file.getParentFile().exists()) {
                boolean mkdirsResult = file.getParentFile().mkdirs();
                if (!mkdirsResult) {
                    throw new RuntimeException("创建目录失败");
                }
            }
            if (!file.exists()) {
                boolean createFileResult = file.createNewFile();
                if (!createFileResult) {
                    throw new RuntimeException("创建文件失败");
                }
            }
            OutputStream outputStream = new FileOutputStream(file);
            ftpClient.enterLocalPassiveMode();// 被动模式
            boolean result = ftpClient.retrieveFile(fileName, outputStream);
            outputStream.flush();
            outputStream.close();
            // 退出FTP
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 上传文件
     *
     * @param ftpConfig   配置
     * @param remotePath  远程目录
     * @param inputStream 待上传文件输入流
     * @param fileName    文件名
     * @return 是否上传成功
     */
    public  boolean upload(FTPConfig ftpConfig, String remotePath, InputStream inputStream, String fileName) {
        try {
            FTPClient ftpClient = connectClient(ftpConfig);
            boolean changeResult = ftpClient.changeWorkingDirectory(remotePath);
            if (!changeResult) {
                throw new RuntimeException("切换目录失败");
            }
            // 设置被动模式
            ftpClient.enterLocalPassiveMode();
            // 设置流上传方式
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            // 设置二进制上传
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //中文存在问题
            // 上传 fileName为上传后的文件名
            boolean result = ftpClient.storeFile(fileName, inputStream);
            // 关闭本地文件流
            inputStream.close();
            // 退出FTP
            ftpClient.logout();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查ip和port
     * @param ftpConfig
     * @return
     */
    public boolean checkIpOrPort(FTPConfig ftpConfig){
        boolean result = true;
        if(null ==this.ftpClient){
            this.ftpClient = new FTPClient();
        }
        // 连接FTP服务器
        try {
            this.ftpClient.connect(ftpConfig.getIp(), ftpConfig.getPort());
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    /**
     * 检查用户名和密码
     * @param ftpConfig
     * @return
     */
    public boolean checkUserOrPwd(FTPConfig ftpConfig){
        boolean result = true;
        // 连接FTP服务器
        try {
            ftpClient.login(ftpConfig.getUserName(), ftpConfig.getPassword());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                result = false;
            }
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    /**
     * 检查用户名和密码
     * @return
     */
    public boolean checkIsExistFilePath(List<String> paths){
        boolean result = true;
        List<String> ontExistPaths = new ArrayList<>();
        // 连接FTP服务器
        try {
            for (String path: paths) {
                if (!this.ftpClient.changeWorkingDirectory(path)){
                    ontExistPaths.add(path);
                }
            }
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    public boolean checkReadAuth(String paths){
        boolean result = true;
        this.ftpClient.enterLocalPassiveMode();
        // 连接FTP服务器
        try {
            return this.ftpClient.changeWorkingDirectory(paths);
        } catch (IOException e) {
            result= false;
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkWriteAuth(String paths){
        boolean result = true;
        this.ftpClient.enterLocalPassiveMode();
        // 连接FTP服务器
        try {
            return this.ftpClient.makeDirectory(paths+"/test2");
        } catch (IOException e) {
            result= false;
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 检查用户名和密码
     * @return
     */
    public boolean checkReadAuth2(FTPClient ftpClient,List<String> paths){
        boolean result = true;
        // 连接FTP服务器
        try {
            for (String path : paths) {
                System.out.println(ftpClient.changeWorkingDirectory(path));
                FTPFile[] ftpFile = ftpClient.listFiles();
                for (FTPFile file: ftpFile) {
                    System.out.println(file.getName());
                }
            }
        } catch (IOException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 登录ftp
     *
     * @param ftpConfig 配置
     * @return 是否登录成功
     * @throws IOException
     */
    public  FTPClient connectClient(FTPConfig ftpConfig) throws IOException {
        if (null != this.ftpClient) {
            return ftpClient;
        }
        FTPClient ftpClient = new FTPClient();
        // 连接FTP服务器
        try {
            ftpClient.connect(ftpConfig.getIp(), ftpConfig.getPort());
        } catch (IOException e) {
            throw new RuntimeException("连接ftp失败,ip地址或端口有误");
        }
        // 登录FTP
        try {
            ftpClient.login(ftpConfig.getUserName(), ftpConfig.getPassword());
            // if (null != ftpConfig.getTransPattern() && ftpConfig.getTransPattern().equals("Active")) {
            //     ftpClient.enterLocalActiveMode();
            // } else {
            //     ftpClient.enterLocalPassiveMode();
            // }
            // 正常返回230登陆成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new RuntimeException("连接ftp失败,用户名或密码有误");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        this.ftpClient = ftpClient;
        ftpClient.enterLocalActiveMode();
        ftpClient.deleteFile("b0bce468-1874-40ef-b39f-ec2fa502938b");
        return this.ftpClient;
    }
    /**
     * 退出ftp
     */
    public void disConnect() throws IOException {
        if (null == this.ftpClient) {
            return ;
        }
        ftpClient.disconnect();
        return;
    }
    /**
     * 文件是否存在
     * @param path
     * @return
     * @throws IOException
     */
    public  Boolean isExistFile(String path) throws IOException {
        Boolean result = ftpClient.changeWorkingDirectory(path);
        ftpClient.disconnect();
        return result;
    }

    public  List<String> listAllFile(String path) throws IOException {
        List<String> names = new ArrayList<>();
        ftpClient.changeWorkingDirectory(path);
        FTPFile[] ftpFile = ftpClient.listFiles();
        for (FTPFile file: ftpFile) {
            names.add(file.getName());
        }
        return names;
    }

    public InputStream readFile(String path) throws IOException {
        ftpClient.changeWorkingDirectory(path);
        InputStream inputStream = ftpClient.retrieveFileStream(path);
        return inputStream;
    }

    public void completePendingCommand() throws IOException {
        this.ftpClient.completePendingCommand();
    }
}

