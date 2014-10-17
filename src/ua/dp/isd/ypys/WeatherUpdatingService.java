package ua.dp.isd.ypys;

import java.io.IOException;

import ua.dp.isd.ypys.sevmeteo.R;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class WeatherUpdatingService extends Service {
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		updateView();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void updateView() {
		System.out.print("Updating...");

		final Service myService = this;
		new Thread() {

			@Override
			public void run() {
				String current = "NA";
				try {
					current = WeatherParsingUtil.getTemperature();
				} catch (IOException e) {
					System.err.println("Error accessing website");
					current = "NA";
				}

				System.out.println(current);

				RemoteViews view = new RemoteViews(getPackageName(),
						R.layout.main);
				view.setTextViewText(R.id.temperature, current);
				ComponentName thisWidget = new ComponentName(
						WeatherUpdatingService.this,
						WeatherWidgetProvider.class);
				AppWidgetManager manager = AppWidgetManager.getInstance(WeatherUpdatingService.this);
				manager.updateAppWidget(thisWidget, view);
			}
		}.start();

	}
}
