package Logic;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TwoVowelsInterface extends Remote {
    String changeString(String input) throws RemoteException;
}
