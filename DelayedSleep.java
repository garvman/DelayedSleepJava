import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Runtime;
import java.io.IOException;


public class DelayedSleep implements ActionListener {

	static DelayedApplicationWindow myDelayedSleepWindow;
	static DelayedApplicationLastChancePopup lastChancePopup;
	static DelayedSleep thisApp;
	
	private static JTextField input;
	boolean delayedSleepIsPaused = false;
	boolean stopDelayedSleepApplication = false;
	boolean stopAtLastChance = false;


	public static void main(String[] argv){


		thisApp = new DelayedSleep();

		thisApp.myDelayedSleepWindow.startButton.addActionListener(thisApp);
		thisApp.myDelayedSleepWindow.pauseButton.addActionListener(thisApp);
		thisApp.myDelayedSleepWindow.stopButton.addActionListener(thisApp);

		DelayedSleep.input = myDelayedSleepWindow.input;
	
	}

	public DelayedSleep (){
		myDelayedSleepWindow = new DelayedApplicationWindow();

	}

	public void executeSleepFunction(int secondsDelay)
		throws InterruptedException{

		int i;
		double percentComplete;

		for(i = 0; i <= secondsDelay; i++){

			if(stopDelayedSleepApplication){
				this.myDelayedSleepWindow.setProgress(0);
				this.myDelayedSleepWindow.setCountdown("HH:MM:SS");			
				return;
			}
				
			while(delayedSleepIsPaused){
				Thread.sleep(1000);
				//System.out.println("derp");
			};
			
			percentComplete = (double) i/secondsDelay;
			this.myDelayedSleepWindow.setProgress(percentComplete*100);
			this.myDelayedSleepWindow.setCountdown(getTimeString(secondsDelay - i));
			
			Thread.sleep(1000);
		}
		
		try{
			executeLastChance();
		}catch (InterruptedException e){
		
		}

	}
	
	public void executeLastChance()
		throws InterruptedException{
		
		lastChancePopup = new DelayedApplicationLastChancePopup();
		lastChancePopup.stopButton.addActionListener(thisApp);
		
		int seconds;
		
		for(int i = 0; i <= 30; i++){
			if(stopAtLastChance)
				return;
				
			seconds = 30 - i;
			this.lastChancePopup.setCountdown("Sleeping in " + seconds + " seconds...");	
			Thread.sleep(1000);
		}	
		lastChancePopup.close();
		this.myDelayedSleepWindow.close();
		
		try{
			Runtime.getRuntime().exec("cmd /c start delayedSleep.bat");
		}catch (IOException e){
			System.out.println("something bad happened");
		}
	}
	
	public String getTimeString(int seconds){
		
		int hours;
		int mins;
		int secondsfinal;
		int tmp;
		String output = "";
		
		hours = (int) seconds/3600;
		
		tmp = seconds%3600;
		mins = (int) tmp/60;
		
		seconds = tmp%60;
		
		if(hours != 0){
			output = hours + "h : " + mins + "m : " + seconds + "s";
		}else if(mins != 0){
			output = mins + "m : " + seconds + "s";
		}else{
			output = seconds + " seconds";
		}
		
		return output;
		
	}

	public void actionPerformed(ActionEvent e){

		if("START".equals(e.getActionCommand())){
			
			if(stopDelayedSleepApplication)
				stopDelayedSleepApplication = false;
				

			int mins = Integer.parseInt(input.getText());
			final int seconds = mins*60;
			delayedSleepIsPaused = false;
		
			new Thread(){
				@Override
				public void run(){
					try{
						executeSleepFunction(seconds);
					}catch (InterruptedException ie){

					}
				}
			}.start();

		}else if("PAUSE".equals(e.getActionCommand())){
		
			if(delayedSleepIsPaused){
				//System.out.println("got here");
				delayedSleepIsPaused = false;
				this.myDelayedSleepWindow.pauseButton.setText("PAUSE");
			}else{
				//System.out.println("delayedSleepIsPaused is false");
				delayedSleepIsPaused = true;
				this.myDelayedSleepWindow.pauseButton.setText("RESUME");
			}
		
		}else if("STOP".equals(e.getActionCommand())){
		
			if(delayedSleepIsPaused){
				//System.out.println("got here");
				delayedSleepIsPaused = false;
				this.myDelayedSleepWindow.pauseButton.setText("PAUSE");
			}
			
			stopDelayedSleepApplication = true;
			
			
			
			
		}else if("STOPLASTCHANCE".equals(e.getActionCommand())){
			
			stopAtLastChance = true;
			this.myDelayedSleepWindow.setProgress(0);
			this.myDelayedSleepWindow.setCountdown("HH:MM:SS");
			lastChancePopup.close();
		}
	}
}
