package com.example.calendardemo;

import android.graphics.Bitmap;

public class DoubleCache {
	ImageCache mImageCache = new ImageCache();
	DiskCache mDiskCache = new DiskCache();

	// �ȴ��ڴ滺���л�ȡͼƬ�����û���ٴ�SD���ж�ȡ
	public Bitmap get(String url) {
		Bitmap bitmap = mImageCache.get(url);
		if (bitmap == null) {
			bitmap = mDiskCache.get(url);
		}
		return bitmap;
	}

	// ��ͼƬ���浽�ڴ��SD����
	public void put(String url, Bitmap bitmap) {
		mImageCache.put(url, bitmap);
		mDiskCache.put(url, bitmap);
	}
}
