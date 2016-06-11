//Node，構文ファイルのチェック

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyLabel extends JLabel {
    class Constant {
	public final static int LABELWIDTH = 200;
    }
    public MyLabel(){
	super("0",RIGHT);
	Dimension d = getSize();
	d.width = Constant.LABELWIDTH;
	setSize(d);
    }
    public ActionListener getActionListener(){
	return new Modifier();
    }
    class Modifier implements ActionListener {
	public Modifier(){
	}
	public void actionPerformed(ActionEvent event){
	    setText(event.getActionCommand());
	}
    }
}

class MyFrame extends JFrame {
    class Constant {
	public final static int WIDTH = 300;
	public final static int HEIGHT = 200;
	public final static String TITLE = "電卓";
    }
    public void setCloseActionListener(ActionListener a){
	closeActionList.add(a);
    }
    private final LinkedList<ActionListener> closeActionList
	= new LinkedList<ActionListener>();
    class MyWindowListener extends WindowAdapter {
	public MyWindowListener(){
	}
	public void windowClosed(WindowEvent e){
	    for(ActionListener listener : closeActionList){
		listener.actionPerformed(
                   new ActionEvent(
                     this,ActionEvent.ACTION_PERFORMED,"close"));
	    }
	}
    }
    public MyFrame(){
        super();    
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	addWindowListener(new MyWindowListener());
	setSize(Constant.WIDTH,Constant.HEIGHT);
	setTitle(Constant.TITLE);
    }
}

class Main {
    public static void main(String[] arg) throws IOException,ParseException {
	final MyFrame frame = new MyFrame();
	final Keyboard keyboard = new Keyboard();
	frame.setCloseActionListener(keyboard.getCloseAction());
	final Container container = frame.getContentPane();
	container.add(keyboard.getPanel(), BorderLayout.CENTER);
	final MyLabel label = new MyLabel();
	container.add(label, BorderLayout.NORTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	while(true){
		try{
	 		Parser parser = new Parser(keyboard);
			parser.setActionListener(label.getActionListener());
	 		Node tree = parser.start();
		}
		catch(ParseException e){
		 label.setText("error!!");
		}
		catch(TokenMgrError e2){
		 label.setText("error!!");
		}
		catch(NumberFormatException e3){
		 label.setText("error!!");
		}
	}
    } 
}
