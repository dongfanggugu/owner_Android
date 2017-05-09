package com.honyum.owner.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;

import com.baidu.mapapi.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static int getInt(String string) {
        int result = 0;

        try {
            result = Integer.parseInt(string);
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    public static double getDouble(String string) {
        double result = 0;

        try {
            result = Double.parseDouble(string);
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    public static boolean isMobileNumber(String mobile) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }


    public static boolean isEmpty(String string) {
        if (string == null || string.length() == 0)
            return true;
        else
            return false;
    }


    /**
     * 旋转图片，使图片保持正确的方向。
     *
     * @param bitmap  原始图片
     * @param degrees 原始图片的角度
     * @return Bitmap 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, float degrees) {
        if (degrees == 0 || null == bitmap) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (null != bitmap) {
            bitmap.recycle();
        }
        return bmp;
    }

    public static Bitmap turnBitmap(Bitmap bitmap) {

        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        if (null == bitmap) {
            bitmap.recycle();
        }

        return bmp;
    }


    /**
     * MD5加密
     *
     * @param string
     * @return
     */
    public static String encryptMD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 获取sd卡路径
     *
     * @return sd卡路径
     */
    public static String getSdPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            return Environment.getExternalStorageDirectory().getPath();
        } else {
            return null;
        }
    }


    /**
     * @return 存储路径
     */
    public static String getUserAvatarPath() {
        if (isEmpty(getSdPath())) {
            return "";
        }

        String dirPath = getSdPath() + "/owner/UserAvatar";

        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            boolean isMkdirs = dirFile.mkdirs();
            if (!isMkdirs) {
                return "";
            }
        }

        return dirPath;
    }


    /**
     * @return 存储路径
     */
    public static boolean saveImgFile(File file, Bitmap bitmap) {

        if (file == null || bitmap == null) {
            return false;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 根据uri获取文件的路径
     */
    public static String getAbsolutePathFromUri(Context context, Uri uri) {

        Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);

        if (cursor == null) {
            return "";
        }

        cursor.moveToFirst();

        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

        cursor.close();

        return path;
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取bitmap文件
     *
     * @param file
     * @return
     */
    public static Bitmap getImageFromFile(String file) {

        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 清除目录中文件，保留目录结构
     */
    public static boolean deleteFiles(File file) {

        if (!file.exists()) {
            return false;
        }

        if (file.isFile()) {
            return file.delete();
        }

        File[] childFiles = file.listFiles();

        if (null == childFiles || 0 == childFiles.length) {
            return false;
        }

        for (File f : childFiles) {
            deleteFiles(f);
        }

        return true;
    }

    /**
     * 清除目录中文件，保留目录结构
     */
    public static boolean deleteFiles(String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            return false;
        }

        if (file.isFile()) {
            return file.delete();
        }

        File[] childFiles = file.listFiles();

        if (null == childFiles || 0 == childFiles.length) {
            return false;
        }

        for (File f : childFiles) {
            deleteFiles(f.getAbsolutePath());
        }

        return true;
    }

    /**
     * 获取输入大小的照片
     *
     * @param path
     * @param height
     * @return
     */
    public static Bitmap getBitmapBySize(String path, int width, int height) {

        Bitmap bitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

        //设置为true,只是获取图片的大小，不加载图片到内存，decoder返回null
        options.inJustDecodeBounds = true;

        //将图片的out...参数赋值给options
        BitmapFactory.decodeFile(path, options);

        //计算缩放比例
        options.inSampleSize = calculateScale(options, width, height);

        //设置为false之后，decoder返回bitmap
        options.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeFile(path, options);


        return bitmap;
    }

    /**
     * 按照指定图片质量保存图片
     *
     * @param bm
     * @param path
     * @param fileName
     * @param quality
     */
    public static void saveBitmapWithQuality(Bitmap bm, String path, String fileName, int quality) {

        if (null == bm) {
            return;
        }

        if (quality < 0 || quality > 100) {
            return;
        }


        //写入文件
        try {
            FileOutputStream out = new FileOutputStream(new File(path, fileName));
            if (fileName.toUpperCase().contains("PNG")) {
                bm.compress(Bitmap.CompressFormat.PNG, quality, out);
            } else {
                bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取图片缩放的比例
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateScale(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        int width = options.outWidth;
        int height = options.outHeight;

        int scale = 1;

        if (width > reqWidth || height > reqHeight) {
            int widthScale = Math.round((float) width / (float) reqWidth);
            int heightScale = Math.round((float) height / (float) reqHeight);

            scale = widthScale > heightScale ? widthScale : heightScale;
        }
        return scale;
    }


    /**
     * 百度坐标系BD-09 转 火星坐标系GCJ-02
     *
     * @param bdLat 百度纬度
     * @param bdLng 百度经度
     * @return 火星坐标系经纬度
     */
    public static LatLng bd2Gcj(double bdLat, double bdLng) {
        double PI = 3.1415926535897932384626;
        double xPi = PI * 3000.0 / 180.0;
        double a = 6378245.0;
        double ee = 0.00669342162296594323;

        double x = bdLng - 0.0065;
        double y = bdLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * xPi);

        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * xPi);
        double gcjLng = z * Math.cos(theta);
        double gcjLat = z * Math.sin(theta);

        return new LatLng(gcjLat, gcjLng);
    }


    /**
     * 获取时间时间戳
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 时间戳
     */
    public static long getTimestamp(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            Date date = format.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * @param time 时间戳
     * @return 时间显示格式
     */
    public static String getTime(long time) {
        String result = "";

        long millis = System.currentTimeMillis();
        int day = (int) (millis / 1000 / 3600 / 24 - time / 1000 / 3600 / 24);

        switch (day) {
            case 0:
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                result = format.format(time);
                break;
            case 1:
                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                result = "昨天" + format2.format(time);
                break;
            case 2:
                SimpleDateFormat format3 = new SimpleDateFormat("HH:mm");
                result = "前天" + format3.format(time);
                break;
            default:
                SimpleDateFormat format4 = new SimpleDateFormat("M月d日 HH:mm");
                result = format4.format(time);
                break;
        }

        return result;
    }


    /**
     * 将图片转换成BASE64
     */
    public static String imgToStrByBase64(Bitmap bitmap) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] bytes = bos.toByteArray();

        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
