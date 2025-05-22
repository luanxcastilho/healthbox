package br.com.fiap.healthbox.dtos;

public class EnfermeiroResponseDTO {
    
    Integer id;
    String nome;
    String coren;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
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
