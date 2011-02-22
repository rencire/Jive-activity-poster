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
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

/**
 * Represents an activity stream
 * <p>
 * See: <a href="http://activitystrea.ms/head/activity-api.html#anchor11">Activity Streams API</a>
 */
@XmlRootElement(name="")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActivityStreamRepresentation implements Serializable {

    /**
     * identifier for the stream, must not be null
     */
    private String id;

    /**
     * Title for the stream, may be null
     */
    private String title;

    /**
     * language of the stream (format not specified in the spec)
     */
    private String language;

    /**
     * Must not be null, items should be in reverse chronological order.
     */
    private List<ActivityRepresentation> activities;


    @JsonProperty("id")    
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("lang")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("lang")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("items")
    public List<ActivityRepresentation> getActivities() {
        return activities;
    }

    @JsonProperty("items")
    public void setActivities(List<ActivityRepresentation> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActivityStreamRepresentation that = (ActivityStreamRepresentation) o;


        if (!id.equals(that.id)) {
            return false;
        }
        if (!activities.equals(that.activities)) {
            return false;
        }
        if (language != null ? !language.equals(that.language) : that.language != null) {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + activities.hashCode();
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
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
