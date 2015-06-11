package api.android.demo.sensor;

import api.android.demo.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class OrenView extends SurfaceView implements SurfaceHolder.Callback{
	SurfaceHolder holder;
	Paint paint;
	Bitmap bit;
	Bitmap background;
                //�����һ�����ԣ��Ƕȣ������Դ�����
	public float degree;
	
	public OrenView(Context context) {
		super(context);
		this.setKeepScreenOn(true);
		paint = new Paint();
		bit = BitmapFactory.decodeResource(context.getResources(),R.drawable.pin);
		background = BitmapFactory.decodeResource(context.getResources(),R.drawable.news);
		background = Bitmap.createScaledBitmap(background, 320, 450,false);
		holder = this.getHolder();
		holder.addCallback(this);
	}

	public float getDegree() {
		return degree;
	}

	public void setDegree(float degree) {
		this.degree = degree;
	}
                
               //����һ���Զ�����߳�
	public void surfaceCreated(SurfaceHolder holder) {
		Thread t = new MyThread();
		t.start();
	}
	
	class MyThread extends Thread{
		public void run() {
			while(true){
				Canvas c = holder.lockCanvas();
				if(c != null){
					c.save();
					c.drawBitmap(background, 0, 0, paint);
//					try {
//						sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					//�ֻ�ת�����ٶȣ��ͻ��Ƹ��Ķ��ٶȣ����ֻ��Ƶ�ָ��ָ��0�ȣ���ָ�򱱷�
					c.rotate(-1*degree,148,216);
					
					c.drawBitmap(bit, 148, 40, paint);
					c.restore();
					holder.unlockCanvasAndPost(c);
				}
			}
		}
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}

	public void surfaceDestroyed(SurfaceHolder holder) {}
}
