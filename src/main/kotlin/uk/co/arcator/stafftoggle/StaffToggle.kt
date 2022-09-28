package uk.co.arcator.stafftoggle

import org.bukkit.plugin.java.JavaPlugin

class StaffToggle : JavaPlugin() {
    companion object {
        var instance: StaffToggle? = null
        private set;
    }

    override fun onEnable() {
        super.onEnable()
        instance = this

        config.options().copyDefaults(true)
        saveConfig()

        getCommand("staff")?.setExecutor(ToggleCommand())
        getCommand("staff")?.tabCompleter = TabCompleter()
    }
}