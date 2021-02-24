package com.example.demo.fileServer;



import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FtpServerTest {
    public static void main(String[] args) throws IOException, SftpException {
        // FTPClient ftpClient = FtpUtils.getFTPClient("172.22.6.53",
        //                                             "ftpuser", "transwarpnj", 21);
        FtpUtils.FtpConfig ftpConfig = new FtpUtils.FtpConfig();
        FTPUtil ftpUtil = new FTPUtil();
        ftpConfig.setIp("172.22.6.53");
        ftpConfig.setUserName("ftpuser");
        ftpConfig.setPassword("transwarpnj");
        ftpConfig.setPort(21);
       // FtpUtils.download(ftpConfig,"/home/htdocs/","1.dat","D:\\IdeaProject\\file","1.dat");
        try {
            System.out.println(FtpUtils.isExistFile(ftpConfig,"/home/htdocs/file"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ftpUtil.disConnect();
        // 获取所有的文件名
        // List<String> names = FtpUtils.listAllFile(ftpConfig, "/home/htdocs/file");
        // InputStream inputStream = FtpUtils.readFile(ftpConfig,"/home/htdocs/file/test2-202011021345367.xlsx");
        // SFTPUtil sftpUtil = new SFTPUtil("sftpusers",
        //                                  "transwarpnj", "172.22.6.53", 22);
        // sftpUtil.login();
        // List ret = new ArrayList();
        // Vector files = sftpUtil.listFiles("/");
        //
        // InputStream inputStream = sftpUtil.readFile("test-202011021245367.xlsx");
        // for (int i = 0; i < files.size(); i++)
        // {
        //     Object obj = files.elementAt(i);
        //     if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry)
        //     {
        //         ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
        //         if (true && !entry.getAttrs().isDir())
        //         {
        //             ret.add(entry.getFilename());
        //         }
        //         if (true && entry.getAttrs().isDir())
        //         {
        //             if (!entry.getFilename().equals(".") && !entry.getFilename().equals(".."))
        //             {
        //                 ret.add(entry.getFilename());
        //             }
        //         }
        //     }
        // }
        // System.out.println(ret);
        // sftpUtil.logout();
    }
    @Test
    public void test(){
        String path = "/home/htdocs/file/test2-202011021245368.xlsx".trim();
        String fileName = path.substring(path.lastIndexOf("/")+1);
        System.out.println(fileName);
        System.out.println(fileName.length());
        path = path.substring(0,path.length()-fileName.length());
        System.out.println(path);
        String date = "2020-11-02 16:48:00";
        String[] str = date.trim().split("-");

        String time = str[0]+str[1]+ str[2].substring(0,2);
        System.out.println(time);
        try{
            int f=1/0;

        }catch (Exception e){
            System.out.println("cache");
            throw e;
        }finally {
            System.out.println("finally");

        }
    }

    @Test
    public void ftpTest() throws IOException {
        FTPConfig ftpConfig = new FTPConfig();
        ftpConfig.setIp("172.22.6.53");
        ftpConfig.setUserName("ftpuser");
        ftpConfig.setPassword("transwarpnj");
        ftpConfig.setPort(21);
        ftpConfig.setTransPattern("Passive");
        FTPUtil ftpUtil = new FTPUtil();

        List<String> list = new ArrayList<>();
        list.add("/home/htdocs/file");
        list.add("/home/htdocs/data");
        list.add("/home/htdocs/data/test");
        String path = "/home/htdocs/data";
        String checkOne = ftpUtil.checkIpOrPort(ftpConfig)? "连接成功":"连接失败，ip或port有误";
        String checkTwo = ftpUtil.checkUserOrPwd(ftpConfig)? "登录成功":"登录失败，用户或密码有误";


        String checkThree = ftpUtil.checkIsExistFilePath(list)? "验证路径成功":"登录失败，用户或密码有误";
        // FtpUtils.download(ftpConfig,"/home/htdocs/","1.dat","D:\\IdeaProject\\file","1.dat");
        // 1.测试连接:url、端口、用户名、密码
        System.out.println(checkOne);
        System.out.println(checkTwo);
        // 2.测试是否文件夹存在，

        //3.测试读写权限,打印无权限的路径
        String checkReadAuth = ftpUtil.checkReadAuth(path)?"有读权限":"没有读权限";

        String checkWrite = ftpUtil.checkWriteAuth(path)?"有写权限":"没有写权限";
        System.out.println(checkReadAuth);
        System.out.println(checkWrite);


    }

    @Test
    public void test2() throws IOException {
        FTPConfig ftpConfig = new FTPConfig();
        ftpConfig.setIp("172.22.6.53");
        ftpConfig.setUserName("ftpuser");
        ftpConfig.setPassword("transwarpnj");
        ftpConfig.setPort(21);
        String path = "*登陆信息{yyyy-MM-dd}*记录.xlsx";
        String tty = path.replaceAll("\\*",".*");
        System.out.println(tty);
        String[] strs = path.split("\\*");
        String s = ".*登陆信息2021-11-02.*记录.xlsx";
        Pattern p = Pattern.compile(s);
        Matcher matcher = p.matcher("ffr登陆信息2021-11-02fgfdg记录.xlsx");
        System.out.println(matcher.matches());
        String filePath = "/home/htdocs/file/test2-{yyyy-MM-dd}1345367.xlsx";
        filePath = filePath.replaceAll("\\{yyyy-MM-dd}","2020-11-02");
        System.out.println(filePath);
    }

    @Test
    public void fileSize() throws IOException {
        FtpUtils.FtpConfig ftpConfig = new FtpUtils.FtpConfig();
        FTPUtil ftpUtil = new FTPUtil();
        ftpConfig.setIp("172.22.6.53");
        ftpConfig.setUserName("ftpuser");
        ftpConfig.setPassword("transwarpnj");
        ftpConfig.setPort(21);
        // FtpUtils.download(ftpConfig,"/home/htdocs/","1.dat","D:\\IdeaProject\\file","1.dat");
        try {
            FtpUtils.fileSize(ftpConfig,"/home/htdocs/file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ftpUtil.disConnect();
    }
}
