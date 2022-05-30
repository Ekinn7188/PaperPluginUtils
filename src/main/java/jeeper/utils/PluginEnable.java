package jeeper.utils;

import org.bukkit.Bukkit;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Useful tools that can be run when a plugin is enabled.
 */
public class PluginEnable {

    /**
     *
     * Checks if a plugin's dependencies are enabled/working<br>
     * <br>
     * It's recommended to place this at the start of your <b>onEnable()</b>
     *
     * @param pluginNames a list of plugin dependencies to search for
     * @param thisPlugin the name of the current plugin, listed in your plugin.yml
     */
    public static void checkForPluginDependencies(List<String> pluginNames, String thisPlugin) {
        for (String plugins : pluginNames){
            if (Bukkit.getServer().getPluginManager().getPlugin(plugins) == null || !Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(plugins)).isEnabled()) {
                Bukkit.getLogger().log(Level.SEVERE, plugins + " not found or not enabled");
                Bukkit.getServer().getPluginManager().disablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(thisPlugin)));
                return;
            }
        }
    }

    /**
     *
     * Gets the version of the running server. Handy for setting up nms
     *
     * @return the server version (e.g. "v1_17_R1"). If there's trouble finding the version, an empty string will be returned.
     */
    public static String getServerVersion() {
        try {
            return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();//weird version
            return "";
        }
    }

}
