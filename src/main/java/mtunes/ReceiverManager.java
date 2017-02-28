package mtunes;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gurapomu on 2017/02/27.
 */
public class ReceiverManager {
    private List<Receiver> receiverList;

    public ReceiverManager(){
        receiverList = new ArrayList<>();
    }

    public List<Receiver> getReceiverList(){
        return receiverList;
    }

    public void addReceiver(Receiver receiver){
        receiverList.add(receiver);
    }

    public void removeReceiver(Receiver receiver){
        receiverList.remove(receiver);
    }
}
