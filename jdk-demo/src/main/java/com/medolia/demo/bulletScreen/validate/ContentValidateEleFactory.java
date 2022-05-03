package com.medolia.demo.bulletScreen.validate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.medolia.demo.bulletScreen.BulletScreen;
import com.medolia.demo.bulletScreen.User;
import lombok.ToString;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lbli
 * @date 2022/5/3
 */
public class ContentValidateEleFactory {

    public static List<ContentValidElement> contentValidElementList() {
        List<ContentValidElement> result = Lists.newArrayList();

        result.add(new PoliticContentValidator());
        result.add(new IntelligentContentValidator(7));
        result.add(new RegexContentValidator(
                ImmutableList.of(".s", "[arena]"/*, ".*"*/)));
        result.add(new UserTargetContentValidator(ImmutableList.of(new User(), new User(), new User())));

        return result;
    }

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

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }

    private static class PoliticContentValidator extends BaseContentValidator {
        @Override
        public int order() {
            return 100;
        }

        @Override
        public ValidateResponse validate(BulletScreen content) {
            // usually remote call
            return ValidateResponse.success();
        }

        @Override
        public boolean isValid() {
            return true;
        }
    }

    private static class IntelligentContentValidator extends BaseContentValidator {
        private final int level;

        public IntelligentContentValidator(int level) {
            this.level = level;
        }

        @Override
        public ValidateResponse validate(BulletScreen content) {
            // usually remote call
            return ValidateResponse.success();
        }

        @Override
        public int order() {
            return 20;
        }

        @Override
        public boolean isValid() {
            return true;
        }
    }

    private static class RegexContentValidator extends BaseContentValidator {

        private final List<Pattern> patternList;

        public RegexContentValidator(List<String> patternList) {
            this.patternList = patternList.stream().map(Pattern::compile).collect(Collectors.toList());
        }

        @Override
        public ValidateResponse validate(BulletScreen content) {
            if (patternList.stream().anyMatch(pattern -> pattern.matcher(content.getOrgContent()).matches())) {
                return ValidateResponse.fail("regex interception");
            }

            return ValidateResponse.success();
        }

        @Override
        public int order() {
            return 30;
        }

        @Override
        public boolean isValid() {
            return true;
        }
    }

    private static class UserTargetContentValidator extends BaseContentValidator {
        private final List<User> blackList;

        public UserTargetContentValidator(List<User> blackList) {
            this.blackList = blackList;
        }

        @Override
        public ValidateResponse validate(BulletScreen content) {

            if (blackList.stream().anyMatch(content.getUser()::equals)) {
                return ValidateResponse.fail("user black list interception");
            }

            return ValidateResponse.success();
        }

        @Override
        public int order() {
            return 40;
        }

        @Override
        public boolean isValid() {
            return true;
        }
    }

}
