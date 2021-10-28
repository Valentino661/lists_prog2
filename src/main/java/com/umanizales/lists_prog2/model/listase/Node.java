package com.umanizales.lists_prog2.model.listase;

import com.umanizales.lists_prog2.model.Boy;
import lombok.Data;

@Data
public class Node {
    private Boy data;
    private Node next;

    public Node(Boy data) {
        this.data = data;
    }
}
