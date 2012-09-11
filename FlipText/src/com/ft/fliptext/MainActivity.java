package com.ft.fliptext;


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
		        flipText.setText(flipString(charS));
				
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
     * Method to flip a charSequence.
     * accepts a char sequence and returns a String.
     * @param charS
     * @return
     */
    public String flipString(CharSequence charS) {

    	String fText = "";
    	for(int i= charS.length()-1;i>=0;i--)
    	{
    		fText += flipChar(Character.toLowerCase(charS.charAt(i)));
    	}
    	return fText;
    }
    
    /***
     * Method to the flipped element of a letter using Uni code.
     * accepts a char and returns suitable reversed unicode char.
     * @param c
     * @return
     */
    public char flipChar(char c)
    {
    	if (c == 'a') {
    		return '\u0250';
    	}
    	else if (c == 'b') {
    		return 'q';
    	}
    	else if (c == 'c') {
    		return '\u0254';
    	}
    	else if (c == 'd') {
    		return 'p';
    	}
    	else if (c == 'e') {
    		
    		return Character.valueOf('\u0259');
    	}
    	else if (c == 'f') {
    		return '\u025F';
    	}
    	else if (c == 'g') {
    		return 'b';
    	}
    	else if (c == 'h') {
    		return '\u0265';
    	}
    	else if (c == 'i') {
    		return '!';
    	}
    	else if (c == 'j') {
    		return '\u027E';
    	}
    	else if (c == 'k') {
    		return '\u029E';
    	}
    	else if (c == 'l') {
    		return 'ǀ';/*0x01C0*/
    	}
    	else if (c == 'm') {
    		return '\u026F';
    	}
    	else if (c == 'n') {
    		return 'u';
    	}
    	else if (c == 'o') {
    		return 'o';
    	}
    	else if (c == 'p') {
    		return 'd';
    	}
    	else if (c == 'q') {
    		return 'b';
    	}
    	else if (c == 'r') {
    		return '\u0279';
    	}
    	else if (c == 's') {
    		return 's';
    	}
    	else if (c == 't') {
    		return '\u0287';
    	}
    	else if (c == 'u') {
    		return 'n';
    	}
    	else if (c == 'v') {
    		return '\u028C';
    	}
    	else if (c == 'w') {
    		return '\u028D';
    	}
    	else if (c == 'x') {
    		return 'x';
    	}
    	else if (c == 'y') {
    		return '\u028E';
    	}
    	else if (c == 'z') {
    		return 'z';
    	}
    	else if (c == '[') {
    		return ']';
    	}
    	else if (c == ']') {
    		return '[';
    	}
    	else if (c == '(') {
    		return ')';
    	}
    	else if (c == ')') {
    		return '(';
    	}
    	else if (c == '{') {
    		return '}';
    	}
    	else if (c == '}') {
    		return '{';
    	}
    	else if (c == '?') {
    		return '\u00BF';  
    	}
    	else if (c == '\u00BF') {
    		return '?';
    	}
    	else if (c == '!') {
    		return '\u00A1';
    	}
    	else if (c == '\'') {
    		return '\u0314';
    	}
    	else if (c == ',') {
    		return '\u02BD';
    	}
    	else if (c == '.') {
    		return '\u02D9';
    	}
    	else if (c == '_') {
    		return '\u203E';
    	}
    	else if (c == '9') {
    		return '6';
    	}
    	else if (c == '6') {
    		return '9';
    	}
    	else if (c == '!') {
    		return '\u00A1';
    	}
    	else if (c == '<') {
    		return '>';
    	}
    	else if (c == '>') {
    		return '<';
    	}
    	else if (c == '\u203F') {
    		return '\u2040';
    	}
    	else if (c == '\u2045') {
    		return '\u2046';
    	}
    	else if (c == '\u2234') {
    		return '\u2235';
    	}
    	else if (c == '\r') {
    		return '\n';
    	}
    	else if(c == '\\'){
    		return '/';
    	}
    	else if(c=='/'){
    		return '\\';
    	}

    	return c;

    }
}
