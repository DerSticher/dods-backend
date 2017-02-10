package io.dods.parser;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author Richard Gottschalk
 */
@Service
public class DocumentService {

    public Document getDocument(@NotNull String url, @NotNull String baseUrl) throws IOException {
        String currentFolder = System.getProperty("user.dir");
        String localFileName = url.replace(":", "").replace("?", "_");
        if (!localFileName.endsWith(".html")) localFileName += ".html";
        File bufferedFile = new File(currentFolder + "/_buffer", localFileName);

        if (bufferedFile.exists() && !localFileName.contains("?")) {
            System.out.println("Parsing local " + url);
            return Jsoup.parse(bufferedFile, "UTF-8", baseUrl);
        } else {
            System.out.println("Parsing remote " + url);
            String decode = URLDecoder.decode(url, "UTF-8");
            Document document = Jsoup.connect(decode).get();

            bufferedFile.getParentFile().mkdirs();

            FileWriter fileWriter = new FileWriter(bufferedFile);
            fileWriter.write(document.toString());
            fileWriter.close();

            try {
                // sleeping to reduce traffic and avoid HTTP 429
                Thread.sleep(getSleep());
            } catch (InterruptedException ignore) {}

            return document;
        }
    }

    public long getSleep() {
        return (long) (Math.random() * 1000 * 20 + 5 * 1000);
    }

}
