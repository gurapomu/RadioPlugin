package mtunes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gurapomu on 2017/02/28.
 */
public class TransmitterManager {
    private List<Transmitter> transmitterList;

    public TransmitterManager(){
        transmitterList = new ArrayList<>();
    }

    public List<Transmitter> getTransmitterList(){
        return transmitterList;
    }

    public void addTransmitter(Transmitter transmitter){
        transmitterList.add(transmitter);
    }

    public void removeTransmitter(Transmitter transmitter){
        transmitterList.remove(transmitter);
    }
}
