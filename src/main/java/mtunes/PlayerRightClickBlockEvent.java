package mtunes;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by gurapomu on 2017/02/28.
 */
public class PlayerRightClickBlockEvent implements Listener {
    @EventHandler
    public void onPlayerInteracting(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            Player player = event.getPlayer();
            player.sendRawMessage(block.getType().name());
            if(!(player.getInventory().getItemInMainHand().getType().isBlock())){
                Transmitter transmitter = MTunes.getTransmitterManager().getTransmitterList().stream().filter(t -> t.getCoreBlockLocation().equals(block.getLocation())).findFirst().orElse(null);
                if(transmitter != null){
                    transmitter.transmit();
                    player.sendRawMessage("Start transmission");
                }
                else {
                    player.sendRawMessage("null");
                }
            }
        }
    }
}
