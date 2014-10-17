package ua.dp.isd.ypys;

import ua.dp.isd.ypys.sevmeteo.R;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WeatherUpdatingService extends Service
{

	@Override
	public void onCreate()
	{
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		updateView();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	private void updateView()
	{
		System.out.print("Updating...");
		new UpdatingThread(this, R.layout.main, R.id.temperature).start();
	}
}
