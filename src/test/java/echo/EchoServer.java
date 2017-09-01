package echo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import udt.UDTInputStream;
import udt.UDTOutputStream;
import udt.UDTServerSocket;
import udt.UDTSocket;
import udt.util.UDTThreadFactory;

public class EchoServer implements Runnable{

	final ExecutorService pool=Executors.newFixedThreadPool(2);

	final UDTServerSocket server;
	final Thread serverThread;

	volatile boolean started=false;
	volatile boolean stopped=false;

	public EchoServer(int port)throws Exception{
		server=new UDTServerSocket(InetAddress.getByName("localhost"),port);
		serverThread=UDTThreadFactory.get().newThread(this);
	}

	public void start(){
		serverThread.start();
	}
	
	public void stop(){
		stopped=true;
	}
	public void run(){
		try{
			started=true;
			while(!stopped){
				final UDTSocket socket=server.accept();
				pool.execute(new Request(socket));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	static String readLine(InputStream r)throws IOException{
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		while(true){
			int c=r.read();
			if(c<0 && bos.size()==0)return null;
			if(c<0 || c==10)break;
			else bos.write(c);
		}
		return bos.toString();
	}


	public static class Request implements Runnable{

		final UDTSocket socket;

		public Request(UDTSocket socket){
			this.socket=socket;
		}

		public void run(){
			try{
				System.out.println("Processing request from <"+socket.getSession().getDestination()+">");
				UDTInputStream in=socket.getInputStream();
				UDTOutputStream out=socket.getOutputStream();
				PrintWriter writer=new PrintWriter(new OutputStreamWriter(out));
				String line=readLine(in);
				if(line!=null){
					System.out.println("ECHO: "+line);
					//echo back the line
					writer.println(line);
					writer.flush();
				}
				System.out.println("Request from <"+socket.getSession().getDestination()+"> finished.");
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

}
