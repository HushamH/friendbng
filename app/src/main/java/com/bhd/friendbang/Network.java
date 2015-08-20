package com.bhd.friendbang;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdhond on 2015-07-14.
 */
@ParseClassName("Network")
public class Network extends ParseObject {
    private Map<String,User> users;
    private Map<String,Group> groups;
    private String description;
    private String name;
    private Date dateCreated;

    public Network(Date dateCreated, String name){
        users = new HashMap<String,User>();
        groups = new HashMap<String,Group>();
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public Map<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Group> groups) {
        this.groups = groups;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
