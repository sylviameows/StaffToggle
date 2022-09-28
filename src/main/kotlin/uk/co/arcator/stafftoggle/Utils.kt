package uk.co.arcator.stafftoggle

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

object Utils {
    fun send(sender: CommandSender, s: String) {
        val msg = StaffToggle.instance!!.config.getString("messages.$s")
        if (msg != null) sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg))
        else send(sender, "error")
    }
    fun sendArray(sender: CommandSender, s: String) {
        val msgs = StaffToggle.instance!!.config.getStringList("messages.$s")
        for (msg: String in msgs) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg))
        }
    }
}