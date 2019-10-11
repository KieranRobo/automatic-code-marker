package com.kieranrobertson.project.service;

import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestResult;

public interface CodeCommander {

    boolean doesCompile();

    TestResult processTestCase(TestCase testCase);

}
