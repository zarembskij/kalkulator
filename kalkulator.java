import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class Kalkulator {
  private static final Insets insets = new Insets(5, 5, 5, 5);
  
  int i = 21;
  JTextArea textarea;
 
  JButton[] buttons = new JButton[i];
  
  Color darkBlue = new Color(5, 16, 171);
  
  Font fontBtn = new Font("Courier New", Font.BOLD, 23);
  Font fontBtnsml = new Font("Courier New", Font.BOLD, 15);
  Font fontTxtArea = new Font("Courier New", Font.BOLD, 32);
  
  Color darkRed = new Color(171, 5, 5);
  
  LineBorder btnLineBrd = new LineBorder(darkRed, 2);
  
  public static void main(String args[]) 
  {
	  new Kalkulator().run();
  }
  
  void run()
  {
	  
	SwingUtilities.invokeLater(  () -> {
    final JFrame frame = new JFrame("Prosty kalkulator 1.0");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 250);
    frame.setMinimumSize(new Dimension(400, 250));
    frame.setMaximumSize(new Dimension(800, 500)); ///  nie działa. http://bugs.java.com/view_bug.do?bug_id=6464548
    frame.setLocationRelativeTo(null);
    frame.setResizable(true);
    
    GridBagLayout gBlayout = new GridBagLayout();
    gBlayout.preferredLayoutSize(frame);
    frame.setLayout(gBlayout);
   
    Memory memory = new Memory("0", 0, 0);

    textarea = new JTextArea();
    textarea.setFont(fontTxtArea);
    
    textarea.setBorder(BorderFactory.createLineBorder(darkRed, 2));
    textarea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT); //przy RIGHT_TO_LEFT dziwnie wyświetla minus i przecinek (ze złej strony)
    textarea.setForeground(darkBlue);
    textarea.setEditable(false);
 
    addComponent(frame, textarea, 0, 0, 7, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    textarea.setText(memory.sDigit);
    
  
    buttons[0] = new JButton("C");
    addComponent(frame, buttons[0], 6, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[0].addActionListener(e->{
    	memory.clearMemory();
    	textarea.setText("0");
    	isClear();
     });
    
    buttons[1] = new JButton("7");
    addComponent(frame, buttons[1], 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[1].addActionListener(e->{
    	textarea.setText(memory.addDigit("7"));
     });
    
    buttons[2] = new JButton("8");
    addComponent(frame, buttons[2], 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[2].addActionListener(e->{
    	textarea.setText(memory.addDigit("8"));
     });
    
    buttons[3] = new JButton("9");
    addComponent(frame, buttons[3], 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[3].addActionListener(e->{
    	textarea.setText(memory.addDigit("9"));
     });
    buttons[4] = new JButton("*");
    addComponent(frame, buttons[4], 4, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[4].addActionListener(e->{
    	memory.addOperator("*");
    	textarea.setText(memory.result());
    	
     });
    
    buttons[5] = new JButton("\u221a");
    addComponent(frame, buttons[5], 5, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[5].addActionListener(e->{
    	String sRes = memory.resultCurr("sq");
    	textarea.setText(sRes);
    	if (sRes == "ERR")
    	{
    		isErr();
    	}
     });
    
    buttons[6] = new JButton("1/x");
    addComponent(frame, buttons[6], 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[6].addActionListener(e->{
    	String sRes = memory.resultCurr("xx");
    	textarea.setText(sRes);
    	if (sRes == "ERR")
    	{
    		isErr();
    	}
     });
    
   
    buttons[7] = new JButton("%");
    addComponent(frame, buttons[7], 6, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[7].addActionListener(e->{
    	textarea.setText(memory.resultCurr("pr"));
     });
    buttons[8] = new JButton("/");
    addComponent(frame, buttons[8], 4, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[8].addActionListener(e->{
    	memory.addOperator("/");
    	textarea.setText(memory.result());
    
     });
    buttons[9] = new JButton("\u00B1");
    addComponent(frame, buttons[9], 4, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[9].addActionListener(e->{
    	textarea.setText(memory.resultCurr("ab"));
    	
     });
   
    
    buttons[10] = new JButton("4");
    addComponent(frame, buttons[10], 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[10].addActionListener(e->{
    	textarea.setText(memory.addDigit("4"));
     });
    buttons[11] = new JButton("5");
    addComponent(frame, buttons[11], 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[11].addActionListener(e->{
        textarea.setText(memory.addDigit("5"));
     });
    buttons[12] = new JButton("6");
    addComponent(frame, buttons[12], 3, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[12].addActionListener(e->{
    	textarea.setText(memory.addDigit("6"));
     });
    buttons[13] = new JButton("-");
    addComponent(frame, buttons[13], 5, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[13].addActionListener(e->{
    	memory.addOperator("-");
    	textarea.setText(memory.result());
    	
     });
    
    buttons[14] = new JButton("+");
    addComponent(frame, buttons[14], 5, 3, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[14].addActionListener(e->{
    	memory.addOperator("+");
    	textarea.setText(memory.result());
    	
     });
    buttons[15] = new JButton("1");
    addComponent(frame, buttons[15], 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[15].addActionListener(e->{
    	textarea.setText(memory.addDigit("1"));
     });
    buttons[16] = new JButton("2");
    addComponent(frame, buttons[16], 2, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[16].addActionListener(e->{
        textarea.setText(memory.addDigit("2"));
     });
    buttons[17] = new JButton("3");
    addComponent(frame, buttons[17], 3, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[17].addActionListener(e->{
    	textarea.setText(memory.addDigit("3"));
     });
    buttons[18] = new JButton(",");
    addComponent(frame, buttons[18], 3, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[18].addActionListener(e->{
    	textarea.setText(memory.addDigit(","));
     });
    buttons[19] = new JButton("0");
    addComponent(frame, buttons[19], 1, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[19].addActionListener(e->{
        textarea.setText(memory.addDigit("0"));
     });
    buttons[20] = new JButton("=");
    addComponent(frame, buttons[20], 6, 3, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    buttons[20].addActionListener(e->{
    	memory.addOperator("=");
    	String sRes = memory.result();
    	textarea.setText(sRes);
    	if (sRes == "ERR")
    	{
    		isErr();
    	}
      });
    setColors();
    frame.setVisible(true);
	});

  }
  void setColors()
  {
	  for (int x = 0; x < i; x++)
	  {
		  buttons[x].setFont(fontBtn);
		  buttons[x].setBackground(Color.WHITE);
		  buttons[x].setBorder(btnLineBrd);
		  buttons[x].setForeground(darkRed);
	  } 
	  
	  buttons[6].setFont(fontBtnsml);
  }
  void isErr()
  {
	 
	  for (int x = 1; x < i; x++)
	  {
		  buttons[x].setEnabled(false);
	  }
	  textarea.setForeground(Color.RED);
  }
  
  void isClear()
  {
	  for (int x = 1; x < i; x++)
	  {
		  buttons[x].setEnabled(true);
	  }
	  
	  textarea.setForeground(darkBlue);
  }
  
  
  private static void addComponent(Container container, Component component, int gridx, int gridy,
	      int gridwidth, int gridheight, int anchor, int fill) {
	    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 0.5, 0.5,
	        anchor, fill, insets, 0, 0);
	    container.add(component, gbc);
	 }

  
}
