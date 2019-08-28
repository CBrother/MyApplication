package com.cbrother.toolbox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class FormatUtils {

    /**
     * 字符串转化成bitmap对象
     *
     * @param string
     * @return
     */
    public static Bitmap strToBitmap(String string) {
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            Log.d("test1", bitmap.getWidth() + "  " + bitmap.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test1", e.toString());
        }
        return bitmap;
    }


    /**
     * bitmap base64
     * @param bitmap
     * @return
     */
    public static String bitmap2Base64(Bitmap bitmap) {

        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }


    /**
     * bitmap 转byte[]
     * @param bitmap
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
        return buffer.array();
    }
}
