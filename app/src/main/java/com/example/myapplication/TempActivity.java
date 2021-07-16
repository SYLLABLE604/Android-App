package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class TempActivity extends AppCompatActivity {
    private TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        temp = findViewById(R.id.temp);

        new Thread(new Runnable(){
            @Override
            public void run() {
                String buffer = doGet("http://datapoint.metoffice.gov.uk/public/data/val/wxfcs/all/xml/310069?res=3hourly&key=347a5b4f-90b2-47d3-b33b-435fd199c0b9");
                temp.setText(buffer);
                try {
                    org.dom4j.Document document = DocumentHelper.parseText(buffer.toString());
                    Element rootElt = document.getRootElement();
                    Iterator DV = rootElt.elementIterator("DV");
                    while (DV.hasNext()) {
                        Element recordEless = (Element) DV.next();
                        Iterator location = recordEless.elementIterator("location");
                        while (location.hasNext()) {
                            Element itemEle = (Element) location.next();
                            Iterator Period = itemEle.elementIterator("Period");
                            while(Period.hasNext()) {
                                Element tableItem = (Element) Period.next();
                                String F = tableItem.attributeValue("F");
                                temp.setText(F);
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();


    }
    /**
     * Http get请求
     * @param httpUrl 连接
     * @return 响应数据
     */
    public static String doGet(String httpUrl){
        //链接
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();
        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //设置连接超时时间
            connection.setReadTimeout(15000);
            //开始连接
            connection.connect();
            //获取响应数据
            if (connection.getResponseCode() == 200) {
                //获取返回的数据
                is = connection.getInputStream();
                if (null != is) {
                    br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String temp = null;
                    while (null != (temp = br.readLine())) {
                        result.append(temp);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭远程连接
            connection.disconnect();
        }
        return result.toString();
    }
}