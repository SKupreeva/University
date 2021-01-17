package com.company;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    new InsertDLG().setVisible(true);
                }
                catch(Exception e){}
            }
        });
    }
}
