package com.ft.fliptext;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import android.os.Bundle;
import android.app.Activity;
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
        
      //Load the text file into hash table
    	final Hashtable<String, String> characters = flipCharHashTable();
        
        inputText = (TextView)findViewById(R.id.inputText);
        inputText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			/***
			 * On TextChange - User entering a key an action to reverse the Char sequence and 
			 * input in another text box is performed.
			 */
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
 
		        flipText = (TextView) findViewById(R.id.flipText);
		        CharSequence charS = inputText.getText();
		        flipText.setText(flipString(charS, characters));
				
			}
        });
        
        final Button button = (Button) findViewById(R.id.clear);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	inputText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
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
    		}else
    		{
    			fText += Character.toLowerCase(charS.charAt(i));
    		}
    	}
    	return fText;
    }
    
    /***
     * Method to read text data and add keys and values of letters to hash table.
     * @return
     */
    public Hashtable<String, String> flipCharHashTable()
    {
    	Hashtable<String, String> characters = new Hashtable<String, String>();
    	
    	try{
    		
    		  //Read input file from assets-unicode.txt
    		  InputStream in = getAssets().open("unicode.txt");
			  BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-16"));
			  String strLine;
			  //Read each line and adding chars key and value to hash table
			  while ((strLine = br.readLine()) != null)   {
			  
				  String letters[] = strLine.split("#");
				  
				  if(letters[0].startsWith("\\u"))
				  {
					  String lett = ConvertByteCodetoString(letters[0]);
					  if(letters[1].startsWith("\\u"))
					  {
						  String lett1 = ConvertByteCodetoString(letters[1]);
						  characters.put(lett, lett1);
					  }
					  else
					  {
						  characters.put(lett, letters[1]);
					  }
				  }else
				  {
					  if(letters[1].startsWith("\\u"))
					  {
						  String lett1 = ConvertByteCodetoString(letters[1]);
						  characters.put(letters[0], lett1);
					  }
					  else
					  {
						  characters.put(letters[0], letters[1]);
					  }
				  }
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
    	return characters;

    }
    
    /***
     * Convert Byte code from file to String
     * @param bString
     * @return
     */
    
    public String ConvertByteCodetoString(String bString)
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
    
}
