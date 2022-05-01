package com.medolia.spring.data.custom;

import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.longValue;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class PostCommentDTO {

    private Long id;

    private String review;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
