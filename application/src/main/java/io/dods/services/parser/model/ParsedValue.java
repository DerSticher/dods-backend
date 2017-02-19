package io.dods.services.parser.model;

import io.dods.model.properties.Attribute;
import io.dods.model.properties.misc.*;
import io.dods.model.publication.Publication;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
public class ParsedValue implements Serializable {

    private String wikiUrl;
    private String name;
    private String regel;

    private CastTime castTime;
    private ImprovementChart improvementChart;
    private Cost cost;
    private Check check;
    private Range range;
    private Duration duration;
    private List<Target> target;
    private List<Aspect> aspect;
    private int apWert;
    private List<Attribute> primaryAttributes;
    private List<Publication> publications;

    public ParsedValue copy() {
        byte[] serialize = SerializationUtils.serialize(this);
        return (ParsedValue) SerializationUtils.deserialize(serialize);
    }

    public String getRegel() {
        return regel;
    }

    public void setRegel(String regel) {
        this.regel = regel;
    }

    public CastTime getCastTime() {
        return castTime;
    }

    public void setCastTime(CastTime castTime) {
        this.castTime = castTime;
    }

    public ImprovementChart getImprovementChart() {
        return improvementChart;
    }

    public void setImprovementChart(ImprovementChart improvementChart) {
        this.improvementChart = improvementChart;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Target> getTarget() {
        return target;
    }

    public void setTarget(List<Target> target) {
        this.target = target;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAspect(List<Aspect> aspect) {
        this.aspect = aspect;
    }

    public List<Aspect> getAspect() {
        return aspect;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public void setApWert(int apWert) {
        this.apWert = apWert;
    }

    public int getApWert() {
        return apWert;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public void setPrimaryAttributes(List<Attribute> primaryAttributes) {
        this.primaryAttributes = primaryAttributes;
    }

    public List<Attribute> getPrimaryAttributes() {
        return primaryAttributes;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Publication> getPublications() {
        return publications;
    }
}
