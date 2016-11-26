/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploteus.tr.client;

import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author makif
 */
public class PloteusTRClientTest {
    
    PloteusTRClient clientInstance = null;
    public PloteusTRClientTest() {
    }
    
    @Before
    public void setUp() {
        clientInstance = new PloteusTRClient("USER_NAME", "PASSWORD");
    }

    /**
     * Test of uploadLearningOpportunitiesXml method, of class PloteusTRClient.
     */
    @Test
    public void testUploadLearningOpportunitiesXml() throws Exception {
        System.out.println("uploadLearningOpportunitiesXml");
        String result = clientInstance.uploadLearningOpportunitiesXml("<LearningOpportunities />");
        
        assertThat(result, containsString("<IsSuccess>false</IsSuccess>"));
    }

    /**
     * Test of uploadQualificationsXml method, of class PloteusTRClient.
     */
    @Test
    public void testUploadQualificationsXml() throws Exception {
        System.out.println("uploadQualificationsXml");
        String result = clientInstance.uploadQualificationsXml("<Qualifications />");
        assertThat(result, containsString("<IsSuccess>false</IsSuccess>"));
    }

    /**
     * Test of getXmlStatus method, of class PloteusTRClient.
     */
    @Test
    public void testGetXmlStatus() throws Exception {
        System.out.println("getXmlStatus");
        
        String result = clientInstance.getXmlStatus("invalidRequestId");
        assertThat(result, containsString("<IsSuccess>false</IsSuccess>"));
    }
    
}
