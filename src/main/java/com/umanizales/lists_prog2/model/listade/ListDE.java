package com.umanizales.lists_prog2.model.listade;

import com.umanizales.lists_prog2.exception.ListaDeException;
import com.umanizales.lists_prog2.model.Boy;
import lombok.Data;
import lombok.NoArgsConstructor;


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
            // y lw pregunta si el código que ingresaron es igual a al que tienen
            {
                count++; // si entró pues se incrementa el contador
            }
            temp = temp.getNext(); // actualizo mi ayudante  y lo posiciono en el dato siguuiente
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
}

