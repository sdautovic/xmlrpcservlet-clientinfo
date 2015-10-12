package hr.sdautovic.apache.webserver.example;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.servlet.ServletException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import hr.sdautovic.apache.webserver.servlet.SSLServletWebServer;
import hr.sdautovic.apache.webserver.xmlrpc.servlet.XmlRpcServletClientInfo;

public class SimpleSSLXmlRpcServletExample {

	public static void main(String[] args) throws XmlRpcException, ServletException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException {
		
		PropertyHandlerMapping phm = new PropertyHandlerMapping();
		phm.addHandler("monitor", hr.sdautovic.apache.webserver.xmlrpc.handlers.ExampleXmlRpcHandler.class);
		
		SSLServletWebServer webServer = new SSLServletWebServer(new XmlRpcServletClientInfo(phm), 8443, 
				"password", "/home/sdautovic/Documents/workspace/Optima/xmlrpcservlet-clientinfo/certificate_howto/keystore.pkcs12",
				"changeit", "/home/sdautovic/opt/java/jre/lib/security/cacerts");		
		webServer.start();
	}

}
