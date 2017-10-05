/*
 * Sample code for CS 2610 Homework 1
 * 
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;        
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.geom.Point2D;

public class Keyboard{
	
	JFrame window;
	JButton b1,b2,b3,back;
	Key[] keys;
	JPanel board; //for loading buttons
	JTextField input;
	JLabel outputdisplay;
	Container panel;
	MouseListener mouselistener;
	private static boolean USE_CROSS_PLATFORM_UI = true;
	private StringBuilder sb;
	
	public Keyboard() throws FileNotFoundException{
		if(USE_CROSS_PLATFORM_UI) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		mouselistener = new MouseListener();
		window = new JFrame("keyboard");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600,400);
		
		//set the keyboard pad layout
		board = new JPanel();
		Border border = BorderFactory.createEmptyBorder(0,10,20,10);
		board.setBorder(border);
		//infomation display
		
		input = new JTextField("ACTIONS SPEAK LOUDER THAN WORDS");
		input.setFont(new Font("Serif", Font.PLAIN, 16));
		input.setBackground(new Color(237,237,237));
		input.setEditable(false);
		Border title = BorderFactory.createTitledBorder("Input: ");
		Border bevel = BorderFactory.createLoweredBevelBorder();
		Border border1 = BorderFactory.createEmptyBorder(0,10,5,10);
		Border border2 = BorderFactory.createEmptyBorder(10,10,5,10);
		Border border3 = BorderFactory.createCompoundBorder(border2, bevel);
		Border border4 = BorderFactory.createCompoundBorder(border3, title);
		input.setBorder(BorderFactory.createCompoundBorder(border4, border1));
		
		//modify input sample border
		outputdisplay = new JLabel("_");
		Border title2 = BorderFactory.createTitledBorder("Output: ");
		Border border5 = BorderFactory.createEmptyBorder(20,10,20,10);
		Border border6 = BorderFactory.createEmptyBorder(10,9,10,9);
		Border border7 = BorderFactory.createCompoundBorder(title2,border6);
		outputdisplay.setBorder(BorderFactory.createCompoundBorder(border5,border7));
		outputdisplay.setForeground(Color.blue);
		outputdisplay.setFont(new Font("Serif", Font.PLAIN, 16));
		
		b1 = new JButton();
		b2 = new JButton();
		b3 = new JButton();
		back = new JButton("Reset");
		b1.addMouseListener(mouselistener);
		b2.addMouseListener(mouselistener);
		b3.addMouseListener(mouselistener);
		back.addMouseListener(mouselistener);
		
		board.setLayout(new GridBagLayout());
		//set the buttons:
		int[] keyNum =  {10,9,7};
		keys = new Key[27];
		String[] keyLabels ={"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"}; //change to keyboard setting
		
		//first line
		for (int i = 0; i < keyNum[0]; i++){ //first line of keys
			String label = keyLabels[0].substring(i, i+1);
			keys[i] = new Key(label);
			keys[i].setName(label);
			keys[i].setFocusPainted(false);
			keys[i].addMouseListener(mouselistener);
			keys[i].addMouseMotionListener(mouselistener);
			addKey(board,keys[i],i+1,0,1,1);
		}
		
		//second line
		for (int i = 0; i < keyNum[1]; i++){ //second line of keys
			String label = keyLabels[1].substring(i, i+1);
			keys[i] = new Key(label);
			keys[i].setName(label);
			keys[i].setFocusPainted(false);
			keys[i].addMouseListener(mouselistener);
			keys[i].addMouseMotionListener(mouselistener);
			addKey(board,keys[i],i+1,1,1,1);
		}
		
		
		for (int i = 0;i< keyNum[2]; i++){ //third line of keys
			String label = keyLabels[2].substring(i, i+1);
			keys[i] = new Key(label);
			keys[i].setName(label);
			keys[i].setFocusPainted(false);
			keys[i].addMouseListener(mouselistener);
			keys[i].addMouseMotionListener(mouselistener);
			addKey(board,keys[i],i+2,2,1,1);
		}
		
		//set the space button
		keys[26] = new Key("                                                              ");
		keys[26].setName(" ");
		keys[26].setFocusPainted(false);
		keys[26].addMouseListener(mouselistener);
		keys[26].addMouseMotionListener(mouselistener);
		addKey(board,keys[26],2,3,7,1);
		
		panel = window.getContentPane();
		//use gridBag layout
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		Container panel2 = new Container();
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor= GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.BOTH;
		c2.insets = new Insets(5,5,5,5);
		c2.gridwidth = 1;
		c2.gridheight = 1;
		panel2.add(b1,c2);
		
		c2.gridx = 1;
		c2.gridy = 0;
		c2.anchor= GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.BOTH;
		c2.insets = new Insets(5,5,5,5);
		c2.gridwidth = 1;
		c2.gridheight = 1;
		panel2.add(b2,c2);
		
		c2.gridx = 2;
		c2.gridy = 0;
		c2.anchor= GridBagConstraints.CENTER;
		c2.fill = GridBagConstraints.BOTH;
		c2.insets = new Insets(5,5,5,5);
		c2.gridwidth = 1;
		c2.gridheight = 1;
		panel2.add(b3,c2);		
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor= GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panel.add(input,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.anchor= GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panel.add(back,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.anchor=GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panel.add(outputdisplay,c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor=GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		panel.add(panel2, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 4;
		c.anchor=GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,10,10);
		panel.add(board,c);
        
		window.pack();// adjust the window size
		window.setVisible(true);
	}	
	
	public void addKey(Container container, Component component, int gridx, int gridy, int gridwidth, int gridheight){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipady = 30;
		c.ipadx = 30;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		c.anchor=GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0,0,0,0);
		container.add(component, c);
	}
	
	class MouseListener extends MouseAdapter implements MouseMotionListener{
		boolean tracing;			// whether the input method is button clicking or tracing
		ArrayList<Key> tracelist;	// a list to store all buttons on the trace
		Key curKey;
		String[] suggestions = {"","",""};
		
		MouseListener() throws FileNotFoundException{
			super();
			tracing = false;
			tracelist = new ArrayList<Key>();
			curKey = new Key("");
		}
		
		private void updateOutput (Key theEventer){			
			String theChar = theEventer.getText();
			String oldString = outputdisplay.getText();
			if(Character.toString(theChar.charAt(0)).equalsIgnoreCase(" ")) {
				theChar = " ";
			}
			String newString = oldString.substring(0, oldString.length()-1) + theChar + "_";
			outputdisplay.setText(newString);
			if(theChar == " ") {
				b1.setText(null);
				b2.setText(null);
				b3.setText(null);
			}
		}
		
		private void recoverState(){
			//when mouse is released, tracing is ended. reset the letter state in the tmptlist
			//change status
			while (!tracelist.isEmpty()){
				Key e = tracelist.get(0);
				String traceKeys = e.getText();
				System.out.println("trace word = "+traceKeys);
				sb.append(traceKeys);
				e.LineList.clear();
				e.PointList.clear();
				e.repaint();
				tracelist.remove(0);	
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e){
			Key theEventer = (Key) e.getSource();
			
			if (tracing == false){//start tracing
				curKey = theEventer;
				tracelist.add(theEventer);
				tracing = true;
				System.out.println("Entering Mouse tracing mode");
				sb = new StringBuilder();
                theEventer.PointList.add(e.getPoint());
			} else{
				Point2D p = e.getPoint();
				int x = (int)p.getX()- (curKey.getX() - theEventer.getX());
				int y = (int)p.getY()- (curKey.getY() - theEventer.getY());
				Point newPoint = new Point(x, y);
				System.out.println("Mouse position:(" + (curKey.getX() + x) + "," + (curKey.getY() + y) + "), In key " + curKey.getText() + ".");				
				curKey.PointList.add(newPoint);
				curKey.repaint();
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Selected");
			// TODO Auto-generated method stub		
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()!=b1 && e.getSource()!=b2 && e.getSource()!=b3 && e.getSource()!=back) {
				Key theEventer = (Key) e.getSource();
				theEventer.setBackground(Color.blue);
				if (tracing) {				
					curKey = theEventer;
					tracelist.add(theEventer);
					theEventer.setFocusPainted(true);				
					//start the mouse trace in this button
					theEventer.PointList.add(e.getPoint());
				}
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()!=b1 && e.getSource()!=b2 && e.getSource()!=b3 && e.getSource()!=back) {
				Key theEventer = (Key) e.getSource();
				theEventer.setBackground(null);
				if(tracing){				
					theEventer.setFocusPainted(false); 
					theEventer.LineList.add(theEventer.PointList);
					theEventer.PointList = new ArrayList<Point>(); 
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			String soundName = "/handbag-lock-8.wav";	
			URL url = Keyboard.class.getResource(soundName);
			if(e.getSource()==b1||e.getSource()==b2||e.getSource()==b3||e.getSource()==back) {
				JButton jb = (JButton) e.getSource();
				jb.setFocusPainted(true);
			} else {
				Key theEventer = (Key) e.getSource();
				theEventer.setFocusPainted(true);
			}			
			try 
			   {
			    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			    Clip clip = AudioSystem.getClip( );
			    clip.open(audioInputStream);
			    clip.start( );
			   }
			   catch(Exception ex)
			   {
			     System.out.println("Error with playing sound.");
			     ex.printStackTrace( );
			   }
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==b1) {				
					String theChar = suggestions[0];
					String oldString = outputdisplay.getText();
					String newString = oldString.substring(0, oldString.length()-1) + theChar + " _";
					outputdisplay.setText(newString);
			} else if(e.getSource()==b2) {
					String theChar = suggestions[1];
					String oldString = outputdisplay.getText();
					String newString = oldString.substring(0, oldString.length()-1) + theChar + " _";
					outputdisplay.setText(newString);
			} else if(e.getSource()==b3) {
					String theChar = suggestions[2];
					String oldString = outputdisplay.getText();
					String newString = oldString.substring(0, oldString.length()-1) + theChar + " _";
					outputdisplay.setText(newString);
			} else if(e.getSource()==back) {
				outputdisplay.setText("_");
				b1.setText(null);
				b2.setText(null);
				b3.setText(null);
			}else {				
				Key theEventer = (Key) e.getSource();//e is the same source as pressed
				if (tracing == false) {					
					updateOutput(theEventer);
					System.out.println("Input key " + theEventer.getText());
				} else {
					tracing = false;
					System.out.println("Tracing Completes. Clear all traces.");				
					recoverState();
					System.out.println("trace word = "+sb.toString());
					String[] keyLabels ={"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"}; //change to keyboard setting
					char[] traceWord = (sb.toString()).toCharArray();
					String searchWord = Character.toString(traceWord[0]);
					for (int i = 1; i<traceWord.length-1; i++) {
						int j = i-1,k=i+1;
						if( (keyLabels[0].contains(Character.toString(traceWord[i])) && keyLabels[0].contains(Character.toString(traceWord[j])) && keyLabels[0].contains(Character.toString(traceWord[k])) ) ||
								(keyLabels[1].contains(Character.toString(traceWord[i]))&&keyLabels[1].contains(Character.toString(traceWord[j])) && keyLabels[1].contains(Character.toString(traceWord[k])) ) ||
								(keyLabels[2].contains(Character.toString(traceWord[i]))&&keyLabels[2].contains(Character.toString(traceWord[j]))) && keyLabels[2].contains(Character.toString(traceWord[k])) ) {
							//searchWord += Character.toString(traceWord[i]);
						} else {
							searchWord += Character.toString(traceWord[i]);
							//searchWord += Character.toString(traceWord[j]);
						}
					}
					searchWord += Character.toString(traceWord[traceWord.length-1]);
					System.out.println("searchWord === "+searchWord);
					Levenshtein_Distance ld = new Levenshtein_Distance();
					ArrayList<String> matches = ld.findMatch(sb.toString(),searchWord);
					String[] result = {"","",""};
					for(int i=0;i<matches.size() && i<3;i++) {
						System.out.println("matching words = "+matches.get(i));
						result[i] = matches.get(i);
						suggestions[i] = matches.get(i);
					}
					
					String[] inputText = input.getText().split(" ");
					String[] outputText = outputdisplay.getText().split(" ");
					int wordNum = 0, count=0;
					wordNum = outputText.length - 1;					
					System.out.println("wordNum == "+wordNum);
					if(wordNum >= inputText.length) {
						inputText[wordNum] = " ";
					}
					while(count < 3) {
						String w="<html>";
						//System.out.println("result[count] == "+result[count] + " inputText[wordNum] == "+inputText[wordNum]);
							if(result[count].equalsIgnoreCase(inputText[wordNum])) {
								w += "<font color=blue>"+result[count]+"</font>";																
							} else {
								for(int i=0; i<result[count].length(); i++) {
									if(inputText[wordNum].contains(Character.toString(result[count].charAt(i)))) {
										w += "<font color=blue>"+result[count].charAt(i)+"</font>";
									} else {
										w += "<font color=red>"+result[count].charAt(i)+"</font>";
									}
									//System.out.println("w = "+w);
								}								
							}
							//System.out.println("w = "+w);
							w += "</html>";
							if(count == 0) {
								b1.setText(w);
							}
							else if(count == 1) {
								b2.setText(w);
							} else {
								b3.setText(w);
							}
						count++;
					}				
				}
			}			
		}
	}
	    
	public static void main(String[] args) throws FileNotFoundException {
		Keyboard gui = new Keyboard(); 
		Dictionary dict = new Dictionary();
	}
}
