package api.android.demo.datastore;

import java.util.Date;

public class ModelUser {
	//�û�������ID
	private int m_UserID;
	//�û����
	private String m_UserName;
	//�������
	private Date m_CreateDate = new Date();
	//״̬ 0ʧЧ 1����
	private int m_State = 1;
	/**
	 * �û�������ID
	 */
	public int GetUserID() {
		return m_UserID;
	}
	/**
	 * �û�������ID
	 */
	public void SetUserID(int p_UserID) {
		this.m_UserID = p_UserID;
	}
	/**
	 * �û����
	 */
	public String GetUserName() {
		return m_UserName;
	}
	/**
	 * �û����
	 */
	public void SetUserName(String p_UserName) {
		this.m_UserName = p_UserName;
	}
	/**
	 * �������
	 */
	public Date GetCreateDate() {
		return m_CreateDate;
	}
	/**
	 * �������
	 */
	public void SetCreateDate(Date p_CreateDate) {
		this.m_CreateDate = p_CreateDate;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public int GetState() {
		return m_State;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public void SetState(int p_State) {
		this.m_State = p_State;
	}
}
