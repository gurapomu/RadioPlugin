package mtunes;

import com.sun.org.apache.regexp.internal.RE;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by gurapomu on 2017/02/27.
 */
public class PlayerBlockPlaceEvent implements Listener {
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){
        Block block = event.getBlockPlaced();

        if(block.getType().equals(Material.GOLD_BLOCK)){
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();
            Material[][][] materials = {{{Material.AIR, Material.AIR, Material.AIR},
                                         {Material.AIR, Material.IRON_FENCE, Material.AIR},
                                         {Material.AIR, Material.AIR, Material.AIR}},
                                        {{Material.AIR, Material.IRON_FENCE, Material.AIR},
                                         {Material.IRON_FENCE, Material.IRON_FENCE, Material.IRON_FENCE},
                                         {Material.AIR, Material.IRON_FENCE, Material.AIR}}};
//            IntStream.range(0,17).forEach(i -> {
//                System.out.println(i + " " + (i%9)/3 + " " + i/9 + " " + i%3);
//                System.out.println(materials[i/9][i%3][(i%9)/3].toString() + " " + new Location(block.getWorld(), x+((i%9)/3)-1, y+(i/9)+1, z+(i%3)-1).getBlock().);
//            });
            if(IntStream.range(0,17).anyMatch(i -> !(materials[i/9][i%3][(i%9)/3]).equals(new Location(block.getWorld(), x+((i%9)/3)-1, y+(i/9)+1, z+(i%3)-1).getBlock().getType())))    return;
            List<Block> blocks = new ArrayList<>();
            blocks.add(block);
            IntStream.range(0,17).filter(i -> materials[i/9][i%3][(i%9)/3].equals(Material.IRON_FENCE)).forEach(i -> blocks.add(new Location(block.getWorld(), x+((i%9)/3)-1, y+(i/9)+1, z+(i%3)-1).getBlock()));
            MTunes.getReceiverManager().addReceiver(new Receiver(blocks, block.getLocation()));
            event.getPlayer().sendRawMessage("Complete making receiver!");
        }
        else if(block.getType().equals(Material.NOTE_BLOCK)){
            List<Block> aroundBlock = Arrays.asList(block.getLocation().add(0,0,1).getBlock(), block.getLocation().add(0,0,-1).getBlock(), block.getLocation().add(1,0,0).getBlock(), block.getLocation().add(-1,0,0).getBlock());
            List<Receiver> receiverList = MTunes.getReceiverManager().getReceiverList();
            Receiver receiver = receiverList.stream().filter(r -> aroundBlock.stream().anyMatch(b -> r.getCoreBlockLocation().getBlock().equals(b) || r.getSpeakerBlocks().stream().anyMatch(l -> l.equals(b)))).findFirst().orElse(null);
            if(receiver != null){
                receiver.addSpeaker(block);
                event.getPlayer().sendRawMessage("Success to add speaker");
            }
        }
        else if(block.getType().equals(Material.QUARTZ_BLOCK)){
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();
            Material[][][] materials = {{{Material.AIR, Material.AIR, Material.AIR},
                    {Material.AIR, Material.IRON_FENCE, Material.AIR},
                    {Material.AIR, Material.AIR, Material.AIR}},
                    {{Material.AIR, Material.IRON_FENCE, Material.AIR},
                            {Material.IRON_FENCE, Material.IRON_FENCE, Material.IRON_FENCE},
                            {Material.AIR, Material.IRON_FENCE, Material.AIR}}};
            if(IntStream.range(0,17).anyMatch(i -> !(materials[i/9][i%3][(i%9)/3]).equals(new Location(block.getWorld(), x+((i%9)/3)-1, y+(i/9)+1, z+(i%3)-1).getBlock().getType())))    return;
            List<Block> blocks = new ArrayList<>();
            blocks.add(block);
            IntStream.range(0,17).filter(i -> materials[i/9][i%3][(i%9)/3].equals(Material.IRON_FENCE)).forEach(i -> blocks.add(new Location(block.getWorld(), x+((i%9)/3)-1, y+(i/9)+1, z+(i%3)-1).getBlock()));
            MTunes.getTransmitterManager().addTransmitter(new Transmitter(blocks, block.getLocation()));
            event.getPlayer().sendRawMessage("Complete making transmitter!");
        }
        else if(block.getType().equals(Material.REDSTONE_BLOCK)){
            List<Block> aroundBlock = Arrays.asList(block.getLocation().add(0,0,1).getBlock(), block.getLocation().add(0,0,-1).getBlock(), block.getLocation().add(1,0,0).getBlock(), block.getLocation().add(-1,0,0).getBlock());
            List<Transmitter> transmitterList = MTunes.getTransmitterManager().getTransmitterList();
            Transmitter transmitter = transmitterList.stream().filter(r -> aroundBlock.stream().anyMatch(b -> r.getCoreBlockLocation().getBlock().equals(b) || r.getPowerBlocks().stream().anyMatch(l -> l.equals(b)))).findFirst().orElse(null);
            if(transmitter != null){
                transmitter.addPowerBlock(block);
                event.getPlayer().sendRawMessage("Success to add powerBlock");
            }
        }
    }
}
