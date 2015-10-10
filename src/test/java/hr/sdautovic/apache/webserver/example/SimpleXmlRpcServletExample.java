package hr.sdautovic.apache.webserver.example;

import java.io.IOException;
import javax.servlet.ServletException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.ServletWebServer;
import hr.sdautovic.apache.webserver.xmlrpc.servlet.XmlRpcServletClientInfo;

public class SimpleXmlRpcServletExample {
	public static void main(String[] args) throws ServletException, IOException, XmlRpcException {
		
		PropertyHandlerMapping phm = new PropertyHandlerMapping();
		phm.addHandler("monitor", hr.sdautovic.apache.webserver.xmlrpc.handlers.ExampleXmlRpcHandler.class);
		
		ServletWebServer webServer = new ServletWebServer(new XmlRpcServletClientInfo(phm), 8080);		
		webServer.start();
	}

}

