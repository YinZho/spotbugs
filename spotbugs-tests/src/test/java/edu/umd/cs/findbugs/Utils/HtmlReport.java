package edu.umd.cs.findbugs.util;

import edu.umd.cs.findbugs.BugCollection;
import edu.umd.cs.findbugs.FindBugs;
import edu.umd.cs.findbugs.HTMLBugReporter;
import edu.umd.cs.findbugs.charsets.UTF8;
import org.dom4j.Document;
import org.dom4j.io.DocumentSource;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;

public class HtmlReport {
    public void generateHtmlReport(BugCollection bugCollection) throws IOException, TransformerConfigurationException, TransformerException {
        bugCollection.setWithMessages(true);
        // Decorate the XML with messages to display
        Document document = bugCollection.toDocument();
        // new AddMessages(bugCollection, document).execute();

        // Get the stylesheet as a StreamSource.
        // First, try to load the stylesheet from the filesystem.
        // If that fails, try loading it as a resource.
        InputStream xslInputStream = getStylesheetStream("default.xsl");
        StreamSource xsl = new StreamSource(xslInputStream);
        xsl.setSystemId("default.xsl");

        // Create a transformer using the stylesheet
        TransformerFactory factory = TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl", null);
        Transformer transformer = factory.newTransformer(xsl);

        // Source document is the XML generated from the BugCollection
        DocumentSource source = new DocumentSource(document);

        // Write result to output stream
        File file = new File("/Users/zhouying/Documents/report.html");
        PrintWriter outputStream = UTF8.printWriter(new PrintStream(file), true);
        StreamResult result = new StreamResult(outputStream);

        // Do the transformation
        transformer.transform(source, result);
    }


    private static InputStream getStylesheetStream(String stylesheet) throws IOException {
        if (FindBugs.DEBUG) {
            System.out.println("Attempting to load stylesheet " + stylesheet);
        }
        try {
            URL u = new URL(stylesheet);
            return u.openStream();
        } catch (Exception e) {
            assert true; // ignore it
        }
        try {
            return new BufferedInputStream(new FileInputStream(stylesheet));
        } catch (Exception fnfe) {
            assert true; // ignore it
        }
        InputStream xslInputStream = HTMLBugReporter.class.getResourceAsStream("/" + stylesheet);
        if (xslInputStream == null) {
            throw new IOException("Could not load HTML generation stylesheet " + stylesheet);
        }
        return xslInputStream;
    }
}
