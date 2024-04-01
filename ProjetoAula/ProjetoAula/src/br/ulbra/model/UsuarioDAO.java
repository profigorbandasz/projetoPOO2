package br.ulbra.model;

import br.ulbra.utils.Utils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    public boolean autenticar(String email, String senha){
        String sql = "SELECT * from TBUSUARIO WHERE emailUsu = ? and senhaUsu = ? and ativoUsu = 1";
        
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()){
                return true;
            }           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            gerenciador.closeConnection(stmt, rs);
        }
        return false;
    }
    
    public boolean adicionarUsuario(Usuario u){
        String sql = "INSERT into TBUSUARIO (nomeUsu, emailUsu, "
                + "senhaUsu, dataNascUsu, ativoUsu, imagemUsu) "
                + "VALUES (?,?,?,?,?, ?)";
        
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        
        try {
            byte[] iconBytes = Utils.iconToBytes(u.getImagem());
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, u.getNome()); 
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getDataNasc());
            stmt.setBoolean(5, u.isAtivo());
            stmt.setBytes(6, iconBytes);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuário: " + u.getNome() + " inserido com sucesso!");
            return true;
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        } finally {
            gerenciador.closeConnection(stmt);
        }
        
        return false;
    }
    
    public List<Usuario> read() {
        String sql = "SELECT * FROM tbusuario";
        List<Usuario> usuarios = new ArrayList<>();

        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setPk(rs.getInt("pkusuario"));
                usuario.setNome(rs.getString("nomeusu"));
                usuario.setEmail(rs.getString("emailusu"));
                usuario.setSenha(rs.getString("senhausu"));
                usuario.setDataNasc(rs.getString("datanascusu"));
                usuario.setAtivo(rs.getBoolean("ativousu"));
                
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            gerenciador.closeConnection(stmt, rs);
        }

        return usuarios;

    }
    
    public List<Usuario> readForDesc(int tipo, String desc) {
        String sql;
        if (tipo == 0 || tipo == 1)
            sql = "SELECT * FROM tbusuario WHERE nomeusu LIKE ?";
        else
            sql = "SELECT * FROM tbusuario WHERE emailusu LIKE ?";
        
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            if (tipo == 0 || tipo == 2)
                stmt.setString(1, desc+"%");
            else    
                stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setPk(rs.getInt("pkusuario"));
                usuario.setNome(rs.getString("nomeusu"));
                usuario.setEmail(rs.getString("emailusu"));
                usuario.setSenha(rs.getString("senhausu"));
                usuario.setDataNasc(rs.getString("datanascusu"));
                usuario.setAtivo(rs.getBoolean("ativousu"));
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            gerenciador.closeConnection(stmt, rs);
        }

        return usuarios;
    }
    
    public Usuario readForPk(int pk) {
        String sql = "SELECT * FROM tbusuario WHERE pkusuario = ?";
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, pk);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                usuario.setPk(rs.getInt("pkusuario"));
                usuario.setNome(rs.getString("nomeusu"));
                usuario.setEmail(rs.getString("emailusu"));
                usuario.setSenha(rs.getString("senhausu"));
                usuario.setDataNasc(rs.getString("datanascusu"));
                usuario.setAtivo(rs.getBoolean("ativousu"));
                
                byte[] bytes = rs.getBytes("imagemUsu");
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                BufferedImage imagem = ImageIO.read(bis);
                
                usuario.setImagem(new ImageIcon(imagem));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }finally {
            gerenciador.closeConnection(stmt, rs);
        }

        return usuario;
    }
    
    public boolean alterarUsuario(Usuario u) {
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            byte[] iconBytes = Utils.iconToBytes(u.getImagem());
            
            stmt = con.prepareStatement("UPDATE tbusuario SET nomeusu = ?, "
                    +" emailusu = ?, senhausu = ?, datanascusu = ?, "
                    +" ativousu = ?, imagemUsu = ? WHERE pkusuario = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getDataNasc());
            stmt.setBoolean(5, u.isAtivo());
            stmt.setBytes(6, iconBytes);
            stmt.setInt(7, u.getPk());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        } finally {
            gerenciador.closeConnection(stmt);
        }
        return false;
    }
    
    public boolean excluirUsuario(int pkUsuario) {
        GerenciadorConexao gerenciador = new GerenciadorConexao();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tbusuario "
                    + "WHERE pkusuario = ?");
            stmt.setInt(1, pkUsuario);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            gerenciador.closeConnection(stmt);
        }
        return false;
    }
}
