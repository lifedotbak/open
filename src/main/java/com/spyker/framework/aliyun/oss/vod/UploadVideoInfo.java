package com.spyker.framework.aliyun.oss.vod;

import lombok.Data;

@Data
public class UploadVideoInfo {

	/*
	 * 必选，视频源文件名称（必须带后缀, 支持 ".3gp", ".asf", ".avi", ".dat", ".dv", ".flv", ".f4v",
	 * ".gif", ".m2t", ".m3u8", ".m4v", ".mj2", ".mjpeg", ".mkv", ".mov", ".mp4",
	 * ".mpe", ".mpg", ".mpeg", ".mts", ".ogg", ".qt", ".rm", ".rmvb", ".swf",
	 * ".ts", ".vob", ".wmv", ".webm"".aac", ".ac3", ".acm", ".amr", ".ape", ".caf",
	 * ".flac", ".m4a", ".mp3", ".ra", ".wav", ".wma"）
	 */
	private String fileName;
	// 必选，视频标题
	private String title;
	// 可选，分类ID
	private long cateId;
	// 可选，视频标签，多个用逗号分隔
	private String tags;
	// 可选，视频描述
	private String description;

	private String templateGroupId;
	private String transcodeMode;
	private String coverURL;
	private Long fileSize;

}
