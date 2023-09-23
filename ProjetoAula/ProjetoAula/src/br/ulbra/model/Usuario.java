/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import javax.swing.Icon;

/**
 *
 * @author Administrador
 */
public class Usuario extends Pessoa{
    private String dataNasc;
    private String senha;
        
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNas) {
        this.dataNasc = dataNas;
    }

    @Override
    public String toString() {
        return "Usuario{" + "pkUsuario=" + pk + ", nomeUsu=" + nome 
                + ", emailUsu=" + email + ", dataNascUsu=" + dataNasc 
                + ", ativoUsu=" + ativo + ", senhaUsu=" + senha + '}';
    }
}
