package api.android.demo.datastore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import api.android.demo.* ;

public class SharePreferenceDemo extends Activity implements OnClickListener {
	
	Button btnSaveUserName;
	Button btnClearUserName;
	EditText etUserName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_preference_demo);
		InitView();
		InitListeners();
		
		LoadUserName();
	}
	
	private void InitView() {
		btnSaveUserName = (Button) findViewById(R.id.btnSaveUserName);
		btnClearUserName = (Button) findViewById(R.id.btnClearUserName);
		etUserName = (EditText) findViewById(R.id.etUserName);
	}
	
	private void InitListeners() {
		btnSaveUserName.setOnClickListener(this);
		btnClearUserName.setOnClickListener(this);
		etUserName.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSaveUserName:
			SaveUserName();
			break;
		case R.id.btnClearUserName:
			ClearUserName();
			break;
		default:
			break;
		}
	}
	
	private void SaveUserName()
	{		
//		MODE_PRIVATE��ΪĬ�ϲ���ģʽ�������ļ���˽����ݣ�ֻ�ܱ�Ӧ�ñ�����ʣ��ڸ�ģʽ�£�д������ݻḲ��ԭ�ļ������ݣ���������д�������׷�ӵ�ԭ�ļ��У�����ʹ��MODE_APPEND
//		MODE_APPEND��ģʽ�����ļ��Ƿ���ڣ����ھ����ļ�׷�����ݣ�����ʹ������ļ�
//		MODE_WORLD_READABLE��MODE_WORLD_WRITEABLE������������Ӧ���Ƿ���Ȩ�޶�д���ļ�
//		MODE_WORLD_READABLE����ʾ��ǰ�ļ����Ա�����Ӧ�ö�ȡ��MODE_WORLD_WRITEABLE����ʾ��ǰ�ļ����Ա�����Ӧ��д��
		
		//��ȡָ��Key��SharedPreferences����
		SharedPreferences _SP = getSharedPreferences("SharePreferenceDemo",MODE_WORLD_WRITEABLE);
		//��ȡ�༭
		SharedPreferences.Editor _Editor = _SP.edit();
		//����ָ��Key�������
		_Editor.putString("UserName", etUserName.getText().toString());
		//�ύ�������
		_Editor.commit();
	}
	
	private void LoadUserName()
	{
		//��ȡָ��Key��SharedPreferences����
		SharedPreferences _SP = getSharedPreferences("SharePreferenceDemo",MODE_WORLD_READABLE);
		//���Ϊ��֤����������
		if (_SP != null) {
			//����ͻ�ȡָ��Key�����
			String _UserName = _SP.getString("UserName", "");
			etUserName.setText(_UserName);
		}
	}
	
	private void ClearUserName()
	{
		//��ȡָ��Key��SharedPreferences����
		SharedPreferences _SP = getSharedPreferences("SharePreferenceDemo",MODE_WORLD_READABLE);
		if (_SP != null) {
			//��ȡ�༭
			SharedPreferences.Editor _Editor = _SP.edit();
			//���
			_Editor.clear();
			//�ύ���
			_Editor.commit();
			etUserName.setText("");
		}
	}
}