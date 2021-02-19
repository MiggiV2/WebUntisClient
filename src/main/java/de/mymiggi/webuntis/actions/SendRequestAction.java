package de.mymiggi.webuntis.actions;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import de.mymiggi.webuntis.util.Response;

public class SendRequestAction
{
	private static String cookieURLPattern = "https://erato.webuntis.com/WebUntis/?school=%s#/basic/timetable";
	private static String dataURLPattern = "https://erato.webuntis.com/WebUntis/api/public/timetable/weekly/data?elementType=1&elementId=195&date=%s&formatId=3";

	public Response run() throws IOException
	{
		LocalDate currentTime = LocalDate.now();
		String dataURL = String.format(dataURLPattern, currentTime.toString());
		String cookieURL = String.format(cookieURLPattern, new LoadConfig().loadSchoolName());
		HttpClientContext context = HttpClientContext.create();
		try (CloseableHttpClient httpClient = HttpClients.createDefault())
		{
			try (CloseableHttpResponse response = httpClient.execute(new HttpGet(cookieURL), context))
			{
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300)
				{
					System.out.println("Done!");
				}
				else
				{
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}
		}
		String responseJSON;
		try (CloseableHttpClient httpclient = HttpClients.createDefault())
		{
			HttpGet cookieRequestTow = new HttpGet(dataURL);
			System.out.println("Executing request " + cookieRequestTow.getRequestLine());
			cookieRequestTow.addHeader(HttpHeaders.ACCEPT, "application/json");
			cookieRequestTow.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:85.0) Gecko/20100101 Firefox/85.0");
			try (CloseableHttpClient httpClient = HttpClients.createDefault())
			{
				try (CloseableHttpResponse response = httpClient.execute(cookieRequestTow, context))
				{
					HttpEntity entity = response.getEntity();
					responseJSON = entity != null ? EntityUtils.toString(entity) : null;
					Gson gson = new Gson();
					return gson.fromJson(responseJSON, Response.class);
				}
			}
		}
	}
}
