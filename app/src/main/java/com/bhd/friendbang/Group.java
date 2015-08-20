package com.bhd.friendbang;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdhond on 2015-07-14.
 */

@ParseClassName("Group")
public class Group extends ParseObject {
    private Map<String,User> users;
    private String description;
    private String name;
    private Network network;
    private Date dateCreated;

    public Group(Date dateCreated, String name){
        users = new HashMap<String,User>();
        this.dateCreated = dateCreated;
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Network getNetwork() {

        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, User> getUsers() {

        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
