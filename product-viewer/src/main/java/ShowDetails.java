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

@WebServlet("/view")
public class ShowDetails extends HttpServlet {
    DAO<Product> products;

    public ShowDetails() throws IOException{
        products = new ProductDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productid");
        Product product = null;
        try {
            Long id = Long.parseLong(productId);
            product = products.get(id);
            if (product != null) {
            } else {
                response.sendRedirect("/product");
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
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
    }
}
