package com.jueda.ndian.entity;
/**
 * 描述：广告信息</br>
 * @author Eden Cheng</br>
 * @version 2015年4月23日 上午11:32:53
 */
public class ADInfo {
	String id = "";
	String url = "";
	String content = "";
	String type = "";//1是视频，2是网页
	String open_url="";//连接地址

	public String getOpen_url() {
		return open_url;
	}
	public void setOpen_url(String open_url) {
		this.open_url = open_url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
