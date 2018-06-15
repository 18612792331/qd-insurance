package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 社区标签中的最新一张照片
 * @author lichao
 *
 */
@XStreamAlias(value="feedCommunityTag")
public class FeedCommunityTag extends Tag{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1565741720452629177L;

	/**
	 *   标签包含图片的数量	
	 */
	private int tagImageCount;
	
	/**
	 *  标签中的图片		
	 */
	private FeedImage lastImage;
	
	public FeedCommunityTag() {

	}

	public FeedCommunityTag(String tagId, String tagName, Integer tagCount,
			int tagImageCount, FeedImage lastImage) {
		super(tagId, tagName, tagCount);
		this.tagImageCount = tagImageCount;
		this.lastImage = lastImage;
	}

	public FeedCommunityTag(int tagImageCount, FeedImage lastImage) {
		super();
		this.tagImageCount = tagImageCount;
		this.lastImage = lastImage;
	}

	public int getTagImageCount() {
		return tagImageCount;
	}

	public void setTagImageCount(int tagImageCount) {
		this.tagImageCount = tagImageCount;
	}

	public FeedImage getLastImage() {
		return lastImage;
	}

	public void setLastImage(FeedImage lastImage) {
		this.lastImage = lastImage;
	}

	@Override
	public String toString() {
		return "OwnerTag [tagImageCount=" + tagImageCount + ", lastImage="
				+ lastImage + ", toString()=" + super.toString() + "]";
	}
	
}
