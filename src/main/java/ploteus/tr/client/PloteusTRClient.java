package ploteus.tr.client;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author makif
 */
public class PloteusTRClient {
    
    private String _userName, _password;
    
    public PloteusTRClient(String userName, String password)
    {
        this._userName = userName;
        this._password = password;
    }
    
    public String uploadLearningOpportunitiesXml(String xml)
            throws IOException
    {
        HttpPost post = new HttpPost("/api/Ploteus/uploadLearningOpportunitiesXml");
        StringEntity entity = new StringEntity(xml, Charset.forName("utf-8"));
        post.setEntity(entity);
        
        return execute(post);
    }
    
    public String uploadQualificationsXml(String xml)
            throws IOException
    {
        HttpPost post = new HttpPost("/api/Ploteus/uploadQualificationsXml");
        StringEntity entity = new StringEntity(xml, Charset.forName("utf-8"));
        post.setEntity(entity);
        
        return execute(post);
    }
    
    public String getXmlStatus(String requestId)
            throws IOException
    {
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("requestId", requestId));
        String paramString = URLEncodedUtils.format(urlParameters, "utf-8");
        
        HttpGet get = new HttpGet("/api/Ploteus/getXmlStatus?" + paramString);
        
        return execute(get);
    }
    
    private String execute(HttpRequestBase requestBase)
            throws IOException
    {
        HttpHost targetHost = new HttpHost("ploteus.iskur.gov.tr", 443, "https");
        requestBase.addHeader("user-agent", "ploteus-tr-dotnet-client");
        requestBase.addHeader("Accept", "application/xml");//Change this header to application/json to get json response
        
        
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(_userName, _password));

        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);        
        
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);

        try(CloseableHttpClient httpclient = HttpClientBuilder.create().build())
        {
            CloseableHttpResponse resp = httpclient.execute(targetHost, requestBase, context);
            HttpEntity httpEntity = resp.getEntity();
            
            String responseData = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
            
            return responseData;
        }
    }
}
