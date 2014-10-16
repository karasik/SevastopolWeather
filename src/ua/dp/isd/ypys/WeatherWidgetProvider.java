package ua.dp.isd.ypys;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class WeatherWidgetProvider extends AppWidgetProvider
{
	private PendingIntent service = null;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		System.out.println("Call onUpdate");

		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		Calendar time = Calendar.getInstance();
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MILLISECOND, 0);

		Intent intent = new Intent(context, WeatherUpdatingService.class);

		if (service == null)
		{
			service = PendingIntent.getService(context, 0, intent,
					PendingIntent.FLAG_CANCEL_CURRENT);
		}

		alarmManager.setRepeating(AlarmManager.RTC, time.getTime().getTime(),
				Globals.UPDATE_INTERVAL_MILLIS, service);
	}

	@Override
	public void onDisabled(Context context)
	{
		System.out.println("Call onDisabled");

		final AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		alarmManager.cancel(service);
	}
}
