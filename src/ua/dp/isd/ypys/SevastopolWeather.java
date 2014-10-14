package ua.dp.isd.ypys;

import java.util.Timer;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

public class SevastopolWeather extends AppWidgetProvider
{
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new WeatherTimer(context), 1,
				Globals.UPDATE_INTERVAL);
	}
}
