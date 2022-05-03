package com.medolia.demo.bulletScreen.validate;

import com.medolia.demo.bulletScreen.BulletScreen;
import com.medolia.demo.bulletScreen.User;

import java.util.List;

/**
 * @author lbli
 * @date 2022/5/3
 */
public class ContentValidateEleFactory {

    private static abstract class BaseContentValidator implements ContentValidElement {

        @Override
        public ValidateResponse validate(BulletScreen content) {
            return ValidateResponse.fail("default fail");
        }

        @Override
        public int order() {
            return Integer.MIN_VALUE;
        }

        @Override
        public boolean isValid() {
            return false;
        }
    }

    private static class PoliticContentValidator extends BaseContentValidator {

    }

    private static class IntelligentContentValidator extends BaseContentValidator {
        private int level;

        public IntelligentContentValidator(int level) {
            this.level = level;
        }
    }

    private static class RegexContentValidator extends BaseContentValidator {

        private String pattern;

        public RegexContentValidator(String pattern) {
            this.pattern = pattern;
        }
    }

    private static class UserTargetContentValidator extends BaseContentValidator {
        private List<User> blackList;

        public UserTargetContentValidator(List<User> blackList) {
            this.blackList = blackList;
        }
    }

}
