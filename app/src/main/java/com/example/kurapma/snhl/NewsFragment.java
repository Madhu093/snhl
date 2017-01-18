package com.example.kurapma.snhl;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kurapma on 1/11/17.
 */

public class NewsFragment extends Fragment {

    private View rootView;
    private RecyclerView rv;
    public NewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.news_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);
        getData();
        return rootView;
    }

    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(getActivity(), "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                parseJSON(s);
                showData();
            }

            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(NewsConfig.GET_URL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }

    private void parseJSON(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray(NewsConfig.TAG_JSON_ARRAY);

            NewsConfig config = new NewsConfig(array.length());

            for(int i=0; i<array.length(); i++){
                JSONObject j = array.getJSONObject(i);
                NewsConfig.names[i] = getName(j);
                NewsConfig.urls[i] = getURL(j);
                NewsConfig.titles[i] =getTitle(j);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        GetBitmap gb = new GetBitmap(getActivity(), NewsConfig.urls);
        gb.execute();
    }

    private String getName(JSONObject j){
        String name = null;
        try {
            name = j.getString(NewsConfig.TAG_IMAGE_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void showData(){
        NewsAdapter adapter = new NewsAdapter(getActivity(), NewsConfig.titles, NewsConfig.names, NewsConfig.urls, NewsConfig.bitmaps);
        rv.setAdapter(adapter);
    }

    private String getURL(JSONObject j){
        String url = null;
        try {
            url = j.getString(NewsConfig.TAG_IMAGE_URL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String getTitle(JSONObject j){
        String title = null;
        try {
            title = j.getString(NewsConfig.TAG_TITLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return title;
    }

}
