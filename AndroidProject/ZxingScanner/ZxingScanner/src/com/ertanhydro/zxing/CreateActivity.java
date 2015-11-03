package com.ertanhydro.zxing;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class CreateActivity extends Activity implements OnClickListener{

	private ImageView result=null;
    Bitmap bmp=null;
	RelativeLayout mAdContainer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create);
        initView();
        
        String content=this.getIntent().getStringExtra("content");
        try {
			bmp=createBitmap(Create2DCode(content));
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        result.setImageBitmap(bmp);
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
    	bmp.compress(Bitmap.CompressFormat.PNG, 100, baos1);
    	try {
			Writetemp(baos1.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    /**
   	 * 初始化控件
   	 */
   	private void initView(){
   		result=(ImageView)findViewById(R.id.img);
   		findViewById(R.id.save).setOnClickListener(this);
   		findViewById(R.id.share).setOnClickListener(this);
   		findViewById(R.id.back).setOnClickListener(this);

   	}
	/**
	 * 按键处理
	 */
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.save:
				// TODO Auto-generated method stub
			 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    	bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
		    	try {
					Write(baos.toByteArray());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
		case R.id.share:
			 	Intent intent=new Intent(Intent.ACTION_SEND);  
			 	intent.setType("image/png");  
			 	File f = new File("/mnt/sdcard/myfile/temp/temp.jpg");
			 	Uri u = Uri.fromFile(f);
			 	intent.putExtra(Intent.EXTRA_SUBJECT, "sharing");  
			 	intent.putExtra(Intent.EXTRA_TEXT,"分享到1");
			 	intent.putExtra(Intent.EXTRA_STREAM, u);
			 	intent.putExtra("sms_body","Description");
			 	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
			 	startActivity(Intent.createChooser(intent, "Share way"));
			break;
		case R.id.back:
				// TODO Auto-generated method stub
				finish();
			break;
		default:
			break;
		}
	}
		
    public Bitmap Create2DCode(String str) throws WriterException, UnsupportedEncodingException {
    	
		BitMatrix matrix = new MultiFormatWriter().encode(new String(str.getBytes("GBK"),"ISO-8859-1"),
				BarcodeFormat.QR_CODE, 300, 300);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(matrix.get(x, y)){
					pixels[y * width + x] = 0xff000000;
				}
			}
		}	
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
    
    public  void Write(byte []b) throws IOException
	{
		File cacheFile =null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();
		
			long time=Calendar.getInstance().getTimeInMillis();
			String fileName =time+".png";
			File dir = new File(sdCardDir.getCanonicalPath()
					+"/myfile/");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			cacheFile = new File(dir, fileName);
		
		}  
		Toast toast = Toast.makeText(getApplicationContext(),
				"save in /myfile Success!", Toast.LENGTH_LONG);
	      	   toast.setGravity(Gravity.CENTER, 0, 0);
	      	   toast.show();
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(cacheFile));
			
				bos.write(b,0,b.length);
				bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public  void Writetemp(byte []b) throws IOException
  	{
  		File cacheFile =null;
  		if (Environment.getExternalStorageState().equals(
  				Environment.MEDIA_MOUNTED)) {
  			File sdCardDir = Environment.getExternalStorageDirectory();
  			String fileName ="temp.jpg";
  			File dir = new File(sdCardDir.getCanonicalPath()
  					+"/myfile/temp");
  			if (!dir.exists()) {
  				dir.mkdirs();
  			}
  			cacheFile = new File(dir, fileName);
  		
  		}  
  		BufferedOutputStream bos = null;
  		try {
  			bos = new BufferedOutputStream(new FileOutputStream(cacheFile));
  			
  				bos.write(b,0,b.length);
  				bos.close();
  		} catch (FileNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
    
    private Bitmap createBitmap( Bitmap src) {
    	
	    if( src == null ){
	    	return null;
	    }
	    Paint paint=new Paint();
	    paint.setColor(Color.WHITE);
	    paint.setAntiAlias(true);
	   
	    int w = 300;
	    int h = 300;
	    Bitmap newb = Bitmap.createBitmap( w, h, Config.ARGB_8888 );
	    Canvas cv = new Canvas( newb );
	
	    cv.drawColor(Color.WHITE);
	    cv.drawBitmap(src, 0, 0, null );
	    cv.save( Canvas.ALL_SAVE_FLAG );
	    cv.restore();
	    return newb;
    }
}