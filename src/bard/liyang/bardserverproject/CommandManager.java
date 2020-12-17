package bard.liyang.bardserverproject;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(label.equals("removeusers"))
		{
			RarityManager.rm.removeYMLData();
			return true;
		}
		return false;
	}
}
