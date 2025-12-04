package pe.edu.utp.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GpsExternoAdapter implements GpsAdapter {
    private GpsExternoServicio gpsExternoServicio;

    @Override
    public String localizar(String deviceId) {
        return gpsExternoServicio.obtenerCoordenadas(deviceId);
    }
}
