package com.emergentes.constructor;

import com.emergentes.modelo.Productos;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
            ArrayList<Productos> lista = new ArrayList<Productos>();
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (op.equals("list")) {
                String sql = "Select * from productos";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Productos prod = new Productos();
                    prod.setId(rs.getInt("id"));
                    prod.setProducto(rs.getString("producto"));
                    prod.setPrecio(rs.getFloat("precio"));
                    prod.setCantidad(rs.getInt("cantidad"));

                    lista.add(prod);

                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            }
            if (op.equals("nuevo")) {
                Productos pro = new Productos();

                request.setAttribute("prod", pro);
                request.getRequestDispatcher("editar.jsp").forward(request, response);

            }
            if (op.equals("modificar")) {

            }
            if (op.equals("eliminar")) {
                int id = Integer.parseInt(request.getParameter("id"));

                String sql = "delete from productos where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ps.executeUpdate();
                response.sendRedirect("MainController");

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String producto = request.getParameter("producto");
            Float precio = Float.parseFloat(request.getParameter("precio"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            Productos prod = new Productos();
            prod.setId(id);
            prod.setProducto(producto);
            prod.setPrecio(precio);
            prod.setCantidad(cantidad);

            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();

            PreparedStatement ps;
            if (id == 0) {
                String sql = "insert into productos(producto, precio, cantidad) values(?,?,?)";

                ps = conn.prepareStatement(sql);
                ps.setString(1, prod.getProducto());
                ps.setFloat(2, prod.getPrecio());
                ps.setInt(3, prod.getCantidad());

                ps.executeUpdate();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
