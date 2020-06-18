package co.grandcircus.final_project_mh.YoutubeApi;

public class Item {
private String kind;
private String etag;
private Id id ;
private Snippet snippet;
private String channelTitle;
private String liveBroadcastContent;
private String publishTime;

public String getKind() {
	return kind;
}
public void setKind(String kind) {
	this.kind = kind;
}
public String getEtag() {
	return etag;
}
public void setEtag(String etag) {
	this.etag = etag;
}
public Id getId() {
	return id;
}
public void setId(Id id) {
	this.id = id;
}
public Snippet getSnippet() {
	return snippet;
}
public void setSnippet(Snippet snippet) {
	this.snippet = snippet;
}
public String getChannelTitle() {
	return channelTitle;
}
public void setChannelTitle(String channelTitle) {
	this.channelTitle = channelTitle;
}
public String getLiveBroadcastContent() {
	return liveBroadcastContent;
}
public void setLiveBroadcastContent(String liveBroadcastContent) {
	this.liveBroadcastContent = liveBroadcastContent;
}
public String getPublishTime() {
	return publishTime;
}
public void setPublishTime(String publishTime) {
	this.publishTime = publishTime;
}


}
