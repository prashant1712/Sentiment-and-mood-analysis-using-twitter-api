/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyProject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.TwitterException;

/**
 *
 * @author Prashant
 */
public class user1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
            final String angryFile = "/WEB-INF/angry.txt";
            final String depressionFile = "/WEB-INF/depression.txt";
            final String happyFile= "/WEB-INF/happy.txt";
            final String lonelyFile= "/WEB-INF/lonely.txt";
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double h=0,a=0,l=0,d=0,hp=0,ap=0,lp=0,dp=0;
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             //String s="spmailme4";
          //out.print("hiuy");
            //String s="spmailme4";
                //out.println(s);
            String s=request.getParameter("search");
            
            if(!"".equals(s)){
                String searchTerm =s;
                searchTerm = searchTerm+"&lang:en";
                user_timeline ut = new user_timeline();
                ArrayList<String> timeline= ut.extract(s);
                Map<String, String> trainingFiles = new HashMap<>();
                trainingFiles.put("Lonely", lonelyFile);
                trainingFiles.put("Happy", happyFile);
                trainingFiles.put("Angry", angryFile);
                
                trainingFiles.put("Depression", depressionFile);
                Map<String, String[]> train1 = new HashMap<>();
                for(Map.Entry<String, String> entry : trainingFiles.entrySet()) {
                    train1.put(entry.getKey(), readLines(entry.getValue()));
                }
                NaiveBayes nb1 = new NaiveBayes();
                nb1.setChisquareCriticalValue(2.366);
                nb1.train(train1);
                System.out.println("hi");
                NaiveBayesKnowledgeBase knowledgeBase1 = nb1.getKnowledgeBase();
                nb1 = null;
                train1 = null;
                nb1 = new NaiveBayes(knowledgeBase1);
                for(int i=0;i<timeline.size();i++){
                    
                    
                    String output = nb1.predict(timeline.get(i));
                    //out.println(timeline.get(i));
                    if(output.equals("Happy"))
                        h++;
                    else if(output.equals("Angry"))
                        a++;
                    else if(output.equals("Lonely"))
                        l++;
                    else
                        d++;
                    out.println(output);
                    
                }
                hp=(h/timeline.size())*100;
                ap=(a/timeline.size())*100;
                lp=(l/timeline.size())*100;
                dp=(d/timeline.size())*100;
                
                request.setAttribute("Happy%",hp);
                request.setAttribute("Angry%",ap);
                request.setAttribute("Lonely%",lp);
                request.setAttribute("Depression%",dp);
                request.setAttribute("searchTerm",s);
                RequestDispatcher rd = request.getRequestDispatcher("favourite1");
                rd.forward(request,response);
            }
        }
                        catch(IOException e1){
                        }
                        
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
        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
