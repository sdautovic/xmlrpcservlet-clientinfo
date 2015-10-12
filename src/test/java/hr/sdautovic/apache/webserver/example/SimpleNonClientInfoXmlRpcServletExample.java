package hr.sdautovic.apache.webserver.example;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.ServletWebServer;
import org.apache.xmlrpc.webserver.WebServer;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;


public class SimpleNonClientInfoXmlRpcServletExample {

	public static void main(String[] args) throws XmlRpcException, ServletException, IOException {
		WebServer webServer = new WebServer(8080);
		
		PropertyHandlerMapping phm = new PropertyHandlerMapping();
		phm.addHandler("monitor", hr.sdautovic.apache.webserver.xmlrpc.handlers.ExampleNonClientInfoXmlRpcHandler.class);
		
		webServer.getXmlRpcServer().setHandlerMapping(phm);
		webServer.start();
	}
}
