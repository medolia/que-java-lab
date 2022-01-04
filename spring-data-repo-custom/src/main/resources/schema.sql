DROP TABLE IF EXISTS post_comment;
DROP TABLE IF EXISTS post;

CREATE TABLE post
(
    id         BIGINT       NOT NULL,
    title      VARCHAR(255) NULL,
    created_on datetime     NULL,
    created_by VARCHAR(255) NULL,
    updated_on datetime     NULL,
    updated_by VARCHAR(255) NULL,
    version    INT          NULL,
    CONSTRAINT pk_post PRIMARY KEY (id)
);

CREATE TABLE post_comment
(
    id      BIGINT       NOT NULL,
    post_id BIGINT       NULL,
    review  VARCHAR(255) NULL,
    CONSTRAINT pk_post_comment PRIMARY KEY (id)
);

ALTER TABLE post_comment
    ADD CONSTRAINT FK_POST_COMMENT_ON_POST FOREIGN KEY (post_id) REFERENCES post (id);

INSERT INTO post
values (1, 'High-Performance Java Persistence eBook has been released!', now(), 'medolia', now(), 'medolia', 1),
       (2, 'Hypersistence Optimizer', now(), 'medolia', now(), 'medolia', 1)
;

Insert Into POST_COMMENT
values (1, 1, ' Best book on JPA and Hibernate! '),
       (2, 1, 'A must-read for every Java developer!'),
       (3, 2, 'It''s like pair programming with Vlad!')
;





