package org.iplacex.proyectos.discografia.discos;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepo;

    public DiscoController(IDiscoRepository discoRepo) {
        this.discoRepo = discoRepo;
    }

        @PostMapping(
        value ="/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )

        public ResponseEntity<Disco> HandleInsetDiscoRequest(@RequestBody Disco disco){
        Disco temp = discoRepo.insert(disco);
        return new ResponseEntity<>(temp, null,HttpStatus.CREATED);


    }

     @SuppressWarnings("null")
    @GetMapping(
        value ="/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id){
        java.util.Optional<Disco> temp = discoRepo.findById(id);
        
        if(!temp.isPresent()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(temp.get(),null,HttpStatus.OK);
    }

            @GetMapping(
        value ="/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<List<Disco>> HandleGetArtistasRequest(){
        List<Disco> disco = discoRepo.findAll();
        return new ResponseEntity<>(disco,null,HttpStatus.OK);
        }


        @GetMapping(
            value ="/artista/{id}/discos",
            produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String id) {
            List<Disco> discos = discoRepo.findDiscosByIdArtista(id);
            if (discos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(discos, HttpStatus.OK);
        } 

}

