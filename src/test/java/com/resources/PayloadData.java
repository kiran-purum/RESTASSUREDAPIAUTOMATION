package com.resources;

import java.util.HashMap;
import java.util.Map;

public class PayloadData {

    public static Map<String, Object> createDataInReqRes(int id, String email, String first_name, String last_name, String avatar) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("email", email);
        payload.put("first_name", first_name);
        payload.put("last_name", last_name);
        payload.put("avatar", avatar);
        return payload;
    }

    public static Map<String, String> createData(String first_name, String last_name) {
        Map<String, String> payload = new HashMap<>();
        payload.put("first_name", first_name);
        payload.put("last_name", last_name);
        return payload;
    }

    public static Map<String, Object> createUserDataInLocalApi(String title, String actor, int id) {
        Map<String, Object> payloadData = new HashMap<>();
        payloadData.put("title", title);
        payloadData.put("actor", actor);
        payloadData.put("id", id);
        return payloadData;
    }

    public static Map<String, String> createUserDataInLocal(String batsmen, String country) {
        Map<String, String> payloadData = new HashMap<>();
        payloadData.put("batsmen", batsmen);
        payloadData.put("country", country);
        return payloadData;
    }

    public static Map<String, Object> updateUserDataInLocalApi(String title, String actor) {
        Map<String, Object> payloadData = new HashMap<>();
        payloadData.put("title", title);
        payloadData.put("actor", actor);
        return payloadData;
    }
}
