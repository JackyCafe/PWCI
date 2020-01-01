package tw.com.ian.pwci.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import tw.com.ian.pwci.Service.OnBootService;

/*  完成OnBoot 後丟出一個 Intent 呼叫Service
*
* */
public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


       // callOnBootService(context, intent);

    }

    private void callOnBootService(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
        {
            Log.v("Jacky","onReceive1");
            Intent it = new Intent();
            it.setClass(context, OnBootService.class);
            it.putExtra("Mary","Hello Mary");
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(it);
            }
            else {
                context.startService(it);
            }

        }
    }
}
