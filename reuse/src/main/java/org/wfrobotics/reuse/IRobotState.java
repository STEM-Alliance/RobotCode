package org.wfrobotics.reuse;

import java.util.Map;

import org.wfrobotics.reuse.math.geometry.Pose2d;
import org.wfrobotics.reuse.math.geometry.Rotation2d;
import org.wfrobotics.reuse.math.geometry.Translation2d;
import org.wfrobotics.reuse.math.geometry.Twist2d;
import org.wfrobotics.reuse.math.interpolation.InterpolatingDouble;
import org.wfrobotics.reuse.subsystems.vision.CoprocessorData;
import org.wfrobotics.reuse.utilities.Reportable;

public interface IRobotState extends Reportable
{
	public static IRobotState getInstance() {
		return null;
	}
	public double robotHeading();
	public void updateRobotHeading(double gryo);

	public void reportState();
	public void resetDriveState(double fpgaTimestamp, Pose2d pose2d);

	public double getDistanceDriven();
	public void resetDistanceDriven();

	public Twist2d getPredictedVelocity();

	public Pose2d getFieldToVehicle(double fpgaTimestamp);
	public Map.Entry<InterpolatingDouble, Pose2d> getLatestFieldToVehicle();
	
	public Twist2d getRobotOdometry(double d, double e, Rotation2d gyro_angle);
	public void addRobotObservation(double fpgaTimestamp, Twist2d odometry_velocity, Twist2d predicted_velocity);

	// VISION STUFF
	
	public double getKCameraAngle();
	public void addVisionUpdate();
	public double getExtrapolatedVisionError();
	public double getVisionError();
	public boolean visionInView();

	public void addVisionUpdate(double timestamp, CoprocessorData coprocessorData);
}