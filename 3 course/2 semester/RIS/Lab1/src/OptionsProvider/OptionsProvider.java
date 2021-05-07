package OptionsProvider;

import java.io.FileReader;

import static Printer.Printer.println;

import Constants.Const;
import Server.ServerOptions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OptionsProvider implements IOptionsProvider{
    @Override
    public void getOptions() {
        var parser = new JSONParser();
        var serverOptions = new ServerOptions();
        try {
            var obj = parser.parse(new FileReader(Const.OPTIONS_FILE));
            var jsonObject = (JSONObject) obj;
            serverOptions.setUnicBindingName((String) jsonObject.get("name"));
            serverOptions.setStubPortNumber((Long) jsonObject.get("stub"));
            serverOptions.setRegistryPortNumber((Long) jsonObject.get("registry"));
        } catch (Exception e) {
            println("Error! JSON reading exception!\n" + e.toString());
        }
    }
}
