package com.medolia.spring.data.custom;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
@Sql("/schema.sql")
@Slf4j
class CustomPostRepositoryImplTest extends BaseIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PostRepository postRepository;

    @Test
    void testPostRepo() {
        List<Post> all = postRepository.findAll();
        assertEquals(all.size(), 2);
    }

    @Test
    void testJPA() {
        List<PostDTO> postDTOList = jdbcTemplate.query("""
                SELECT p.id AS p_id,
                       p.title AS p_title,
                       pc.id AS pc_id,
                       pc.review AS pc_review
                FROM post p
                JOIN post_comment pc ON p.id = pc.post_id
                ORDER BY pc.id
                """, BeanPropertyRowMapper.newInstance(PostDTO.class));

        assertEquals(postDTOList.size(), 3);
        log.info("post dto list: {}", postDTOList);
    }

}