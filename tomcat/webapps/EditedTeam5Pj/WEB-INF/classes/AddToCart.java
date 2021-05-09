import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AddToCart extends HttpServlet {
    
     public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        final Map<String, Integer> price = new HashMap<String, Integer>();
        price.put("PURITY BAR", 6);
        price.put("AWAKE", 7);
        price.put("GRAPEFRUIT AND BASIL", 7);
        price.put("TEA TREE BLISS", 10);
        price.put("SWEET HARMONY", 7);
        price.put("COTTON TOWEL", 40);
        price.put("TURKISH COTTON TOWEL", 120);
        price.put("EGYPTIAN COTTON TOWEL", 150);
        
        HttpSession session = req.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new HashMap<String, Integer>());
        }

        Map<String, Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
        
        Enumeration formdata = req.getParameterNames();
        String itemName = null;
        int itemQuantity=0;
        while (formdata.hasMoreElements()){
            itemName = (String) formdata.nextElement();
            itemQuantity=Integer.parseInt(req.getParameter(itemName));
            if (itemQuantity>0){
                if (cart.containsKey(itemName)) cart.put(itemName, cart.get(itemName)+itemQuantity);
                else cart.put(itemName,itemQuantity);
            }
        } // while

        res.setContentType("text/html");
        PrintWriter msg = res.getWriter();
        msg.println("<h1>Your Cart<h1>");
        msg.println("<table border=1>");    
        msg.println("<tr><th>Item Name</th><th>Price</th><th>Quantity</th><th>Total</th></tr>");

        int total=0;
        for (String i: cart.keySet()){
            int p=price.get(i);
            int quantity=cart.get(i);
            int amount=quantity*p;
            msg.println("<tr><td>"+i+"</td><td>$"+p+"</td><td>"+quantity+"</td><td>$"+amount+"</td></tr>");
            total+=amount;
        }
        msg.println("<tr><th>Order Total</th><td></td><td></td><th>$"+total+"</th></tr>");
        msg.println("</table>");

        msg.println("<span style='font-size:50%; margin:30px'><a href='soap.html'>Go to SOAP</a></span>");     
        msg.println("<span style='font-size:50%; margin:30px'><a href='towel.html'>Go to TOWEL</a></span>");           
    }//doPost
}//AddToCard