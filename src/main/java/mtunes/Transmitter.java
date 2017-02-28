package mtunes;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gurapomu on 2017/02/28.
 */
public class Transmitter {
    private byte channel;
    private Location coreBlockLocation;
    private List<Block> blocks;
    private List<Block> powerBlocks;

    public Transmitter(List<Block> blocks, Location coreBlockLocation){
        channel = 0;
        this.blocks = blocks;
        this.coreBlockLocation = coreBlockLocation;
        powerBlocks = new ArrayList<>();
    }

    public List<Block> getLocations(){
        return blocks;
    }

    public Location getCoreBlockLocation(){
        return coreBlockLocation;
    }

    public List<Block> getPowerBlocks(){
        return powerBlocks;
    }

    public void addPowerBlock(Block block){
        powerBlocks.add(block);
    }

    public void removePowerBlock(Block block){
        powerBlocks.remove(block);
    }

    public void transmit(){
        MTunes.getReceiverManager().getReceiverList().forEach(r -> {
            if(r.getChannel() != channel)   return;
            r.play();
        });
    }

}
