package ecommerce.api.validator;

import ecommerce.api.dto.UsuarioDTO;

public class UsuarioValidator {

    public static void validate(UsuarioDTO usuarioDTO){
        if(!usuarioDTO.email().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
            throw new RuntimeException("email: %s não é valido".formatted(usuarioDTO.email()));
        }

        if (!usuarioDTO.telefone().matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            throw new RuntimeException("Formato inválido para o telefone");
        }
    }
}

