package com.meituan.danmuku.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.widget.Toast;

import com.meituan.android.common.locate.LocationLoaderFactory;
import com.meituan.android.common.locate.LocationLoaderFactoryImpl;
import com.meituan.android.common.locate.MasterLocator;
import com.meituan.android.common.locate.MasterLocatorFactoryImpl;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

import java.util.Date;

/**
 * Created by zhangmeng on 2017/6/9.
 */

public class LocationHelper {
    public final HttpClient httpClient = getThreadSafeClient();
    private android.support.v4.content.Loader<Location> loader;//类中声明loader对象
    private Location data;
    Context context;
    public LocationHelper(Context context) {
       this.context=context;
        data=null;
        tryget();
        loader.startLoading();
    }

    public void tryget(){
        MasterLocator masterLocator = new MasterLocatorFactoryImpl().createMasterLocator(context, httpClient,"");
        LocationLoaderFactory loaderFactory = new LocationLoaderFactoryImpl(masterLocator);
        loader = loaderFactory.createLocationLoader(context, LocationLoaderFactory.LoadStrategy.instant);
        loader.registerListener(0, new Loader.OnLoadCompleteListener<Location>() {
            @Override
            public void onLoadComplete(Loader<Location> loader, Location data) {
                Bundle args = null;
                if (data != null) {
                    args = data.getExtras();
//                    String add = (args == null ? "" : args.getString("address"));
//                    args.putString("location", data.getLatitude() + "," + data.getLongitude() + " provider : " + data.getProvider()
//                            + "\n" + "Accuracy : " + data.getAccuracy() + " Time : " + sdf.format(new Date(data.getTime())));
//                    args.putString("address", TextUtils.isEmpty(add) ? "" : add);
                } else {
                    Toast.makeText(context,"getlocation failed",Toast.LENGTH_LONG).show();
                }
                loader.stopLoading();
//                Message msg = Message.obtain();
//                msg.setData(args);
            }
        });
    }

    public Location getLocation(){
        if(data!=null){
            return data;
        }else {
            loader.startLoading();
            //location

        }
        return null;
    }

    public static DefaultHttpClient getThreadSafeClient() { //构建线程安全的httpClient对象
        DefaultHttpClient client = new DefaultHttpClient();
        ClientConnectionManager mgr = client.getConnectionManager();
        HttpParams params = client.getParams();
        client = new DefaultHttpClient(new ThreadSafeClientConnManager(params,mgr.getSchemeRegistry()), params);
        //通过HttpRequestInterceptor将utm等业务中通用参数进行设置
        return client;
    }

}
