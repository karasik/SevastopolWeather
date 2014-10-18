package ua.dp.isd.ypys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class WeatherWidgetProvider extends AppWidgetProvider
{

	@Override
	public void onEnabled(Context context)
	{

//		System.out.println("Call onEnabled");

		Intent intent = new Intent(context, WeatherUpdatingService.class);
		PendingIntent serviceIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
				SystemClock.elapsedRealtime() + 100,
				Globals.UPDATE_INTERVAL_MILLIS, serviceIntent);

	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{

		System.out.println("Call onUpdate");
		/*
		 * 
		 * if (serviceIntent == null) { Intent intent = new Intent(context,
		 * WeatherUpdatingService.class); serviceIntent =
		 * PendingIntent.getService(context, 0, intent,
		 * PendingIntent.FLAG_CANCEL_CURRENT);
		 * 
		 * AlarmManager alarmManager = (AlarmManager) context
		 * .getSystemService(Context.ALARM_SERVICE);
		 * 
		 * alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
		 * SystemClock.elapsedRealtime() + 100, Globals.UPDATE_INTERVAL_MILLIS,
		 * serviceIntent); }
		 */
	}

	@Override
	public void onDisabled(Context context)
	{
//		System.out.println("Call onDisabled");

		Intent intent = new Intent(context, WeatherUpdatingService.class);
		PendingIntent serviceIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		alarmManager.cancel(serviceIntent);
	}
}
