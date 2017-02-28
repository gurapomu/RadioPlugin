package mtunes;

import a.test.testtest.Testtest;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gurapomu on 2017/02/27.
 */
public class Receiver {
    private boolean running = false;
    private byte channel;
    private Location coreBlockLocation;
    private List<Block> blocks;
    private List<Block> speakerBlocks;
    public Receiver(List<Block> blocks, Location coreBlockLocation){
        channel = 0;
        this.blocks = blocks;
        this.coreBlockLocation = coreBlockLocation;
        speakerBlocks = new ArrayList<>();
    }

    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public byte getChannel(){
        return channel;
    }

    public void setChannel(byte channel){
        this.channel = channel;
    }

    public List<Block> getLocations(){
        return blocks;
    }

    public Location getCoreBlockLocation(){
        return coreBlockLocation;
    }

    public List<Block> getSpeakerBlocks(){
        return speakerBlocks;
    }

    public void addSpeaker(Block block){
        speakerBlocks.add(block);
    }

    public void removeSpeaker(Block block){
        speakerBlocks.remove(block);
    }

    public void play(){
        if(running == true) return;
        running = true;
        Bukkit.broadcastMessage("play");
        List<Float> pitches = new ArrayList<>(Testtest.pitches);
        if(pitches.size() == 0) return;
        PlayNoteTask playNoteTask = new PlayNoteTask(MTunes.getInstance());
        playNoteTask.setPitches(pitches);
        playNoteTask.setReceiver(this);
        MTunes.getInstance().getServer().getScheduler().runTaskLater(MTunes.getInstance(), playNoteTask, 0L);
    }
}
