package br.com.fiap.healthbox.dtos;

public class EnfermeiroCreateDTO {
    
    String nome;
    String coren;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCoren() {
        return coren;
    }
    
    public void setCoren(String coren) {
        this.coren = coren;
    }
}
