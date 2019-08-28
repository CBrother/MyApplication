package com.cbrother.toolbox;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 带日志文件输入的，又可控�?关的日志调试
 */
public class LogUtil {
    public  static String DEFAULT_TAG = "test";


    /**
     * 日志文件总开关
     * true 为打开，false 为关闭
     */
    public static Boolean LOG_SWITCH = true;

    /**
     * 日志写入文件开关
     * true 为打开，false 为关闭
     */
    public static Boolean LOG_WRITE_TO_FILE = true;
    /**
     * 日志文件在sdcard中的路径
     */
    private static String LOG_PATH_SDCARD_DIR = "PA-VTM";
    /**
     * sd卡中日志文件的最多保存天数
     */
    private static int SDCARD_LOG_FILE_SAVE_DAYS = 0;

    private static String VANKE_DIR = "Vanke";

    /**
     * 本类输出的日志文件名
     */
    private static String LOGFILEName = "toolboxLog.txt";
    /**
     * 日志的输出格式
     */
    private static SimpleDateFormat LogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");


    public static void w(String tag, Object msg) { // 警告信息
        log(tag, msg.toString(), 'w');
    }

    public static void e(String tag, Object msg) { // 错误信息
        log(tag, msg.toString(), 'e');
    }

    public static void d(String tag, Object msg) {// 调试信息
        log(tag, msg.toString(), 'd');
    }

    public static void i(String tag, Object msg) {//
        log(tag, msg.toString(), 'i');
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), 'v');
    }

    public static void w(String tag, String text) {
        log(tag, text, 'w');
    }

    public static void e(String tag, String text) {
        log(tag, text, 'e');
    }

    public static void d(String tag, String text) {
        log(tag, text, 'd');
    }

    public static void i(String tag, String text) {
        log(tag, text, 'i');
    }

    public static void v(String tag, String text) {
        log(tag, text, 'v');
    }


    public static void e(String text) {
        log(DEFAULT_TAG, text, 'e');
    }
    public static void d(String text) {
        log(DEFAULT_TAG, text, 'd');
    }
    public static void v(String text) {
        log(DEFAULT_TAG, text, 'v');
    }
    public static void i(String text) {
        log(DEFAULT_TAG, text, 'i');
    }



    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     * @return void
     * @since v 1.0
     */
    private static void log(String tag, String msg, char level) {
        if (LOG_SWITCH) {
            if ('e' == level) {
                Log.e(tag, msg);
            } else if ('w' == level) {
                Log.w(tag, msg);
            } else if ('d' == level) {
                Log.d(tag, msg);
            } else if ('i' == level) {
                Log.i(tag, msg);
            } else {
                Log.v(tag, msg);
            }
            if (LOG_WRITE_TO_FILE) {
                writeLogtoFile(String.valueOf(level), tag, msg);
            }
        }
    }

    private static String getLogPath() {
        String path = "";
        // 获取扩展SD卡设备状态?
        String sDStateString = android.os.Environment.getExternalStorageState();
        // 拥有可读可写权限
        if (sDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {
            // 获取扩展存储设备的文件目录
            File SDFile = android.os.Environment.getExternalStorageDirectory();
            path = SDFile.getAbsolutePath() + File.separator
                    + LOG_PATH_SDCARD_DIR + File.separator + VANKE_DIR;
        }
        return path;

    }

    /**
     * 打开日志文件并写入日志
     *
     * @return
     **/
    private static synchronized void writeLogtoFile(String logtype, String tag, String text) {
        Date nowtime = new Date();
        String needWriteFiel = logfile.format(nowtime);
        String needWriteMessage = LogSdf.format(nowtime) + "    " + logtype
                + "    " + tag + "    " + text;
        // 取得日志存放目录
        String path = getLogPath();
        if (path != null && !"".equals(path)) {
            try {
                // 创建目录
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                // 打开文件
                File file = new File(path + File.separator + needWriteFiel
                        + LOGFILEName);
                // 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
                FileWriter filerWriter = new FileWriter(file, true);
                BufferedWriter bufWriter = new BufferedWriter(filerWriter);
                bufWriter.write(needWriteMessage);
                bufWriter.newLine();
                bufWriter.close();
                filerWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除制定的日志文件
     */
    public static synchronized void delFile() {
        // 取得日志存放目录
        String path = getLogPath();
        if (path != null && !"".equals(path)) {
            String needDelFiel = logfile.format(getDateBefore());
            File file = new File(path, needDelFiel + LOGFILEName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件
     */
    private static Date getDateBefore() {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }

}
