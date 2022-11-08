package pl.edu.pw.infstos.szsdsr.appshared;

import java.util.List;

public interface ServiceCRUD<DTO, ID> {

    DTO create(DTO dto);

    DTO update(DTO dto);

    DTO read(ID id);

    List<DTO> readAll();

    int delete(ID id);

}
