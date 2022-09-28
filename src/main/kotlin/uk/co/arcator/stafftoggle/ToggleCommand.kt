package uk.co.arcator.stafftoggle

import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.model.user.User
import net.luckperms.api.node.Node
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player


class ToggleCommand : CommandExecutor {

    companion object {
        val config: FileConfiguration? = StaffToggle.instance?.config
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        val player: Player = sender as Player
        val group: String? = config?.getString("settings.adminGroup")

        if (player.hasPermission("stafftoggle.toggle")) {
            if (args.isNotEmpty() && args[0].equals("off", true) && player.hasPermission("luckperms.user.parent.removetemp")) {
                player.performCommand("lp user "+ player.name +" parent removetemp "+group)
                return true
            } else if (args.isNotEmpty() && args[0].equals("on", true) && player.hasPermission("luckperms.user.parent.addtemp")) {
                var time: String? = config?.getString("settings.defaultTime")
                if (args.size >= 2) time = args[1]
                player.performCommand("lp user "+ player.name +" parent addtemp "+group+" "+time)
                return true
            }
            Utils.sendArray(sender, "help")
            return true
        } else {
            Utils.send(sender, "noPerm")
            return true
        }
    }
}