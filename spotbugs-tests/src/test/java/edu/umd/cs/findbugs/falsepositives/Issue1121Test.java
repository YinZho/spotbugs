package edu.umd.cs.findbugs.falsepositives;

import edu.umd.cs.findbugs.*;
import edu.umd.cs.findbugs.charsets.UTF8;
import edu.umd.cs.findbugs.util.HtmlReport;
import org.dom4j.Document;
import org.dom4j.io.DocumentSource;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;

import org.junit.Rule;


import edu.umd.cs.findbugs.BugCollection;
import edu.umd.cs.findbugs.test.SpotBugsRule;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Issue1121Test {
    @Rule
    public SpotBugsRule spotbugs = new SpotBugsRule();

    @Test
    public void test(){
        BugCollection bugCollection = spotbugs.performAnalysis(
                Paths.get("../spotbugsTestCases/build/classes/java/main/ghFalsePositives/Issue1121.class"));
        HtmlReport htmlReport = new HtmlReport();
        try {
            htmlReport.generateHtmlReport(bugCollection);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
