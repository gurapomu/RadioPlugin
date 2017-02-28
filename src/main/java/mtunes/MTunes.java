package mtunes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MTunes extends JavaPlugin {
    private static ReceiverManager receiverManager;
    private static TransmitterManager transmitterManager;
    private static MTunes instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        receiverManager = new ReceiverManager();
        transmitterManager = new TransmitterManager();
        getServer().getPluginManager().registerEvents(new PlayerBlockPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerBlockBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerRightClickBlockEvent(), this);
        System.out.println("mTunes is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("soundtest")){
            if(!(sender instanceof Player)){

            }
            else{
                Player player = (Player) sender;
                if(args.length != 1 && args.length != 3){
                    System.out.println(args.length);
                    return false;
                }
                Bukkit.getWorld("world").playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 100, Float.valueOf(args[0]));
                return true;
            }
        }
        return false;
    }

    public static MTunes getInstance(){
        return instance;
    }

    public static ReceiverManager getReceiverManager(){
        return receiverManager;
    }
    public static TransmitterManager getTransmitterManager(){
        return transmitterManager;
    }
}
