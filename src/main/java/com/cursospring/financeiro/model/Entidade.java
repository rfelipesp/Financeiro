package com.cursospring.financeiro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="entidade")
public class Entidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty
	@Column(length = 60)
	private String nome;
	
	@NotEmpty
	@Column(length = 20, name = "cpf_ou_cnpj")
	private String cpfOuCnpj;
	
	@NotEmpty
	@Column(length = 80)
	private String endereco;
	
	@NotEmpty
	@Column(length = 60)
	private String cidade;
	
	@NotEmpty
	@Column(length = 60)
	private String bairro;
	
	@NotEmpty
	@Column(length = 20)
	private String estado;
	
	@NotEmpty
	@Column(length = 20)
	private String telefone;
	
	@NotEmpty
	@Column(length = 100, name = "e_mail")
	private String email;
	
	@OneToMany(mappedBy = "entidade") // orphanRemoval - Não pode ter títulos sem entidade
	private List<Titulo> titulos = new ArrayList<Titulo>();
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Titulo> getTitulos() {
		return titulos;
	}
	public void setTitulos(List<Titulo> titulos) {
		this.titulos = titulos;
	}

}
