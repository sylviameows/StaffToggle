package uk.co.arcator.stafftoggle

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

class ToggleCommand : CommandExecutor {

    companion object {
        // gets the config for the plugin
        val config: FileConfiguration? = StaffToggle.instance?.config
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        // checks if a player sent the command
        if (sender !is Player) {
            sender.sendMessage("not player")
            return true
        }

        val player: Player = sender

        // check if player has the toggle permission
        if (!player.hasPermission("stafftoggle.toggle")) {
            Utils.send(sender, "noPerm")
            return true
        }

        // if there are args, start the checks.
        if (args.isNotEmpty()) {
            // get the admin group from the config
            val group: String? = config?.getString("settings.adminGroup")
            if (group == null) {
                Utils.send(sender, "error")
                return true
            }

            if (args[0].equals("off",true) ){
                // check if player has luck perms permission
                if (!player.hasPermission("luckperms.user.parent.removetemp")) {
                    Utils.send(sender, "noPerm")
                    return true
                }

                player.performCommand("lp user "+ player.name +" parent removetemp "+ group)
                return true;
            } else if (args[0].equals("on", true)) {
                // check if player has luck perms permission
                if (!player.hasPermission("luckperms.user.parent.addtemp")) {
                    Utils.send(sender, "noPerm")
                    return true
                }

                if (args.size < 2) {
                    val defaultTime: String? = config?.getString("settings.defaultTime")
                    if (defaultTime != null)
                        player.performCommand("lp user "+ player.name +" parent addtemp "+group+" "+defaultTime)
                    else Utils.send(sender, "error")
                } else {
                    player.performCommand("lp user "+ player.name +" parent addtemp "+group+" " + args[1])
                }
                return true;
            }
            Utils.sendArray(sender, "help")
            return true
        } else {
            Utils.sendArray(sender, "help")
            return true
        }
    }
}