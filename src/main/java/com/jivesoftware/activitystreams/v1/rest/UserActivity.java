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
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

@XmlRootElement(name = "UserActivity")
public class UserActivity implements Serializable {

    private long activityID;
    private String jiveInstanceID;
    private long userID;
    private String appUUID;
    private int targetType; // InstanceAppUserObject.TargetType.INBOX == 0 ? 1
    private ActivityRepresentation activity;

    @JsonCreator
    public UserActivity(@JsonProperty("jiveInstanceID") String jiveInstanceID,
                        @JsonProperty("userID") long userID,
                        @JsonProperty("appUUID") String appUUID,
                        @JsonProperty("targetType") int targetType,
                        @JsonProperty("activity") ActivityRepresentation activity)
    {
        this.jiveInstanceID = jiveInstanceID;
        this.userID = userID;
        this.appUUID = appUUID;
        this.targetType = targetType;
        this.activity = activity;
    }

    @JsonIgnore
    public long getActivityID() {
        return activityID;
    }

    public void setActivityID(long activityID) {
        this.activityID = activityID;
    }

    @JsonProperty("jiveInstanceID")
    public String getJiveInstanceID() {
        return jiveInstanceID;
    }

    @JsonProperty("userID")
    public long getUserID() {
        return userID;
    }

    @JsonProperty("appUUID")
    public String getAppUUID() {
        return appUUID;
    }

    @JsonProperty("targetType")
    public int getTargetType() {
        return targetType;
    }

    @JsonProperty("activity")
    public ActivityRepresentation getActivity() {
        return activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserActivity that = (UserActivity) o;

        if (activityID != that.activityID) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (activityID ^ (activityID >>> 32));
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
