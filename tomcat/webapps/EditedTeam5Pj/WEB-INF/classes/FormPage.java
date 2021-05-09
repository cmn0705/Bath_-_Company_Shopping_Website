import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class FormPage extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter msg = res.getWriter();
        msg.println("<h2>Your Feedback Is Saved<h2>");
        msg.println("<table border=1>");

        Enumeration formdata = req.getParameterNames();
        String PARAM = null;
        String feedbackData="";
        while (formdata.hasMoreElements()){
            PARAM = (String) formdata.nextElement();
            msg.println("<tr><td>"+PARAM+"</td><td>"+req.getParameter(PARAM)+"</td></tr>");
            feedbackData+= PARAM + ": " + req.getParameter(PARAM) + "\n";
            } // while
        msg.println("</table>");
        BufferedWriter outFile = new BufferedWriter(new FileWriter("feedbackData", true));
        outFile.write(feedbackData);
        outFile.close();
        } // doPost
    } // FormPage
