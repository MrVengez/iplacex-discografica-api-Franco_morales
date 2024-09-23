package org.iplacex.proyectos.discografia.discos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
public class Disco {
    @Id
    public String _id;
    public String idArtista;
    public String nombre;
    public int aniosLanzamiento;
    public List<String> canciones;
}
