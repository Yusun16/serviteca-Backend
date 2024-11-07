package serviteca.st.servicio;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serviteca.st.modelo.Dto.EjecucionServicioDto;
import serviteca.st.modelo.Dto.buscarOrdenEspecificaDto;
import serviteca.st.modelo.Dto.vehiculosClientes;
import serviteca.st.modelo.Orden;
import serviteca.st.modelo.Revision;
import serviteca.st.modelo.Servicio;
import serviteca.st.repositorio.OrdenRepositorio;
import serviteca.st.utils.GlobalConstants;
import java.lang.reflect.Field;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class OrdenServicio implements IOrdenServicio{

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Override
    public List<Orden> listarOrden() {
        return ordenRepositorio.findAll();
    }

    @Override
    public Orden buscarOrdenPorId(Integer idOrden) {
       Orden orden = ordenRepositorio.findById(idOrden).orElse(null);
        return orden;
    }

    @Override
    public Orden guardarOrden(Orden orden) {
        return ordenRepositorio.save(orden);
    }

    @Override
    public void eliminarOrden(Orden orden) {
        ordenRepositorio.delete(orden);

    }

    @Override
    public String buscarCodigo() {
        String ultimoCodigo = ordenRepositorio.buscarCodigo();

        if (ultimoCodigo == null || ultimoCodigo.isEmpty()) {
            // Si no hay ningún código, iniciamos con "1".
            return "1";
        }

        // Asumimos que el código es numérico y puede ser convertido a entero.
        // Agregamos uno al último código y lo devolvemos.
        int nuevoCodigo = Integer.parseInt(ultimoCodigo) + 1;
        return String.valueOf(nuevoCodigo);
    }


    @Override
    public List<vehiculosClientes> findVehiculosClientes(Integer clienteId) {
        return ordenRepositorio.findVehiculosClientes(clienteId);
    }

    @Override
    public List<EjecucionServicioDto> findejecucionservicio(Integer idOrden) {
        return ordenRepositorio.findejecucionservicio(idOrden);
    }

    @Override
    public void update(Integer id, Orden object) throws Exception {
        Optional<Orden> optionalEntity = ordenRepositorio.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        Orden entityToUpdate = optionalEntity.get();

        updateNonNullProperties(object, entityToUpdate);

        ordenRepositorio.save(entityToUpdate);
    }

    @Override
    public List<buscarOrdenEspecificaDto> buscarOrdenEspecifica(String codigo, String nombreCliente, LocalDate fecha, String placa) {
        return ordenRepositorio.buscarOrdenEspecifica(codigo, nombreCliente, fecha, placa);
    }

    private void updateNonNullProperties(Orden source, Orden target) {
        Field[] fields = Orden.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException e) {

                e.printStackTrace();
            }
        }
    }


}
