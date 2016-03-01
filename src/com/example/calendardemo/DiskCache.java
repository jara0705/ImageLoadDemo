package com.example.calendardemo;

import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class DiskCache {

	static String cacheDir = "sdcard/cache/";

	// �ӻ����л�ȡͼƬ
	public Bitmap get(String url) {
		return BitmapFactory.decodeFile(cacheDir + url);
	}

	// ��ͼƬ���浽�ڴ���
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
