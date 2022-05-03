package com.medolia.demo.bulletScreen;

import lombok.*;

import java.time.Duration;
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

    private Duration videoInstant;



}
