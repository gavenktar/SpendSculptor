package by.kirylarol.spendsculptor.dto;

import jakarta.validation.Valid;

public class RegistrationDTO {
    @Valid
    private IdentityDTO identityDTO;
   @Valid
   private UserDTO userDTO;

    public RegistrationDTO(IdentityDTO identityDTO, UserDTO userDTO) {
        this.identityDTO = identityDTO;
        this.userDTO = userDTO;
    }

    public RegistrationDTO() {
    }

    public IdentityDTO getIdentityDTO() {
        return identityDTO;
    }

    public void setIdentityDTO(IdentityDTO identityDTO) {
        this.identityDTO = identityDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
