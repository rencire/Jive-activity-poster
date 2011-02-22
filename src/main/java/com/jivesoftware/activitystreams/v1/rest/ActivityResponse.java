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
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@XmlType(name="")
@XmlRootElement(name = "Activity")
public class ActivityResponse {

    private String responseID;
    private List<UserActivity> userActivity;

    @JsonCreator
    public ActivityResponse(@JsonProperty("userActivity") List<UserActivity> userActivity, @JsonProperty("responseID") String responseID) {
        this.userActivity = userActivity;
        this.responseID = responseID;
    }

    @JsonProperty("userActivity")
    public List<UserActivity> getUserActivity() {
        return userActivity;
    }

    @JsonProperty("responseID")
    public String getResponseID() {
        return responseID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityResponse response = (ActivityResponse) o;

        if (responseID != null ? !responseID.equals(response.responseID) : response.responseID != null) return false;
        if (userActivity != null ? !userActivity.equals(response.userActivity) : response.userActivity != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = responseID != null ? responseID.hashCode() : 0;
        result = 31 * result + (userActivity != null ? userActivity.hashCode() : 0);
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
