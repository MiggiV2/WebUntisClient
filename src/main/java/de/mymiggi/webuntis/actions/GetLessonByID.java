package de.mymiggi.webuntis.actions;

import de.mymiggi.webuntis.util.Elements;
import de.mymiggi.webuntis.util.Response;

public class GetLessonByID
{
	public Elements get(Response response, int ID) throws Exception
	{
		Elements[] lessons = response.getData().getResult().getData().getElements();
		for (Elements temp : lessons)
		{
			if (temp.getId() == ID)
			{
				return temp;
			}
		}
		throw new Exception("Not found!");
	}
}
