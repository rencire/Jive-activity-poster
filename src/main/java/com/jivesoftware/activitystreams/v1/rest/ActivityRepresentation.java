/*
 * $
 * $
 *
 * Copyright (C) 1999-2010 Jive Software. All rights reserved.
 *
 * This software is the proprietary information of Jive Software. Use is subject to license terms.
 */
package com.jivesoftware.activitystreams.v1.rest;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an incoming activity
 * <p>
 * See: <a href="http://activitystrea.ms/head/activity-api.html#anchor9">Activity Streams API</a>
 */
@XmlRootElement(name="")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActivityRepresentation implements Serializable {

    /**
     * A unique id for the activity entry. It is recommended that this be a
     * URI, but will that work?
     */
    private String id;

    /**
     * An icon URL for this entry.
     */
    private String streamFaviconUrl;

    /**
     * Formatted time string, per RFC-4287
     */
    private Date postedTime;

    /**
     * An object representation describing the user
     */
    private ObjectRepresentation actor;

    /**
     * One or more verbs in order from most specific to most general. It is recommended that this be a URI, but will that work?
     */
    private List<String> verb;

    /**
     * The object being acted upon. This value is required.
     */
    private ObjectRepresentation object;

    /**
     * The target, or "to" clause of the activity. This is defined as optional,
     * so we will assume the "user" as the target if this is not preset.
     */
    private ObjectRepresentation target;

    /**
     * The app originating the activity. This is defined as optional, so we
     * will assume the "user" as the generator if this is not preset.
     */
    private ObjectRepresentation generator;

    /**
     * It is recommended that this value be HTML, but will that really work?
     */
    private String title;

    /**
     * It is recommended that this value be HTML, but will that really work?
     */
    private String body;

    private Map<String, Object> unmappedValues = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(String ID) {
        this.id = ID;
    }

    @JsonProperty
    public String getStreamFaviconUrl() {
        return streamFaviconUrl;
    }

    @JsonProperty
    public void setStreamFaviconUrl(String streamFaviconUrl) {
        this.streamFaviconUrl = streamFaviconUrl;
    }

    @JsonProperty
    @JsonSerialize(using=AtomDateSerializer.class)
    public Date getPostedTime() {
        return postedTime;
    }

    @JsonProperty
    @JsonDeserialize(using=AtomDateDeserializer.class)
    public void setPostedTime(Date postedTime) {
        this.postedTime = postedTime;
    }

    @JsonProperty
    public ObjectRepresentation getActor() {
        return actor;
    }

    @JsonProperty
    public void setActor(ObjectRepresentation actor) {
        this.actor = actor;
    }

    @JsonProperty
    public List<String> getVerb() {
        return verb;
    }

    /**
     * Verb may be a list of verbs from most specific to least specific. If no verb is specified the verb 'post'
     * is assumed
     *
     * @param verb a list of verbs from most specific to least specific 
     */
    @JsonProperty
    @JsonDeserialize(using=StringOrStringArrayDeserializer.class)
    public void setVerb(List<String> verb) {
        this.verb = verb;
    }

    @JsonProperty
    public ObjectRepresentation getObject() {
        return object;
    }

    @JsonProperty
    public void setObject(ObjectRepresentation object) {
        this.object = object;
    }

    @JsonProperty
    public ObjectRepresentation getTarget() {
        return target;
    }

    @JsonProperty
    public void setTarget(ObjectRepresentation target) {
        this.target = target;
    }

    @JsonProperty
    public ObjectRepresentation getGenerator() {
        return generator;
    }

    @JsonProperty
    public void setGenerator(ObjectRepresentation generator) {
        this.generator = generator;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public String getBody() {
        return body;
    }

    @JsonProperty
    public void setBody(String body) {
        this.body = body;
    }

    // not a getter because of http://jira.codehaus.org/browse/JACKSON-384
    @JsonAnyGetter
    public Map<String, Object> unmappedValues() {
        return unmappedValues;
    }

    @JsonAnySetter
    public void setUnmappedValue(String key, Object value) {
        unmappedValues.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActivityRepresentation that = (ActivityRepresentation) o;

        if (actor != null ? !actor.equals(that.actor) : that.actor != null) {
            return false;
        }
        if (body != null ? !body.equals(that.body) : that.body != null) {
            return false;
        }
        if (generator != null ? !generator.equals(that.generator) : that.generator != null) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (object != null ? !object.equals(that.object) : that.object != null) {
            return false;
        }
        if (postedTime != null ? !postedTime.equals(that.postedTime) : that.postedTime != null) {
            return false;
        }
        if (streamFaviconUrl != null ? !streamFaviconUrl.equals(that.streamFaviconUrl) : that.streamFaviconUrl != null) {
            return false;
        }
        if (target != null ? !target.equals(that.target) : that.target != null) {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        if (unmappedValues != null ? !unmappedValues.equals(that.unmappedValues) : that.unmappedValues != null) {
            return false;
        }
        if (verb != null ? !verb.equals(that.verb) : that.verb != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (streamFaviconUrl != null ? streamFaviconUrl.hashCode() : 0);
        result = 31 * result + (postedTime != null ? postedTime.hashCode() : 0);
        result = 31 * result + (actor != null ? actor.hashCode() : 0);
        result = 31 * result + (verb != null ? verb.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (generator != null ? generator.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (unmappedValues != null ? unmappedValues.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            JsonGenerator jsonGenerator = mapper.getJsonFactory().createJsonGenerator(sw);
            jsonGenerator.useDefaultPrettyPrinter();
            mapper.writeValue(jsonGenerator, this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
