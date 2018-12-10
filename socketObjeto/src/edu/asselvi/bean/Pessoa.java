package edu.asselvi.bean;

import java.io.Serializable;
import java.util.Date;

import edu.asselvi.util.Padroes;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = -3767679445088113278L;
	
	private int codigo;
	private String nome;
	private Date nascimento;
	private char sexo;
	private boolean ativo;
	private float salario;

	public Pessoa() {
		this(0, "sem nome", new Date(), '-', true, 0);
	}

	public Pessoa(int codigo, String nome, Date nascimento, char sexo, boolean ativo, float salario) {
		setCodigo(codigo);
		setNascimento(nascimento);
		setNome(nome);
		setSexo(sexo);
		setAtivo(ativo);
		setSalario(salario);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return getCodigo() + "#" + getNome() + "#" + Padroes.dataFormatada.format(getNascimento()) + "#" + getSexo() + "#" + (isAtivo() ? 'S' : 'N') + Padroes.numeroFormatado.format(getSalario());
	}
}
