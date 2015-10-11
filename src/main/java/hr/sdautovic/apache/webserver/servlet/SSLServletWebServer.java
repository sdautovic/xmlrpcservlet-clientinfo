package hr.sdautovic.apache.webserver.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.xmlrpc.webserver.ServletWebServer;

public class SSLServletWebServer extends ServletWebServer {

	public SSLServletWebServer(HttpServlet pServlet, int pPort) throws ServletException {
		super(pServlet, pPort);
	}

	@Override
	protected ServerSocket createServerSocket(int pPort, int backlog, InetAddress addr) throws IOException {
		
		ServerSocketFactory ssocketFactory = SSLServerSocketFactory.getDefault();
		ServerSocket socket = ssocketFactory.createServerSocket(pPort, backlog, addr);
		return socket;
	}
}
