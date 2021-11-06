package com.umanizales.lists_prog2.controller;

import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.service.ListSeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que nos permite
 * */
@RestController
@RequestMapping(path = "boys") // etiqueta nombre para poderlo llamar por la URL
public class BoysControllerSE {

    @Autowired // Etiqueta para inyectar la listaSE
    private ListSeService listSeService;

    /**
     * Método Que nos permite mandar todo los datos del niño adicionado
     * */
    @PostMapping  // Etiqueta que se utiliza para modificar o agregar datos
    public ResponseEntity<ResponseDTO> addBoy(@RequestBody Boy boy) throws ListaSeException
    { return listSeService.addBoy(boy);}

    /**
     * Método que nos permite mandar todo los datos de todos los niños adicionados
     * */
    @GetMapping  // Etiqueta que se utiliza para acceder a los datos
    public ResponseEntity<ResponseDTO> listBoys() throws ListaSeException
    {
        return listSeService.listBoys();
    }

    @GetMapping(path = "free")
    public ResponseEntity<ResponseDTO> listBoysFree() throws ListaSeException
    {
            return listSeService.listBoysFree();
    }

    @GetMapping(path = "invert")
    public ResponseEntity<ResponseDTO> invertList() throws ListaSeException
    {
        return listSeService.invertList();
    }

    @PostMapping(path = "addtostart")
    public ResponseEntity<ResponseDTO> addBoyToStart(@RequestBody Boy boy)throws ListaSeException
    {
        return listSeService.addBoyToStart(boy);
    }

    @PostMapping(path = "addtoposition/{position}")
    public ResponseEntity<ResponseDTO> addBoyByPosition(@PathVariable int position, @RequestBody Boy boy)throws ListaSeException
    {
        return listSeService.addBoyToStart(boy);
    }

    @PostMapping(path = "addboys")
    public ResponseEntity<ResponseDTO> addBoys(@RequestBody List<Boy> boys) throws ListaSeException
    {
        for (Boy boy:boys)
        {
            listSeService.addBoy(boy);
        }
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Satisfactirio",
                listSeService.listBoys(),null), HttpStatus.OK);
    }

    @GetMapping(path = "getcount")
    public ResponseEntity<ResponseDTO> getCount()
    {
        return listSeService.getCount();
    }

    @GetMapping(path = "count")
    public ResponseEntity<ResponseDTO> count()
    {
        return listSeService.count();
    }

    @GetMapping(path = "changextremes")
    public ResponseEntity<ResponseDTO> changeXtremes() throws ListaSeException
    {
        return listSeService.changeXtremes();
    }

    @GetMapping(path = "delete/{identification}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String identification) throws ListaSeException
    {
        return listSeService.delete(identification);
    }

    @GetMapping(path = "variant")
    public ResponseEntity<ResponseDTO> variantList() throws ListaSeException
    {
        return listSeService.variantList();
    }

    @GetMapping(path = "boysbylocation")
    public ResponseEntity<ResponseDTO> boysByLocation()
    {
        return listSeService.getBoysByLocation();
    }
/*
    @GetMapping(path = "boysbylocation")
    public ResponseEntity<ResponseDTO> boysByLocation()
    {
        return listSeService.getBoysByLocation();
    }
*/
    @GetMapping(path = "deletebygender/{gender}")
    public ResponseEntity<ResponseDTO>deleteByGender(@PathVariable String gender)throws ListaSeException
    {
        return listSeService.deleteByGender(gender);
    }

    @GetMapping(path = "getboysbygrade/{grade}")
    public ResponseEntity<ResponseDTO> getBoysByGrade(Integer grade)
    {
        return listSeService.getBoysByGrade(grade);
    }

}
