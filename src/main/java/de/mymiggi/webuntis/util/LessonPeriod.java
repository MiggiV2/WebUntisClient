package de.mymiggi.webuntis.util;

public class LessonPeriod
{
	private int startTime;
	private int endTime;
	private Elements[] elements;

	public int getEndTime()
	{
		return endTime;
	}

	public void setEndTime(int endTime)
	{
		this.endTime = endTime;
	}

	public int getStartTime()
	{
		return startTime;
	}

	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}

	public Elements[] getElements()
	{
		return elements;
	}

	public void setElements(Elements[] elements)
	{
		this.elements = elements;
	}
}
