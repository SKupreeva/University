package app.entities;

public class Result {
    private double x;
    private String fun;
    private String way;
    private double res = 0;

    public Result() {
    }

    public Result(String XS, String Fun, String Way) {
        double X = Double.parseDouble(XS);
        x = X;
        this.fun = Fun;
        this.way = Way;
        countResult(x, fun, way);
    }

    public double getX() {
        return x;
    }

    public void setX(double X) {
        x = X;
    }

    public String getFun() {
        return this.fun;
    }

    public void setFun(String Fun) {
        this.fun = Fun;
    }

    public String getWay() {
        return this.way;
    }

    public void setWay(String Way) {
        this.way = Way;
    }

    public double getRes() {
        return res;
    }

    void countResult(double x, String fun, String way) {
        switch (fun) {
            case "sin(x)": res = Math.sin(x);
                break;
            case "cos(x)": res = Math.cos(x);
                break;
            case "tg(x)": res = Math.tan(x);
                break;
            case "ctg(x)": res = 1.0/Math.atan(x);
                break;
            default: res = 0;
                break;
        }
        if(way.equals("degrees"))
            res = Math.toDegrees(res);
    }
}
