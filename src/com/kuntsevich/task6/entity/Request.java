package com.kuntsevich.task6.entity;

import java.util.Map;

public class Request {

    private String command;
    private Map<String, String> params;

    public Request(String command, Map<String, String> params) {
        this.command = command;
        this.params = params;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    // TODO toString etc...
}
