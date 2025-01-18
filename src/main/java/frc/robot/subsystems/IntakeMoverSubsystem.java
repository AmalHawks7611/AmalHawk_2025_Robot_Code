package frc.robot.subsystems;

import frc.robot.Constants.IntakeMover;
import frc.robot.Constants.OI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

public class IntakeMoverSubsystem extends SubsystemBase {
    public TalonFX leaderMotor;
    public TalonFX followerMotor;

    private StatusSignal<Angle> leaderMotorPosition;
    private StatusSignal<Angle> followerMotorPosition;

    public IntakeMoverSubsystem() {
        leaderMotor = new TalonFX(IntakeMover.LEADER_MOTOR_PORT);
        followerMotor = new TalonFX(IntakeMover.FOLLOWER_MOTOR_PORT);
        leaderMotorPosition = leaderMotor.getPosition();
        followerMotorPosition = followerMotor.getPosition();

        resetEncoders();
    }

    public double getLeaderMotorEncoder() {
        return leaderMotorPosition.refresh().getValueAsDouble();
    }

    public double getFollowerMotorEncoder() {
        return followerMotorPosition.refresh().getValueAsDouble();
    }

    public void resetEncoders() {
        leaderMotor.setPosition(0);
        followerMotor.setPosition(0);
    }

    public void manualControl(double speed) {
        leaderMotor.set(speed);
        followerMotor.set(speed);
    }

    public void MoveIT(double speed, double setpoint) {
        if (OI.IS_INTAKE_MOVING) {
            double leaderPosition = getLeaderMotorEncoder();
            if (isWithinTolerance(leaderPosition, setpoint)) {
                stopMotors();
                OI.IS_INTAKE_MOVING = false;
            } else {
                if(leaderPosition > setpoint){
                    leaderMotor.set(-speed);
                    followerMotor.set(-speed);
                }
                else{
                    leaderMotor.set(speed);
                    followerMotor.set(speed);
                }
            }
        }
    }

    private boolean isWithinTolerance(double value, double target) {
        return value >= target - 0.06 && value <= target + 0.06;
    }

    private void stopMotors() {
        leaderMotor.stopMotor();
        followerMotor.stopMotor();
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("IsIntakeMoving", OI.IS_INTAKE_MOVING);
        SmartDashboard.putNumber("Intake Mover Leader Motor Value", getLeaderMotorEncoder());
        SmartDashboard.putNumber("Intake Mover Follower Motor Encoder", getFollowerMotorEncoder());

        Logger.recordOutput("Is Intake Moving", Boolean.toString(OI.IS_INTAKE_MOVING).getBytes());
        Logger.recordOutput("Intake Mover Leader Motor Value", Double.toString(getLeaderMotorEncoder()).getBytes());
        Logger.recordOutput("Intake Mover Follower Motor Encoder", Double.toString(getFollowerMotorEncoder()).getBytes());
    }
}
