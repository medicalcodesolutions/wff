package com.fmb.wwf.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

public class WWF implements EntryPoint
{
	private FlexTable counts = new FlexTable ();

	private class Letter
	{
		private String letter;
		private int count;
		
		public Letter (String letter, int count)
		{
			this.letter = letter;
			this.count = count;
		}

		public String getLetter ()
		{
			return letter + "&nbsp;&nbsp;&nbsp;";
		}

		public int getCount ()
		{
			return count;
		}

		public void setCount (int count)
		{
			this.count = count;
		}
	}

	private ArrayList<Letter> remaining = new ArrayList<Letter> ();
	private TextArea lettersPlayed;
	
	public void onModuleLoad ()
	{
		resetCounts ();
		
		lettersPlayed = TextArea.wrap (RootPanel.get ("lettersPlayed").getElement ());
		RootPanel.get ("remaining").add (counts);
		displayResults ();
		
		lettersPlayed.addKeyUpHandler (new KeyUpHandler () {

			@Override
			public void onKeyUp (KeyUpEvent event)
			{
				updateCounts ();
			}});

		lettersPlayed.addValueChangeHandler (new ValueChangeHandler<String> () {

			@Override
			public void onValueChange (ValueChangeEvent<String> event)
			{
				updateCounts ();
			}});
		
		lettersPlayed.setFocus (true);
	}
	
	private void updateCounts ()
	{
		String s = lettersPlayed.getText ().toUpperCase ().trim ();
		for (int i = 0; i < s.length (); i++)
		{
			String l = s.substring (i, i + 1);
			int index = 0;
			
			switch (l.charAt (0))
			{
			case '*':
				break;
				
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':
				index = (l.charAt (0) - 'A' + 1);
				break;
				
			default:
				continue;
			}

			Letter letter = remaining.get (index) ;
			int count = Math.max (letter.getCount () - 1, 0);
			letter.setCount (count);
		}	
		
		displayResults ();
	}
	private void resetCounts ()
	{
		remaining.clear ();
		remaining.add (new Letter ("*", 2));
		remaining.add (new Letter ("A", 9));
		remaining.add (new Letter ("B", 2));
		remaining.add (new Letter ("C", 2));
		remaining.add (new Letter ("D", 5));
		remaining.add (new Letter ("E", 13));
		remaining.add (new Letter ("F", 2));
		remaining.add (new Letter ("G", 3));
		remaining.add (new Letter ("H", 4));
		remaining.add (new Letter ("I", 8));
		remaining.add (new Letter ("J", 1));
		remaining.add (new Letter ("K", 1));
		remaining.add (new Letter ("L", 4));
		remaining.add (new Letter ("M", 2));
		remaining.add (new Letter ("N", 5));
		remaining.add (new Letter ("O", 8));
		remaining.add (new Letter ("P", 2));
		remaining.add (new Letter ("Q", 1));
		remaining.add (new Letter ("R", 6));
		remaining.add (new Letter ("S", 5));
		remaining.add (new Letter ("T", 7));
		remaining.add (new Letter ("U", 4));
		remaining.add (new Letter ("V", 2));
		remaining.add (new Letter ("W", 2));
		remaining.add (new Letter ("X", 1));
		remaining.add (new Letter ("Y", 2));
		remaining.add (new Letter ("Z", 1));
	}
	
	private void displayResults ()
	{
		for (int i = 0; i < remaining.size (); i++)
		{
			Letter letter = remaining.get (i);
			counts.setWidget (0, i, new HTML (letter.getLetter ()));
			int count = letter.getCount ();
			counts.setWidget (1, i, new Label (count == 0 ? "" : count + ""));
		}
		
		resetCounts ();
	}
}
