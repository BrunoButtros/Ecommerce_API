package ecommerce.api.dto;



public record UsuarioDTO(Long id, String nome, String email, String telefone) {
    public UsuarioDTO{
        if(id == null) id = 0L;
        if(nome == null) nome = "";
        if(email == null) email = "";
        if(telefone == null) telefone = "";
    }
}
