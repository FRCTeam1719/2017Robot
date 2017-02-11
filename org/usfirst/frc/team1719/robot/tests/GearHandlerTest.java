package org.usfirst.frc.team1719.robot.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1719.robot.interfaces.IGearHandler;
import org.usfirst.frc.team1719.robot.interfaces.ISolenoid;
import org.usfirst.frc.team1719.robot.mockHardware.MockSolenoid;
import org.usfirst.frc.team1719.robot.subsystems.LogicalGearHandler;

public class GearHandlerTest {
	ISolenoid elevator;
	ISolenoid clawTop;
	ISolenoid clawBottom;
	LogicalGearHandler handler;
	ISolenoid[] solenoids;
	boolean[][] pistonStates = {
			{true,false,true},
			{true,true,true},
			{false,true,true},
			{false,false,true},
			{false,false,false}
	};
	
	@Before
	public void setup(){
		elevator = new MockSolenoid();
		clawTop = new MockSolenoid();
		clawBottom = new MockSolenoid();
		handler = new LogicalGearHandler(elevator,clawTop,clawBottom);
		solenoids = new ISolenoid[] {elevator,clawTop,clawBottom};
	}
	
	@Test
	public void testStates(){
		for(int i=0;i<IGearHandler.order.length;i++){
			handler.setState(IGearHandler.order[i]);
			//Test
			for(int j=0;j<solenoids.length;j++){
				assertTrue(solenoids[j].get()==pistonStates[i][j]);
			}
		}
	}
	
	@Test
	public void testDisable(){
		handler.setState(IGearHandler.gearStates.DELIVER);
		//Set all solenoids to true
		for(int i=0;i<solenoids.length;i++){
			solenoids[i].set(true);
		}
		handler.disable();
		for(int i=0;i<solenoids.length;i++){
			assertTrue(solenoids[i].get()==false);
		}
		assertTrue(handler.getState()==IGearHandler.gearStates.DELIVER);
	}
	

}
