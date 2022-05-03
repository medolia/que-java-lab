package com.medolia.demo.guava;


import com.google.common.base.Converter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

/**
 * @author lbli
 * @date 2022/5/3
 */
@Slf4j
public class ConverterDemo {

    public static void main(String[] args) {
        FrontEndDTO frontEndDTO = FrontEndDTO.builder()
                .id(129709290234L)
                .content("test content")
                .build();

        FrontBackDTOConverter converter = new FrontBackDTOConverter();
        BackEndDTO backEndDTO = converter.doForward(frontEndDTO);
        FrontEndDTO frontEndDTO1 = converter.doBackward(backEndDTO);

        log.info("equals: {}", frontEndDTO.equals(frontEndDTO1));
    }

    private static class FrontBackDTOConverter extends Converter<FrontEndDTO, BackEndDTO> {
        @Override
        protected BackEndDTO doForward(FrontEndDTO frontEndDTO) {

            BackEndDTO result = new BackEndDTO();
            result.setToken(
                    Base64Utils.encodeToString(
                            ("" + frontEndDTO.getId()).getBytes(StandardCharsets.UTF_8)));
            result.setMainContent(frontEndDTO.getContent());
            return result;
        }

        @Override
        protected FrontEndDTO doBackward(BackEndDTO backEndDTO) {

            FrontEndDTO result = new FrontEndDTO();
            String s = new String(Base64Utils.decodeFromString(backEndDTO.getToken()));
            result.setId(Long.parseLong(s));
            result.setContent(backEndDTO.getMainContent());
            return result;
        }

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class FrontEndDTO {
        private Long id;
        private String content;
    }

    @Data
    private static class BackEndDTO {
        private String token;
        private String mainContent;
    }

}
