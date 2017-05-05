
package MyProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

/**
 *
 * @author Prashant
 */
public class favourite1 extends HttpServlet {

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
        double h1=0,a1=0,l1=0,d1=0,hp1=0,ap1=0,lp1=0,dp1=0,p1=0,p2=0,p3=0,p4=0;
        response.setContentType("text/html;charset=UTF-8");
        double hp = (Double)request.getAttribute("Happy%");
        double ap = (Double)request.getAttribute("Angry%");
        double lp = (Double)request.getAttribute("Lonely%");
        double dp = (Double)request.getAttribute("Depression%");
        String s=(String)request.getAttribute("searchTerm");
        try (PrintWriter out = response.getWriter()) {
            
            
            
            if(!"".equals(s)){
                
                get_favourite gf=new get_favourite();
                ArrayList<String> timeline= gf.find(s);
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
                        h1++;
                    else if(output.equals("Angry"))
                        a1++;
                    else if(output.equals("Lonely"))
                        l1++;
                    else
                        d1++;
                    /*out.println(output);
                    out.println(h1);
                    out.println(a1);
                    out.println(l1);
                    out.println(d1);*/
                }
                hp1=(h1/timeline.size())*100;
                ap1=(a1/timeline.size())*100;
                lp1=(l1/timeline.size())*100;
                dp1=(d1/timeline.size())*100;
               /* out.println(hp1);
                out.println(ap1);
                out.println(lp1);
                out.println(dp1);*/
                p1=(hp1+hp)/2;
                p2=(ap1+ap)/2;
                p3=(lp1+lp)/2;
                p4=(dp1+dp)/2;
                /*out.println(p1);
                out.println(p2);
                out.println(p3);
                out.println(p4);*/
               request.setAttribute("Happy Percentage",p1);
                request.setAttribute("Angry Percentage",p2);
                request.setAttribute("Lonely Percentage",p3);
                request.setAttribute("Depression Percentage",p4);
               
       
           
                RequestDispatcher rd = request.getRequestDispatcher("/finaluser.jsp");
            
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
