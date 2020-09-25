package edu.umd.cs.findbugs.falsepositives;

import edu.umd.cs.findbugs.BugCollection;
import org.junit.Test;

import java.nio.file.Paths;

import org.junit.Rule;


import edu.umd.cs.findbugs.BugCollection;
import edu.umd.cs.findbugs.test.SpotBugsRule;

public class Issue1121Test {
    @Rule
    public SpotBugsRule spotbugs = new SpotBugsRule();

    @Test
    public void test() {
        BugCollection bugCollection = spotbugs.performAnalysis(
                Paths.get("../spotbugsTestCases/build/classes/java/main/ghFalsePositives/Issue1121.class"));
    }
}
