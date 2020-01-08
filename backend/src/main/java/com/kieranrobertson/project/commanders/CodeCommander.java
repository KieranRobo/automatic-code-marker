package com.kieranrobertson.project.commanders;

import com.kieranrobertson.project.model.TestCase;
import com.kieranrobertson.project.model.TestResult;

public interface CodeCommander {

    boolean doesCompile();

    TestResult processTestCase(TestCase testCase);

}
