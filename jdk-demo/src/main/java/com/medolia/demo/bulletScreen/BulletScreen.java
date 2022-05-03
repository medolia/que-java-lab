package com.medolia.demo.bulletScreen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author lbli
 * @date 2022/5/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulletScreen {

    private String id;

    private User user;

    private LocalDateTime sendTime;

    private Boolean isValid;

    private String orgContent;

    private Instant videoInstant;

}
