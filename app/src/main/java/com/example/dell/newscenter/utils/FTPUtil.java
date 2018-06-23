package com.example.dell.newscenter.utils;

import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {
	private static final String TAG = "FTPUtil";
static  public FTPClient ftpClient = null;
static public String FTP_HOST = "140.143.16.51";
static public String FTP_USER_NAME = "my";
static public String FTP_PWD = "wang";
static public int FTP_PORT = 21;
static public int SFTP_PORT = 22;
static public String FTP_VIDEO_PATH = "/public/nginx/video";
static public String FTP_IMAGE_PATH = "/public/nginx/image";
static public String localPath = "G:\\迅雷下载";

static public String localFileName = "a.mp4";
static public String ftpFileName = "a.mp4";
	static public void configFTP(String ftpHost,String ftpUserName,
			String ftpPasswd,int ftpPort ,
			String ftpPath,String localPath ,String fileName ) {
		FTPUtil.FTP_HOST = ftpHost;
		FTPUtil.FTP_USER_NAME = ftpUserName;
		FTPUtil.FTP_PWD = ftpPasswd;
		FTPUtil.FTP_PORT = ftpPort;
	}
	static public FTPClient connFTP(
			String ftpHost,String ftpUserName,
			String ftpPasswd, int ftpPort) {
		 ftpClient =   new FTPClient();
		try {
			ftpClient.connect(ftpHost, ftpPort);
			ftpClient.login(ftpUserName, ftpPasswd);
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				Log.e(TAG, "未连接到FTP服务器" );
                Log.d(TAG, "使用SFTP连接: ");

                ftpClient.connect(ftpHost, ftpPort);
                ftpClient.login(ftpUserName, ftpPasswd);
                if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                    Log.e(TAG, "未连接到FTP服务器" );
                    ftpClient.disconnect();
                }else {
                    Log.d(TAG, "连接成功");
                }
			} else {
				Log.d(TAG, "连接成功");
			} 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ftpClient;
	}
	
	//  ftpPath 要写ftp目录下的相对路径
	static public void downloadFtpFile(final String ftpPath,
                                       final String localPath, final String fileName, final FTPCallBack ftpCallBack) {
	    new Thread(new Runnable() {
            @Override
            public void run() {
                connFTP(FTP_HOST, FTP_USER_NAME, FTP_PWD, FTP_PORT);
                try {
                    ftpClient.setControlEncoding("utf-8");
                    ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                    ftpClient.enterLocalActiveMode();// 主动模式


                    boolean flag = ftpClient.changeWorkingDirectory(ftpPath);
                    if (flag) {
                        Log.d(TAG, "进入文件夹" + ftpPath + " 成功！");
                    } else {
                        Log.d(TAG, "进入文件夹" + ftpPath + " 失败！");
                    }


                    File localFile = new File(localPath+File.separatorChar,fileName);
                    OutputStream os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(fileName, os);//从服务器检索命名文件并将其写入给定的OutputStream中。如果成功完成返回True，否则为false。
                    Log.d(TAG, "下载成功 ");

                    ftpCallBack.callBack();//执行回调函数
                    os.close();
                    ftpClient.logout();
                } catch (FileNotFoundException e) {

                    Log.e(TAG, "未找到相关文件 ");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e(TAG, "文件读取错误");
                    e.printStackTrace();
                }finally {
                    if (ftpClient.isConnected()) {
                        try {
                            ftpClient.disconnect();
                        } catch (IOException ioe) {
                        }
                    }
                }
            }
        }).start();
	}
	static public void uploadFtpFile(final String ftpPath,
                                     final String ftpFileName,
                                     final String localPath, final String loaclFileName, final FTPCallBack ftpCallBack) {
	    new Thread(new Runnable() {

            @Override
            public void run() {
                connFTP(FTP_HOST, FTP_USER_NAME, FTP_PWD, FTP_PORT);
                boolean success = false;
                try {
                    ftpClient.setControlEncoding("utf-8");
                    ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                    ftpClient.enterLocalActiveMode();// 主动模式

                    boolean flag = ftpClient.changeWorkingDirectory(ftpPath);
                    if (flag) {
                        Log.d(TAG, "进入文件夹" + ftpPath + " 成功！");

                    } else {
                        Log.e(TAG, "进入文件夹" + ftpPath + " 失败！" );
                    }
                    //读取文件到流
                    File localFile = new File(localPath+File.separatorChar,loaclFileName);
                    InputStream is = new FileInputStream(localFile);
                    // 上传

                    ftpClient.storeFile(ftpFileName, is); //执行此条语句的时间， 就是上传的过程，比较耗时
//                    Toast.makeText(ApplicationUtil.getContext(),"上传成功",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "上传成功:");

                    ftpCallBack.callBack();// 回调函数
                    is.close();
                    ftpClient.logout();
                } catch (FileNotFoundException e) {
                    Log.e(TAG, "未找到相关文件");
                    Toast.makeText(ApplicationUtil.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e(TAG, "文件读取错误" );
                    Toast.makeText(ApplicationUtil.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage() );
                    e.printStackTrace();
                }finally {
                    if (ftpClient.isConnected()) {
                        try {
                            ftpClient.disconnect();
                        } catch (IOException ioe) {
                        }
                    }
                }
            }
        }).start();

	}
    /** * 删除文件 * 
     * @param pathname FTP服务器保存目录 * 
     * @param filename 要删除的文件名称 * 
     * @return */ 
     static public void deleteFile(final String pathname, final String filename , final FTPCallBack ftpCallBack){

         new Thread(new Runnable() {
             @Override
             public void run() {
                 connFTP(FTP_HOST, FTP_USER_NAME, FTP_PWD, FTP_PORT);
                 try {

                     Log.d(TAG, "切换目录 ");
                     //切换FTP目录
                     ftpClient.changeWorkingDirectory(pathname);
                     Log.d(TAG, "开始删除");
                     ftpClient.dele(filename);
                     ftpClient.logout();
                     System.out.println("删除文件成功");
                     Log.d(TAG, "删除成功");

                     ftpCallBack.callBack();//执行回调函数


                 } catch (Exception e) {
                     Log.e(TAG, "删除文件失败" );
                     e.printStackTrace();
                 } finally {
                     if(ftpClient.isConnected()){
                         try{
                             ftpClient.disconnect();
                         }catch(IOException e){
                             e.printStackTrace();
                         }
                     }
                 }
             }
         }).start();

     }
//	public static void main(String[] args) {
//
//		String localPath = "G:\\迅雷下载";
//		String fileName = "a.mp4";
////		downloadFtpFile(FTP_IMAGE_PATH, localPath, fileName);
////		uploadFtpFile( FTP_IMAGE_PATH, localPath, "Y灭.mp4");
////		deleteFile(FTP_VIDEO_PATH, fileName);
//	}



    public  interface   FTPCallBack {
        public void  callBack();
    }

}

