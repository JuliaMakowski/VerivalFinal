package com.bcopstein.entidades;

import java.util.ArrayList;
import java.util.List;

public class Passageiro{
	private String cpf;
	private String nome;
	private int pontuacaoAcumulada;
	private int qtdadeAvaliacoes;
	private static ArrayList<Passageiro> passageiroList = new ArrayList<>();

	public static Passageiro novoPassageiro(String cpf, String nome){
		Passageiro passageiro = new Passageiro(cpf,nome,8,1);
		passageiroList.add(passageiro);
		return passageiro;
	}
	//passageiro existene só criava novo pass
	public static Passageiro passageiroExistente(String cpf, String nome,int pontuacaoAcumulada,int qtdadeAvaliacoes){
		for(Passageiro passageiro : passageiroList){
			if(passageiro.cpf.equals(cpf)){
				return passageiro;
			}
		}
		return new Passageiro(cpf,nome,pontuacaoAcumulada,qtdadeAvaliacoes);
	}

	private Passageiro(String cpf, String nome, int pontuacaoAcumulada, int qtdadeAvaliacoes) {
		this.cpf = cpf;
		this.nome = nome;
		this.pontuacaoAcumulada = pontuacaoAcumulada;
		this.qtdadeAvaliacoes = qtdadeAvaliacoes;
	}

	public String getCPF() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public int getPontuacaoMedia() {
		return pontuacaoAcumulada / qtdadeAvaliacoes;
	}

	public void infoPontuacao(int pontuacao) {
		if (pontuacao > 0) {
			pontuacaoAcumulada += pontuacao;
			qtdadeAvaliacoes++;
		} else {
			throw new IllegalArgumentException("Pontucao invalida !");
		}
	}

	public int getPontuacaoAcumulada() {
		return pontuacaoAcumulada;
	}

	public int getQtdadeAvaliacoes() {
		return qtdadeAvaliacoes;
	}

	@Override
	public String toString() {
		return "Passageiro [cpf=" + cpf + ", nome=" + nome + ", pontuacaoAcumulada=" + pontuacaoAcumulada
				+ ", qtdadeAvaliacoes=" + qtdadeAvaliacoes + "]";
	}
}
