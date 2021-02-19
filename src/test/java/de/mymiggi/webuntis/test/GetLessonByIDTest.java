package de.mymiggi.webuntis.test;

import org.junit.Test;

import de.mymiggi.webuntis.actions.GetLessonByID;
import de.mymiggi.webuntis.actions.SendRequestAction;
import de.mymiggi.webuntis.util.Elements;
import de.mymiggi.webuntis.util.LessonPeriod;
import de.mymiggi.webuntis.util.Response;

public class GetLessonByIDTest
{

	@Test
	public void test() throws Exception
	{
		Response response = new SendRequestAction().run();
		Elements element;
		for (int i = 0; i < 20; i++)
		{
			element = new GetLessonByID().get(response, randomLessonIDFromResponse(response));
			System.out.println("Subject " + element.getLongName());
		}
	}

	private int randomLessonIDFromResponse(Response reponse)
	{
		LessonPeriod[] lessonPeriod = reponse.getData().getResult().getData().getElementPeriodsValue().get("195");
		int randomIndex = (int)(lessonPeriod.length * Math.random());
		return lessonPeriod[randomIndex].getElements()[1].getId();
	}
}
