package com.bcopstein.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Viagem{
    private int id;
    private LocalDate dataHora;
    private Roteiro roteiro;
    private Passageiro passageiro;
    private double valorCobrado;

    public Viagem(int id, LocalDate dataHora, Roteiro roteiro, Passageiro passageiro,double valorCobrado){
        this.id = id;
        this.dataHora = dataHora;
        this.roteiro = roteiro;
        this.passageiro = passageiro;
        this.valorCobrado = valorCobrado;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public Roteiro getRoteiro() {
        return roteiro;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    @Override
    public String toString() {
        return "Viagem [valor cobrado=" + valorCobrado + ", dataHora=" + dataHora + ", id=" + id + 
                ", passageiro=" + passageiro + ", roteiro=" + roteiro + "]";
    }
}