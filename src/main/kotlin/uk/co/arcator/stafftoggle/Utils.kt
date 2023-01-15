package uk.co.arcator.stafftoggle

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration

object Utils {
    fun send(config: FileConfiguration, sender: CommandSender, s: String) {
        val msg = config.getString("messages.$s")
        if (msg != null) sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg))
        else send(config, sender, "error")
    }
    fun sendArray(config: FileConfiguration, sender: CommandSender, s: String) {
        val msgs = config.getStringList("messages.$s")
        for (msg: String in msgs) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg))
        }
    }
}