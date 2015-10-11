package hr.sdautovic.apache.webserver.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.xmlrpc.webserver.ServletWebServer;

public class SSLServletWebServer extends ServletWebServer {
	private String m_keyStorePassphrase = "";
	private SSLServerSocketFactory m_sslSocketFactory = null;
	
	public SSLServletWebServer(HttpServlet pServlet, int pPort, String keyStorePassphrase, String keyStoreFile) throws ServletException, NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException, KeyManagementException {
		super(pServlet, pPort);
	    this.m_sslSocketFactory = createSSLServerSocketFactory(keyStorePassphrase, keyStoreFile);
	}

	@Override
	protected ServerSocket createServerSocket(int pPort, int backlog, InetAddress addr) throws IOException {
		ServerSocket socket = this.m_sslSocketFactory.createServerSocket(pPort, backlog, addr);
		return socket;
	}
	
	private SSLServerSocketFactory createSSLServerSocketFactory(String keyStorePassphrase, String keyStoreFile) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException, KeyManagementException {
		this.m_keyStorePassphrase = keyStorePassphrase;
//	    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		
	    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	    keyStore.load(new FileInputStream(keyStoreFile), this.m_keyStorePassphrase.toCharArray());
	 
	    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
	    tmf.init(keyStore);
	    
		SSLContext ctx = SSLContext.getInstance("SSL");
	    ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
	    return ctx.getServerSocketFactory();
	}
}
