<%-- 
    Document   : graph
    Created on : 1 May, 2017, 8:07:17 PM
    Author     : Prashant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page  import="java.awt.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="org.jfree.chart.*" %>
<%@ page  import="org.jfree.chart.axis.*" %>
<%@ page  import="org.jfree.chart.entity.*" %>
<%@ page  import="org.jfree.chart.labels.*" %>
<%@ page  import="org.jfree.chart.plot.*" %>
<%@ page  import="org.jfree.chart.renderer.category.*" %>
<%@ page  import="org.jfree.chart.urls.*" %>
<%@ page  import="org.jfree.data.category.*" %>
<%@ page  import="org.jfree.data.general.*" %>

<%
  double po=(Double)(request.getAttribute("pos"));
  double no=(Double)(request.getAttribute("neg"));
%>
<%
   
  final double[][] data = new double[][]{
  {po},{no}};

 
  final CategoryDataset dataset;
  dataset = DatasetUtilities.createCategoryDataset("Sentiments","", data);

  JFreeChart chart = null;
  BarRenderer renderer = null;
  CategoryPlot plot = null;


  final CategoryAxis categoryAxis = new CategoryAxis("Sentiment1: Positive     Sentiment1: Negative");
  final ValueAxis valueAxis = new NumberAxis("Percentage");
  renderer = new BarRenderer();

  plot = new CategoryPlot(dataset, categoryAxis, valueAxis, 
  renderer);
  plot.setOrientation(PlotOrientation.VERTICAL);
  chart = new JFreeChart("User Analysis", JFreeChart.DEFAULT_TITLE_FONT, 
  plot, true);

  chart.setBackgroundPaint(new Color(249, 231, 236));

  Paint p5 = new GradientPaint(
 0.0f, 0.0f, new Color(16, 89, 172), 0.0f, 0.0f, new Color
   (201, 201, 244));

  renderer.setSeriesPaint(1, p5);

  Paint p6 = new GradientPaint(
  0.0f, 0.0f, new Color(255, 35, 35), 0.0f, 0.0f, new Color
   (255, 180, 180));

  renderer.setSeriesPaint(2, p6);
  
   
  plot.setRenderer(renderer);
  try {
  final ChartRenderingInfo info = new ChartRenderingInfo
   (new StandardEntityCollection());
 final File file1 = new File("F://netbeans//minor2//web//WEB-INF/user.png");
  ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
  } catch (Exception e) {
 out.println(e);
  }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Analyzed</title>
    </head>
    <body>
        <IMG SRC="F://netbeans//minor2//web//WEB-INF/user.png" 
             WIDTH="600" 
        HEIGHT="400" BORDER="0" USEMAP="#chart">
        
    </body>
</html>
    
