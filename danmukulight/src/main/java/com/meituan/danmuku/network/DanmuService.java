package com.meituan.danmuku.network;

import com.sankuai.meituan.retrofit2.Call;
import com.sankuai.meituan.retrofit2.http.GET;
import com.sankuai.meituan.retrofit2.http.POST;
import com.sankuai.meituan.retrofit2.http.QueryMap;

import java.util.Map;

/**
 * Created by zhangmeng on 2017/5/2.
 */

public interface DanmuService {
    @POST("group/v1/timestamp/milliseconds")
    Call<DanmuContent> getDanmuContent(@QueryMap Map<String, String> options);
}
