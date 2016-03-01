package com.example.calendardemo;

import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class DiskCache {

	static String cacheDir = "sdcard/cache/";

	// 从缓存中获取图片
	public Bitmap get(String url) {
		return BitmapFactory.decodeFile(cacheDir + url);
	}

	// 将图片缓存到内存中
	public void put(String url, Bitmap bitmap) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(cacheDir + url);
			bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (fileOutputStream!=null) {
				try {
					fileOutputStream.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
}
