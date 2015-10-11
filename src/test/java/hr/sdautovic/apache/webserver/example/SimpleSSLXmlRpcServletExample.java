package hr.sdautovic.apache.webserver.example;

import java.io.IOException;

import javax.servlet.ServletException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import hr.sdautovic.apache.webserver.servlet.SSLServletWebServer;
import hr.sdautovic.apache.webserver.xmlrpc.servlet.XmlRpcServletClientInfo;

public class SimpleSSLXmlRpcServletExample {

	public static void main(String[] args) throws XmlRpcException, ServletException, IOException {
		PropertyHandlerMapping phm = new PropertyHandlerMapping();
		phm.addHandler("monitor", hr.sdautovic.apache.webserver.xmlrpc.handlers.ExampleXmlRpcHandler.class);
		
		SSLServletWebServer webServer = new SSLServletWebServer(new XmlRpcServletClientInfo(phm), 8443);		
		webServer.start();
	}

}
