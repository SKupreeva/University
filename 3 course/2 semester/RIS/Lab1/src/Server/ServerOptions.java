package Server;

import Constants.Const;
import OptionsProvider.OptionsProvider;

public class ServerOptions extends OptionsProvider {

    public void setRegistryPortNumber(Long port){
        Const.REGISTRY_PORT_NUMBER = port.intValue();
    }

    public void setStubPortNumber(Long port){
        Const.STUB_PORT_NUMBER = port.intValue();
    }

    public void setUnicBindingName(String name){
        Const.UNIC_BINDING_NAME = name;
    }
}
