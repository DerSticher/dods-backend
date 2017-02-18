package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.CheckImpl;
import io.dods.services.properties.check.Check;
import io.dods.services.properties.check.CheckService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseCheckService {

    private static final Pattern PATTERN = Pattern.compile("Probe ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?(..)/(..)/(..)");

    private final Map<String, Integer> idMap = new HashMap<>();

    public ParseCheckService() {
        idMap.put("MU", 1);
        idMap.put("KL", 2);
        idMap.put("IN", 3);
        idMap.put("CH", 4);
        idMap.put("FF", 5);
        idMap.put("GE", 6);
        idMap.put("KO", 7);
        idMap.put("KK", 8);
    }

    @Autowired
    private CheckService checkService;

    public CheckImpl parseProbe(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            int id1 = idMap.get(matcher.group(1));
            int id2 = idMap.get(matcher.group(2));
            int id3 = idMap.get(matcher.group(3));

            ParsedCheck parsedCheck = new ParsedCheck(id1, id2, id3);

            return checkService.create(parsedCheck);
        }

        return null;
    }

    public class ParsedCheck implements Check {

        private final int teilprobe1Id;
        private final int teilprobe2Id;
        private final int teilprobe3Id;

        public ParsedCheck(int teilprobe1Id, int teilprobe2Id, int teilprobe3Id) {
            this.teilprobe1Id = teilprobe1Id;
            this.teilprobe2Id = teilprobe2Id;
            this.teilprobe3Id = teilprobe3Id;
        }

        @Override
        public long getCheck1Id() {
            return teilprobe1Id;
        }

        @Override
        public long getCheck2Id() {
            return teilprobe2Id;
        }

        @Override
        public long getCheck3Id() {
            return teilprobe3Id;
        }
    }

}
