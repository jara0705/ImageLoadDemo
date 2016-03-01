package com.example.calendardemo;

import android.graphics.Bitmap;

public class DoubleCache {
	ImageCache mImageCache = new ImageCache();
	DiskCache mDiskCache = new DiskCache();

	// 先从内存缓存中获取图片，如果没有再从SD卡中读取
	public Bitmap get(String url) {
		Bitmap bitmap = mImageCache.get(url);
		if (bitmap == null) {
			bitmap = mDiskCache.get(url);
		}
		return bitmap;
	}

	// 将图片缓存到内存和SD卡中
	public void put(String url, Bitmap bitmap) {
		mImageCache.put(url, bitmap);
		mDiskCache.put(url, bitmap);
	}
}
