package com.javatechie.entity;

import java.io.Serializable;

public record Book(int id, String name, String title) implements Serializable {

}