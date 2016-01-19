package com.minhtien.parserxml;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class MainActivity extends Activity {
    private static final String TEST = "test";
    private XmlPullParserFactory xmlPullParserFactory;
    public volatile boolean parsingComplete = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream inputStream;
        try
        {
            inputStream = this.getResources().openRawResource(R.raw.xmldata);
            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlPullParserFactory.newPullParser();
            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            myparser.setInput(inputStream, null);
            parseXML(myparser);
        }
        catch (XmlPullParserException e){
           e.printStackTrace();
            Log.d(TEST,"k tim dc file");
        }
    }

    private void parseXML(XmlPullParser myparser) {
        int event;
        String text = null;
        String a = null, b = null, c = null, d = null;
        try {
            event = myparser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myparser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        Log.d(TEST,"start tag  +++++");
                        break;
                    case XmlPullParser.TEXT:
                        text = myparser.getText();
                        Log.d(TEST,"text tag +++++");
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(TEST,"end tag +++++");
                        if (name.equals("name")) {
                            a = text;
                        } else if (name.equals("age")) {
                            b = text;
                        } else if (name.equals("description")) {
                            c = text;
                        } else if (name.equals("weight")) {
                            d = text;
                        } else {
                        }
                        break;
                }
                event = myparser.next();
            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView txtView = (TextView) findViewById(R.id.myText);
        txtView.setText("Name : " + a + "\nAge : " + b + "\nDescription : "
                + c + "\nWeight : " + d);
    }
}
