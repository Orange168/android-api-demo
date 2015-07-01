/**
 * Thread activity
 * @author Sundy
 */
package api.android.demo.java4android;

import api.android.demo.R;
import api.android.demo.configuration.CommonConstants;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

/**
 * Multithread demo class
 * @author Sundy
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class ThreadActivity extends Activity {

	
	class MyThread extends Thread
	{
		public MyThread(String p_Name)
		{
			super(p_Name);
		}
		
		@Override
		public void run()
		{
			Log.i(CommonConstants.LOGCAT_TAG_NAME,getName() + "�߳����п�ʼ��");
			
			for (int i = 0; i < 10; i++)
			{
				Log.i(CommonConstants.LOGCAT_TAG_NAME,i + "---" + getName());
				try
				{
					sleep((int) Math.random() * 10);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Thread from Runnable .
	 * @author Sundy
	 * @version 1.0
	 */
	class MyRunnable implements Runnable
	{

		@Override
		public void run()
		{
			Log.i(CommonConstants.LOGCAT_TAG_NAME,Thread.currentThread().getName() + " �߳����п�ʼ��");
	        for (int i = 0; i < 10; i++) {
	        	Log.i(CommonConstants.LOGCAT_TAG_NAME,i + " " + Thread.currentThread().getName());
	            try {
	                Thread.sleep((int) Math.random() * 10);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        Log.i(CommonConstants.LOGCAT_TAG_NAME,Thread.currentThread().getName() + " �߳����н���");
			
		}

	}

	
	
	/**
	 * @author Administrator
	 * 
	 */
	class Ticket implements Runnable
	{
		private int m_Tickets = 100;
		Object _Locker = new Object();
		
		@Override
		public void run()
		{
			for (int i = 0; i < 100; i++)
			{
				//����ͬ����Ϳ��ܻᵼ��ͬһ��Ʊ���������ε����������ͨ��ͬ�������ʵ����
				synchronized (this)
				{
					Sale();
				}
			}
		}

		//����һ��ͬ������������ͨ��ͬ������ʵ����
		//public synchronized void Sale()
		
		
		
		public void Sale()
		{
			if(m_Tickets > 0)
			{
				Log.i(CommonConstants.LOGCAT_TAG_NAME,"��Ʊ��" + Thread.currentThread().getName() + "�����˵�" + m_Tickets + "��Ʊ");
				m_Tickets--;
				
				try {
	                Thread.sleep((int) Math.random() * 10);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}
		}
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_threadactivity)  ;
		
		//1,Runnable demo 
		Button buttonRunnable = (Button)findViewById(R.id.buttonRunnable)  ;
		buttonRunnable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(CommonConstants.LOGCAT_TAG_NAME,Thread.currentThread().getName() + " �߳����п�ʼ��");
				MyRunnable _MyRunnable = new MyRunnable();
		        Thread _Thread1 = new Thread(_MyRunnable);
		        
		        
		        
		        Thread _Thread2 = new Thread(_MyRunnable);
		        _Thread1.start();
		        _Thread2.start();
		        Log.i(CommonConstants.LOGCAT_TAG_NAME,Thread.currentThread().getName() + " �߳����н���");
			}
		}) ;
		
		//2, Thread demo
		Button buttonThread = (Button)findViewById(R.id.buttonThread)  ;
		buttonThread.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(CommonConstants.LOGCAT_TAG_NAME,Thread.currentThread().getName() + " �߳����п�ʼ��");
				new MyThread("A").start();
		        new MyThread("B").start();
		        Log.i(CommonConstants.LOGCAT_TAG_NAME,Thread.currentThread().getName() + " �߳����н���");
			}
		}) ;
		
		
		//3, Synchronized
		Button buttonSynchronized = (Button)findViewById(R.id.buttonSynchronized)  ;
		buttonSynchronized.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Ticket _Ticket = new Ticket();
		        Thread _Thread1 = new Thread(_Ticket, "Thread-A"); 
		        Thread _Thread2 = new Thread(_Ticket, "Thread-B"); 
		        _Thread1.start(); 
		        _Thread2.start(); 
			}
		}) ;
		
	}

}
