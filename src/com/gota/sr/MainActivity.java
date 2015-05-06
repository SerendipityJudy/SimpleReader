package com.gota.sr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	ReadView readView;
	BufferedReader reader;
	CharBuffer buffer = CharBuffer.allocate(8000);
	int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		readView = (ReadView) findViewById(R.id.read_view);
		
		loadBook();
		loadPage(0);
	}

	/**
	 * �������鶼һ���ֵ�������
	 */
	private void loadBook() {
		AssetManager assets = getAssets();
		try {
			InputStream in = assets.open("documents/Three.txt");
			Charset charset = CharsetDetector.detect(in);
			reader = new BufferedReader(new InputStreamReader(in, charset));
			reader.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ָ��λ�ÿ�ʼ����һҳ
	 */
	private void loadPage(int position) {
		buffer.position(position);
		readView.setText(buffer);
	}
	
	/**
	 * ��һҳ��ť
	 */
	public void previewPageBtn(View view) {
		
	}
	
	/**
	 * ��һҳ��ť
	 */
	public void nextPageBtn(View view) {
		position += readView.getCharNum();
		loadPage(position);
		readView.resize();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
