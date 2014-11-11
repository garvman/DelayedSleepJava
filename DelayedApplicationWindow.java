import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DelayedApplicationWindow {

	private JProgressBar progressBar;
	JTextField input;
	JLabel countdown;
	JButton startButton;
	JButton pauseButton;
	JButton stopButton;
	private JFrame guiFrame;

	int numRows = 3;
	int numCols = 3;
	
	
	public DelayedApplicationWindow(){

		
		guiFrame = new JFrame();
		addComponentsToGUI(guiFrame.getContentPane());

		//close when app shuts down
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("zzzZZZ + DELAYED SLEEP + ZZZzzz");
		guiFrame.setSize(750,350);

		//this will center the window
		guiFrame.setLocationRelativeTo( null );

		guiFrame.setVisible( true );


	}
	public void addComponentsToGUI(Container content){
		
		//GridBagLayout init
		content.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		//progressBar init
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted( true );
		Border border = BorderFactory.createTitledBorder("Progress...");
		progressBar.setBorder(border);
		
		//layout constraints for progressBar
		c.ipady = 30;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		content.add(progressBar, c);
		
		countdown = new JLabel("HH:MM:SS");
		countdown.setFont(new Font(countdown.getFont().getName(), Font.PLAIN, 72));
		c.gridwidth = 1;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		content.add(countdown, c);
		
		
		pauseButton = new JButton("PAUSE");
		pauseButton.setActionCommand("PAUSE");
		
		c.weighty = 0.0;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;		
		c.fill = GridBagConstraints.HORIZONTAL;
		content.add(pauseButton, c);


		
		startButton = new JButton("START");
		startButton.setActionCommand("START");
		
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		content.add(startButton, c);

		stopButton = new JButton("STOP");
		stopButton.setActionCommand("STOP");
		
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		content.add(stopButton, c);

		
		input = new JTextField(10);
		input.setHorizontalAlignment(SwingConstants.CENTER);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.insets = new Insets(10,0,10,0);
		content.add(input, c);

		
		
	}
	public void setProgress(double progress){
		progressBar.setValue((int)progress);
	}
	public void setCountdown(String countdownInput){
		countdown.setText(countdownInput);
	}
	public void close(){
		guiFrame.setVisible(false);
		guiFrame.dispose();
	}


}
