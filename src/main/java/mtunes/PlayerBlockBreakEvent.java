package mtunes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by gurapomu on 2017/02/28.
 */
public class PlayerBlockBreakEvent implements Listener {
    @EventHandler
    public void onBlockBroken(BlockBreakEvent event){
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if(block.getType().equals(Material.IRON_FENCE) || block.getType().equals(Material.GOLD_BLOCK) || block.getType().equals(Material.QUARTZ_BLOCK)){
//            player.sendRawMessage("event");
//            MTunes.getReceiverManager().getReceiverList().forEach(r -> {
//                r.getLocations().forEach(l -> {
//
//                });
//            });

            Receiver receiver = MTunes.getReceiverManager().getReceiverList().stream().filter(r -> r.getLocations().stream().anyMatch(l -> l.equals(block))).findFirst().orElse(null);
            if(receiver != null){
                MTunes.getReceiverManager().removeReceiver(receiver);
                player.sendRawMessage("Receiver is broken");
                return;
            }
            Transmitter transmitter = MTunes.getTransmitterManager().getTransmitterList().stream().filter(r -> r.getLocations().stream().anyMatch(l -> l.equals(block))).findFirst().orElse(null);
            if(transmitter != null){
                MTunes.getTransmitterManager().removeTransmitter(transmitter);
                player.sendRawMessage("Transmitter is broken");
                return;
            }
        }
        else if(block.getType().equals(Material.NOTE_BLOCK)){
            Transmitter transmitter = MTunes.getTransmitterManager().getTransmitterList().stream().filter(r -> r.getPowerBlocks().stream().anyMatch(l -> l.equals(block))).findFirst().orElse(null);
            if(transmitter != null){
               transmitter.removePowerBlock(block);
               player.sendRawMessage("Speaker is removed");
            }
        }
        else if(block.getType().equals(Material.REDSTONE_BLOCK)){
            Transmitter transmitter = MTunes.getTransmitterManager().getTransmitterList().stream().filter(r -> r.getPowerBlocks().stream().anyMatch(l -> l.equals(block))).findFirst().orElse(null);
            if(transmitter != null){
                transmitter.removePowerBlock(block);
                player.sendRawMessage("PowerBlock is removed");
            }
        }
    }
}
