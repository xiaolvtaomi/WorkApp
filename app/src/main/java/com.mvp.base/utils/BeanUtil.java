package com.mvp.base.utils;

import com.mvp.base.model.bean.Cell;
import com.mvp.base.model.bean.VideoInfo;
import com.mvp.base.model.bean.VideoRes;
import com.mvp.base.model.bean.VideoType;

/**
 * Description: BeanUtil
 * Creator: yxc
 * date: 2016/9/21 14:39
 */
public class BeanUtil {
    public static VideoInfo VideoType2VideoInfo(VideoType videoType, VideoInfo videoInfo) {
        if (videoInfo == null)
            videoInfo = new VideoInfo();
        videoInfo.title = videoType.title;
        videoInfo.dataId = videoType.dataId;
        videoInfo.pic = videoType.pic;
        videoInfo.airTime = videoType.airTime;
        videoInfo.score = videoType.score;
        return videoInfo;
    }

    public static VideoRes VideoInfo2VideoRes(VideoInfo videoInfo, VideoRes videoRes) {
        if (videoRes == null)
            videoRes = new VideoRes();
        videoRes.title = StringUtils.isEmpty(videoInfo.title);
        videoRes.pic = StringUtils.isEmpty(videoInfo.pic);
        videoRes.score = StringUtils.isEmpty(videoInfo.score);
        videoRes.airTime = StringUtils.isEmpty(videoInfo.airTime);
        videoRes.pic = StringUtils.isEmpty(videoInfo.pic);
        return videoRes;
    }

    public static VideoInfo Cell2VideoInfo(Cell cell, VideoInfo videoInfo){
        if (videoInfo == null)
            videoInfo = new VideoInfo();
        videoInfo.title = cell.title;
        videoInfo.moreURL = cell.npath;
        videoInfo.dataId = cell.getId();
        return videoInfo;
    }
}
