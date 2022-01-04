package com.medolia.spring.data.custom;

import lombok.ToString;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@ToString
public class PostDTO {

    private Long pId;
    private String pTitle;
    private String pcId;
    private String pcReview;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getPcReview() {
        return pcReview;
    }

    public void setPcReview(String pcReview) {
        this.pcReview = pcReview;
    }

}
