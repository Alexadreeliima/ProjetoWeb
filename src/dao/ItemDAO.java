package dao;

import java.sql.*;
import java.util.ArrayList;

import control.ItemBean;

public class ItemDAO {
	
	private ItemBean map(ResultSet rs) throws SQLException {
        ItemBean resultado = new ItemBean(rs.getInt("id"), rs.getInt("id_produto"),
        		rs.getInt("id_fornecedor"), rs.getInt("quantidade"));
        return resultado;
    }
	
	public void inserir(int idPedidoCompra, int idProduto, int qtd) throws DAOException{
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            String insert_sql = "insert into item (id_pedido_compra,id_produto, qtd) values (?, ?, ?)";
            PreparedStatement pst;
            pst = con.prepareStatement(insert_sql);
            //Passando os paramentros para o SQL
            pst.setInt(1, idPedidoCompra);
            pst.setInt(2, idProduto);
            pst.setInt(3, qtd);
            //Executando os comandos
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
	}
	
	public void update(int id, int idPedidoCompra, int idProduto, int qtd) throws DAOException{
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            String insert_sql = "update item set id_pedido_compra = ?, id_produto = ?, qtd = ? where id = ?";
            PreparedStatement pst;
            pst = con.prepareStatement(insert_sql);
            //Passando os paramentros para o SQL
            pst.setInt(1, idPedidoCompra);
            pst.setInt(2, idProduto);
            pst.setInt(3, qtd);
            pst.setInt(4, id);
            //Executando os comandos
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
    }

    public void delete(int id, int idPedidoCompra) throws DAOException{
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "delete from item where id = ? and id_pedido_compra = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, idPedidoCompra);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
    }

	public ItemBean encontrar(int id, int idPedidoCompra) throws DAOException{
        Connection con = null;
        ItemBean item = null;
                
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from item where id = ? and id_pedido_compra = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, idPedidoCompra);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                item = map(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
        return item;
    }
	
	public ArrayList<ItemBean> mostrarTodos() throws DAOException {
        Connection con = null;
        ArrayList<ItemBean> itens = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from item";
            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                itens.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
        return itens;
    }

	public ArrayList<ItemBean> mostrarItensPedido(int idPedidoCompra) throws DAOException {
        Connection con = null;
        ArrayList<ItemBean> itens = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from item where id_pedido_compra = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idPedidoCompra);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                itens.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
        return itens;
    }
	public ArrayList<ItemBean> mostrarItensProduto(int idProduto) throws DAOException {
        Connection con = null;
        ArrayList<ItemBean> itens = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select * from item where id_produto = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idProduto);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                itens.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Operação não realizada com sucesso.", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Não foi possível fechar a conexão.", e);
            }
        }
        return itens;
    }
}
