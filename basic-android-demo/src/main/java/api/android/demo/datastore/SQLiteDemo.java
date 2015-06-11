package api.android.demo.datastore;

import java.lang.reflect.Field;
import api.android.demo.* ;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SQLiteDemo extends Activity {
	
	ListView lvUser;
	private int m_ListViewPosition;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlite_demo);
		
		InitView();
		InitListeners();
		BindData();
	}

	private void InitView() {
		lvUser = (ListView) findViewById(R.id.lvUser);
	}
	
	private void InitListeners() {
		registerForContextMenu(lvUser);
	}
	
	/**
	 * �����
	 */
	public void BindData()
	{
		AdapterUser _AdapterUser = new AdapterUser(this);
		lvUser.setAdapter(_AdapterUser);
	}
	
	/**
	 * ɾ��һ��ָ�����û�
	 * @param p_ModelUser
	 */
	public void Delete(ModelUser p_ModelUser)
	{	
		String _Message = "��ȷ��Ҫɾ�û�" + p_ModelUser.GetUserName();
		//��ʾ�����
		ShowAlertDialog("ɾ���û�",_Message,new OnDeleteClickListener());
	}
		
	/**
	 * ���ɾ���¼�
	 *
	 */
	private class OnDeleteClickListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			//��ȡ�󶨵�Adapter
			AdapterUser _AdapterUser = (AdapterUser)lvUser.getAdapter();
			//��ȡ����е�Userʵ����
			ModelUser _ModelUser = (ModelUser)_AdapterUser.getItem(m_ListViewPosition);
			BusinessUser _BusinessUser = new BusinessUser(SQLiteDemo.this);
			//����ɾ��ɾ��
			Boolean _Result = _BusinessUser.DeleteUserByUserID(_ModelUser.GetUserID());
			if(_Result == true)
			{
				//�Ƴ�ɾ��Ҫɾ�����
				_AdapterUser.GetList().remove(m_ListViewPosition);
				//�����ı䣬ˢ��ListViewUI
				_AdapterUser.notifyDataSetChanged();
			}
		}
		
	}
	
	protected AlertDialog ShowAlertDialog(String p_Title,String p_Message,DialogInterface.OnClickListener p_ClickListener)
	{
		return new AlertDialog.Builder(this).setTitle(p_Title).setMessage(p_Message).setPositiveButton("ȷ��", p_ClickListener).setNegativeButton("ȡ��", null).show();
	}
	
	//���������ĳ���Itemʱ�Ĳ˵�
	@Override
	public void onCreateContextMenu(ContextMenu p_ContextMenu, View p_View, ContextMenuInfo p_MenuInfo) {
		AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo)p_MenuInfo;
		ListAdapter _ListAdapter = lvUser.getAdapter();
		ModelUser _ModelUser = (ModelUser)_ListAdapter.getItem(_AdapterContextMenuInfo.position);
		m_ListViewPosition = _AdapterContextMenuInfo.position;
		//���ò˵�Title
		p_ContextMenu.setHeaderTitle(_ModelUser.GetUserName());
		int _ItemID[] = {1,2};
		//���ò˵�
		CreateMenu(p_ContextMenu, _ItemID);
	}
	
	//���õ���ֻ�Menu����½��˵�
	@Override    
	public boolean onCreateOptionsMenu(Menu menu) {         
		menu.add(0, 1, 1, "�½�");        
//		menu.add(0, 2, 2, "ɾ��");        
		return super.onCreateOptionsMenu(menu);
	}
	
	//����Menu�˵��½��������¼�
	@Override    public boolean onOptionsItemSelected(MenuItem item) { 
		if(item.getItemId() == 1){    
			ShowUserAddOrEditDialog(null);  
		}               
		return true;   
	}
	
	//��ɳ����˵�����
	protected void CreateMenu(Menu p_Menu,int p_ItemID[])
	{
		int _GroupID = 0;
		int _Order = 0;
		
		for(int i=0;i<p_ItemID.length;i++)
		{
			switch(p_ItemID[i])
			{
			case 1:
				p_Menu.add(_GroupID, p_ItemID[i], _Order, "�༭");
				break;
			case 2:
				p_Menu.add(_GroupID, p_ItemID[i], _Order, "ɾ��");
				break;
			default:
				break;
			}
		}
	}
	
	//�����˵�����¼�
	@Override
	public boolean onContextItemSelected(MenuItem p_Item) {
		AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo)p_Item.getMenuInfo();
		ListAdapter _ListAdapter = lvUser.getAdapter();
		ModelUser _ModelUser = (ModelUser)_ListAdapter.getItem(_AdapterContextMenuInfo.position);
		
		switch (p_Item.getItemId()) {
		case 1:
			ShowUserAddOrEditDialog(_ModelUser);
			break;
		case 2:
			Delete(_ModelUser);
			break;
		default:
			break;
		}
		
		return super.onContextItemSelected(p_Item);
	}
	
	//�������ӻ����޸ģ�������ӻ�༭�Ի���
	protected void ShowUserAddOrEditDialog(ModelUser p_ModelUser)
	{
		LayoutInflater _LayoutInflater = LayoutInflater.from(this);
		//���ضԻ���Ҫ��ʾ�Ĳ����ļ�
		View _View = _LayoutInflater.inflate(R.layout.user_add_or_edit, null);
		
		EditText _etUserName = (EditText)_View.findViewById(R.id.etUserName);
		
		if(p_ModelUser != null)
		{
			_etUserName.setText(p_ModelUser.GetUserName());
		}
		
		String _Title;
		if(p_ModelUser == null)
		{
			_Title = "����û�";
		}
		else {
			_Title = "�޸��û�";
		}
		
		//����һ�������Ի����¼�
		AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
		_Builder.setTitle(_Title);
		_Builder.setView(_View);
		//���ñ��桢ȡ��ť�����󶨵������õ��¼�
		_Builder.setNeutralButton("����", new OnAddOrEditUser(p_ModelUser,_etUserName,true));
		_Builder.setNegativeButton("ȡ��", new OnAddOrEditUser(null,null,false));
		_Builder.show();
	}
	
	//�رնԻ���
	protected void SetAlertDialogIsClose(DialogInterface p_Dialog,Boolean p_IsClose)
	{
		try {
			Field _Field = p_Dialog.getClass().getSuperclass().getDeclaredField("mShowing");
			_Field.setAccessible(true);
		    _Field.set(p_Dialog, p_IsClose);
		} catch (Exception e) {
		}
	}
	
	//�Ի���������õ���ӻ��޸��¼�
	private class OnAddOrEditUser implements DialogInterface.OnClickListener
	{
		private ModelUser m_ModelUser;
		private EditText m_txtUserName;
		private Boolean m_IsSaveButton;
		
		public OnAddOrEditUser(ModelUser p_ModelUser,EditText p_txtUserName,Boolean p_IsSaveButton)
		{
			m_ModelUser = p_ModelUser;
			m_txtUserName = p_txtUserName;
			m_IsSaveButton = p_IsSaveButton;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			//����Ĳ��Ǳ�����ȡ����رնԻ���
			if(m_IsSaveButton == false)
			{
				SetAlertDialogIsClose(dialog,true);
				return;
			}
			
			if(m_ModelUser == null)
			{
				m_ModelUser = new ModelUser();
			}
			
			String _UserName = m_txtUserName.getText().toString().trim();
		    
		    BusinessUser _BusinessUser = new BusinessUser(SQLiteDemo.this);

		    //�ж��Ƿ��Ѿ����ڸ��û���
		    boolean _CheckResult = !_BusinessUser.IsExistUserByUserName(_UserName,m_ModelUser.GetUserID());
		    if(!_CheckResult)
		    {
				Toast.makeText(getApplicationContext(), "���û����Ѿ�����", 1).show();
		    	SetAlertDialogIsClose(dialog,false);
		    	return;
		    }
		    else {
		    	SetAlertDialogIsClose(dialog,true);
			}

			m_ModelUser.SetUserName(String.valueOf(m_txtUserName.getText().toString().trim()));
			
			Boolean _Result = false;
			//�������ӻ����޸ĵ��÷���
			if(m_ModelUser.GetUserID() == 0)
			{
				_Result = _BusinessUser.InsertUser(m_ModelUser);
			}
			else {
				_Result = _BusinessUser.UpdateUserByUserID(m_ModelUser);
			}

			if(_Result)
			{
				//ListView���°�Adapter
				AdapterUser _AdapterUser = new AdapterUser(SQLiteDemo.this);
				lvUser.setAdapter(_AdapterUser);
			}
			else {
				Toast.makeText(getApplicationContext(), "���ʧ��", 1).show();
			}
		}
		
	}
}