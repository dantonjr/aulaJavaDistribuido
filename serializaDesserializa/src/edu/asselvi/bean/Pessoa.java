package edu.asselvi.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = -3606838819964390989L;

	private int codigo;
	private String nome;
	private char sexo;
	private Date nascimento;
	private float salario;

	public Pessoa() {
		this(0, "Não informado", 'M', new Date(), 0);
	}

	public Pessoa(int codigo, String nome, char sexo, Date nascimento, float salario) {
		setCodigo(codigo);
		setNome(nome);
		setSexo(sexo);
		setNascimento(nascimento);
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = (Character.toUpperCase(sexo) == 'M' ? 'M' : 'F');
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("000,000.00");
		return getCodigo() + "#" + getNome() + "#" + getSexo() + "#" + sdf.format(getNascimento()) + "#" + df.format(getSalario());
	}

}
