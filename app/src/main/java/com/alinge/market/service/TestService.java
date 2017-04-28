package com.alinge.market.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-17 09:28
 * Describe:
 */
public class TestService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
   /* static  Map<Integer,People> peoples=new HashMap<>();
    static {

        peoples.put(1,new People("java",1,22));
        peoples.put(2,new People("php",2,26));
        peoples.put(3,new People("linux",3,25));
        peoples.put(4,new People("mac os",4,29));

    }*//*
    static class InnerTest extends ITest.Stub{
        @Override
        public int add(int x, int y) throws RemoteException {
            return x+y;
        }

       @Override
        public People getObject(int num) throws RemoteException {
            return peoples.get(num);
        }
    }*/
}
