package com.example.dadjoke;

import com.google.gson.annotations.SerializedName;

public class FetchJokeResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("joke")
    private String joke;
    @SerializedName("status")
    private String status;

    public FetchJokeResponse(String id, String joke, String status) {
        this.id = id;
        this.joke = joke;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
