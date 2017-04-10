package cn.yy.pc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PcUtils {


	/**
	 * 抓取到内容后把内容一行一行的放到集合中
	 * @param url
	 * @return
	 */
	static List<String> getHtmlArrayList(String url){
		//返回集合
		List<String> result = new ArrayList<>();
		//缓冲输入流
		BufferedReader in = null;
		try{
			//将参数转换成url对象
			URL realURL = new URL(url);
			//初始化链接
			URLConnection connection = realURL.openConnection();
			//开始链接
			connection.connect();
			//初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while((line=in.readLine()) != null){
				//抓取每一行到result中
				result.add(line);
			}
		}catch(Exception e){
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}finally {
			try {
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 抓取到内容后把内容放到字符串中
	 * @param url
	 * @return
	 */
	static String getHtmlString(String url){
		//存储网络内容的字符串
		StringBuilder result = new StringBuilder();
		//缓冲输入流
		BufferedReader in = null;
		try{
			//将参数转换成url对象
			URL realURL = new URL(url);
			//初始化链接
			URLConnection connection = realURL.openConnection();
			//开始链接
			connection.connect();
			//初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while((line=in.readLine()) != null){
				//抓取每一行到result中
				result.append(line);
			}
		}catch(Exception e){
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}finally {
			try {
				if(in != null){
					in.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return result.toString();
	}
	/**
	 * 用正则过滤0.0
	 * @param targetStr
	 * @param patternStr
	 * @return
	 */
	static String RegexString(String targetStr, String patternStr)
	{
		// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		// 相当于埋好了陷阱匹配的地方就会掉下去
		Pattern pattern = Pattern.compile(patternStr);
		// 定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr);
		// 如果找到了
		if (matcher.find())
		{
			// 打印出结果
			return matcher.group(1);
		}
		return "Nothing";
	}
	/**
	 * 用正则查找字符串中符合内容的字符
	 * @param context 内容
	 * @param regx 正则
	 * @return
	 */
	static String[] get_img(String context , String regx){
		return context.split(regx);
	}

}
