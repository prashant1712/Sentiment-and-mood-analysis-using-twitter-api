package MyProject;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
 
public class bar_graph {
    public void bar_graph(){
  
    try {
                
                /* Step - 1: Define the data for the bar chart  */
                DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();
                my_bar_chart_dataset.addValue(93.3, "chi=2.366", "Happy");
                my_bar_chart_dataset.addValue(0, "chi=2.366", "Angry");
                my_bar_chart_dataset.addValue(6.66, "chi=2.366", "Lonely");
                my_bar_chart_dataset.addValue(0, "chi=2.366", "Depression");
                
                 my_bar_chart_dataset.addValue(88.33, "chi=7.81", "Happy");
                my_bar_chart_dataset.addValue(7.5, "chi=7.81", "Angry");
                my_bar_chart_dataset.addValue(4.167, "chi=7.81", "Lonely");
                my_bar_chart_dataset.addValue(0, "chi=7.81", "Depression");
                
                 my_bar_chart_dataset.addValue(92.5, "chi=15", "Happy");
                my_bar_chart_dataset.addValue(7.5, "chi=15", "Angry");
                my_bar_chart_dataset.addValue(0, "chi=15", "Lonely");
                my_bar_chart_dataset.addValue(0, "chi=15", "Depression");
                
                 my_bar_chart_dataset.addValue(100, "chi=20", "Happy");
                my_bar_chart_dataset.addValue(0, "chi=20", "Angry");
                my_bar_chart_dataset.addValue(0, "chi=20", "Lonely");
                my_bar_chart_dataset.addValue(0, "chi=20", "Depression");
                
                
                /* Step -2:Define the JFreeChart object to create bar chart */
                JFreeChart BarChartObject=ChartFactory.createBarChart("State Of Mind Analysis for different critical chi square values ","","Percentage",my_bar_chart_dataset,PlotOrientation.VERTICAL,true,true,false);                
                          
                 /* Step -3: Write the output as PNG file with bar chart information */                
                 int width=640; /* Width of the image */
                 int height=480; /* Height of the image */                
                 File BarChart=new File("F://netbeans//minor2//web//WEB-INF/output_chart.png");              
                 ChartUtilities.saveChartAsPNG(BarChart,BarChartObject,width,height); 
         }
      catch (Exception i)
         {
            System.out.println(i);
         }    
 
   
    
}
    public static void main(String args[]){
        bar_graph obj=new bar_graph();
        obj.bar_graph();
    }
}