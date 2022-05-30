package jeeper.utils;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class takes apart a permission string and grabs the largest number at the end, if any exists
 */
public class PermissionInfo {
    /**
     * @param player The org.bukkit.entity.Player
     * @param permissionPrefix The prefix before the number. For example, "plugin.sethome."
     * @return Returns an integer for the largest number found. If no number was found, -1 is returned
     */
    public static int getPermissionNumber(Player player, String permissionPrefix){
        List<Integer> allNumbers = new ArrayList<>();
        int largestNumber;
        player.recalculatePermissions();
        for (PermissionAttachmentInfo attachmentInfo: player.getEffectivePermissions()) {//find all values that start with the prefix and get the number
            String permission = attachmentInfo.getPermission();
            if (permission.startsWith(permissionPrefix)){//example: dirtlands.sethome.
                allNumbers.add(Integer.parseInt(permission.substring(permission.lastIndexOf(".") + 1)));
            }
        }
        if (allNumbers.isEmpty()){//if there were no results, -1 is returned
            largestNumber = -1;
        } else { //otherwise, sort the list from before and get the largest value
            Collections.sort(allNumbers);
            largestNumber = allNumbers.get(allNumbers.size()-1);
        }

        return largestNumber;
    }
}
