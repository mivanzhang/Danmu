package com.meituan.danmuku.control.dispatcher;

import com.meituan.danmuku.model.DanMuModel;
import com.meituan.danmuku.model.channel.DanMuChannel;

/**
 * Created by android_ls on 2016/12/7.
 */
public interface IDanMuDispatcher {

    void dispatch(DanMuModel iDanMuView, DanMuChannel[] danMuChannels);

}
