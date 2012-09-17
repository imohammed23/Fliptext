package com.ft.fliptext;


import java.io.IOException;
import java.util.Hashtable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Imran
 *
 */

public class MainActivity extends Activity {
	
	private TextView inputText, flipText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Resources res = getResources();
        //Populate data into hastable using threads and XML file
    	final Hashtable<String, String> charactersTest = new LoadDataClass().doInBackground(res);
    	
        
        inputText = (TextView)findViewById(R.id.inputText);
        inputText.addTextChangedListener(new TextWatcher(){
		/***
			 * On TextChange - User entering a key an action to reverse the Char sequence and 
			 * input in another text box is performed.
			 */
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
		        flipText = (TextView) findViewById(R.id.flipText);
		        CharSequence charS = inputText.getText();
		        flipText.setText(flipString(charS, charactersTest));
				
			}
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
				// TODO Auto-generated method stub
			
			}
        });
        
        final Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	inputText.setText("");
            }
        });
        
        final Button share = (Button) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
			
        	
			@Override
			public void onClick(View v) {
				if(flipText.getText()!= null || flipText.getText()!= "")
					OnClickShare();
			}
		});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * Method to share content via other apps
     * @param view
     */
    public void OnClickShare()
    {
    	Intent shareIntent = new Intent(Intent.ACTION_SEND);
    	shareIntent.setType("text/plain");
    	shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Flipped Text");
    	shareIntent.putExtra(Intent.EXTRA_TEXT, flipText.getText().toString());
    	startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
    

    /***
     * Method to Flip characters
     * @param charS
     * @return String
     */
    public String flipString(CharSequence charS, Hashtable<String, String> characters)
    {
    	String fText = "";
    	for(int i= charS.length()-1;i>=0;i--)
    	{
    		if(characters.containsKey(Character.toLowerCase(charS.charAt(i))+""))
    		{
    			fText += characters.get(Character.toLowerCase(charS.charAt(i))+"");
    			continue;
    		}
    			fText += Character.toLowerCase(charS.charAt(i));
    	}
    	return fText;
    }
    
}


/***
 * 
 * @author Imran
 *
 *
 *This class create a working thread to populate data into the hash table.
 *This thread works completely unrelated to the UI thread.
 *Although this is not necessary for a small program like this.
 *It is a good practice to Acess and load data in the background.
 *
 *Now accesses data from a XML file.(Earlier a text file)
 */
class LoadDataClass extends AsyncTask<Resources, Void, Hashtable<String, String>>{

	/**
	 * Method runs in the background and loads data into the hash table.
	 */
	@Override
	protected Hashtable<String, String> doInBackground(Resources... params) {
		Hashtable<String, String> characters = new Hashtable<String, String>();
		XmlPullParser xmlR =  params[0].getXml(R.xml.unicode);
		int eventType;
		try {
			
			eventType = xmlR.next();
			while(eventType != XmlPullParser.END_DOCUMENT)
			{
				String key = null;
				String value = null;
				if(eventType == XmlPullParser.START_TAG)
				{
					if(xmlR.getName().equals("string"))
					{
					key = ConvertByteCodetoString(xmlR.getAttributeValue(0));
					value = ConvertByteCodetoString(xmlR.nextText());
					}
					else
					{
						eventType = xmlR.next();
						continue;
					}
				}
				
				if(key != null || value != null)
				{
					characters.put(key, value);
				}
				
				eventType = xmlR.next();
				
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return characters;
	}
	
	/**
	 * Method to convert byte code into string.
	 * @param bString
	 * @return
	 */
	public String ConvertByteCodetoString(String bString)
    {
		if(bString.startsWith("\\u"))
		{
    	String lett = "";
    	String temp = bString.split(" ")[0];
		  temp = temp.replace("\\","");
		  String[] arr = temp.split("u");
		  for(int i = 1; i < arr.length; i++){
			  int hexVal = Integer.parseInt(arr[i], 16);
			  lett += (char)hexVal;
			  }
    	return lett;
		}
		return bString;
    }
	
}
	
