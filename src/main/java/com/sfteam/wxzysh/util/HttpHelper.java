package com.sfteam.wxzysh.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HttpHelper.java
 * @Description: 请求响应处理
 * @author: knight
 * @date: 2016年7月14日 下午3:09:12
 * @company: sfteam
 */
public class HttpHelper {

	/**
	 * 
	 * @Title: post
	 * @Description: POST请求
	 * @return: String
	 * @author: knight
	 * @throws Exception
	 * @date: 2016年7月14日 下午3:10:10
	 */
	public static String post(String req_url, String json) throws Exception {

		URL url = new URL(req_url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());

		out.write(json.getBytes());
		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "UTF-8"));
		String lines;
		StringBuffer sbf = new StringBuffer();
		while ((lines = reader.readLine()) != null) {
			sbf.append(lines);
		}
		// System.out.println(sbf);
		reader.close();
		connection.disconnect();
		return sbf.toString();
	}

	/**
	 * 
	 * @Title: get
	 * @Description: GET请求
	 * @return: void
	 * @author: knight
	 * @date: 2016年7月14日 下午4:17:37
	 */
	public static String get(String req_url) throws Exception {
		URL url = new URL(req_url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.connect();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "UTF-8"));
		String lines;
		StringBuffer sbf = new StringBuffer();
		while ((lines = reader.readLine()) != null) {
			sbf.append(lines);
		}
		// System.out.println(sbf);
		reader.close();
		connection.disconnect();
		return sbf.toString();
	}

	public static String upload(String req_url, List<String> list)
			throws Exception {
		String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线
		URL url = new URL(req_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + BOUNDARY);

		OutputStream out = new DataOutputStream(conn.getOutputStream());
		byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
		int leng = list.size();
		for (int i = 0; i < leng; i++) {
			String fname = list.get(i);
			File file = new File(fname);
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file" + i
					+ "\";filename=\"" + file.getName() + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");

			byte[] data = sb.toString().getBytes();
			out.write(data);
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
			in.close();
		}
		out.write(end_data);
		out.flush();
		out.close();

		// 定义BufferedReader输入流来读取URL的响应
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String line = null;
		StringBuffer sbf = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			sbf.append(line);
		}
		return sbf.toString();
	}
}
