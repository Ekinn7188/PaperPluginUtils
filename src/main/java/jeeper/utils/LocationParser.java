package jeeper.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationParser {

    /**
     *
     * Turn a location into comma seperated coordinates that are rounded to the nearest <b>hundredth</b>
     *
     * @param loc The Bukkit location
     * @return A string of comma seperated coordinates
     */
    public static String roundedLocationToString(Location loc){
        return loc.getWorld().getName() + ", " + Math.round(loc.getX()*100)/100.0 + ", " + Math.round(loc.getY()*100.0)/100.0 + ", " + Math.round(loc.getZ()*100.0)/100.0 + ", " + Math.round(loc.getYaw()*100.0)/100.0 + ", " + Math.round(loc.getPitch()*100.0)/100.0;
    }

    /**
     *
     * Turn a location into comma seperated coordinates that is <b>not rounded</b>
     *
     * @param loc The Bukkit location
     * @return A string of comma seperated coordinates
     */
    public static String locationToString(Location loc){
        return loc.getWorld().getName() + ", " + loc.getX() + ", " + loc.getY() + ", " + loc.getZ() + ", " + loc.getYaw() + ", " + loc.getPitch();
    }

    /**
     *
     * Turn a comma seperated coordinate list into a <b>Location</b>
     *
     * @param value a comma seperated list of coordinates ("WorldName, X, Y, Z, Yaw, Pitch")
     * @return the location based on the string list.
     */
    public static Location stringToLocation(String value){
        String[] arg = value.replaceAll(" ", "").split(",");
        return new Location(Bukkit.getWorld(arg[0]), Double.parseDouble(arg[1]), Double.parseDouble(arg[2]), Double.parseDouble(arg[3]), Float.parseFloat(arg[4]), Float.parseFloat(arg[5]));
    }

}
