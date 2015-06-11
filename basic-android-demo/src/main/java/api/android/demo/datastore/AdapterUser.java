package api.android.demo.datastore;

import java.util.List;
import api.android.demo.* ;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ʵ��һ���Զ����UserAdapter
 *
 */
public class AdapterUser extends BaseAdapter {

	
	/**
	 * ���Ҫ��ʾ�Ŀؼ���ʵ���࣬����ͳһ����ؼ�
	 *
	 */
	private class Holder
	{
		TextView Name;
	}
	
	private List m_List;
	private LayoutInflater m_LayoutInflater;
	
	
	public AdapterUser(Context p_Context)
	{
		BusinessUser _BusinessUser = new BusinessUser(p_Context);
		//��ȡȫ���û�List
		m_List = _BusinessUser.GetUser("");
		//�Ӹ��context�л�ȡLayoutInflaterʵ��
		m_LayoutInflater = LayoutInflater.from(p_Context);
	}
	
	@Override
	public int getCount() {
		return m_List.size();
	}
	
	@Override
	public Object getItem(int position) {
		return m_List.get(position);
	}

	@Override
	public long getItemId(int position) {
		return (long)position;
	}

	@Override
	public View getView(int p_Position, View p_ConvertView, ViewGroup p_Parent) {
		Holder _Holder;
		
		//���View����Ϊ�����������
		if (p_ConvertView == null) {
			//��һ�������ļ�����View
			p_ConvertView = m_LayoutInflater.inflate(R.layout.user_list_item, null);
			_Holder = new Holder();
			//�ҵ��ؼ�������ʼ��Holder��Ŀؼ�
			_Holder.Name = (TextView)p_ConvertView.findViewById(R.id.tvUserName);
			//��View���һ�����
			p_ConvertView.setTag(_Holder);
		}
		else {
			//���View�����Ѿ����ع�Ϊ�գ���ֱ��ȡTag��ݣ�����Holder��ֵ
			_Holder = (Holder) p_ConvertView.getTag();
		}
		
		//�õ����е�Userʵ�����ؼ���ֵ
		ModelUser _ModelUser = (ModelUser)getItem(p_Position);
		_Holder.Name.setText(_ModelUser.GetUserName());
		//���ؿؼ�
		return p_ConvertView;
	}
	
	public List GetList() {
		return m_List;
	}
}
