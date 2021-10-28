package com.umanizales.lists_prog2.service;

import com.umanizales.lists_prog2.controller.dto.ResponseDTO;
import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.model.BoysByLocation;
import com.umanizales.lists_prog2.model.Location;
import com.umanizales.lists_prog2.model.listase.ListSE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListSeService {
    private ListSE listBoys;
    private List<Location> locations;

    public ListSeService() {
        listBoys = new ListSE();
        initializaLocations();
    }

    private void initializaLocations()
    {
        locations = new ArrayList<>();
        locations.add(new Location("54321", "Manizales"));
        locations.add(new Location("12345", "Chinchina"));
        locations.add(new Location("13245", "Neira"));
    }

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

    public ResponseEntity<ResponseDTO> addBoy(Boy boy) throws ListaSeException
    {
        if (!validateLocation(boy.getLocation())){
            throw new ListaSeException("La ubicacion ingresada no es valida.");
        }

        listBoys.add(boy);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addBoyByPosition(Boy boy, int position) throws ListaSeException
    {
        listBoys.addByPosition(boy, position);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> listBoysFree() throws ListaSeException
    {
            return new ResponseEntity<>(
                    new ResponseDTO("Satisfactirio", listBoys.list(), null),
                    HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> listBoys() throws ListaSeException
    {
        if (listBoys.getHead() == null)
        {
            throw new ListaSeException("No hay datos en la lista.");
        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactirio",listBoys.getHead(),null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> invertList() throws ListaSeException
    {
        listBoys.invert();
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactirio",listBoys.getHead(),null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addBoyToStart(Boy boy) throws ListaSeException
    {
        listBoys.addToStart(boy);
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

    public ResponseEntity<ResponseDTO> changeXtremes() throws ListaSeException
    {
        listBoys.changeXtremes();
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado", true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> delete(String identification) throws ListaSeException
    {
        listBoys.delete(identification);
        return new ResponseEntity<>(
                new ResponseDTO("Niño adicionado",true, null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> variantList() throws ListaSeException
    {
        listBoys.variantBoys();
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", listBoys.getHead(),null),
                HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> getBoysByLocation()
    {
        List<BoysByLocation> boysByLocations = new ArrayList<>();
        for (Location loc: locations)
        {
            int count = listBoys.getCoundBoysByLocation(loc.getCode());
            boysByLocations.add(new BoysByLocation(loc,count));
        }
        return new ResponseEntity<>(
                new ResponseDTO("Satisfactorio", boysByLocations,null)
                ,HttpStatus.OK);
    }


}
