/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DirectDriveCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DrivingSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  public static final DrivingSubsystem drivingSubsystem = new DrivingSubsystem();
  public static final ArmSubsystem armSubsystem = new ArmSubsystem();
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  public static OI oi;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  StringBuilder _sb = new StringBuilder();
  Joystick _joy = new Joystick(0);
  int _loops = 0;
  boolean _lastButton1 = false;
	double targetPositionRotations;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    Robot.oi = new OI();
    oi = new OI();
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    drivingSubsystem.initDrive();
    armSubsystem.initArm();
		/* Config the sensor used for Primary PID and sensor direction */
    RobotMap.elevatorTalon.configFactoryDefault();
    RobotMap.elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
    /* Ensure sensor is positive when output is positive */
		RobotMap.elevatorTalon.setSensorPhase(RobotMap.kSensorPhase);

		/**
		 * Set based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. 
		 */ 
		RobotMap.elevatorTalon.setInverted(RobotMap.kMotorInvert);

		/* Config the peak and nominal outputs, 12V means full */
		RobotMap.elevatorTalon.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		RobotMap.elevatorTalon.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		RobotMap.elevatorTalon.configPeakOutputForward(1, RobotMap.kTimeoutMs);
		RobotMap.elevatorTalon.configPeakOutputReverse(-1, RobotMap.kTimeoutMs);

		/**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		RobotMap.elevatorTalon.configAllowableClosedloopError(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		RobotMap.elevatorTalon.config_kF(RobotMap.kPIDLoopIdx, RobotMap.kGains.kF, RobotMap.kTimeoutMs);
		RobotMap.elevatorTalon.config_kP(RobotMap.kPIDLoopIdx, RobotMap.kGains.kP, RobotMap.kTimeoutMs);
		RobotMap.elevatorTalon.config_kI(RobotMap.kPIDLoopIdx, RobotMap.kGains.kI, RobotMap.kTimeoutMs);
		RobotMap.elevatorTalon.config_kD(RobotMap.kPIDLoopIdx, RobotMap.kGains.kD, RobotMap.kTimeoutMs);

		/**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePosition = RobotMap.elevatorTalon.getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (RobotMap.kSensorPhase) { absolutePosition *= -1; }
		if (RobotMap.kMotorInvert) { absolutePosition *= -1; }
		
		/* Set the quadrature (relative) sensor to match absolute */
		RobotMap.elevatorTalon.setSelectedSensorPosition(absolutePosition, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
  }
  void commonLoop() {
		/* Gamepad processing */
		double leftYstick = _joy.getY();
		boolean button1 = _joy.getRawButton(1);	// X-Button
		boolean button2 = _joy.getRawButton(2);	// A-Button

		/* Get Talon/Victor's current output percentage */
		double motorOutput = RobotMap.elevatorTalon.getMotorOutputPercent();

		/* Deadband gamepad */
		if (Math.abs(leftYstick) < 0.10) {
			/* Within 10% of zero */
			leftYstick = 0;
		}

		/* Prepare line to print */
		_sb.append("\tout:");
		/* Cast to int to remove decimal places */
		_sb.append((int) (motorOutput * 100));
		_sb.append("%");	// Percent

		_sb.append("\tpos:");
		_sb.append(RobotMap.elevatorTalon.getSelectedSensorPosition(0));
		_sb.append("u"); 	// Native units

		/**
		 * When button 1 is pressed, perform Position Closed Loop to selected position,
		 * indicated by Joystick position x10, [-10, 10] rotations
		 */
		if (!_lastButton1 && button1) {
			/* Position Closed Loop */

			/* 10 Rotations * 4096 u/rev in either direction */
			targetPositionRotations = leftYstick * 10.0 * 4096;
			RobotMap.elevatorTalon.set(ControlMode.Position, targetPositionRotations);
		}

		/* When button 2 is held, just straight drive */
		if (button2) {
			/* Percent Output */

			RobotMap.elevatorTalon.set(ControlMode.PercentOutput, leftYstick);
		}

		/* If Talon is in position closed-loop, print some more info */
		if (RobotMap.elevatorTalon.getControlMode() == ControlMode.Position) {
			/* ppend more signals to print when in speed mode. */
			_sb.append("\terr:");
			_sb.append(RobotMap.elevatorTalon.getClosedLoopError(0));
			_sb.append("u");	// Native Units

			_sb.append("\ttrg:");
			_sb.append(targetPositionRotations);
			_sb.append("u");	/// Native Units
		}

		/**
		 * Print every ten loops, printing too much too fast is generally bad
		 * for performance.
		 */
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
		}

		/* Reset built string for next loop */
		_sb.setLength(0);
		
		/* Save button state for on press detect */
		_lastButton1 = button1;
    }
    
	/**
	 * This function is called periodically during operator control
	 */


  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }
	@Override
	public void disabledInit() {
		Robot.drivingSubsystem.stop();
	
	}
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  Scheduler.getInstance().run();
  commonLoop();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
