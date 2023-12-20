package com.mundobinario.sqliteconpatrondao.modelo.entidades;

public class Alimento {

    private String alimento;
    private Integer hidratos;
    private Integer proteinas;
    private Integer grasas;

    public Alimento() {
    }

    public String getAlimento() {
        return alimento;
    }

    public Integer getHidratos() {
        return hidratos;
    }

    public Integer getProteinas() {
        return proteinas;
    }

    public Integer getGrasas() {
        return grasas;
    }

    public void setAlimento(String alim) {
        alimento = alim;
    }

    public void setHidratos(Integer hidr) {
        hidratos = hidr;
    }

    public void setProteinas(Integer prot) {
        proteinas = prot;
    }

    public void setGrasas(Integer gras) {
        grasas = gras;
    }
}
