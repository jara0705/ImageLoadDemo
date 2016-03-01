package com.example.calendardemo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * ͼƬ������
 * 
 * @author Jiaqi
 *
 */
public class ImageLoader {
	// ͼƬ���� �ڴ滺��
	ImageCache mImageCache = new ImageCache();
	// SD������
	DiskCache mDiskCache = new DiskCache();
	// �Ƿ�ʹ��SD������
	boolean isUseDiskCache = false;
	// �̳߳أ��߳�����ΪCPU����
	ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime
			.getRuntime().availableProcessors());

	public void displayImage(final String url, final ImageView imageView) {
		Bitmap bitmap = isUseDiskCache ? mDiskCache.get(url) : mImageCache
				.get(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
			return;
		}
		imageView.setTag(url);
		mExecutorService.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Bitmap bitmap = downloadImage(url);
				if (bitmap == null) {
					return;
				}
				if (imageView.getTag().equals(url)) {
					imageView.setImageBitmap(bitmap);
				}
				mImageCache.put(url, bitmap);
			}
		});
	}

	public Bitmap downloadImage(String imageUrl) {
		Bitmap bitmap = null;
		try {
			URL url = new URL(imageUrl);
			final HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			bitmap = BitmapFactory.decodeStream(conn.getInputStream());
			conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bitmap;
	}

	public void useDiskCache(boolean useDiskCache) {
		isUseDiskCache = useDiskCache;
	}
}
