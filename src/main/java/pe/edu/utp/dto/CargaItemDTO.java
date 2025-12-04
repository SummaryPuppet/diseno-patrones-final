package pe.edu.utp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CargaItemDTO {
    private Long id;
    private String nombre;
    private Double pesoKg;
    private Double volumenM3;
}
