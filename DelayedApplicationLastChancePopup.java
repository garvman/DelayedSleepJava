import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DelayedApplicationLastChancePopup{

	JLabel countdown;
	JButton stopButton;
	private JFrame guiFrame;
	
	public DelayedApplicationLastChancePopup(){
	
	
		guiFrame = new JFrame();
		addComponentsToGUI(guiFrame.getContentPane());
		
		//close when app shuts down
		//guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("zzzZZZ + LAST CHANCE + ZZZzzz");
		guiFrame.setSize(300,100);

		//this will center the window
		guiFrame.setLocationRelativeTo( null );

		guiFrame.setVisible( true );
	
	
	}
	public void addComponentsToGUI(Container content){
	
		stopButton = new JButton("STOP");
		stopButton.setActionCommand("STOPLASTCHANCE");
		content.add(stopButton, BorderLayout.SOUTH);
		
		countdown = new JLabel("");
		countdown.setFont(new Font(countdown.getFont().getName(), Font.PLAIN, 24));
		content.add(countdown, BorderLayout.CENTER);
	
	}
	
	public void setCountdown(String countdownInput){
		countdown.setText(countdownInput);
	}
	
	public void close(){
		guiFrame.setVisible(false);
		guiFrame.dispose();
	}
}