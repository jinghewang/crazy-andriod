
import java.net.*;
import java.io.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SimpleServer
{
	public static void main(String[] args)
		throws IOException
	{
		// 创建一个ServerSocket，用于监听客户端Socket的连接请求
		ServerSocket serverSocket = new ServerSocket(30000);  // ①
		// 采用循环不断接受来自客户端的请求
		while (true)
		{
			//接受客户端请求
			Socket client = serverSocket.accept();
			System.out.println("accept");
			try
			{
				//接收客户端消息
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String str = in.readLine();
				System.out.println("read:" + str);
				//向服务器发送消息
				PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(client.getOutputStream())),true);
				out.println("server message:" + str);
				//关闭流
				out.close();
				in.close();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				//关闭
				client.close();
				System.out.println("close");
			}
		}
	}
}
