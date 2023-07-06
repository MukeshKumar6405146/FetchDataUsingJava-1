package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

        public static void main(String[] args) throws IOException {
            URL url;
            String urlString="https://api.zippopotam.us/us/33162";
            HttpURLConnection connection;
            int responseCode;
            try{
                url = new URL(urlString);
            } catch(MalformedURLException e){
                throw new RuntimeException(e);
            }

            //connection
            try{
                connection = (HttpURLConnection)url.openConnection();
                responseCode = connection.getResponseCode();
            }catch (IOException e){
                throw new RuntimeException(e);
            }

            // extract information from the connection object

            if(responseCode==200){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder apiData = new StringBuilder();
                String readLine;

                while((readLine = in.readLine())!=null){
                    apiData.append(readLine);
                }

                in.close();
                JSONObject jsonAPIResponse = new JSONObject(apiData.toString());

                System.out.println(jsonAPIResponse);

            }else{
                System.out.println("API call could not be made!!!");
            }

        }

}