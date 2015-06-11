package api.android.demo.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class CompassDemo extends Activity
{
    //�������������
	SensorManager manager;
                //����������
	Sensor sensor;
                //����
	OrenView view;
	
                //�½�һ���������¼�����������д�䷽������Ҫ����д  onSensorChanged
	SensorEventListener listener = new SensorEventListener(){
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
        //�����������ϸ˵��һ�£���һ������Ϊ��ĸ��������飬ÿһ��Ԫ���ڲ�ͬ�Ĵ��������в�ͬ�ĺ��塣�˴���Ϊʹ�õ��Ƿ��򴫸���������ֻ��ע��һ��������Ƕȡ���������Ƕ���������ڱ��������һ���Ƕȣ�����Ϊ0��˳ʱ��ת��������Ϊ90���Ϸ�Ϊ180������Ϊ270��
		float orentation = event.values[0];
		Log.i("####",""+orentation);
                                            //�����ֵ����ҳ��
		view.setDegree(orentation);
	}
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //����ҳ�����
        view = new OrenView(this);
        setContentView(view);
        
        //�õ��������������
        manager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        //�õ�����������Sensor.TYPE_ORIENTATION�Ǹ���̬int��������ʾ��ͬ�Ĵ�����
        sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //ע�ᴫ����������һ������ֵ�������ж��Ƿ�ע��ɹ���ȷ����һ������
        //����������ʾ�������������Ȼ���˵ˢ����
        //boolean reg = 
        	manager.registerListener(listener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    //ע����д��������������������Ҫִ�д�������ע��
//�����˳�Ӧ��ʱ��������Ȼ����
    public void onStop(){
    	super.onStop();
    	manager.unregisterListener(listener, sensor);
    }
}
