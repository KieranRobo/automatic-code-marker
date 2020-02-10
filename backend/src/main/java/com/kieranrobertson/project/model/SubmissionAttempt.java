package com.kieranrobertson.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="student_submissions")
@NoArgsConstructor
public class SubmissionAttempt {

    public SubmissionAttempt(int studentId, int challengeId, int testsPassed, String code) {
        this.key = new Key(studentId, challengeId);
        this.testsPassed = testsPassed;
        this.code = code;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Key implements Serializable {
        private int studentId;
        private int challengeId;
    }

    @Id
    private Key key;

    @Column(name="tests_passed")
    @Getter
    private int testsPassed;

    @Column(name="code")
    @Getter
    private String code;

    private int getStudentId() {
        return key.getStudentId();
    }

    private int getChallengeId() {
        return key.getChallengeId();
    }


}
