package br.ulbra.model;

import javax.swing.Icon;

public class Usuario extends Pessoa {
    private String dataNasc;
    private String senha;

    public Usuario (){};
    
    public Usuario(int pk, String nome, String email, boolean ativo, String dataNasc, String senha) {
        super(pk, nome, email, ativo);
        this.dataNasc = dataNasc;
        this.senha = senha;
    }
    
    public Usuario(int pk, String nome, String email, boolean ativo, Icon imagem, String dataNasc, String senha) {
        super(pk, nome, email, ativo, imagem);
        this.dataNasc = dataNasc;
        this.senha = senha;
    }
        
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public String toString() {
        return "Usuario{" + "pkUsuario=" + getPk() + ", nome=" + getNome() 
                + ", email=" + getEmail() + ", dataNasc=" + dataNasc 
                + ", ativo=" + isAtivo() + ", senha=" + senha + '}';
    }
    
    public String ativoToString(){
        if(isAtivo() == true)
            return "Ativo";
        else
            return "Inativo";
    }
}
