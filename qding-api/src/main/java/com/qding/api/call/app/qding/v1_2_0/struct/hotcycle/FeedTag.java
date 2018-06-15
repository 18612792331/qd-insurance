package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;


public class FeedTag extends Tag{

	private static final long serialVersionUID = 3618043584066503971L;

	private String tagLeft;
	
	private String tagTop;

	private ActivityPropertyBean activityPropertyBean;
	
	public FeedTag() {

	}

	public FeedTag(String tagLeft, String tagTop) {
		super();
		this.tagLeft = tagLeft;
		this.tagTop = tagTop;
	}

	public String getTagLeft() {
		return tagLeft;
	}

	public void setTagLeft(String tagLeft) {
		this.tagLeft = tagLeft;
	}

	public String getTagTop() {
		return tagTop;
	}

	public void setTagTop(String tagTop) {
		this.tagTop = tagTop;
	}

	@Override
	public String toString() {
		return "FeedTag [tagLeft=" + tagLeft + ", tagTop=" + tagTop
				+ ", toString()=" + super.toString() + "]";
	}

	public ActivityPropertyBean getActivityPropertyBean() {
		return activityPropertyBean;
	}

	public void setActivityPropertyBean(ActivityPropertyBean activityPropertyBean) {
		this.activityPropertyBean = activityPropertyBean;
	}
}
