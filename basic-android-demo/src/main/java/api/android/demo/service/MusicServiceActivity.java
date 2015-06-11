/**
 * Lesson5 Service�����İ���
 */
package api.android.demo.service;

import api.android.demo.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Administrator
 *
 */
public class MusicServiceActivity extends Activity {

	private Intent intent  ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_musicservice)  ;
		//��Activity��ֱ�Ӳ�������
		Button button1 = (Button)findViewById(R.id.buttonPlayMusic)  ;
		button1.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MediaPlayer mp = MediaPlayer.create(MusicServiceActivity.this, api.android.demo.R.raw.maria) ;
				mp.start() ;
			}
			
		}) ;
		
		//��Service���������ֲ���
		Button button2 = (Button)findViewById(R.id.buttonStartMusic)  ;
		button2.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(MusicServiceActivity.this,MusicService.class) ;
				startService(intent)  ;
			}
			
		}) ;
		
		
		Button button3 = (Button)findViewById(R.id.buttonStopMusic)  ;
		button3.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(intent != null)
				{
					stopService(intent) ;
				}
			}
			
		}) ;
		
		
	}

}
