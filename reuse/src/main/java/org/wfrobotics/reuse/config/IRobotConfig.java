package org.wfrobotics.reuse.config;

public interface IRobotConfig
{

	static IRobotConfig getInstance() {
		return null;
	}

	TankConfig getTankConfig();
}