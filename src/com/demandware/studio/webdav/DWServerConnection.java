package com.demandware.studio.webdav;

import com.demandware.studio.settings.DWSettingsProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;

public class DWServerConnection {
    private final DWSettingsProvider settingsProvider;
    private final CloseableHttpClient client;
    private final HttpClientContext context;

    public DWServerConnection(DWSettingsProvider settingsProvider) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.settingsProvider = settingsProvider;

        // SSLContextFactory to allow all hosts. Without this an SSLException is thrown with self signed certs
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("https", socketFactory).build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        client = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        context = new HttpClientContext();
        context.setCredentialsProvider(getCredientials());
    }

    public String getBaseServerPath() {
        return String.format("https://%s/on/demandware.servlet/webdav/Sites/Cartridges/%s", settingsProvider.getHostname(), settingsProvider.getVersion());
    }

    public String getCartridgeName(String rootPath) {
        return Paths.get(rootPath).getFileName().toString();
    }

    public String getRemoteFilePath(String rootPath, String filePath) {
        String relPath = filePath.substring(rootPath.length(), filePath.length());
        String cartridgeName = getCartridgeName(rootPath);
        return getBaseServerPath() + "/" + cartridgeName + relPath;
    }

    public ArrayList<String> getRemoteDirPaths(String rootPath, String filePath) {
        ArrayList<String> serverPaths = new ArrayList<String>();
        Path relPath = Paths.get(rootPath).relativize(Paths.get(filePath)).getParent();
        String cartridgeName = getCartridgeName(rootPath);

        String dirPath = "";
        for (Path subPath : relPath) {
            dirPath = dirPath + "/" + subPath.getFileName();
            serverPaths.add(getBaseServerPath() + "/" + cartridgeName + dirPath);
        }

        return serverPaths;
    }

    public CloseableHttpClient getClient() {
        return client;
    }

    public HttpClientContext getContext() {
        return context;
    }

    public CredentialsProvider getCredientials() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(settingsProvider.getHostname(), AuthScope.ANY_PORT),
                new UsernamePasswordCredentials(settingsProvider.getUsername(), settingsProvider.getPassword()));
        return credentialsProvider;
    }

}

