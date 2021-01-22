import domain.DAO;
import domain.Product;
import domain.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShowProducts extends HttpServlet {

    DAO<Product> products;

    public ShowProducts() throws IOException{
        products = new ProductDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Products</h2>");
        for (Product product: products.getAll()) {
            out.println(
                    "<p>" + product.getId() + ": " + product.getName() +
                    " $" + String.format("%,.2f",product.getPrice()) + "</p>");
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("product-name");
        String priceInput = request.getParameter("product-price");
        Double price = Double.parseDouble(priceInput);
        products.add(new Product(
                null,
                name,
                price,
                null
        ));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Product was added");
    }

}
