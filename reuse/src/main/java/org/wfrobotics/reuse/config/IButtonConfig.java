package org.wfrobotics.reuse.config;

public interface IButtonConfig
{
    public static IButtonConfig getInstance() {
        return null;
	}

    public double getThrottle();

    public double getTurn();

    public boolean getDriveQuickTurn();

    public void setRumble(boolean rumble);

	public void assignButtons();
}