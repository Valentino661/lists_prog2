package com.umanizales.lists_prog2.controller;

import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.service.ListDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "boys_de") // etiqueta nombre para poderlo llamar por la URL
public class BoysControllerDE {

    @Autowired // Etiqueta para inyectar la listaDE
    private ListDeService listDeService;

    @PostMapping
    public ResponseEntity<ResponseDTO> add(@RequestBody @Valid Boy boy) throws ListaDeException {
        return listDeService.add(boy);
    }

    @GetMapping(path = "count")
    public ResponseEntity<ResponseDTO> count(){
        return listDeService.count();
    }

    @GetMapping(path = "addbyposition/{positon}")
    public ResponseEntity<ResponseDTO> addByPosition(@RequestBody Boy boy, int positon)
    {
        return listDeService.addByPosition();
    }

    @GetMapping(path = "invert")
    public ResponseEntity<ResponseDTO> invertir()
    {
        return listDeService.invertir();
    }

    @GetMapping(path = "delete/{identification}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String identification)
    {
        return listDeService.delete();
    }




}





