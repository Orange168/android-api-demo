package api.android.demo.datastore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import api.android.demo.* ;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FileOperationDemo extends Activity implements OnClickListener {
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
		Write(etUserName.getText().toString());
	}
	
	private void LoadUserName()
	{
		String _Message = Read();
		etUserName.setText(_Message);
	}
	
	private void ClearUserName()
	{
		Write("");
		etUserName.setText("");
	}
	
	private void Write(String p_Message) {
		//�õ����õ�SD��·��
		String _SDCardPath = GetExternalStoragePath();
		if (_SDCardPath != null) {
			String _LogFolderPath = "/Log/";
			String _LogFilePath = _SDCardPath + _LogFolderPath + "Log.txt";
			try {
				//��ݶ���õ��ļ�·����ɸ��ļ�
				CreateFile(_LogFilePath);
				//���·����ȡ���ļ���
				FileOutputStream _FileOutputStream = new FileOutputStream(_LogFilePath);
				byte[] bytes = p_Message.getBytes();
				//��ת���õ���Ϣд����ļ���
				_FileOutputStream.write(bytes);
				//�ر��ļ���
				_FileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private String Read()
	{
		//�õ����õ�SD��·��
		String _SDCardPath = GetExternalStoragePath();
		if (_SDCardPath != null) {
			String _LogFolderPath = "/Log/";
			String _LogFilePath = _SDCardPath + _LogFolderPath + "Log.txt";
			try {
				//��ݶ���õ��ļ�·���õ����ļ���
				FileInputStream _FileInputStream = new FileInputStream(_LogFilePath);
				//��ȡ�ļ�����ݳ���
				int _Length = _FileInputStream.available(); 
			    byte [] _Buffer = new byte[_Length];
			    //��ȡ����
			    _FileInputStream.read(_Buffer);     
//			    res = EncodingUtils.getString(_Buffer, "UTF-8");
			    //���ֽ�ת��ΪString
			    String _Message = new String(_Buffer);
			    //�ر���
			    _FileInputStream.close();
			    return _Message;
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		else
		{
			return "û���ҵ�SD��";
		}
	}
	
	/**
	 * 
	 * ��ȡSdCard·��
	 */
	private String GetExternalStoragePath() {
		// ��ȡSdCard״̬
		String _State = android.os.Environment.getExternalStorageState();

		// �ж�SdCard�Ƿ���ڲ����ǿ��õ�
		if (android.os.Environment.MEDIA_MOUNTED.equals(_State)) {
			if (android.os.Environment.getExternalStorageDirectory().canWrite()) {
				return android.os.Environment.getExternalStorageDirectory().getPath();
			}
		}

		return null;
	}
	
	private void CreateFile(String p_Path) throws IOException
	{
		File _LogFile = new File(p_Path);
		if(!_LogFile.exists())
		{
			String _FolderPath = p_Path.substring(0,p_Path.lastIndexOf("/"));
			IsFolderExists(_FolderPath);
			_LogFile.createNewFile();
		}
	}
	
	/**
	 * ֻ��SD���Ͻ���һ��Ŀ¼��"/sdcard/audio/")
	 * @param strFolder
	 * @return
	 */
	private boolean IsFolderExists(String p_Folder)
    {
        File _File = new File(p_Folder);      
        if (!_File.exists())
        {
            if (_File.mkdir())
            {
                return true;
            }
            else
                return false;
        }
        return true;
    }
}