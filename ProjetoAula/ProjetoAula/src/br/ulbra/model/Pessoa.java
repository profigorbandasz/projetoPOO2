/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ulbra.model;

import javax.swing.Icon;

/**
 *
 * @author iband
 */
public abstract class Pessoa {
    public int pk;
    public String nome;
    public String email;
    public int ativo;
    public Icon imagem;
    
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int isAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
    
    public String ativoToString(){
        if (this.ativo == 1)
            return "Ativo";
        else
            return "Inativo";
    }
    
    public Icon getImagem(){
        return this.imagem;
    }
    
    public void setImagem(Icon imagem){
        this.imagem = imagem;
    }
}
