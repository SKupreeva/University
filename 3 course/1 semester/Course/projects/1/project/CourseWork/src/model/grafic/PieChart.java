package model.grafic;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class PieChart extends  ApplicationFrame {
    public PieChart( String title ) {
        super( title );
        //setContentPane(createDemoPanel( ));
    }
    private static PieDataset createDataset(String[] keys, Integer[] values) {
        int sum = 0;
        for (int i = 0; i < values.length; i++)
            sum += values[i];
        DefaultPieDataset dataset = new DefaultPieDataset( );
        for (int i = 0; i < keys.length; i++) {
            dataset.setValue(keys[i] + " " + values[i] * 100 / sum + "%", new Double(values[i]));
        }
        return dataset;
    }
    private static JFreeChart createChart(PieDataset dataset )
    {
        JFreeChart chart = ChartFactory.createPieChart(
                "Планируемые рейсы",  // chart title
                dataset,        // data
                true,           // include legend
                true,
                false);

        return chart;
    }
    public static JPanel createDemoPanel(String[] keys, Integer[] values)
    {
        JFreeChart chart = createChart(createDataset(keys, values) );
        return new ChartPanel( chart );
    }
//    public static void main( String[ ] args )
//    {
//        PieChart demo = new PieChart( "Mobile Sales" );
//        demo.setSize( 560 , 367 );
//        RefineryUtilities.centerFrameOnScreen( demo );
//        demo.setVisible( true );
//    }
}
