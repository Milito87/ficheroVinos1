package org.izv.ad.jmunoz.ficherovinos;

import java.util.Objects;

public class Wine {

    //atributos del vino
    private long id;
    private String name;
    private String cellar;
    private String colour;
    private String origin;
    private double degrees;
    private int date;

    //constructor que recibe los datos del vino
    public Wine(long id, String name, String cellar, String colour, String origin, double degrees, int date) {
        this.id = id;
        this.name = name;
        this.cellar = cellar;
        this.colour = colour;
        this.origin = origin;
        this.degrees = degrees;
        this.date = date;
    }

    //constructor que inicia los datos del vino
    public Wine() {
        this(0, null, null, null, null, 0.0, 0);
    }

    //metodos GET y SET de cada atributo
    public String getName() {
        return name;
    }

    public String getCellar() {
        return cellar;
    }

    public String getColour() {
        return colour;
    }

    public String getOrigin() {
        return origin;
    }

    public double getDegrees() {
        return degrees;
    }

    public int getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCellar(String cellar) {
        this.cellar = cellar;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //método para comparar objetos vino
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wine wine = (Wine) o;

        if (date != wine.date) return false;
        if (name != null ? !name.equals(wine.name) : wine.name != null) return false;
        return cellar != null ? cellar.equals(wine.cellar) : wine.cellar == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //método que crea un objeto vino desde el archivo.csv
    public static Wine readWine(String s) {
        //cada posicion dle array es un atributo del vino
        String[] atributos = s.split(";");

        Wine w = null;
        if (atributos.length >= 6) {
            //creamos objeto vino y le pasamos la informacion recogida en el array (atributos)
            w = new Wine();
            try {
                w.setId(Long.parseLong(atributos[0].trim()));
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
            w.setName(atributos[1].trim());
            w.setCellar(atributos[2].trim());
            w.setColour(atributos[3].trim());
            w.setOrigin(atributos[4].trim());
            try {
                w.setDegrees(Double.parseDouble(atributos[5].trim()));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
            try {
                w.setDate(Integer.parseInt(atributos[6].trim()));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return w;
    }

    //método que recibe un vino y lo guarda en archivo.csv en formato CSV
    public static String writeWine(Wine w){

        return w.getId() + "; " +
                w.getName() + "; " +
                w.getCellar() + "; " +
                w.getColour() + "; " +
                w.getOrigin() + "; " +
                w.getDegrees() + "; " +
                w.getDate();

    }

}