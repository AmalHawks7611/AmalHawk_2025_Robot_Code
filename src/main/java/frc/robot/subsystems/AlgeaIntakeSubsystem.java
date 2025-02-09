package frc.robot.subsystems;

import frc.robot.Constants.AlgeaIntake;
import frc.robot.Constants.OI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

//SAME WITH THE INTAKE. JUST ADDED FOR SPEED CONTROLLING.
//I USED TIMER FOR ALGEA. BEACUSE WE CAN'T DETECT ALGEA WITH COLOR SENSOR!!!
public class AlgeaIntakeSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    private StatusSignal<Angle> leaderMotorPosition;

    public Timer timer = new Timer();

    public AlgeaIntakeSubsystem() {
        leaderMotor = new TalonFX(AlgeaIntake.ALGEA_LEADER_MOTOR_PORT);
        leaderMotorPosition = leaderMotor.getPosition();

        resetEncoders();
    }

    public double getLeaderMotorEncoder() {
        return leaderMotorPosition.refresh().getValueAsDouble();
    }

    public void resetEncoders() {
        leaderMotor.setPosition(0);
    }

    public void manualControl(double speed) {
        leaderMotor.set(speed);
    }

    public void Intake() {
        if(timer.hasElapsed(1.5)){
            leaderMotor.stopMotor();
            OI.IS_ALGEA_INTAKING = false;
        }
        else{
            leaderMotor.set(AlgeaIntake.INTAKE_SPEED);
        }
    }

    public void Shoot(){
        if(timer.hasElapsed(1.5)){
            leaderMotor.stopMotor();
            OI.IS_ALGEA_INTAKING = false;
        }
        else{
            leaderMotor.set(-AlgeaIntake.OUTTAKE_SPEED);
        }
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("IsAlgIntaking", OI.IS_ALGEA_INTAKING);
        SmartDashboard.putNumber("Algea Intake Leader Motor Value", getLeaderMotorEncoder());
    }
}
