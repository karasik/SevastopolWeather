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
	private PendingIntent service = null;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		System.out.println("Call onUpdate");

		if (service == null)
		{
			Intent intent = new Intent(context, WeatherUpdatingService.class);
			service = PendingIntent.getService(context, 0, intent,
					PendingIntent.FLAG_CANCEL_CURRENT);

			AlarmManager alarmManager = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);
		
			alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
					SystemClock.elapsedRealtime() + 100,
					Globals.UPDATE_INTERVAL_MILLIS, service);
		}

	}

	@Override
	public void onDisabled(Context context)
	{
		System.out.println("Call onDisabled");

		if (service != null)
		{
			final AlarmManager alarmManager = (AlarmManager) context
					.getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.cancel(service);
		}
	}
}
