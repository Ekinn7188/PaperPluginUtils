package jeeper.utils.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
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

    /**
     * Read the config file and set its values to the defaults
     * @param plugin the plugin
     * @param configName the name of the yaml file to read from
     */
    public void readDefaults(JavaPlugin plugin, String configName){
        InputStream inputStream = plugin.getResource(configName);
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(inputStream);
        config.addDefaults(map);
    }

}
