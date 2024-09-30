package serviteca.st.servicio;

import serviteca.st.modelo.Vehiculo;

import java.util.List;

public interface IVehiculoServicio {

     List<Vehiculo> listarVehiculo();

    void eliminarVehiculo(Vehiculo vehiculo);

     String buscarPlaca();

     List<Vehiculo> listarVehiculosbyparams(String placa, String obsevacion, String modelo);


}
