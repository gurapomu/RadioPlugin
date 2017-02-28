package mtunes;


import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gurapomu on 2017/02/28.
 */
public class PlayNoteTask implements Runnable {
    JavaPlugin plugin;
    List<Float> pitches = null;
    Receiver receiver = null;
    public PlayNoteTask(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public void setReceiver(Receiver receiver){
        this.receiver = receiver;
    }

    public void setPitches(List<Float> pitches){
        this.pitches = new ArrayList<>();
        this.pitches = pitches;
    }

    @Override
    public void run(){
        if(pitches == null || receiver == null){
            receiver.setRunning(false);
            return;
        }
        if(pitches.get(0) != 0F)    Bukkit.getWorld("world").playSound(receiver.getCoreBlockLocation(), Sound.BLOCK_NOTE_HARP, 1*((receiver.getSpeakerBlocks()).size()+1), pitches.get(0));
        pitches.remove(0);
        if(pitches.size() == 0){
            receiver.setRunning(false);
            return;
        }
        PlayNoteTask playNoteTask = new PlayNoteTask(MTunes.getInstance());
        playNoteTask.setPitches(pitches);
        playNoteTask.setReceiver(receiver);
        Bukkit.broadcastMessage("play" + pitches.size());
        MTunes.getInstance().getServer().getScheduler().runTaskLater(MTunes.getInstance(), playNoteTask, 15L);
    }
}
