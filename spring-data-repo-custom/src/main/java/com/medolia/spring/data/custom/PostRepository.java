package com.medolia.spring.data.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
