package api.android.demo.datastore;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import api.android.demo.* ;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NetworkDemo extends Activity {

	private ListView lvDataList;
	private ProgressDialog m_ProgressDialog = null;
	private List<String> m_Data = new ArrayList<String>();
	private Handler m_Handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network_demo);

		InitView();
		InitListeners();
		BindData();
	}

	private void InitView() {
		lvDataList = (ListView) findViewById(R.id.lvDataList);
	}

	private void InitListeners() {

	}

	public void BindData() {
		// ��ʾһ�������
		m_ProgressDialog = ProgressDialog.show(NetworkDemo.this, "���Ե�...",
				"��ȡ��Ϣ�б�...", true);

		new Thread() {
			public void run() {
				try {
					// ��ȡ��վ�������
					String _Content = Request("www.caoegg.cn");
					// ����һ��������ʽ����������ݵ���ȡ������
					String _Str = "(<div class=\"c\">){1}[\\S ]+(</div>){1}";
					// ����������ʽ
					Pattern _Pattern = Pattern.compile(_Str);
					// ����ƥ���Matcher
					Matcher _Matcher = _Pattern.matcher(_Content);
					_Content = "";
					// ����Matcher��ȡƥ������
					while (_Matcher.find()) {
						String _Tmp = _Matcher.group();
						_Content += _Tmp + "\r\n";
						// ��Html���ת��ΪText��ʽ������List
						m_Data.add(Html2Text(_Tmp));
					}
					// ����ListView����ʾ���
					UpdateList();
					// �رս����
					m_ProgressDialog.dismiss();
				} catch (Exception e) {
					Toast.makeText(NetworkDemo.this, "��ȡ��Ϣʧ�ܣ�", 1).show();
				}
			}
		}.start();
	}

	private void UpdateList() {
		m_Handler.post(new Runnable() {
			public void run() {
				// ����һ��Adapter�����Ұ�Listveiw
				lvDataList.setAdapter(new ArrayAdapter<String>(
						NetworkDemo.this, android.R.layout.simple_list_item_1,
						m_Data));
			}
		});
	}

	private String Request(String p_Url) throws ClientProtocolException,
			IOException, URISyntaxException {
		//����һ��HttpClientʵ��
		HttpClient httpClient = new DefaultHttpClient();
		//ͨ��URIUtils����������һ��Uri����������װ���Url
		URI _Uri = URIUtils.createURI("http", p_Url, -1, null, null, null);
		//����HttpGet����ʹ��Get��ʽ��ȡ��վ���
		HttpGet _Get = new HttpGet(_Uri);
		//��ϵͳ�ṩ��Ĭ�ϵĻָ�����
		ResponseHandler<String> _ResponseHandler = new BasicResponseHandler();
		//ִ��execute��ȡ��ָ����ʽ��ȡ����
		String _Content = new String(httpClient.execute(_Get, _ResponseHandler).getBytes("ISO-8859-1"));
		return _Content;
		//HttpClient
	}
	
	public String Html2Text(String inputString) {
		String htmlStr = inputString; // ��html��ǩ���ַ�
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // ����script��ǩ

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // ����style��ǩ

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // ����html��ǩ

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// �����ı��ַ�
	}
}