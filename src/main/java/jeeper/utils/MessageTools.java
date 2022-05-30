package jeeper.utils;

import jeeper.utils.config.Config;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageTools {

    public static Pattern urlPattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);

    /**
     *
     * Parse a <b>string</b> from a <b>config file</b> and turn it into a <b>component</b><br>
     * <br>
     * Uses both minecraft and kyori minimessage color codes <br>
     * @param config the config file to parse from
     * @param path the path to parse from (e.g. Path.Path.Path)
     * @param placeholders what to replace in the message (check {@link net.kyori.adventure.text.minimessage.tag.resolver.Placeholder#parsed(String, String)})
     * @return a component based on all the criteria specified
     */
    public static Component parseFromPath(Config config, @NotNull String path, @NotNull TagResolver... placeholders){
        String message = getString(config, path);
        return parseText(message, placeholders);
    }

    /**
     * Parse a <b>string</b> and turn it into a <b>component</b> <br>
     * <br>
     * Uses both minecraft and kyori minimessage color codes <br>
     *
     * @param message the string to parse
     * @param placeholders what to replace in the message (check {@link net.kyori.adventure.text.minimessage.tag.resolver.Placeholder#parsed(String, String)})
     * @return a component based on the criteria specified
     */
    public static Component parseText(@NotNull String message, @NotNull TagResolver... placeholders){
        if (message.contains("&")){
            message = message.replaceAll("&a", "<green>")
                    .replaceAll("&b", "<aqua>")
                    .replaceAll("&c", "<red>")
                    .replaceAll("&d", "<light_purple>")
                    .replaceAll("&e", "<yellow>")
                    .replaceAll("&f", "<white>")
                    .replaceAll("&1", "<dark_blue>")
                    .replaceAll("&2", "<dark_green>")
                    .replaceAll("&3", "<dark_aqua>")
                    .replaceAll("&4", "<dark_red>")
                    .replaceAll("&5", "<dark_purple>")
                    .replaceAll("&6", "<gold>")
                    .replaceAll("&7", "<gray>")
                    .replaceAll("&8", "<dark_gray>")
                    .replaceAll("&9", "<blue>")
                    .replaceAll("&0", "<black>")
                    .replaceAll("&k", "<obfuscated>")
                    .replaceAll("&l", "<bold>")
                    .replaceAll("&m", "<strikethrough>")
                    .replaceAll("&n", "<underlined>")
                    .replaceAll("&o", "<italic>")
                    .replaceAll("&r", "<reset>");
        }

        TagResolver resolver = TagResolver.resolver(placeholders);
        return MiniMessage.miniMessage().deserialize(message, resolver);
    }

    /**
     * Get a string from a specific path in a config file<br>
     * <br>
     * Uses both minecraft and kyori minimessage color codes
     * @param config the config file to search in
     * @param path the path in the config file
     * @return the string specified in the config file
     */
    public static String getString(Config config, @NotNull String path){
        try{
            return Objects.requireNonNull(config.get().getString(path));
        } catch (NullPointerException e){
            Set<OfflinePlayer> ops = Bukkit.getOperators();
            while (ops.iterator().hasNext()) {
                OfflinePlayer operator = ops.iterator().next();
                if (operator.isOnline()) {
                    Objects.requireNonNull(Bukkit.getPlayer(operator.getUniqueId())).sendMessage(Component.text()
                            .content("Config \"" + path + "\" does not exist. Check logs for details")
                            .color(NamedTextColor.RED).build());
                }
                ops.remove(ops.iterator().next());
            }
            e.printStackTrace();
            return "";
        }
    }

    /**
     * get the urls from string
     * @param input the string to search for urls
     * @return a list of urls
     */
    public static List<String> fetchURLs(String input) {
        Matcher urlMatcher = urlPattern.matcher(input);
        List<String> urls = new ArrayList<>();
        while (urlMatcher.find()) urls.add(urlMatcher.group());
        return urls;

    }

}
