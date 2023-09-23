/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ulbra.model;

/**
 *
 * @author iband
 */
public class Cliente extends Pessoa{
    private String cpf;
    
    public String getCpf(){
        return this.cpf;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "pkUsuario=" + pk + ", nomeUsu=" + nome 
                + ", emailUsu=" + email + ", cpf=" + cpf 
                + ", ativoUsu=" + ativo + '}';
    }
}
