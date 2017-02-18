package io.dods.services.parser;

import io.dods.model.properties.*;
import io.dods.services.parser.misc.DocumentService;
import io.dods.services.parser.model.ParsedData;
import io.dods.services.parser.model.ParsedValue;
import io.dods.services.parser.references.ParseReferenceService;
import io.dods.services.parser.references.ParseValueLevelService;
import io.dods.services.parser.valueParser.*;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Service
class ParserService {

    private final ParseValueLevelService parseValueLevelService;

    private final ParseReferenceService parseReferenceService;

    private final ParseApValueService parseApValueService;

    private final ParseNameService parseNameService;

    private final ParseCastTimeService parseCastTimeService;

    private final ParseImprovementChartService parseImprovementChartService;

    private final ParseCheckService parseCheckService;

    private final ParseRangeService parseRangeService;

    private final ParseTargetService parseTargetService;

    private final ParseDurationService parseDurationService;

    private final ParseCostService parseCostService;

    private final ParseAspectService parseAspectService;

    private final DocumentService documentService;

    private final ParsePrimaryAttributesService parsePrimaryAttributesService;

    private final ParsePublicationService parsePublicationServiceService;

    @Autowired
    public ParserService(ParseCastTimeService parseCastTimeService, ParseValueLevelService parseValueLevelService, ParseReferenceService parseReferenceService, ParsePrimaryAttributesService parsePrimaryAttributesService, ParseApValueService parseApValueService, ParseNameService parseNameService, ParseImprovementChartService parseImprovementChartService, ParseCheckService parseCheckService, ParseRangeService parseRangeService, DocumentService documentService, ParseAspectService parseAspectService, ParseTargetService parseTargetService, ParseDurationService parseDurationService, ParseCostService parseCostService, ParsePublicationService parsePublicationServiceService) {
        this.parseCastTimeService = parseCastTimeService;
        this.parseValueLevelService = parseValueLevelService;
        this.parseReferenceService = parseReferenceService;
        this.parsePrimaryAttributesService = parsePrimaryAttributesService;
        this.parseApValueService = parseApValueService;
        this.parseNameService = parseNameService;
        this.parseImprovementChartService = parseImprovementChartService;
        this.parseCheckService = parseCheckService;
        this.parseRangeService = parseRangeService;
        this.documentService = documentService;
        this.parseAspectService = parseAspectService;
        this.parseTargetService = parseTargetService;
        this.parseDurationService = parseDurationService;
        this.parseCostService = parseCostService;
        this.parsePublicationServiceService = parsePublicationServiceService;
    }

    private ParsedValue parseDetails(String url) throws IOException {
        ParsedValue value = new ParsedValue();

        Document document = documentService.getDocument(url, "http://www.ulisses-regelwiki.de/");

        value.setWikiUrl(url);
        value.setName(parseNameService.parseName(document));
        value.setRange(parseRangeService.parseRange(document));
        value.setCost(parseCostService.parseCost(document));

        value.setApWert(parseApValueService.parseApValue(document));
        value.setCastTime(parseCastTimeService.parseCastTime(document));
        value.setImprovementChart(parseImprovementChartService.parseImprovementChart(document));
        value.setCheck(parseCheckService.parseProbe(document));
        value.setDuration(parseDurationService.parseDuration(document));
        value.setTarget(parseTargetService.parseTargets(document));
        value.setAspect(parseAspectService.parseAspect(document));
        value.setLeiteigenschaft(parsePrimaryAttributesService.parsePrimaryAttributes(document));
        value.setPublications(parsePublicationServiceService.parsePublications(document));

        return value;
    }

    public ParsedData<CombatTechnique> parseCombatTechnique(String url, boolean isRangedCombat) {
        try {
            return parseSimple(url, value -> new CombatTechnique(
                    value.getWikiUrl(),
                    value.getPublications(),
                    value.getLeiteigenschaft(),
                    value.getImprovementChart(),
                    value.getName(),
                    isRangedCombat));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<LiturgicalChant> parseLiturgicalChant(String url) {
        try {
            return parseSimple(url, value -> new LiturgicalChant(
                    value.getWikiUrl(),
                    value.getPublications(),
                    value.getCastTime(),
                    value.getImprovementChart(),
                    value.getRange(),
                    value.getCost(),
                    value.getCheck(),
                    value.getDuration(),
                    value.getTarget(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Ritual> parseRitual(String url) {
        try {
            return parseSimple(url, value -> new Ritual(
                    value.getWikiUrl(),
                    value.getPublications(),
                    value.getCost(),
                    value.getCastTime(),
                    value.getImprovementChart(),
                    value.getRange(),
                    value.getCheck(),
                    value.getDuration(),
                    value.getTarget(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<SpecialAbility> parseSpecialAbility(String url, SpecialAbility.Group group) {
        try {
            return parseSimple(url, value -> new SpecialAbility(
                        value.getWikiUrl(),
                        value.getPublications(),
                        value.getApWert(),
                    group,
                        value.getCastTime(),
                        value.getCost(),
                        value.getCheck(),
                        value.getRange(),
                        value.getDuration(),
                        value.getImprovementChart(),
                        value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Bless> parseBless(String url) {
        try {
            return parseSimple(url, value -> new Bless(
                value.getWikiUrl(),
                value.getPublications(),
                value.getAspect(),
                value.getCost(),
                value.getRange(),
                value.getDuration(),
                value.getTarget(),
                value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Advantage> parseAdvantage(String url) {
        try {
            return parseSimple(url, value -> new Advantage(
                        value.getWikiUrl(),
                        value.getPublications(),
                        value.getApWert(),
                        value.getRange(),
                        value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Spell> parseSpell(String url) {
        try {
            return parseSimple(url, value -> new Spell(
                    value.getWikiUrl(),
                    value.getPublications(),
                    value.getCost(),
                    value.getImprovementChart(),
                    value.getCheck(),
                    value.getRange(),
                    value.getDuration(),
                    value.getCastTime(),
                    value.getTarget(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Cantrip> parseCantrip(String url) {
        try {
            return parseSimple(url, value -> new Cantrip(
                    value.getWikiUrl(),
                    value.getPublications(),
                    value.getRange(),
                    value.getTarget(),
                    value.getDuration(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Ceremony> parseCeremony(String url) {
        try {
            return parseSimple(url, value -> new Ceremony(
                    value.getWikiUrl(),
                    value.getPublications(),
                    value.getCost(),
                    value.getCheck(),
                    value.getRange(),
                    value.getCastTime(),
                    value.getDuration(),
                    value.getImprovementChart(),
                    value.getTarget(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    private interface ParseCallback<T extends Property> {
        T parse(ParsedValue parsedValue);
    }

    public <T extends Property> ParsedData<T> parseSimple(String url, ParseCallback<T> parseCallback) throws IOException {
        ParsedValue parsedValue = parseDetails(url);

        List<ParsedValue> parsedValues = new ArrayList<>();
        parsedValues.addAll(parseValueLevelService.checkForLevels(parsedValue));
        parsedValues.addAll(parseReferenceService.checkForSubcategories(parsedValue));

        List<T> collect = parsedValues.stream()
                .map(parseCallback::parse)
                .collect(Collectors.toList());

        parseValueLevelService.setRealName(parsedValue);

        T data = parseCallback.parse(parsedValue);

        return new ParsedData<>(data, collect);
    }
}
