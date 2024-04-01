package br.ulbra.model;

import javax.swing.Icon;

public abstract class Pessoa {
    private int pk;
    private String nome;
    private String email;
    private boolean ativo;
    private Icon imagem;
    
    public Pessoa(){};
    
    public Pessoa(int pk, String nome, String email, boolean ativo) {
        this.pk = pk;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
    }
    
    public Pessoa(int pk, String nome, String email, boolean ativo, Icon imagem) {
        this.pk = pk;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.imagem = imagem;
    }
        
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
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public Icon getImagem(){
        return this.imagem;
    }
    
    public void setImagem(Icon imagem){
        this.imagem = imagem;
    }
}
