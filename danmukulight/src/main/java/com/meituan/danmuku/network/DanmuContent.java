package com.meituan.danmuku.network;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangmeng on 2017/6/9.
 */

public class DanmuContent implements Serializable {
    private List<RecommandDish> commentList;
    private List<CommentDetail> recommendDishList;
}
