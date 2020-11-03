package main;
import hieracy.*;

public class My_class {
    public static void main(String[] args) {
        cornivorous c = new cornivorous();
        c.showInfoCornivorous();
        c.numberDiff(342435, 234521);
        c.numberSum(12345, 65623);
        c.numberFeMale(2346517, 53457538);

        fish f = new fish();
        f.showInfoFish();
        f.numberDiff(45435435, 523415);
        f.numberDiff(454532435, 523415);
        f.numberSum(2143651, 425467);

        gidrovit g = new gidrovit();
        g.showInfo();
        g.showTableGidrovit();
        g.showString("Гидрофит\n");
        g.average(23445, 36435235);
        g.numberSum(215312246, 3764623);
        g.ratioLess(24345, 325461);
        g.redactString("Гидрофит");

        herbivorous h = new herbivorous();
        h.showInfoHerbivorous();
        h.numberDiff(2514543, 6246734);
        h.numberFeMale(2531462, 615635467);
        h.numberFeMale(2531462, 615235467);
        h.numberSum(36467, 3265732);

        symbiotrop s = new symbiotrop();
        s.showInfo();
        s.showStat();
        s.showTableSymbiotrop();
        s.showString("Симбиотроп\n");
        s.redactString("Симбиотроп");
        s.average(32456, 623576);
        s.numberDiff(243546347, 5268546);
        s.numberSum(3656573, 23578);
        s.ratioLess(267457236, 236456);
    }

    public void showTable(){
        symbiotrop s = new symbiotrop();
        System.out.println("Some table");
        s.numberDiff(243546347, 52644346);
        s.numberSum(3656573, 23578);
        s.ratioLess(264357236, 236456);
    }
}
