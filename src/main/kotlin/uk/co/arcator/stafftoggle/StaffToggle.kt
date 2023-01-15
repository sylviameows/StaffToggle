package uk.co.arcator.stafftoggle

import org.bukkit.plugin.java.JavaPlugin

class StaffToggle : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()

        config.addDefault("settings.adminGroup", "admin")
        config.addDefault("settings.defaultTime", "10m")
        config.options().copyDefaults(true)
        saveConfig()

        getCommand("staff")?.setExecutor(ToggleCommand(this))
        getCommand("staff")?.tabCompleter = TabCompleter()
    }
}