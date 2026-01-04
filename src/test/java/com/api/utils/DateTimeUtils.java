package com.api.utils;

import java.time.temporal.ChronoUnit;

import java.time.*;
//Add date time utils

public class DateTimeUtils {

	private DateTimeUtils()
	{}
	public static String getTimewithDaysAgo(int days)
	{
		return Instant.now().minus(days, ChronoUnit.DAYS).toString();
	}
	
}
