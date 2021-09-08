package com.example.dadjoke;

import androidx.lifecycle.MutableLiveData;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JokeRepo {

    public MutableLiveData<List<JokeModel>> requestJokes() throws JSONException {
        MutableLiveData<List<JokeModel>> mutableLiveData = new MutableLiveData<>();
        List<JokeModel> jokeModelList = new ArrayList<>();
        JokeModel jokeModel = new JokeModel();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String textUrl = "https://icanhazdadjoke.com/";
                try  {
                    InputStream in = null;
                    BufferedReader br= null;
                    try {
                        URL url = new URL(textUrl);
                        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                        httpConn.setAllowUserInteraction(false);
                        httpConn.setInstanceFollowRedirects(true);
                        httpConn.setRequestMethod("GET");
                        httpConn.setRequestProperty("Accept", "application/json");

                        httpConn.connect();
                        int resCode = httpConn.getResponseCode();
                        if (resCode == HttpURLConnection.HTTP_OK) {
                            in = httpConn.getInputStream();
                            br= new BufferedReader(new InputStreamReader(in));

                            StringBuilder sb= new StringBuilder();
                            String s= null;
                            while((s= br.readLine())!= null) {
                                sb.append(s);
                                sb.append("\n");
                            }
                            JSONObject jokeObj = new JSONObject(String.valueOf(sb));
                            jokeModel.setId(jokeObj.getString("id"));
                            jokeModel.setJoke(jokeObj.getString("joke"));
                            jokeModel.setStatus(jokeObj.getString("status"));
                            List<JokeModel> jokeModelList = new ArrayList<>();
                            jokeModelList.add(jokeModel);
                            mutableLiveData.postValue(jokeModelList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        mutableLiveData.setValue(jokeModelList);
        return mutableLiveData;
    }
}
