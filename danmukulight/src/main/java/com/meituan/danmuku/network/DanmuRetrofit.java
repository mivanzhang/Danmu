package com.meituan.danmuku.network;

import android.net.Uri;
import android.util.Log;

import com.meituan.danmuku.utils.FileUtils;
import com.sankuai.meituan.retrofit2.Call;
import com.sankuai.meituan.retrofit2.Callback;
import com.sankuai.meituan.retrofit2.MediaType;
import com.sankuai.meituan.retrofit2.MultipartBody;
import com.sankuai.meituan.retrofit2.Response;
import com.sankuai.meituan.retrofit2.ResponseBody;
import com.sankuai.meituan.retrofit2.Retrofit;
import com.sankuai.meituan.retrofit2.converter.gson.GsonConverterFactory;
import com.sankuai.meituan.retrofit2.raw.RawCall;

import java.io.File;

import okhttp3.RequestBody;

/**
 * Created by zhangmeng on 2017/6/9.
 */

public class DanmuRetrofit {
    private volatile static DanmuRetrofit instance;
    private final static String SNTP_URL="http://api.mobile.meituan.com/";
    private Retrofit retrofit;

    private DanmuRetrofit(RawCall.Factory callFactory) {
        retrofit=new Retrofit.Builder().baseUrl(SNTP_URL).callFactory(callFactory)
//                .addConverterFactory(SntpConvertFactory.getInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static DanmuRetrofit getInstance(RawCall.Factory callFactory) {
        if (null == instance) {
            synchronized (DanmuRetrofit.class) {
                if (null == instance) {
                    instance = new DanmuRetrofit(callFactory);
                }
            }
        }
        return instance;
    }

    public Call<DanmuContent> getDanmuContent(){
        return retrofit.create(DanmuService.class).getDanmuContent();
    }

    private void uploadFile(Uri fileUri) {
        // create upload service client
        DanmuService service =retrofit.create(DanmuService.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(fileUri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);

        // finally, execute the request
        Call<ResponseBody> call = service.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }
}
