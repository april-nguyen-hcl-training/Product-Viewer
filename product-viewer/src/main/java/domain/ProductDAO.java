package domain;

import com.hcl.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAO implements DAO<Product>{

    final static String SELECT_ALL_SQL = "select id, name, price, date_added from product";
    final static String INSERT_SQL = "insert into product (name, price) values (?, ?)";
    final static String BY_ID_SQL = "select id, name, price, date_added from product where id=?";

    List<Product> products = new ArrayList<>();

    Connection conn = null;

    public ProductDAO() {
        conn = new DBConnection().getConnection();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Date dateAdded = rs.getDate("date_added");
                products.add(new Product(id, name, price, dateAdded));
            }
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product get(Long id) {
        Product product = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement(BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.first()) {
                long rsId = rs.getLong("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Date dateAdded = rs.getDate("date_added");
                product = new Product(rsId, name, price, dateAdded);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product add(Product product) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                INSERT_SQL,
                Statement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Unable to create record");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getLong(1));
                    product.setDateAdded(generatedKeys.getTimestamp("date_added"));
                    return product;
                } else {
                    throw new SQLException("Adding product failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Product update(Product entity) {
        return null;
    }
}