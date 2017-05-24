package com.alinge.market.home;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alinge.market.R;
import com.alinge.market.common.log.Log;
import com.alinge.market.service.TestService;


/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-02-28 10:14
 * Describe:
 */
public class HomeActivity_back extends AppCompatActivity{
    private  boolean isConnected;
    private  HeadSetBroadCast headSet;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
           isConnected=false;
            Log.info("TestService disconnected!!");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
            Intent intent=new Intent(this, TestService.class);
            bindService(intent,mServiceConnection, Service.BIND_AUTO_CREATE);
        headSet= new HeadSetBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        filter.addAction(AudioManager.ACTION_HEADSET_PLUG);
        filter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        filter.addAction(AudioManager.ACTION_HDMI_AUDIO_PLUG);
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        filter.addAction("android.net.ethernet.ETHERNET_STATE_CHANGED");
        filter.addAction("android.net.ethernet.STATE_CHANGE");
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);

        filter.addAction("android.hardware.usb.action.USB_STATE");
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(AudioManager.EXTRA_AUDIO_PLUG_STATE);
        filter.addAction(AudioManager.ACTION_HEADSET_PLUG);
        filter.addAction(Intent.ACTION_MEDIA_EJECT);
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_REMOVED);
        filter.addAction(Intent.ACTION_MEDIA_CHECKING);

        filter.addAction(AudioManager.ACTION_HDMI_AUDIO_PLUG);
        filter.addAction(AudioManager.EXTRA_SCO_AUDIO_STATE);
        filter.addDataScheme("file");
        filter.setPriority(1000);
        //filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        //filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(headSet, filter);

        AudioManager audoManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        if(audoManager.isWiredHeadsetOn()){
            Toast.makeText(this,"耳机ok",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"耳机不ok",Toast.LENGTH_SHORT).show();
        }

    }
    public class HeadSetBroadCast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.info("action: "+intent.getAction());
        }
    }

    @Override
    protected void onDestroy() {
        if(mServiceConnection!=null){
            unbindService(mServiceConnection);
        }
        unregisterReceiver(headSet);
        super.onDestroy();
    }




}
