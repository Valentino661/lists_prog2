package com.umanizales.lists_prog2.model.listase;

import com.umanizales.lists_prog2.exception.ListaSeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.model.Gender;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que me permite gestionar una lista simplemente enlazada
 * contiene los métodos u operaciones ...
 * Solo cuenta con los atributos head = que representa el primer niño
 * y el count = que lleva un conteo de los niños de la lista
 */

@Data
public class ListSE {
    private Node head; // Atributo que representa el inicio de la lista (la cabeza)
    private int count; // Atributo que representa el conteo de los niños


    /**
     * Método que adiciona un niño al final de la lista
     * @param boy
     * @throws ListaSeException
     */
    public void add(Boy boy) throws ListaSeException
    {
        /**
         * Se invoca el método encontrar por identificación, para verificar si
         * el niño que está ingresando ya existe
         */
        Boy boyExist = findByIndentification(boy.getIdentification());
        if (boyExist != null)
        {
            /**
             * Si el niño ya existe se lanza la excepción para comunicar al usuario
             */
            throw new ListaSeException("La identificación ingresada ya existe.");
        }
        if (head == null) // Si hay datos en la lista
        {
            head = new Node(boy); // El niño adicionado queda en la cabeza
        }
        else // Si hay datos
        {
            Node temp = head; // Llamo a un ayudante que se pare en la cabeza
            while (temp.getNext()!=null) // Ayudante pase hasta que el siguiente este vacío
            {
                temp = temp.getNext(); // El ayudante pasa al siguiente
            }
            temp.setNext(new Node(boy)); // Quedo parado en el último
        }
        count++; //  Añadimos el niño y el contador aumenta
    }

    /**
     * Método que adiciona un niño en una posición dada
     * @param boy, position
     * @throws ListaSeException
     */
    public void addByPosition(Boy boy, int position) throws ListaSeException
    {
        /**
         * Se invoca el método encontrar por identificación, para verificar si
         * el niño que está ingresando ya existe
         */
        Boy boyExist = findByIndentification(boy.getIdentification());
        if (boyExist != null)
        {
            /**
             * Si el niño ya existe se lanza la excepción para comunicar al usuario
             */
            throw new ListaSeException("La identificación ingresada ya existe");
        }
        if(position > count) // Si la posición ingresada es mayor al número de niños
        {
            /**
             * Si la posición no existe lanza la excepción para comunicar al usuario
             */
            throw new ListaSeException("La posición ingresada no es valida.");
        }
        if (position == 1) // Si la posición es igual a 1
        {
            addToStart(boy); // Añadir el niño en la cabeza
        }
        else
        {
            int count=1;
            Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
            while (temp != null) // Ayudante pase hasta que el siguiente este vacío
            {
                if (count == position-1) // Si la posición ingresada es negativa
                {
                    break; // Rompe el ciclo
                }
                temp = temp.getNext(); // El ayudante pasa al siguiente
                count++; // Aumenta el contador
            }
            // Ayudante va estar posicionada en la posición anterior
            Node nodeNew = new Node(boy);
            nodeNew.setNext(temp.getNext()); // Nuevo coge al sig de su sig
            temp.setNext(nodeNew); // temp va hacer su sig
            count++; // incrementa contador
        }
    }


    /**
     * Método que cambia el primer niño de la lista y el último (los invierte)
     * @throws ListaSeException
     */
    public void invert() throws ListaSeException
    {
        if (this.head != null) // Si hay datos en la lista
        {
            ListSE listTemp = new ListSE(); // inicializamos lista temporal donde head está vacía
            Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
            while (temp != null) // Ayudante pase hasta que el siguiente este vacío
            {
                listTemp.addToStart(temp.getData()); // Se adiciona a la lista temporal
                temp = temp.getNext(); // El ayudante pasa al siguiente
            }
            this.head = listTemp.getHead(); // la head o lista va hacer igual a la temporal
        }
        count++; // Aumenta el contador de acuerdo a los niños add
    }

    /**
     * Método que adiciona un niño al inicio de la lista.
     * @param boy
     * @throws ListaSeException
     */
    public void addToStart(Boy boy) throws ListaSeException
    {
        /**
         * Se invoca el método encontrar por identificación, para verificar si
         * el niño que está ingresando ya existe
         */
        Boy boyExist = findByIndentification(boy.getIdentification());
        if (boyExist != null)
        {
            /**
             * Si el niño ya existe se lanza la excepción para comunicar al usuario
             */
            throw new ListaSeException("La identificación ingresada ya existe.");
        }

        if (this.head == null) // Si no hay datos en la cabeza
        {
            this.head = new Node (boy); // El niño que llego es head
        }
        else  // Si hay datos
        {
            Node temp = new Node(boy); // Meto el niño en el Nodo (temp)
            temp.setNext(this.head);  // El brazo del Nodo se asocia al primero
            this.head = temp; // temp va hacer igual a la cabeza
        }
    }

    /**
     * Método que cuenta los niños adicionados
     */
    public int count(){
        int cont = 0;
        if (this.head != null) // Si hay datos en la lista
        {
            Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
            while (temp != null)  // Ayudante pase hasta que el siguiente este vacío
            {
                cont++;
                temp = temp.getNext(); // El ayudante pasa al siguiente
            }
        }
        return cont; // Retorna contador con la cantidad obtenida
    }

    /**
     * Método que lista los niños adicionados
     * @param List<Boy>
     * @throws ListaSeException
     */
    public List<Boy> list() throws ListaSeException
    {
        if (this.head != null)  // Si hay datos en la lista
        {
            List<Boy> list = new ArrayList<>(); // Creamos un lista para guardar los datos
            Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
            while (temp != null)  // Ayudante pase hasta que el siguiente este vacío
            {
                list.add(temp.getData()); // Guarda los datos de los niños adicionados
                temp = temp.getNext(); // El ayudante pasa al siguiente
            }
            return list; // Retorna lista con los datos obtenidos
        }
        throw new ListaSeException("No hay datos en la lista");
    }

    /**
     * Método que invierte los extremos de la lista
     * @throws ListaSeException
     */
    public void changeXtremes() throws ListaSeException
    {
        /**
         * Si hay datos en la lista Y hay datos en su siguiente
         * */
        if (this.head != null && this.head.getNext() != null)
        {
            Boy start = this.head.getData(); // Quita el niño de la cabeza
            Node temp = head; // Llamo a un ayudante que se pare en la cabeza
            while (temp.getNext() != null) // Recorro la lista hasta que su sig sea null
            {
                temp = temp.getNext(); // El ayudante pasa al siguiente
            }
            this.head.setData(temp.getData());
            temp.setData(start);
        }
        else
        {
            throw new ListaSeException("No es posible ejecutar el cambio de extremos.");
        }
    }

    /**
     * Método que elimina los niños por identificación
     * @param identification
     * @throws ListaSeException
     */
    public void delete(String identification) throws ListaSeException
    {
        if (this.head != null) // Si hay datos en la lista
        {
            if (this.head.getData().getIdentification().equals(identification))
            {
                this.head = this.head.getNext(); // El ayudante pasa al siguiente
            }
            else
            {
                Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
                while (temp != null) // Ayudante pase hasta que el siguiente este vacío
                {
                    /**
                     * Si hay datos en la lista Y pregunto si el niño en el cual está ubicado
                     * el ayudante es el niño de la identificación ingresada
                     * */
                    if (temp.getNext() != null &&
                            temp.getNext().getData().getIdentification().equals(identification))
                    {
                        break; // Rompe el ciclo
                    }
                    temp = temp.getNext(); // El ayudante pasa al siguiente
                }
                if (temp != null) // Si temp está en el anterior al que debo eliminar
                {
                    temp.setNext(temp.getNext().getNext()); // temp va a agarrar al sig de su sig
                }
                else
                {
                    throw new ListaSeException("La identificación "
                            +identification+" no existe en la lista.");
                }
            }
        }
        else
        {
            throw new ListaSeException("No hay datos en la lista.");
        }
    }

    /**
     * Método que busca un niño por su identificación
     * @param identification
     * @return niño con sus datos
     */
    public Boy findByIndentification(String identification)
    {
        Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
        while (temp != null) // Ayudante pase hasta que el siguiente este vacío
        {
            /**
             * Pregunto si el niño en el cual está ubicado el ayudante es el niño
             * de la identificación ingresada
             */
            if (temp.getData().getIdentification().equals(identification))
            {
                return temp.getData(); // Retorna al niño con sus datos
            }
            temp = temp.getNext(); // El ayudante pasa al siguiente
        }
        return null; // No se encontro el niño y retorna null
    }

    /**
     * Método que válida si ya existe el niño que voy a ingresar
     */
    public void validateListEmpty() throws ListaSeException
    {
        if (this.head == null)
        {
            /**
             * Si no hay niños se lanza la excepción para comunicar al usuario
             */
            throw new ListaSeException("No hay datos en la lista.");
        }
    }

    /**
     * Método que obtiene el listado de los niños por género
     * @param gender
     * @throws ListaSeException
     * */
    public ListSE getListSeBoysByGender(String gender) throws ListaSeException
    {
        validateListEmpty();
        Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
        ListSE listTemp = new ListSE();
        while (temp != null) // Ayudante pase hasta que el siguiente este vacío
        {
            /**
             * Si el dato y el género del niño es el género ingresado
             * */
            if (temp.getData().getGender().name().equals(gender))
            {
                listTemp.add(temp.getData()); // Guardamos en la lista temp los datos del niño
            }
            temp = temp.getNext(); // El ayudante pasa al siguiente
        }
        return listTemp; // Retornamos los niños del género ingresado
    }

    /**
     * Método que intercala los niños por género
     * @throws ListaSeException
     * */
    public void variantBoys() throws  ListaSeException
    {
        validateListEmpty(); // Válida si la lista tiene datos
        ListSE kids = this.getListSeBoysByGender("MASCULINO");
        ListSE girls = this.getListSeBoysByGender("FEMENINO");
        ListSE minList = null; // Inicializo una lista
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

    /**
     * Método que recibe el código de una ciudad y retorna la cantidad de niños de la ciudad recibida
     * @param code
     * */
    public int getCoundBoysByLocation(String code)
    {
            Node temp = this.getHead(); // Llamo a un ayudante que se pare en la cabeza
            int count = 0; // inicializamos contador
            while (temp != null) // Ayudante pase hasta que el siguiente este vacío
            {
                if(temp.getData().getLocation().getCode().equals(code))
                {
                    count++;
                }
                temp = temp.getNext(); // El ayudante pasa al siguiente
            }
            return count; // retorna la cantidad de niños guardados en el contador
    }

    /**
     * Método que busca los niños por ubicación
     * @param code
     */
    public Boy findByLocation(String code)
    {
        Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
        while (temp != null) // Ayudante pase hasta que el siguiente este vacío
        {
            if (temp.getData().getLocation().equals(code))
            {
                return temp.getData();
            }
            temp = temp.getNext(); // El ayudante pasa al siguiente
        }
        return null;
    }

    /**
     * Método que dada una edad y un municipio permita obtener el listado de los niños
     * pertenecientes al municipio y la edad menor o igual a la dada y liste los niños.
     * @param age, location
     */
    public List<Boy> listBoysByLocationByAge(byte age, String location)
    {
        if (this.head != null) // Si hay datos en la lista
        {
            Node temp = this.getHead(); // Llamo a un ayudante que se pare en la cabeza
            List<Boy> list = new ArrayList<>(); // Llamo a una lista temp para guardar los datos
            while (temp != null) // Ayudante pase hasta que el siguiente este vacío
            {
                /**
                 * Si el ayudante con el dato y la edad es menor o igual a la edad del niño
                 */
                if (temp.getData().getAge() <= age &&
                        temp.getData().getLocation().getDescription().equals(location))
                {
                    /**
                     * Válida si el dato de la locación que tiene el ayudante es
                     * igual al dato de la locación ingresada
                     */
                    if (temp.getData().getLocation().getDescription().equals(location))
                    {
                        list.add(temp.getData());// Agreda los niños con los datos a la nueva lista
                    }
                }
                temp = temp.getNext(); // El ayudante pasara a su sig
            }
            return list;  // Se retorna la lista con los datos solicitados
        }
        return null;  // Termino el ciclo y retorno null
    }

    /**
     * Método que, dado un género y una edad, me deje al inicio de la lista los niños del género
     * dado y con la edad menor o igual a la ingresada
     * @param age, gender
     * @throws ListaSeException
     */
    public void listByGenderAge(byte age, String gender) throws ListaSeException
    {
        if (this.head != null)// Si hay datos en la lista
        {
            Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
            ListSE list = new ListSE(); // Llamo a una lista para guardar los datos del género y la edad
            while (temp != null)  // Ayudante pase hasta que el siguiente este vacío
            {
                /**
                 * Si el dato que tiene el ayudante del género es igual al género Y el dato de
                 * la edad es menor o igual a la edad del niño
                 */
                if (temp.getData().getGender().equals(gender) && temp.getData().getAge() <= age)
                {
                    list.addToStart(temp.getData()); // Los listo al inicio con el dato
                }
                else
                {
                    list.add(temp.getData());
                }
                temp = temp.getNext();  // El ayudante pasa al siguiente
            }
            this.head = list.getHead(); // La cabeza va hacer igual a la lista
        }
        throw new ListaSeException("No hay niños "+ age + gender +"para listar");
    }

    /**
     * Método que me permita eliminar los niños con una edad mayor a la suministrada
     * @param age
     */
    public void deleteByAge ( byte age)
    {
        if (this.head != null) // Si hay datos en la lista
        {
            /**
             * Si la edad del niño en la cabeza es mayor a la edad ingresada
             * */
            if (this.head.getData().getAge() > age)
            {
                this.head = this.getHead().getNext(); // Pasa a su siguiente validando
            }
            else
            {
                Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
                while (temp != null) // Ayudante pase hasta que el siguiente este vacío
                {
                    if (temp.getNext() != null && temp.getData().getAge() > age);
                }
                temp = temp.getNext(); // El ayudante pasa al siguiente
            }

        }
    }

    /**
     * Método que me permita eliminar a todos los niños de un género de la lista
     * @param gender
     * @throws ListaSeException
     */
    public void deleteByGender (String gender) throws ListaSeException {
        validateListEmpty(); // Método que mira si hay niños en la lista
        if (this.head != null)  // Si hay datos en la lista
        {
            Node temp = this.head; // Llamo a un ayudante que se pare en la cabeza
            while (temp != null)  // Ayudante pase hasta que el siguiente este vacío
            {
                /**
                 * Si el género del niño en el que está pasado el ayudante es el género ingresado
                 */
                if (temp.getData().getGender().equals(gender)) ;
                {
                    delete(temp.getData().getIdentification()); // Se elimina al niño con los datos
                }
                temp = temp.getNext(); // El ayudante pasa al siguiente
            }
            throw new ListaSeException("la lista esta vacía");
        }
    }

    /**
     * Método que me permita obtener los niños de un grado de escuela dado (1,2,3,4,5)
     * @param grade
     */
    public List<Boy> getBoysByGrade(Integer grade)
    {
        if (this.head != null) // Si hay datos en la lista
        {
            Node temp = this.head; // Llamo a un ayudante y lo paro en la cabeza
            List<Boy> list = new ArrayList<>(); // Inicializamos una lista para guardar los datos
            while (temp != null) // Recorremos la lista hasta que su sig sea null
            {
                /**
                 * Si el niño en el que está el ayudante es igual al grado ingresado
                 */
                if (temp.getData().getGrade().equals(grade))
                {
                    list.add(temp.getData()); // Agregamos el niño en la lista
                }
                temp = temp.getNext(); // Ayudante pasa a su sig
            }
            return list; // Retorno los datos de la lista
        }
        return null; // Retorna vacío si no hay datos
    }

}
