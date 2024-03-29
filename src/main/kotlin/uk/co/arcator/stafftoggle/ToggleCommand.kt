package uk.co.arcator.stafftoggle

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class ToggleCommand(private val instance: StaffToggle) : CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        val player: Player = sender as Player
        val group: String = instance.config.getString("settings.adminGroup")!!

        if (player.hasPermission("stafftoggle.toggle")) {
            if (args.isNotEmpty() && args[0].equals("off", true) && player.hasPermission("luckperms.user.parent.removetemp")) {
                player.performCommand("lp user ${player.name} parent removetemp $group")
            } else if (args.isNotEmpty() && args[0].equals("on", true) && player.hasPermission("luckperms.user.parent.addtemp")) {
                var time: String = instance.config.getString("settings.defaultTime")!!
                if (args.size >= 2) time = args[1]
                player.performCommand("lp user ${player.name} parent addtemp $group $time")
            } else {
                Utils.sendArray(instance.config, sender, "help")
            }
        } else {
            Utils.send(instance.config, sender, "noPerm")
        }
        return true
    }
}