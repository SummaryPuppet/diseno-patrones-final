package pe.edu.utp.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CargaDTO {
    private Long id;
    private String descripcion;
    private Double pesoKg;
    private Double volumenM3;
    private List<CargaItemDTO> items = new ArrayList<>();
}
