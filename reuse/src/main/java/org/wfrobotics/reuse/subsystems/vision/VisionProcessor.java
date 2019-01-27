package org.wfrobotics.reuse.subsystems.vision;

import org.wfrobotics.reuse.subsystems.background.BackgroundUpdate;
import org.wfrobotics.reuse.subsystems.vision.CameraServer.CameraListener;
import org.wfrobotics.reuse.IRobotState;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessor implements BackgroundUpdate, CameraListener
{
    private static VisionProcessor instance = null;
    private CoprocessorData rx = null;

    public synchronized void Notify(CoprocessorData message)
    {
        rx = message;
    }

    private VisionProcessor() { }

    public static VisionProcessor getInstance()
    {
        if (instance == null)
        {
            instance = new VisionProcessor();
        }
        return instance;
    }


    public void onStartUpdates(boolean isAutonomous)
    {

    }

    public void onBackgroundUpdate()
    {
        SmartDashboard.putString("Reported State", "True");
        CoprocessorData update;
        synchronized (this)
        {
            if (rx == null)
            {
                return;
            }
            update = rx;
            rx = null;
        }
        if (update != null)
        {
            IRobotState.getInstance().addVisionUpdate(update.atCameraTimestampMs, update);
        }
    }

    public void onStopUpdates()
    {

    }
}
