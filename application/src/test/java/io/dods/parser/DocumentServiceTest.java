package io.dods.parser;

import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class DocumentServiceTest {

    @Autowired
    DocumentService service;

    @Before
    public void setUp() throws Exception {
        getFile().delete();
    }

    @After
    public void tearDown() throws Exception {
        getFile().delete();
    }

    @Test
    public void parseTest() throws IOException {
        String currentFolder = System.getProperty("user.dir");
        String localFileName = "http://www.ulisses-regelwiki.de/".replace(":", "");
        if (!localFileName.endsWith(".html")) localFileName += ".html";
        File file = new File(currentFolder + "/_buffer", localFileName);

        assertFalse(file.exists());

        Document remoteFile = service.getDocument("http://www.ulisses-regelwiki.de/", "http://www.ulisses-regelwiki.de/");
        assertNotNull(remoteFile);

        assertTrue(file.exists());

        Document localFile = service.getDocument("http://www.ulisses-regelwiki.de/", "http://www.ulisses-regelwiki.de/");
        assertNotNull(localFile);
    }

    private File getFile() {
        String currentFolder = System.getProperty("user.dir");
        String localFileName = "http://www.ulisses-regelwiki.de/".replace(":", "");
        if (!localFileName.endsWith(".html")) localFileName += ".html";
        return new File(currentFolder + "/_buffer", localFileName);
    }

}
