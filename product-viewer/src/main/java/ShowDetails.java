import com.hcl.DBConnection;
import domain.DAO;
import domain.Product;
import domain.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/view")
public class ShowDetails extends HttpServlet {
    DAO<Product> products;

    public ShowDetails() throws IOException{
        products = new ProductDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productid");
        Product product = null;
        PrintWriter out = response.getWriter();
        try {
            Long id = Long.parseLong(productId);
            if (!productId.isEmpty() && validId(id)) {
                product = products.get(id);
                response.setContentType("text/html");
                out.println("<h2>Product Detail</h2>");
                out.println(
                    "<table>\n" +
                    "  <tr>\n" +
                    "    <th>Id</th>\n" +
                    "    <th>Name</th>\n" +
                    "    <th>Price</th>\n" +
                    "    <th>Date Added</th>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>"+ product.getId() +"</td>\n" +
                    "    <td>"+ product.getName() +"</td>\n" +
                    "    <td>"+ String.format("%,.2f",product.getPrice()) +"</td>\n" +
                    "    <td>"+ product.getDateAdded() +"</td>\n" +
                    "  </tr>\n" +
                    "</table>"
                );
                out.println("</br><h3>View Another Product</h3>");
            } else {
                response.setContentType("text/html");
                out = response.getWriter();
                out.println("<h2>Try Again</h2>");
                out.println("<h3 style=\"color:red;\">Invalid Product Id</h2>");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        out.println(
                "<form action='view' method='get'>\n" +
                        "    <label>Enter Product Id: <input type='number' name='productid' value=\"1\" required></input></label>\n" +
                        "    <input type='submit' value=\"View Details\"></input>\n" +
                        "</form>"
        );
    }

    private boolean validId(long id) {
        List<Long> validIds = products.getIds();
        if (validIds.contains(id)) {
            return true;
        } else {
            return false;
        }
    }

}
