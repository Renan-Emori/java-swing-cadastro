package Pacientes;

//Entity
public class Paciente {
	private String codigo;
	private String nome;
	private String nascimento;
	private String endereco;
	private String observacoes;
	
	public Paciente(String codigo, String nome, String nascimento, String endereco, String observacoes) {
		this.codigo = codigo;
		this.nome = nome;
		this.nascimento = nascimento;
		this.endereco = endereco;
		this.observacoes = observacoes;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
}
