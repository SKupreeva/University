public class Factory {
    public infoType getType(String inp) {
        infoType it = null;
        if(inp.equals("Success")){
            it = new success();
        } else if(inp.equals("Fail")) {
            it = new fail();
        }
        return it;
    }
}
