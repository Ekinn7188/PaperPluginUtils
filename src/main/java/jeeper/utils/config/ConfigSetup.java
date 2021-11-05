package jeeper.utils.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ConfigSetup {

    private final File file;
    private FileConfiguration config;

    /**
     * generate or find custom config
     * @param configName the name of your config file
     * @param pluginName the name of the plugin (located in plugin.yml)
     */
    public ConfigSetup(String configName, String pluginName){
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(pluginName)).getDataFolder(), configName + ".yml");

        if (!file.exists()){
            try{
                if (file.createNewFile()){
                    Bukkit.getLogger().info("File \"" + configName + ".yml\" Created Successfully!");
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration get(){
        return config;
    }

    /**
     * saves the config file
     */
    public void save(){
        try{
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reload the config file
     */
    public void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }


}
