package edu.umd.cs.findbugs.falsepositives;

import edu.umd.cs.findbugs.BugCollection;
import edu.umd.cs.findbugs.test.SpotBugsRule;
import edu.umd.cs.findbugs.util.HtmlReport;
import org.junit.Rule;
import org.junit.Test;

import java.nio.file.Paths;

public class Issue1155Test {
    @Rule
    public SpotBugsRule spotbugs = new SpotBugsRule();

    @Test
    public void test(){
        BugCollection bugCollection = spotbugs.performAnalysis(
                Paths.get("../spotbugsTestCases/build/classes/java/main/ghFalsePositives/Issue1155.class"));
        HtmlReport htmlReport = new HtmlReport();
        try {
            htmlReport.generateHtmlReport(bugCollection);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
