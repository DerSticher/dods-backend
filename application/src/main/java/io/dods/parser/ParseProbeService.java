package io.dods.parser;

import io.dods.attributeService.probe.HatProbeApi;
import io.dods.attributeService.probe.ProbeService;
import io.dods.model.attribute.misc.Probe;
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
class ParseProbeService {

    private static final Pattern PATTERN = Pattern.compile("Probe:</strong> (..)/(..)/(..)");

    private final Map<String, Integer> idMap = new HashMap<>();

    public ParseProbeService() {
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
    private ProbeService probeService;

    public Probe parseProbe(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            int id1 = idMap.get(matcher.group(1));
            int id2 = idMap.get(matcher.group(2));
            int id3 = idMap.get(matcher.group(3));

            ParsedProbe parsedProbe = new ParsedProbe(id1, id2, id3);

            return probeService.create(parsedProbe);
        }

        return null;
    }

    public class ParsedProbe implements HatProbeApi {

        private final int teilprobe1Id;
        private final int teilprobe2Id;
        private final int teilprobe3Id;

        public ParsedProbe(int teilprobe1Id, int teilprobe2Id, int teilprobe3Id) {
            this.teilprobe1Id = teilprobe1Id;
            this.teilprobe2Id = teilprobe2Id;
            this.teilprobe3Id = teilprobe3Id;
        }

        @Override
        public int getTeilprobe1Id() {
            return teilprobe1Id;
        }

        @Override
        public int getTeilprobe2Id() {
            return teilprobe2Id;
        }

        @Override
        public int getTeilprobe3Id() {
            return teilprobe3Id;
        }
    }

}
