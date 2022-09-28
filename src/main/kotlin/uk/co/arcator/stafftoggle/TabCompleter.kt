package uk.co.arcator.stafftoggle

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import java.util.*

class TabCompleter : TabCompleter {
    override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<String>): List<String>? {
        val arguments: MutableList<String> = ArrayList()
        if (args.isEmpty()) {
            arguments.add("off")
            arguments.add("on")
        } else if (args.size == 1) {
            arguments.add("off")
            arguments.add("on")
            arguments.removeIf { s: String -> !s.lowercase(Locale.getDefault()).startsWith(args[0]) }
        } else if (args.size == 2) {
            if (args[0].equals("on", ignoreCase = true)) arguments.add("<time>")
            arguments.removeIf { s: String -> !s.lowercase(Locale.getDefault()).startsWith(args[1]) }
        }
        return arguments
    }
}