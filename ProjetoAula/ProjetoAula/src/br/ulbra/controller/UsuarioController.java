package br.ulbra.controller;

import br.ulbra.model.Usuario;
import br.ulbra.model.UsuarioDAO;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    } 
    
    public boolean autenticar(String email, String senha) {
        if (usuarioDAO.autenticar(email, senha)){
            return true;
        } else {
            JOptionPane.showMessageDialog(null,"Usuário ou senha incorreta");
            return false;
        }
    }
    
    public boolean adicionarUsuario(Usuario u){
        return usuarioDAO.adicionarUsuario(u);
    }

    public List<Usuario> readForDesc(int tipo, String desc) {
        return usuarioDAO.readForDesc(tipo, desc);
    }
    
    public Usuario readForPk(int pk) {
        return usuarioDAO.readForPk(pk);
    }
    
    public boolean alterarUsuario(Usuario u) {
        return usuarioDAO.alterarUsuario(u);
    }
    
    public boolean excluirUsuario(int pkUsuario) {
        return usuarioDAO.excluirUsuario(pkUsuario);
    }
}
