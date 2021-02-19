package de.mymiggi.webuntis;

import java.io.IOException;

import de.mymiggi.webuntis.actions.SendRequestAction;
import de.mymiggi.webuntis.util.Response;

public class Client
{
	public static void main(String... args)
	{
		try
		{
			Response response = new SendRequestAction().run();
			response.getData();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
