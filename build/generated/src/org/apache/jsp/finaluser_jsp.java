package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.awt.*;
import java.io.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.urls.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;

public final class finaluser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 double p1=(Double)request.getAttribute("Happy Percentage");
        double p2=(Double)request.getAttribute("Angry Percentage");
        double p3=(Double)request.getAttribute("Lonely Percentage");
        double p4=(Double)request.getAttribute("Depression Percentage");
//double nc=(Double)request.getAttribute("Neutral");
        out.println("\t\t\n Happy Per.: "+p1+"\t\t\n Angry Per.: "+p2+"\t\t\n Lonely Per.: "+p3+"\t\t\n Depression Per.: "+p4);
        
      out.write('\n');
      out.write('\n');

  final double[][] data = new double[][]{
  {p1, p2, p3, p4}
 };

  final CategoryDataset dataset = 
   DatasetUtilities.createCategoryDataset(
  "Happy", "", data);

  JFreeChart chart = null;
  BarRenderer renderer = null;
  CategoryPlot plot = null;


  final CategoryAxis categoryAxis = new CategoryAxis("Moods");
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

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>User Analyzed</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1></h1>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("    \n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
