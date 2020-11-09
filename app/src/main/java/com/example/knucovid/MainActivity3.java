package com.example.knucovid;

import java.net.URL;
import android.app.Activity;
import android.os.StrictMode;
import org.xmlpull.v1.XmlPullParser;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParserFactory;
import android.os.Bundle;


import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;



public class MainActivity3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        StrictMode.enableDefaults();

        TextView status1 = (TextView) findViewById(R.id.result);
        Boolean initem = false;
        Boolean inconfCase = false;
        Boolean increateDt = false;
        Boolean incriticalRate = false;
        Boolean inconfCaseRate = false;
        Boolean indeath = false;
        Boolean indeathRate = false;
        Boolean ingubun = false;
        Boolean inseq = false;
        Boolean inupdateDt = false;
        int number=1;
        String confCase = null, confCaseRate = null, createDt = null, criticalRate = null;
        String death = null, deathRate = null, seq = null, updateDt = null;
        String gubun = null;

        try {
            URL url = new URL("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19GenAgeCaseInfJson?serviceKey=A2vwv2O8EWf4MCBQKD6bR4eVSjK0Jylzm0x5uedn553xDUchtjh%2F8uWq595vL8SYzGbB5ty0uUCWYQk1duB7pw%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200414");

            XmlPullParserFactory paserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = paserCreator.newPullParser();

            parser.setInput(url.openStream(), null);
            int paserEvent = parser.getEventType();
            System.out.println("Loading..");
            while (number!=213) {
                switch (paserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("confCase")) {
                            inconfCase = true;
                        }
                        if (parser.getName().equals("confCaseRate")) {
                            inconfCaseRate = true;
                        }
                        if (parser.getName().equals("createDt")) {
                            increateDt = true;
                        }
                        if (parser.getName().equals("criticalRate")) {
                            incriticalRate = true;
                        }

                        if (parser.getName().equals("death")) {
                            indeath = true;
                        }
                        if (parser.getName().equals("deathRate")) {
                            indeathRate = true;
                        }
                        if (parser.getName().equals("gubun")) {
                            ingubun = true;
                        }
                        if (parser.getName().equals("seq")) {
                            inseq = true;
                        }
                        if (parser.getName().equals("updateDt")) {
                            inupdateDt = true;
                        }
                        if (parser.getName().equals("message")) {
                            status1.setText(status1.getText() + "에러");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (inconfCase) {
                            confCase = parser.getText();
                            inconfCase = false;
                        }
                        if (increateDt) {
                            createDt = parser.getText();
                            increateDt = false;
                        }
                        if (incriticalRate) {
                            criticalRate = parser.getText();
                            incriticalRate = false;
                        }
                        if (inconfCaseRate) {
                            confCaseRate = parser.getText();
                            inconfCaseRate = false;
                        }
                        if (indeath) {
                            deathRate = parser.getText();
                            indeath = false;
                        }
                        if (indeathRate) {
                            death = parser.getText();
                            indeathRate = false;
                        }
                        if (ingubun) {
                            gubun = parser.getText();
                            ingubun = false;
                        }
                        if (inseq) {
                            seq = parser.getText();
                            inseq = false;
                        }
                        if (inupdateDt) {
                            updateDt = parser.getText();
                            inupdateDt = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item"))
                        {
                            if((number==103)|(number==113))
                            {
                                status1.setText(status1.getText() +  "확진자 : " + confCase + "\n 확진률: " + confCaseRate + "\n 신규확진자 : " + createDt +
                                        "\n 치명률 : " + criticalRate + "\n 사망자 : " + death + "\n 사망률 : " + deathRate
                                        + "\n 번호 : " + seq + "\n 수정일 : " + updateDt + "\n성별별 : " + gubun + "\n-------------------------\n");
                                initem = false;
                            }
                        }
                        number++; //하나의 status1
                        break;
                }
                paserEvent = parser.next();
            }
        } catch (XmlPullParserException e) {
            status1.setText("에러");
        } catch (IOException e) {
            status1.setText("에러");
        }
    }
}