package com.umanizales.lists_prog2.service;

import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.model.BoysByLocation;
import com.umanizales.lists_prog2.model.Location;
import com.umanizales.lists_prog2.model.listade.ListDE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListDeService {
    private ListDE listBoys;
    private List<Location> locations;


    public boolean validateLocation(Location location)
    {
        for (Location loc: locations)
        {
            if (loc.getCode().equals(location.getCode()) &&
                    loc.getDescription().equals(location.getDescription()))
            {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<ResponseDTO> add(Boy boy) throws ListaDeException
    {
        if (!validateLocation(boy.getLocation())){
            throw new ListaDeException("La ubicación ingresada no es valida.");
        }

        listBoys.add(boy);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> findByIdentification(String identification)
    {
        listBoys.findByIdentification(identification);
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio",true,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addBoyByPosition(Boy boy, int position) throws ListaDeException
    {
        listBoys.addByPosition(boy, position);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> listBoys() throws ListaDeException
    {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactirio",listBoys.getHead(),null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addToStart(Boy boy) throws ListaDeException
    {
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> getCount()
    {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactirio",listBoys.getCount(),null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> count()
    {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactirio",listBoys.count(),null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> delete()
    {
        return new ResponseEntity<>(
                new ResponseDTO("Niño eliminado",true,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addByPosition()
    {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio",true,null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> invertir()
    {
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio",true,null), HttpStatus.OK);
    }








}
