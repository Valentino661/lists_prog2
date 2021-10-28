package com.umanizales.lists_prog2.model.listase;

import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    private Node head;
    private int count;

    public void add(Boy boy) throws ListaSeException
    {
        Boy boyExist = findByIndentification(boy.getIdentification());
        if (boyExist != null)
        {
            throw new ListaSeException("La identificacion ingresada ya existe.");
        }
        if (head == null)
        {
            head = new Node(boy);
        }
        else
        {
            Node temp = head;
            while (temp.getNext()!=null)
            {
                temp = temp.getNext();
            }
            // El quedo parado en el ultimo
            temp.setNext(new Node(boy));
        }
        count++;
    }

    public void addByPosition(Boy boy, int position) throws ListaSeException
    {
        Boy boyExist = findByIndentification(boy.getIdentification());
        if (boyExist != null)
        {
            throw new ListaSeException("La identificacion ingresada ya existe");
        }
        /// Validacion de la posicion
        if(position > count)
        {
            throw new ListaSeException("La posicion ingresada no es valida.");
        }
        if (position == 1)
        {
            addToStart(boy);
        }
        else
        {
            int count=1;
            Node temp = this.head;
            while (temp != null)
            {
                if (count == position-1)
                {
                    break;
                }
                temp = temp.getNext();
                count++;
            }
            // Ayudante va estar posicionada en la posicion anterior
            Node nodeNew = new Node(boy);
            nodeNew.setNext(temp.getNext());
            temp.setNext(nodeNew);
            count++;
        }

    }

    public void invert() throws ListaSeException
    {
        if (this.head != null){
            ListSE listTemp = new ListSE();
            //Recorree la lista principal de principio a fin
            Node temp = this.head;
            while (temp != null)
            {
                listTemp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listTemp.getHead();
        }
        count++;
    }

    public void addToStart(Boy boy) throws ListaSeException
    {
        Boy boyExist = findByIndentification(boy.getIdentification());
        if (boyExist != null)
        {
            throw new ListaSeException("La identificacion ingresada ya existe.");
        }
        if (this.head == null)
        {
            this.head = new Node (boy);
        }
        else
        {
            Node temp = new Node(boy);
            temp.setNext(this.head);
            this.head = temp;
        }
    }

    public int count(){
        int cont = 0;
        if (this.head != null){
            //Recorree la lista principal de principio a fin
            Node temp = this.head;
            while (temp != null)
            {
                cont++;
                temp = temp.getNext();
            }
        }
        return cont;
    }

    public List<Boy> list() throws ListaSeException
    {
        if (this.head != null)
        {
            Node temp = this.head;
            List<Boy> list = new ArrayList<>();
            while (temp != null)
            {
                list.add(temp.getData());
                temp = temp.getNext();
            }
            return list;
        }
        throw new ListaSeException("No hay datos en la lista");
        //return null;
    }

    public void changeXtremes() throws ListaSeException
    {
        if (this.head != null && this.head.getNext() != null)
        {
            //Sacar el niño de la cabeza
            Boy start = this.head.getData();
            Node temp = head;
            while (temp.getNext() != null)
            {
                temp = temp.getNext();
            }
            this.head.setData(temp.getData());
            temp.setData(start);
        }
        else
        {
            throw new ListaSeException("No es posible ejecutar el cambio de extremos.");
        }
    }

    public void delete(String identification) throws ListaSeException
    {
        if (this.head != null)
        {
            if (this.head.getData().getIdentification().equals(identification))
            {
                this.head = this.head.getNext();
            }
            else {
                Node temp = this.head;
                while (temp != null) {
                    if (temp.getNext() != null &&
                            temp.getNext().getData().getIdentification().equals(identification)) {
                        break;
                    }
                    temp = temp.getNext();
                }
                // temp va estar parado en el anterios al que debo eliminar o va a ser null
                if (temp != null)
                {
                    temp.setNext(temp.getNext().getNext());
                }
                else
                {
                    throw new ListaSeException("La identificacion "+identification+" no existe en la lista.");
                }
            }
        }
        else
        {
            throw new ListaSeException("No hay datos en la lista.");
        }
    }

    public Boy findByIndentification(String identification)
    {
        Node temp = this.head;
        while (temp != null)
        {
            if (temp.getData().getIdentification().equals(identification))
            {
                return temp.getData();
            }
            temp = temp.getNext();
        }
        return null;
    }

    public void validateListEmpty() throws ListaSeException
    {
        if (this.head == null)
        {
            throw new ListaSeException("No hay datos en la lista.");
        }
    }

    public ListSE getListSeBoysByGender(String gender) throws ListaSeException
    {
        validateListEmpty();
        Node temp = this.head;
        ListSE listTemp = new ListSE();
        while (temp != null)
        {
            if (temp.getData().getGender().name().equals(gender))
            {
                listTemp.add(temp.getData());
            }
            temp = temp.getNext();
        }
        return listTemp;
    }

    public void variantBoys() throws  ListaSeException
    {
        validateListEmpty();
        ListSE kids = this.getListSeBoysByGender("MASCULINO");
        ListSE girls = this.getListSeBoysByGender("FEMENINO");
        ListSE minList = null;
        ListSE maxList = null;
        if (kids.getCount() > girls.getCount())
        {
            minList = girls;
            maxList = kids;
        }
        else
        {
            minList = kids;
            maxList = girls;
        }
        Node temp = minList.getHead();
        int pos = 2;
        while (temp != null)
        {
            maxList.addByPosition(temp.getData(), pos);
            pos = pos + 2;
            temp = temp.getNext();
        }
        this.head = maxList.getHead();
    }

    // Metodo que recibe el codigo de una ciudad y retorna la cantidad de niños
    public int getCoundBoysByLocation(String code)
    {
            Node temp = this.getHead();
            int count = 0;
            while (temp != null)
            {
                if(temp.getData().getLocation().getCode().equals(code))
                {
                    count++;
                }
                temp = temp.getNext();
            }
            return count;
    }

}
