
package MyProject;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.TwitterException;
/**
 *
 * @author Prashant
 */
public class normal extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TwitterException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            //out.println("Welcome");
            String s=request.getParameter("search");
            //out.println(s);
            if(!"".equals(s)){
			try{
              //              out.println("pras");
				
		
		//Get Tweets given search term
		TwitterData t = new TwitterData();
		String searchTerm =s;
                
		searchTerm = searchTerm+"&lang:en";
		//out.println(searchTerm);
                ArrayList<String> tweets = t.search(searchTerm);
                request.setAttribute("tweets",tweets);
                RequestDispatcher rd = request.getRequestDispatcher("accept");
                rd.forward(request,response);
               
		
	  }
                        catch(TwitterException e1){
                        }
                        
            }
        }
    }

		
       	
    
    
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TwitterException ex) {
            Logger.getLogger(normal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TwitterException ex) {
            Logger.getLogger(normal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

    