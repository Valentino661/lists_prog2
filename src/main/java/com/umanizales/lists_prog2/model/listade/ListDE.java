package com.umanizales.lists_prog2.model.listade;

import com.umanizales.lists_prog2.controller.dto.*;
import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.model.Boy;
import com.umanizales.lists_prog2.model.Gender;
import com.umanizales.lists_prog2.model.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


/**
 * Clase que me permite gestionar una lista doblemente enlazada
 * contiene los métodos u operaciones ...
 * Solo cuenta con los atributos head = que representa el primer niño
 * y el atributo count para llevar un conteo de los niños en la lista
 */
@Data
@NoArgsConstructor
public class ListDE {
    private Node head;  // Atributo que representa la cabeza de la lista
    private int count;  // Atributo que nos permite guardar una serie de datos

    /**
     * Método que adiciona un niño al final de la lista
     *
     * @param boy es el parámetro que estamos usando
     * @throws ListaDeException método para expresar el mensaje o excepción
     */
    public void add(Boy boy) throws ListaDeException {
        /**
         * si la cabeza esta vacía
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if (boyExist != null) {
            /**
             * Si el niño ya existe se lanza la excepción para comunicar al usuario
             */
            throw new ListaDeException("La identificación ingresada ya existe");
        }
        if (this.head == null) // Si la cabeza esta vacía
        {
            this.head = new Node(boy); // El niño ingresado es la cabeza
        } else {
            Node temp = this.head; // Llamo a un ayudante y lo ubico en la cabeza
            while (temp.getNext() != null) {
                temp = temp.getNext(); // temporal va a pasar a su siguiente hasta llegar a null
            }
            Node newBoy = new Node(boy); // se crea el Nodo con el objeto en el que estoy
            temp.setNext(newBoy); // el siguiente toma al nuevo
            newBoy.setPrevious(temp); // nuevo en el previous tome al ayudante
        }
        count++;
    }

    /**
     * Método que busca un niño por su identificación
     * @param identification
     * @return niño con sus datos
     */
    public Boy findByIdentification(String identification) {
        Node temp = head; // Llamo a un ayudante y lo ubico en la cabeza
        while (temp != null) // Recorro la lista hasta llegar a null
        {
            /**
             * Pregunto si el niño en el cual está ubicado el ayudante es igual al niño
             * de la identificación ingresada
             */
            if (temp.getData().getIdentification().equals(identification)) {
                return temp.getData(); // Retorna el niño con sus datos
            }
            temp = temp.getNext(); // Ayudante pasa a su sig
        }
        return null; // No se encontro el niño y retorna null
    }

    /**
     * Método por el cual validaremos si la lista está vacía
     * @throws ListaDeException
     */
    public void validateListEmpty() throws ListaDeException {
        if (this.head == null) // Si no hay datos en la lista
        {
            /**
             * Se lanza la excepción para comunicar al usuario
             */
            throw new ListaDeException("No hay datos en la lista");
        }
    }

    /**
     * Método que nos permite llevar el conteo de los niños ingresados
     * @return count
     */
    public int count() {
        int count = 0; // Inicializamos el contador
        if (this.head != null) // Recorremos la lista hasta llegar a null
        {
            Node temp = this.head; // Llamo a un ayudante y lo paro en la cabeza
            while (temp != null)  // Recorremos la lista hasta que el sig sea null
            {
                count++; // incrementa el contador
                temp = temp.getNext(); // Ayudante pasa a su sig
            }
        }
        return count; // Retorna contador con la cantidad obtenida
    }

    /**
     * Método que adiciona un niño al inicio de lista (la cabeza)
     * @param boy
     * @throws ListaDeException
     */
    public void addToStart(Boy boy) throws ListaDeException {
        /**
         * Se invoca al método encontrar por identificación, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if (boyExist != null) {
            /**
             * Si la identificació ya existe se lanza la excepción para comunicar al usuario
             */
            throw new ListaDeException("La identificación ingresada ya existe");
        }
        if (this.head == null) // Si la cabeza esta vacía
        {
            /**
             *  El dato que se esta ingresando sera la cabeza
             */
            this.head = new Node(boy);
            this.head.setPrevious(null);
        }
        else
        {
            Node temp = new Node(boy); // Ayudantes toma al nuevo nodo o niño
            temp.setNext(this.head); // Nuevo nodo toma a la cabeza
            this.head = temp; // Nuevo nodo será igual a la cabeza
            this.head.setPrevious(null); // Nuevo (cabeza) en el previous sera null
        }
        count++; // Aumenta el contador
    }

    /**
     * Método que adiciona un niño en una posición ingresada
     * @param boy, positon
     * @throws ListaDeException
     */
    public void addByPosition(Boy boy, int positon) throws ListaDeException {
        /**
         * Se invoca al método encontrar por identificación, para saber
         * si el niño que se esta ingresando ya existe
         */
        Boy boyExist = findByIdentification(boy.getIdentification());
        if (boyExist != null) {
            /**
             * Si la identificació ya existe se lanza la excepción para comunicar al usuario
             */
            throw new ListaDeException("La identificación ingresada ya existe");
        }
        if (positon > count) // Si la posición ingresada es mayor a los niños que tengo
        {
            /**
             * Si la posición no existe se lanza la excepción para comunicar al usuario
             */
            throw new ListaDeException("La posición ingresada no es válida");
        }
        if (positon == 1) // Si la posición ingresada es igual a 1
        {
            addToStart(boy); // Añadimos en niño en la cabeza
        } else {
            int cont = 1;
            Node temp = this.head; // Llamo a un ayudante y lo ubico en la cabeza
            while (temp != null) // Recorre la lista hasta que su sig sea null
            {
                if (cont == positon - 1) // Si el contador es igual a una posicion negativa
                {
                    break; // Rompe el ciclo
                }
                temp = temp.getNext(); // Ayudante pasa a su sig
                cont++; // incrementa el contador
            }
            /**
             * mi ayudante va a estar posicionado en la posición anterior y adicionara el nodo
             * en el brazo siguiente y el anterior tomara al ayudante
             */
            Node nodeNew = new Node(boy); // Ayudante va a estar posicionado
            temp.setNext(this.head);
            this.head = temp;
            this.head.setPrevious(null);
            count++;
        }
    }

    public void invertir() throws ListaDeException {
        if (this.head != null)
        {
            ListDE listTemp = new ListDE();
            Node temp = this.head;
            while (temp != null)
            {
                listTemp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listTemp.getHead();
        }
    }

    public void delete(String identification) throws ListaDeException
    {
        if (this.head != null) // Si hay datos en la lista
        {
            /**
             * Si la identificación del niño es igual a la ingresada
             * */
            if (this.head.getData().getIdentification().equals(identification))
            {
                this.head = this.head.getNext(); // si entra entonces ponemos a nuestra cabeza a apuntar a su
                // siguiente, que es null
            }
            else
            {
                Node temp = this.head; // Llamo a un ayudante y lo paro en la cabeza
                while (temp != null) // Recorre la lista hasta que su sig sea null
                {
                    /**
                     * Si hay algo en el sig de mi ayudante Y la identificación del sig de mi ayudante
                     * sea igual a la del niño ingresado
                     * */
                    if (temp.getNext() != null && temp.getNext().getData().
                            getIdentification().equals(identification))
                    {
                        break; // Termina el ciclo
                    }
                    temp = temp.getNext(); // Ayudantes pasa a su siguiente
                    temp.getNext().setPrevious(temp); // Nuevo en el previous tome al ayudante
                }
                if (temp != null) // Si temp tiene datos
                {
                    temp.setNext(temp.getNext().getNext()); // Ayudante agarre al siguiente del siguiente
                    temp.getNext().setPrevious(temp); // Nuevo en el previous tome al ayudante
                } else {
                    /**
                     * Si la identificación no existe se lanza la excepción para comunicar al usuario
                     */
                    throw new ListaDeException("La identificación "
                            + identification + " no existe en la lista");
                }
            }
        }
        else
        {
            /**
             * Si no hay niños se lanza la excepción para comunicar al usuario
             */
            throw new ListaDeException("No hay datos en la lista");
        }
    }

    /**
     * Método que recibe el código de una ciudad y retorna la cantidad de niños
     * @param code de la ciudad
     * @return Contador con la cantidad obtenida
     **/
    public int getCountBoysByLocation(String code)
    {
        Node temp = this.getHead(); // Llamo a un ayudante y lo paro en la cabeza
        int count = 0; // inicializamos contador para la ciudad
        while (temp != null) // Recorre la lista hasta que su sig sea null
        {
            /**
             *
             * */
            if (temp.getData().getLocation().getCode().equals(code)) // va a la data de todos los niños
            // y le pregunta si el código que ingresaron es igual a al que tienen
            {
                count++; // si entró pues se incrementa el contador
            }
            temp = temp.getNext(); // actualizo mi ayudante  y lo posiciono en el dato siguiente
        }
        return count; // Retorna contador con la cantidad obtenida

    }

    public void addNoRepeat(Boy boy)
    {
        if (head == null)  // Si no hay datos en la lista
        {
            head = new Node(boy); // El niño ingresado será la cabeza
        }
        else
        {
            Node temp = head; // creo mi ayudante y lo posiciono en el inicio de la lista
            if (temp.equals(boy))  // comparo si el niño que estoy ingresando ya existe
            {
                while (temp.getNext() != null) // Recorre la lista hasta que su sig sea null
                {
                    temp = temp.getNext(); // Ayudante pasa a su sig
                }
                temp.setNext(new Node(boy)); // Añadimos el niño
                temp.getNext().setPrevious(temp); // Nuevo en el previous tome al ayudante
                count++; // Incremento el contador
            }
        }
    }

    /**
     * Método que muestra los niños huérfanos según su grado y municipio en el que vive y nos muestra
     * la cantidad de niños que hay en el grado
     * @param grade, location
     * @throws ListaDeException
     * @return datos obtenidos por gendersByGradeDTO
     */
    public GendersByGradeDTO getGendersByGradeByLocation(byte grade, Location location) throws ListaDeException
    {
        validateListaEmpty(); // Validamos si hay niños en la lista
        int countTotal = 0;   // Iniciamos contador para todos los niños
        int countM = 0;   // Iniciamos contador para las niñas huérfanas
        int countF = 0;   // Iniciamos contador para los niños huérfanos
        Node temp = this.head; // Llamamos a un ayudante y lo paramos en la cabeza
        while (temp != null)   // Recorremos la lista hasta que su sig sea null
        {
            /**
             * Si el dato en el que está parado el contador es igual a la localización ingresada Y si
             * el grado en que sta el ayudante es igual al ingresado
             * */
            if (temp.getData().getLocation().getCode().equals(location.getCode())
            && temp.getData().getGrade()==grade)
            {
                countTotal++; // Aumentamos contador para la cantidad de los niños
                if (temp.getData().isOrphan()) // Si el dato en el que está parado el ayudante es huérfano
                {
                    /**
                     * Miramos cuál es el género del niño huérfano, Si es MASCULINO
                     * */
                    if (temp.getData().getGender().equals(Gender.MASCULINO))
                    {
                        countM++; // Aumentamos contador para los niños huérfanos
                    }
                    else
                    {
                        countF++; // Aumentamos contador para los niñas huérfanos
                    }
                }
            }
            temp = temp.getNext(); // El ayudante pasa a su sig
        }
        // Iniciamos un listá para guardar los datos obtenidos
        List<CountByGenderDTO> countByGenderDTOS = new ArrayList<>();
        // Adicionamos al contador por género el niño según su género con los datos obtenidos
        countByGenderDTOS.add(new CountByGenderDTO(Gender.MASCULINO,countM));
        countByGenderDTOS.add(new CountByGenderDTO(Gender.FEMENINO,countF));
        //
        GendersByGradeDTO gendersByGradeDTO = new GendersByGradeDTO(grade, countByGenderDTOS,countTotal);
        return gendersByGradeDTO; // Retornamos los datos obtenidos por

    }

    /**
     * Método que muestra los niños según el municipio en el que vive y el grado en el que esta
     * @param location
     * @throws ListaDeException
     * @return datos obtenidos de gradesByLocationDTO
     */
    public GradesByLocationDTO getGradesByLocation(Location location) throws ListaDeException
    {
        List<GendersByGradeDTO> gendersByGradeDTOS = new ArrayList<>();
        for (byte i=1; i<=5;i++)
        {
            gendersByGradeDTOS.add(getGendersByGradeByLocation(i,location));
        }
        GradesByLocationDTO gradesByLocationDTO = new GradesByLocationDTO(location, gendersByGradeDTOS);
        return gradesByLocationDTO;
    }

    /**
     * Método que válida si hay datos en la lista
     * @throws ListaDeException
     */
    public void validateListaEmpty() throws ListaDeException
    {
        if (this.head == null)
        {
            /**
             * Si no hay niños se lanza la excepción para comunicar al usuario
             */
            throw new ListaDeException("No hay datos en la lista.");
        }
    }

    /**
     * Método que me lista a los niños de forma ascendente y las niñas de forma descendente
     * @throws ListaDeException
     */
    public void orderListBoysAndGirls() throws ListaDeException
    {
        validateListaEmpty(); // Validamos si la lista esta vacía
        ListDE listTemp = new ListDE(); // Creamos una lista temporal para guardar los datos
        Node temp = this.head; // Llamamos a un ayudante y lo paramos en la cabeza
        while (temp != null) // Recorremos la lista hasta que su sig sea null
        {
            /**
             *  Si el dato en el que está el ayudante es MASCULINO
             * */
            if (temp.getData().getGender().equals(Gender.MASCULINO))
            {
                // En la lista añadimos al inicio el niño para que quede invertido
                listTemp.addToStart(temp.getData());
            }
            else // No entonces es FEMENINO
            {
                listTemp.add(temp.getData());
            }
            temp = temp.getNext(); // El ayudante pasa a su sig
        }
        this.head = listTemp.head; // La cabeza va ser igual la lista con los datos obtenidos
        /**
         * Si no hay datos existes se lanza la excepción para comunicar al usuario
         */
        throw new ListaDeException("No hay datos en la lista");
    }


    /**
     * Creammos un metodo que nos adiciona un nodo
     * @param nodeint recibimos como parametro el nodo
     * @throws ListaDeException
     */
    public void addNode (Node nodeint)
    {

        if(this.head == null) // si la cabeza es igual a null no adicione lo que ya tenemos
        {
            /**
             * nodo int es la lista ya existente
             */
            this.head = nodeint;
        }
        else
        {
            Node temp = head;// Llamamos un ayudante
            while (temp.getNext() != null) // Recorremos la lista hasta quedad en el ultimo
            {
                temp = temp.getNext(); // Temp pasa a su sig
            }
            temp.setNext(nodeint); // Le decimos a nuestro ayudante que tome el nodo
            nodeint.setPrevious(temp); //El nodo agarra a su anterior que es el temp
        }
    }
    /**
     * Metodo que nos de una lista por cada localización
     * @param location
     * @return retorna la lista de cada localización
     * @throws ListaDeException
     */
    public ListDE listDeLocation (Location location) throws ListaDeException
    {
        validateListaEmpty();
        ListDE listemp = new ListDE(); //creamos una lista temporal
        Node temp = this.head; //llamamos un ayudante
        while(temp!= null) // recorremos la lista
        {
            if (temp.getData().getLocation().equals(location)) // Si son iguales a los de la localización ingresada
            {
                listemp.add(temp.getData()); // los agrega ese dato a la lista temporal
            }
            temp = temp.getNext(); // El ayudante pasa a su siguiente
        }
        return listemp; // Retornamos la lista con los datos
    }

    /**
     * metodo para encontrar niños de un grado y saber el rh
     * @param grade
     * @return
     */
    public GradeRHDTO getBoysByGenderRh(byte grade)
    {
        Node temp = this.head; //creamos una ayudante y lo paramos en la cabeza
        String rh = "";  int count = 0;//creamos una variable que cuente y guarde los niños con el rh
        while (temp != null)// mientras el ayudante no este vacío siga preguntando
        {
            /**
             * si el dato que tiene el ayudante del grado es igual al grado solicitado
             */
            if (temp.getData().getGrade() == grade){
                /**
                 * y si el rh es diferente a lo que tiene el ayudante guardado en el dato
                 */
                if (!rh.contains(temp.getData().getRh())){
                    rh = rh+ "," + temp.getData().getRh();
                }
                count++; // Aumentamos en contador en 1
            }
            temp = temp.getNext(); // Temp pasa a su sig
        }
        /**
         * retorno lo que tenga guardado en el método de niños por grado y rh con los datos
         */
        return  new GradeRHDTO(grade, rh, count);
    }

    /**
     * Método donde encontraremos los niños de un grado y de un género
     * @param gender
     * @return
     */
    public GenderGradesRHDTO getboysByGradeByGenderDTO(Gender gender)
    {
        GradeRHDTO[] boysByGradeRhDTO = new GradeRHDTO[5]; // Creamos un arreglo de 5 posiciones
        for (byte i = 0; i < 5; i++)  // recorremos el arreglo
        {
            /**
             * recorremos la lista de niños por grado y rh y empezamos a contar las posiciones
             */
            boysByGradeRhDTO[i] = getBoysByGenderRh((byte) (i + 1));
        }
        /**
         * retornamos los niños por grados y por género con los datos de género
         * y le adicionamos los niños por grado y rh
         */
        return new GenderGradesRHDTO(gender, boysByGradeRhDTO);
    }

    /**
     * Método por el cual encontraremos los niños por locación y por género
     * @param location
     * @return
     */
    public LocationByGenderDTO boysByLocationByGenderDTO(Location location)
    {
        /**
         * creamos un arraylist donde guardaremos los datos de los niños por grado y por género
         */
        List<GenderGradesRHDTO> boysByGradeByaGenderDTOS = new ArrayList<>();
        int count =0; // Inocializamos contador para llevar el dato
        Node temp = head; // Llamamos a un ayudante y lo paramos en la cabeza
        while (temp != null) // Recorremos la lista hasta que su sig sea null
        {
            /**
             * si el dato que tiene el ayudante de la locación y el código de la locación
             * es igual a la locación del niño
             */
            if (temp.getData().getLocation().getCode().equals(location))
            {
                /**
                 * adicionamos a la lista los niños que tenemos con el grado y el género validando
                 * que sea lo que tiene el ayudante
                 */
                boysByGradeByaGenderDTOS.add(getboysByGradeByGenderDTO(temp.getData().getGender()));
                count++; //Aumentamos contador y llevamos el conteo
            }
            temp = temp.getNext(); // El ayudante pasa a su siguiente
        }
        LocationByGenderDTO boysByLocationByGenderDTO = new LocationByGenderDTO(location);
        return boysByLocationByGenderDTO; //Retornamos la lista de los niños por género y locación
    }

}

