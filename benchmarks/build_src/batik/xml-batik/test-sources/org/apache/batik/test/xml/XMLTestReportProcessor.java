/*

   Copyright 2001-2003  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.test.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import java.net.URL;

import java.util.Calendar;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.batik.test.TestReport;
import org.apache.batik.test.TestReportProcessor;
import org.apache.batik.test.TestSuite;
import org.apache.batik.test.TestException;

import org.apache.batik.util.XMLConstants;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.DOMImplementation;

/**
 * This implementation of the <tt>TestReportProcessor</tt> interface
 * converts the <tt>TestReports</tt> it processes into an 
 * XML document that it outputs in a directory. The directory
 * used by the object can be configured at creation time.
 * <br />
 * The <tt>XMLTestReportProcessor</tt> can optionally notify a 
 * report consumer of the XML file it created.
 *
 * @author <a href="mailto:vhardy@apache.org">Vincent Hardy</a>
 * @version $Id: XMLTestReportProcessor.java,v 1.19 2004/08/18 07:17:06 vhardy Exp $
 */
public class XMLTestReportProcessor 
    implements TestReportProcessor,
               XTRConstants, XMLConstants {
    /**
     * An <tt>XMLReportConsumer</tt> is notified every time a 
     * new report is generated by an <tt>XMLTestReportProcessor</tt>
     */
    public static interface XMLReportConsumer {
        /**
         * Invoked when new report has been generated.
         * @param xmlReport file containing the xml report
         * @param reportDirectory base directory where any resource relative
         *        to the report processing should be stored.
         */
        public void onNewReport(File xmlReport,
                                File reportDirectory) throws Exception ;
    }

    /**
     * Error message if report directory does not exist.
     */
    public static final String ERROR_REPORT_DIRECTORY_UNUSABLE
        = "xml.XMLTestReportProcessor.error.report.directory.unusable";
								 
    /**
     * Error message if report resources directory does not exist.
     */
    public static final String ERROR_REPORT_RESOURCES_DIRECTORY_UNUSABLE
        = "xml.XMLTestReportProcessor.error.report.resources.directory.unusable";

    /**
     * Default report directory
     */
    public static final String XML_TEST_REPORT_DEFAULT_DIRECTORY 
        = Messages.formatMessage("XMLTestReportProcessor.config.xml.test.report.default.directory", null);

    /**
     * Directory where the XML report is created
     */
    public static final String XML_REPORT_DIRECTORY
        = Messages.formatMessage("XMLTestReportProcessor.xml.report.directory", null);

    /**
     * Directory where resources (e.g., images) referenced by the
     * XML report are copied.
     */
    public static final String XML_RESOURCES_DIRECTORY
        = Messages.formatMessage("XMLTestReportProcessor.xml.resources.directory", null);

    /**
     * Test report name
     */
    public static final String XML_TEST_REPORT_NAME
        = Messages.formatMessage("XMLTestReportProcessor.config.xml.test.report.name", null);

    /**
     * The XMLReportConsumer instance is notified whenever 
     * this object generates a new report.
     */
    protected XMLReportConsumer consumer;

    /**
     * String encoding the date the report was generated.
     */
    protected String reportDate;

    /**
     * Directory into which this processor puts all files and resources.
     */
    protected File reportDirectory;

    /**
     * Directory into which XML files are created
     */
    protected File xmlDirectory;

    /**
     * Directory into whichr resources refered to by XML files are created
     */
    protected File xmlResourcesDirectory;

    /**
     * Default constructor
     */
    public XMLTestReportProcessor(){
    }

    /**
     * @param reportConsumer consumer for the XML report generated
     *        by this object. May be null.
     */
    public XMLTestReportProcessor(XMLTestReportProcessor.XMLReportConsumer consumer){
        this.consumer = consumer;
    }

    /**
     * Recursively processes the input <tt>TestReport</tt> and
     * any of its children.
     */
    public void processReport(TestReport report) 
        throws TestException {

        /**
         * First, create the directories for the 
         * report and report resources 
         */
        initializeReportDirectories();
        
        try {
            
            /**
             * Create a new document and build the root
             * <testReport> element. Then, process the 
             * TestReports recursively.
             */
            DocumentBuilder docBuilder 
                = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            DOMImplementation impl 
                = docBuilder.getDOMImplementation();
            
            Document document = null;

            if(report.getTest() instanceof TestSuite){
                document = impl.createDocument(XTR_NAMESPACE_URI, 
                                               XTR_TEST_SUITE_REPORT_TAG, null);
            }
            else {
                document = impl.createDocument(XTR_NAMESPACE_URI, 
                                               XTR_TEST_REPORT_TAG, null);
            }

            Element root = document.getDocumentElement();
            
            root.setAttribute(XTR_DATE_ATTRIBUTE,
                                reportDate);

            processReport(report, root, document);
            
            File xmlReport = serializeReport(root);

            if(consumer != null){
                consumer.onNewReport(xmlReport, getReportDirectory());
            }

        } catch(Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new TestException(INTERNAL_ERROR,
                                    new Object[] { e.getClass().getName(),
                                                   e.getMessage(),
                                                   sw.toString() },
                                    e);
        }
    }

    /**
     * Checks that the input File represents a directory that
     * can be used. If the directory does not exist, this method
     * will attempt to create it.
     */
    public void checkDirectory(File dir,
                               String errorCode) 
        throws TestException {
        boolean dirOK = false;
        try{
            if(!dir.exists()){
                dirOK = dir.mkdir();
            }
            else if(dir.isDirectory()){
                dirOK = true;
            }
        }finally{
            if(!dirOK){
                throw new TestException(errorCode,
                                        new Object[] {dir.getAbsolutePath()},
                                        null);
                
            }
        }
    }

    /**
     * By default, the report directory is given by a configuration 
     * variable. Each test run will create a sub directory with 
     * the current date and time as the same. All the resources 
     * created by the report processor are then put into that
     * "dated" directory.
     */
    public void initializeReportDirectories() throws TestException {
        //
        // Base report directory
        //
        File baseReportDir = new File(XML_TEST_REPORT_DEFAULT_DIRECTORY);
        checkDirectory(baseReportDir, ERROR_REPORT_DIRECTORY_UNUSABLE);
        
        //
        // Create sub-directory name based on date and time
        //
        Calendar c = Calendar.getInstance();
        String dirName = "" + c.get(Calendar.YEAR) + "."
            + makeTwoDigits  (c.get(Calendar.MONTH)+1) + "." 
            + makeTwoDigits  (c.get(Calendar.DAY_OF_MONTH)) + "-"
            + makeTwoDigits  (c.get(Calendar.HOUR_OF_DAY)) + "h"
            + makeTwoDigits  (c.get(Calendar.MINUTE)) + "m"
            + makeTwoDigits  (c.get(Calendar.SECOND)) + "s";
        
        reportDate = dirName;
        reportDirectory = new File(baseReportDir, dirName);
        checkDirectory(reportDirectory, ERROR_REPORT_DIRECTORY_UNUSABLE);

        //
        // Now, create a sub-directory for XML files and 
        // anotherone for resources
        //
        xmlDirectory = new File(reportDirectory, XML_REPORT_DIRECTORY);
        checkDirectory(xmlDirectory, ERROR_REPORT_DIRECTORY_UNUSABLE);

        xmlResourcesDirectory = new File(xmlDirectory, XML_RESOURCES_DIRECTORY);
        checkDirectory(xmlResourcesDirectory, ERROR_REPORT_DIRECTORY_UNUSABLE);
    }

    /**
     * Forces a two digit string 
     */
    protected String makeTwoDigits(int i){
        if(i > 9){
            return "" + i;
        }
        else{
            return "0" + i;
        }
    }

    /**
     * Returns the report directory
     */
    public File getReportDirectory(){
        return reportDirectory;
    }

    /**
     * By default, the report resources directory is
     * given by a configuration variable.
     */
    public File getReportResourcesDirectory() {
        return xmlResourcesDirectory;
    }

    /**
     * Recursively processes the input <tt>TestReport</tt> adding
     * the report information to the input element.
     */
    protected void processReport(TestReport report,
                                 Element reportElement,
                                 Document reportDocument) throws IOException {
        if(report == null){
            throw new IllegalArgumentException();
        }

        reportElement.setAttribute(XTR_TEST_NAME_ATTRIBUTE,
                                   report.getTest().getName());

        String id = report.getTest().getQualifiedId();
        if( !"".equals(id) ){
            reportElement.setAttribute(XTR_ID_ATTRIBUTE,
                                       id
                                       );
        }

        String status = report.hasPassed() 
            ? XTR_PASSED_VALUE
            : XTR_FAILED_VALUE;
        
        reportElement.setAttribute(XTR_STATUS_ATTRIBUTE,
                                   status);

        String className = report.getTest().getClass().getName();

        reportElement.setAttribute(XTR_CLASS_ATTRIBUTE,
                                   className);

        if(!report.hasPassed()){
            reportElement.setAttribute(XTR_ERROR_CODE_ATTRIBUTE,
                                       report.getErrorCode());
        }
        
        TestReport.Entry[] entries = report.getDescription();
        int n = entries != null ? entries.length : 0;

        if (n>0) {
            Element descriptionElement
                = reportDocument.createElementNS(null,
                                                 XTR_DESCRIPTION_TAG);
            
            reportElement.appendChild(descriptionElement);

            for(int i=0; i<n; i++){
                processEntry(entries[i],
                             descriptionElement,
                             reportDocument);

            }
        }
    }

    protected void processEntry(TestReport.Entry entry,
                                Element descriptionElement,
                                Document reportDocument) throws IOException {

        Object value = entry.getValue();
        String key   = entry.getKey();

        if(value instanceof TestReport){
            TestReport report = (TestReport)value;
            
            Element reportElement = null;

            if(report.getTest() instanceof TestSuite){
                reportElement 
                    = reportDocument.createElementNS(XTR_NAMESPACE_URI,
                                                     XTR_TEST_SUITE_REPORT_TAG);
            }
            else{
                reportElement 
                    = reportDocument.createElementNS(XTR_NAMESPACE_URI,
                                                     XTR_TEST_REPORT_TAG);
            }

            descriptionElement.appendChild(reportElement);
            processReport((TestReport)entry.getValue(),
                          reportElement,
                          reportDocument);
        }
        else if(value instanceof URL){
            Element entryElement 
                = reportDocument.createElementNS(XTR_NAMESPACE_URI,
                                                 XTR_URI_ENTRY_TAG);

            descriptionElement.appendChild(entryElement);
            
            entryElement.setAttribute(XTR_KEY_ATTRIBUTE,
                                      key.toString());
            
            entryElement.setAttribute(XTR_VALUE_ATTRIBUTE,
                                      value.toString());

        }
        else if(value instanceof File){
            //
            // The entry is a potentially temporary File. Copy
            // the file to the repository and create a file entry
            // referencing that file copy.
            //
            File tmpFile = (File)value;

            File tmpFileCopy = createResourceFileForName(tmpFile.getName());

            copy(tmpFile, tmpFileCopy);

            Element entryElement 
                = reportDocument.createElementNS(XTR_NAMESPACE_URI,
                                                 XTR_FILE_ENTRY_TAG);

            descriptionElement.appendChild(entryElement);
            
            entryElement.setAttribute(XTR_KEY_ATTRIBUTE,
                                      key.toString());
            
            entryElement.setAttribute(XTR_VALUE_ATTRIBUTE,
                                      tmpFileCopy.toURL().toString());

        }
        else {
           
            Element entryElement 
                = reportDocument.createElementNS(XTR_NAMESPACE_URI,
                                                 XTR_GENERIC_ENTRY_TAG);

            descriptionElement.appendChild(entryElement);
            
            entryElement.setAttribute(XTR_KEY_ATTRIBUTE,
                                      key.toString());
            
            Attr a = reportDocument.createAttribute(XTR_VALUE_ATTRIBUTE);
            a.setValue(value!=null?value.toString():"null");
            entryElement.setAttributeNode(a);
        }
    }

    /**
     * Untility method. Creates a file in the resources directory
     * for the given name. If a file in that directory does not 
     * exist yet, then it is used. Otherwise, a file with the same
     * name with a digit suffix is created. For example, if "myFile.png"
     * is requested, then "myFile.png" is created or "myFile<n>.png"
     * where <n> will be one or several digits.
     */
    protected File createResourceFileForName(String fileName){
        File r = new File(xmlResourcesDirectory, fileName);
        if(!r.exists()){
            return r;
        }
        else{
            return createResourceFileForName(fileName, 1);
        } 
    }

    protected File createResourceFileForName(String fileName,
                                             int instance){
        // First, create a 'versioned' file name
        int n = fileName.lastIndexOf('.');
        String iFileName = fileName + instance;
        if(n != -1){
            iFileName = fileName.substring(0, n) + instance
                + fileName.substring(n, fileName.length());
        }

        File r = new File(xmlResourcesDirectory, iFileName);
        if(!r.exists()){
            return r;
        }
        else{
            return createResourceFileForName(fileName,
                                             instance + 1);
        }
    }

    /**
     * Utility method. Copies in to out
     */
    protected void copy(File in, File out) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(in));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(out));
        
        final byte[] b = new byte[1024];
        int n = -1;
        while( (n = is.read(b)) != -1 ){
            os.write(b, 0, n);
        }
        
        is.close();
        os.close();
    }

    /**
     * Saves the XML document into a file
     */
    protected File serializeReport(Element reportElement) throws IOException {
        //
        // First, create a new File
        //
        File reportFile = new File(xmlDirectory, 
                                   XML_TEST_REPORT_NAME);

        FileWriter fw = new FileWriter(reportFile);

        serializeElement(reportElement,
                         "",
                         fw);

        fw.close();

        return reportFile;
    }

    
    static private String EOL;

    static private String PROPERTY_LINE_SEPARATOR = "line.separator";
    static private String PROPERTY_LINE_SEPARATOR_DEFAULT = "\n";
    static {
        String  temp;
        try { 
            temp = System.getProperty (PROPERTY_LINE_SEPARATOR, 
                                       PROPERTY_LINE_SEPARATOR_DEFAULT); 
        } catch (SecurityException e) { 
            temp = PROPERTY_LINE_SEPARATOR_DEFAULT;
        }
        EOL = temp;
    }

    protected void serializeElement(Element element,
                                    String  prefix,
                                    Writer  writer) throws IOException {
        writer.write(prefix);
        writer.write(XML_OPEN_TAG_START);
        writer.write(element.getTagName());
        
        serializeAttributes(element,
                            writer);
        
        NodeList children = element.getChildNodes();
        if(children != null && children.getLength() > 0){
            writer.write(XML_OPEN_TAG_END_CHILDREN);
            writer.write(EOL);
            int n = children.getLength();
            for(int i=0; i<n; i++){
                Node child = children.item(i);
                if(child.getNodeType() == Node.ELEMENT_NODE){
                    serializeElement((Element)child,
                                     prefix + XML_TAB,
                                     writer);
                }
            }
            writer.write(prefix);
            writer.write(XML_CLOSE_TAG_START);
            writer.write(element.getTagName());
            writer.write(XML_CLOSE_TAG_END);
        }
        else{
            writer.write(XML_OPEN_TAG_END_NO_CHILDREN);
        }

        writer.write(EOL);

    }

    protected void serializeAttributes(Element element,
                                       Writer  writer) throws IOException{
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null){
            int nAttr = attributes.getLength();
            for(int i=0; i<nAttr; i++){
                Attr attr = (Attr)attributes.item(i);
                writer.write(XML_SPACE);
                writer.write(attr.getName());
                writer.write(XML_EQUAL_SIGN);
                writer.write(XML_DOUBLE_QUOTE);
                writer.write(encode(attr.getValue()));
                writer.write(XML_DOUBLE_QUOTE);
            }
        }
    }

    /**
     * Poor way of replacing '<', '>', '"', '&' and '''
     * in attribute values.
     */
    protected String encode(String attrValue){
        StringBuffer sb = new StringBuffer(attrValue);
        replace(sb, XML_CHAR_AMP, XML_ENTITY_AMP);
        replace(sb, XML_CHAR_LT, XML_ENTITY_LT);
        replace(sb, XML_CHAR_GT, XML_ENTITY_GT);
        replace(sb, XML_CHAR_QUOT, XML_ENTITY_QUOT);
        replace(sb, XML_CHAR_APOS, XML_ENTITY_APOS);
        return sb.toString();
    }

    protected void replace(StringBuffer s, 
                             char c, 
                             String r){
        String v = s.toString() + 1;
        int i = v.length();

        while( (i=v.lastIndexOf(c, --i)) != -1 ){
            s.deleteCharAt(i);
            s.insert(i, r);
        }
    }
}
