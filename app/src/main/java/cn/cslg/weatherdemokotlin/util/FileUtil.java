/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.cslg.weatherdemokotlin.util;

import java.io.File;

import android.content.Context;

public class FileUtil {


    public static File getSaveFile(Context context,String name) {
        File file = new File(context.getFilesDir(), name+".jpg");
        return file;
    }

    public static void  dleteFile(String filePath){
        File file = new File(filePath);
        file.delete();
    }

    public static File getSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "pic.jpg");
        return file;
    }
}
