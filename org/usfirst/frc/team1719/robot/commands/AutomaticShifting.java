package org.usfirst.frc.team1719.robot.commands;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team1719.robot.interfaces.IDrive;
import org.usfirst.frc.team1719.robot.interfaces.IOI;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;

public class AutomaticShifting extends Command{
	
	private final PowerDistributionPanel pdp;
	private final IDrive drive;
	private final IOI oi;
	private final int BUFFER_SIZE = 10;
	private List<Double> ampBuffer = new LinkedList<>();

	
	public AutomaticShifting(IDrive drive, PowerDistributionPanel pdp, IOI oi){
		this.pdp = pdp;
		this.drive = drive;
		this.oi = oi;
	}
	
	@Override
	protected void execute(){
		//Check manual override
		if(oi.getShifter()){
			drive.shift(IDrive.Shift.LOW);
		}else{
			//Average the readings
			double avgReading = 0;
			for(int i=0;i<3;i++){
				avgReading += pdp.getCurrent(i);
			}
			avgReading /= 4;
			updateBuffer(avgReading);
			//Average the buffer
			double avgBuffer = 0;
			for(double reading: ampBuffer){
				avgBuffer += reading;
			}
			avgBuffer /= ampBuffer.size();
			if(avgBuffer>5 && avgBuffer < 25){
				drive.shift(IDrive.Shift.HIGH);
			}else{
				drive.shift(IDrive.Shift.LOW);
			}
		}
	}
	
	private void updateBuffer(double newReading){
		if(ampBuffer.size()==BUFFER_SIZE){
			ampBuffer.remove(0);
		}
		ampBuffer.add(newReading);
	}
	
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
