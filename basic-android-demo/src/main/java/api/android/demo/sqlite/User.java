package api.android.demo.sqlite;

public class User {

	private String userName  ;
	private String userAddress  ;
	private int userAge ;
	private boolean isMale  ;
	private int id  ;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * �õ��û����
	 * @return �û���
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * �����û���
	 * @param userName �����û���
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * �õ��û���ַ
	 * @return ���ص�ַ
	 */
	public String getUserAddress() {
		return userAddress;
	}
	
	/**
	 * �����û���ַ
	 * @param userAddress �û���ַ
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	/**
	 * �õ��û�����
	 * @return
	 */
	public int getUserAge() {
		return userAge;
	}
	/**
	 * �����û�����
	 * @param userAge
	 */
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	
	/**
	 * �Ա�
	 * @return
	 */
	public boolean isMale() {
		return isMale;
	}
	/**
	 * �Ա�
	 * @param isMale
	 */
	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}
	
}
