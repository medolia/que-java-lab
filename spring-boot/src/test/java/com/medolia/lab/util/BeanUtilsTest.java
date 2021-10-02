package com.medolia.lab.util;

import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author lbli
 * @date 2021/10/2
 */
public class BeanUtilsTest {
    @Test
    void copyProps() {
        Course course = new Course();
        course.setName("Computer Science");
        course.setCodes(Lists.newArrayList("CS"));
        course.setEnrolledStudent("ST-1", new Student());

        CourseEntity courseEntity = new CourseEntity();
        BeanUtils.copyProperties(course, courseEntity);
    }

    @Data
    static class Course {
        private String name;
        private List<String> codes;
        private Map<String, Student> enrolledStudent = new HashMap<>();

        public void setEnrolledStudent(String s, Student student) {
            this.enrolledStudent.putIfAbsent(s, student);
        }

        //  standard getters/setters
    }

    @Data
    static class Student {
        private String name;

        //  standard getters/setters
    }

    @Data
    static class CourseEntity {
        private String name;
        private List<String> codes;
        private Map<String, Student> students = new HashMap<>();

        //  standard getters/setters
    }
}
