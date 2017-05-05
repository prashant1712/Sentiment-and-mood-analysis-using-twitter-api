package MyProject;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.TwitterException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.System.out;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;


/**
 *
 * @author Prashant
 */
public class accept extends HttpServlet {

    final String positiveFile = "/WEB-INF/pos-words.txt";
            final String negativeFile = "/WEB-INF/neg-words.txt";
           // final String neutralFile = "/WEB-INF/neutral-words.txt";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html");
        double p=0, n=0,neutral=0,po=0,no=0,nc=0;
        
        
        try (PrintWriter out = response.getWriter()) {
            
            
            ArrayList<String> tweets = (ArrayList<String>)request.getAttribute("tweets");
           // out.print(tweets.size());
		
		//TestData test = new TestData();
		
		
                Map<String, String> trainingFiles = new HashMap<>();
                trainingFiles.put("Positive", positiveFile);
                trainingFiles.put("Negative", negativeFile);
                
                Map<String, String[]> train1 = new HashMap<>();
                for(Map.Entry<String, String> entry : trainingFiles.entrySet()) {
                    train1.put(entry.getKey(), readLines(entry.getValue()));
                }
                NaiveBayes nb1 = new NaiveBayes();
                nb1.setChisquareCriticalValue(0.455);
                nb1.train(train1);
                System.out.println("hi");
                NaiveBayesKnowledgeBase knowledgeBase1 = nb1.getKnowledgeBase();
                nb1 = null;
                train1 = null;
                nb1 = new NaiveBayes(knowledgeBase1);
                for(int i=0;i<tweets.size();i++){
                    
                    
                    String output = nb1.predict(tweets.get(i));
                    //out.println(timeline.get(i));
                    if(output.equals("Positive"))
                        p++;
                    if(output.equals("Negative"))
                        n++;
                    
                    
                        
                    out.println(output);
                      
                    
                }
                po=(p/tweets.size())*100;
                no=(n/tweets.size())*100;
                //nc=(neutral/tweets.size())*100;
            //     out.println(po);
            //out.println(no);
                request.setAttribute("Positive Percentage",po);
        request.setAttribute("Negative Percentage",no);
       
           
            RequestDispatcher rd = request.getRequestDispatcher("/analyse.jsp");
            rd.forward(request, response);
            
              
            }
        
             
		//out.println("hi");;
                    //end for
	//end method
                
             

    
    }
     public String[] readLines(String file) throws IOException {
        List<String> lines= new ArrayList<>();
        ServletContext context = this.getServletContext();
            InputStream is8 = context.getResourceAsStream(file);
            if (is8 != null) {
                InputStreamReader isr8 = new InputStreamReader(is8);
                
        
        try (BufferedReader bf8 = new BufferedReader(isr8)) {
            
            String line;
            while ((line = bf8.readLine()) != null) {
                lines.add(line);
            }
        }
            }
        return lines.toArray(new String[lines.size()]);
    }
    
       // HttpServletResponse.sendRedirect("/analyse.jsp");
			
		
             //this.getServletContext().getRequestDispatcher("/analyse.jsp").include(request, response);
                
        
    
  

        
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       

        /*RequestDispatcher rd = request.getRequestDispatcher("accept");
        rd.forward(request,response);
        out.println(p);*/
        
			
        
                
         /*request.setAttribute("Positive Percentage", p);
         request.setAttribute("Negative Percentage", n);
         request.getRequestDispatcher("analyse.jsp").forward(request, response);
        */processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
