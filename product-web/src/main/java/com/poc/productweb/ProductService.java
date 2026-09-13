package com.poc.productweb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductService {

    @Resource(lookup = "jdbc/policyDS")
    private DataSource dataSource;

    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT product_id, name, description, premium FROM product ORDER BY product_id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBigDecimal("premium")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to load products", e);
        }

        return products;
    }
}
